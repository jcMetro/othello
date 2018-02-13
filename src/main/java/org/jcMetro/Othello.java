package org.jcMetro;

import java.util.Arrays;

public class Othello {

    private final char[][] cells ;

    public Othello() {
        this.cells = new char[8][8];

        for (char[] row: cells){
            Arrays.fill(row, '-');
        }

        this.cells[3][3] = 'O';
        this.cells[3][4] = 'X';
        this.cells[4][3] = 'X';
        this.cells[4][4] = 'O';
    }

    public String displayBoard() {

        String result = "";
        for (int i = 0; i < 8; i++) {
            result += (i+1);
            result += " ";
            result += String.valueOf(this.cells[i]);
            result += '\n';
        }

        result += "  abcdefgh";
        return result;
    }
}
