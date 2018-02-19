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

        while(!othello.isEndGame()){
            System.out.println(othello.displayBoard());
            System.out.println("current player is: " + othello.currentPlayer());

            String move = readMoveInput(othello, in);

            othello.placeMove(move);
        }

        System.out.println("Game ended, final board is: ");

        System.out.println(othello.displayBoard());
        System.out.println("Score for Player X: " + othello.score(Player.X));
        System.out.println("Score for Player O: " + othello.score(Player.O));

        if (othello.winner().isPresent()) {
            System.out.println("Winner is Player " + othello.winner().get());
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
}