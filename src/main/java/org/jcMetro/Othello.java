package org.jcMetro;

import java.util.*;
import java.util.stream.Collectors;

import static org.jcMetro.Cell.cell;
import static org.jcMetro.CellStatus.Empty;

public class Othello {

    public static final int BOARD_SIZE = 8;

    private final Map<Cell, CellStatus> board = new HashMap<>();
    private Map<Cell, Set<Cell>> validMoves = new HashMap<>();

    private Player currentPlayer;
    private boolean endGame;
    private List<String> moveHistory = new ArrayList<>();

    public Othello() {

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board.put(cell(i, j), Empty);
            }
        }

        board.put(cell(3, 3), Player.O.cellStatus());
        board.put(cell(3, 4), Player.X.cellStatus());
        board.put(cell(4, 3), Player.X.cellStatus());
        board.put(cell(4, 4), Player.O.cellStatus());

        endGame = false;
        currentPlayer = Player.X;
        calculateValidMoves();
    }

    public boolean isValidMove(String input) {
        Coordinate coordinate = new Coordinate(input);
        return coordinate.isValid() && validMoves.containsKey(cell(coordinate));
    }

    public void placeMove(String input) {

        moveHistory.add(input);

        Coordinate coordinate = new Coordinate(input);
        Cell currentCell = cell(coordinate);

        board.put(currentCell, currentPlayer.cellStatus());

        for (Cell cell : validMoves.get(currentCell)) {
            board.put(cell, currentPlayer.cellStatus());
        }

        updateCurrentPlayer();
    }

    private void updateCurrentPlayer() {
        currentPlayer = currentPlayer.opposite();
        calculateValidMoves();

        if (validMoves.isEmpty()) {
            currentPlayer = currentPlayer.opposite();
            calculateValidMoves();
            if (validMoves.isEmpty()) {
                endGame = true;
                currentPlayer = null;
            }
        }
    }

    private boolean continueSearch(Player oppositePlayer, Cell searchCell) {
        return isWithinBoard(searchCell) &&
                board.get(searchCell) == oppositePlayer.cellStatus();
    }

    private boolean isWithinBoard(Cell searchCell) {
        return searchCell.rowIndex < BOARD_SIZE && searchCell.colIndex < BOARD_SIZE;
    }

    public Player currentPlayer() {
        return currentPlayer;
    }

    public String displayBoard() {

        String result = "";
        for (int i = 0; i < BOARD_SIZE; i++) {
            result += (i + 1);
            result += " ";
            for (int j = 0; j < BOARD_SIZE; j++) {
                result += displayCell(i, j);
            }
            result += '\n';
        }

        result += "  abcdefgh";
        return result;
    }

    private String displayCell(int i, int j) {
        Cell cell = cell(i, j);
        if (validMoves.containsKey(cell)) {
            return "*";
        }
        return board.get(cell).displayValue();
    }

    public void calculateValidMoves() {
        validMoves = board.entrySet().stream()
                .filter(entry -> entry.getValue() == CellStatus.Empty)
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), cellToFlips(entry.getKey())))
                .filter(entry -> !entry.getValue().isEmpty())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Set<String> displayValidMoves() {
        return validMoves.keySet().stream().map(cell -> cell.displayCoordinate()).collect(Collectors.toSet());
    }

    private Set<Cell> cellToFlips(Cell cell) {

        Set<Cell> allCellToFlip = new HashSet<>();
        for (Direction direction : Direction.values()) {
            Cell searchCell = cell.moveTo(direction);
            Set<Cell> potentialFlips = new HashSet<>();

            while (continueSearch(currentPlayer.opposite(), searchCell)) {
                potentialFlips.add(searchCell);
                searchCell = searchCell.moveTo(direction);
            }

            if (board.get(searchCell) == currentPlayer.cellStatus()) {
                allCellToFlip.addAll(potentialFlips);
            }
        }
        return allCellToFlip;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public List<String> getMoveHistory() {
        return moveHistory;
    }

    public Optional<Player> winner() {
        long playerXCount = score(Player.X);
        long playerOCount = score(Player.O);

        if (playerXCount > playerOCount){
            return Optional.of(Player.X);
        }
        else if (playerOCount > playerXCount){
            return Optional.of(Player.O);
        }
        else {
            return Optional.empty(); // tie is possible
        }
    }

     public int score(Player player) {
        return Math.toIntExact(board.entrySet().stream()
                .filter(entry -> entry.getValue() == player.cellStatus())
                .count());
    }
}
