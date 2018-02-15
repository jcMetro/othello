package org.jcMetro;

import java.awt.*;
import java.util.Arrays;

import static org.jcMetro.Player.O;
import static org.jcMetro.Player.X;

public class Othello {

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

        updateBoard();
    }

    private void updateBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cells[i][j] != '-' && needFlip(i,j)){
                    flipIfNeeded(i,j);
                }
            }
        }
    }

    private boolean needFlip(int row, int index) {
        return false;
    }

    private void flipIfNeeded(int row, int col) {
        char symbol = cells[row][col];
        char opposite = symbol == 'X' ? 'O' : 'X';

        Point northOpposite = null;
        Point southOpposite = null;

        for(int rowIndex = row; rowIndex >= 0; rowIndex--){
            if (cells[rowIndex][col] == opposite){

            }
        }
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
