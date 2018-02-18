package org.jcMetro;

import com.google.common.collect.Maps;

import java.util.*;
import java.util.stream.Collectors;

import static org.jcMetro.Cell.cell;
import static org.jcMetro.CellStatus.Empty;

public class Othello {

    public static final int BOARD_SIZE = 8;

    private final Map<Cell, CellStatus> board = new HashMap<>();
    private Map<Cell, Set<Cell>> validMoves = new HashMap<>();

    private Player currentPlayer = Player.X;

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

        currentPlayer = Player.X;
        calculateValidMoves();
    }

    public void placeMove(String input) {
        Coordinate coordinate = new Coordinate(input);

        if (!coordinate.isValid()) {
            throw new IllegalArgumentException("Invalid coordinate input: " + coordinate);
        }

        Cell currentCell = cell(coordinate);

        board.put(currentCell, currentPlayer.cellStatus());

        for (Cell cell : validMoves.get(currentCell)) {
            board.put(cell, currentPlayer.cellStatus());
        }

        toggleCurrentPlayer();
        calculateValidMoves();
    }

    private void toggleCurrentPlayer() {
        currentPlayer = currentPlayer.opposite();
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
                result += board.get(cell(i, j)).displayValue();
            }
            result += '\n';
        }

        result += "  abcdefgh";
        return result;
    }

    public void calculateValidMoves() {
        validMoves = board.entrySet().stream()
                .filter(entry -> entry.getValue() == CellStatus.Empty)
                .map(entry -> Maps.immutableEntry(entry.getKey(), cellToFlips(entry.getKey())))
                .filter(entry -> !entry.getValue().isEmpty())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Set<String> displayValidMoves(){
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
}
