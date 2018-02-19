package org.jcMetro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OthelloMain {

    public static void main(String[] args) throws IOException {
        Othello othello = new Othello();

        String appBanner =
                " ██████╗ ████████╗██╗  ██╗███████╗██╗     ██╗      ██████╗ \n" +
                "██╔═══██╗╚══██╔══╝██║  ██║██╔════╝██║     ██║     ██╔═══██╗\n" +
                "██║   ██║   ██║   ███████║█████╗  ██║     ██║     ██║   ██║\n" +
                "██║   ██║   ██║   ██╔══██║██╔══╝  ██║     ██║     ██║   ██║\n" +
                "╚██████╔╝   ██║   ██║  ██║███████╗███████╗███████╗╚██████╔╝\n" +
                " ╚═════╝    ╚═╝   ╚═╝  ╚═╝╚══════╝╚══════╝╚══════╝ ╚═════╝ \n" +
                "                                                           ";
        System.out.println(appBanner);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        testMoves(othello,
                "e6", "d6", "c7", "d7", "c8", "b8", "a8", "d8", "e8", "f7",
                "e7", "f8", "g8", "g7", "h6", "h7", "h8", "e3", "e2", "f2",
                "g2", "e1", "d2", "g1", "b7", "a7", "a6", "f6", "g6", "c2",
                "b2", "c1", "c6", "c5", "d3", "a2", "b6", "c3", "a1", "b3",
                "a3", "b4", "b1");

        while(!othello.isEndGame()){
            System.out.println(othello.displayBoard());
            System.out.println("current player is: " + othello.currentPlayer());

            String move = readMoveInput(othello, in);

            othello.placeMove(move);
            System.out.println(othello.getMoveHistory());
        }

        System.out.println("Game ended, final board is: ");

        System.out.println(othello.displayBoard());
        System.out.println("Score for Player X: " + othello.score(Player.X));
        System.out.println("Score for Player O: " + othello.score(Player.O));

        if (othello.winner().isPresent()) {
            System.out.println("Winner is: " + othello.winner());
        }
        else{
            System.out.println("Game tie. No winner");
        }
    }

    private static String readMoveInput(Othello othello, BufferedReader in) throws IOException {
        do {
            System.out.println("Enter the move: ");
            String move = in.readLine();
            if (!othello.isValidMove(move)){
                System.out.println("Invalid move input, valid moves are: " + othello.displayValidMoves());
            }
            else{
                return move;
            }
        }
        while(true);
    }


    private static void testMoves(Othello othello, String... coordinates) {
        for (String coordinate : coordinates) {
            othello.placeMove(coordinate);
        }
    }
}