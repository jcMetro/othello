package org.jcMetro;

import java.util.*;

import static org.jcMetro.Player.O;
import static org.jcMetro.Player.X;

public class Othello {

    private enum Direction{
        North(-1, 0),
        South(1,0);

        public final int rowStep;
        public final int colStep;

        Direction(int rowStep, int colStep) {
            this.rowStep = rowStep;
            this.colStep = colStep;
        }
    }

    public class Cell{

        public final int rowIndex;
        public final int colIndex;

        public Cell(int rowIndex, int colIndex) {
            this.rowIndex = rowIndex;
            this.colIndex = colIndex;
        }

        public Cell moveTo(Direction direction) {
            return new Cell(rowIndex + direction.rowStep, colIndex + direction.colStep);
        }

        @Override
        public String toString() {
            return "Cell{" +
                    "rowIndex=" + rowIndex +
                    ", colIndex=" + colIndex +
                    '}';
        }
    }


    public static final int N = 8;

    private final char[][] cells ;

    public Othello() {
        cells = new char[N][N];

        for (char[] row: cells){
            Arrays.fill(row, '-');
        }

        placeMove(O, "d4");
        placeMove(X, "e4");
        placeMove(X, "d5");
        placeMove(O, "e5");
    }

    public void placeMove(Player player, String coordinate){

        int colIndex = coordinate.charAt(0) - 'a';
        int rowIndex = coordinate.charAt(1) - '1';

        cells[rowIndex][colIndex] = player.symbol();

        // detect any flipping required
        Cell currentCell = new Cell(rowIndex, colIndex);
        Player currentPlayer = Player.valueOf(String.valueOf(cells[currentCell.rowIndex][currentCell.colIndex]));
        Player oppositePlayer = currentPlayer == Player.O ? Player.X : Player.O;

        Set<Cell> cellsToFlip = new HashSet<>();

        for (Direction direction : Direction.values()) {
            Cell searchCell = currentCell.moveTo(direction);
            Set<Cell> potentialFlips = new HashSet<>();
            boolean continueSearch = isWithinBoard(searchCell) && cells[searchCell.rowIndex][searchCell.colIndex] == oppositePlayer.symbol();

            while(continueSearch){
                potentialFlips.add(searchCell);
                searchCell = searchCell.moveTo(direction);
                continueSearch = isWithinBoard(searchCell) && cells[searchCell.rowIndex][searchCell.colIndex] == oppositePlayer.symbol();
            }

            if (cells[searchCell.rowIndex][searchCell.colIndex] == currentPlayer.symbol()){
                cellsToFlip.addAll(potentialFlips);
            }
        }

        for (Cell cell : cellsToFlip) {
            cells[cell.rowIndex][cell.colIndex] = currentPlayer.symbol();
        }
    }

    private boolean isWithinBoard(Cell searchCell) {
        return searchCell.rowIndex < N && searchCell.colIndex < N ;
    }

    public String displayBoard() {

        String result = "";
        for (int i = 0; i < N; i++) {
            result += (i+1);
            result += " ";
            result += String.valueOf(this.cells[i]);
            result += '\n';
        }

        result += "  abcdefgh";
        return result;
    }
}
