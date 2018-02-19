package org.jcMetro;

import com.google.common.collect.Sets;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OthelloTest {

    private final Othello othello = new Othello();

    @Test
    public void initial_state() {
        assertThat(othello.displayBoard(), is(String.join("\n",
                "1 --------",
                "2 --------",
                "3 ---*----",
                "4 --*OX---",
                "5 ---XO*--",
                "6 ----*---",
                "7 --------",
                "8 --------",
                "  abcdefgh")));

        assertThat(othello.isEndGame(), is(false));
        assertThat(othello.currentPlayer(), is(Player.X));
        assertThat(othello.displayValidMoves(), is(Sets.newHashSet("d3", "c4", "f5", "e6")));
    }

    @Test
    public void first_move() {
        othello.placeMove("d3");

        assertThat(othello.displayBoard(), is(String.join("\n",
                "1 --------",
                "2 --------",
                "3 --*X*---",
                "4 ---XX---",
                "5 --*XO---",
                "6 --------",
                "7 --------",
                "8 --------",
                "  abcdefgh")));

        assertThat(othello.isEndGame(), is(false));
        assertThat(othello.currentPlayer(), is(Player.O));
        assertThat(othello.displayValidMoves(), is(Sets.newHashSet("c3", "c5", "e3")));
    }

    @Test
    public void move_sequences_should_return_correct_result() {
        placeMoves("6e", "4f", "3d", "7e");

        assertThat(othello.displayBoard(), is(String.join("\n",
                "1 --------",
                "2 --------",
                "3 ---X-*--",
                "4 ---XOO*-",
                "5 ---XO*--",
                "6 ----O*--",
                "7 ----O*--",
                "8 --------",
                "  abcdefgh")));

        assertThat(othello.isEndGame(), is(false));
        assertThat(othello.currentPlayer(), is(Player.X));
        assertThat(othello.displayValidMoves(), is(Sets.newHashSet("f3", "g4", "f5", "f6", "f7")));
    }

    @Test
    public void end_game_with_board_full_and_winner_is_x() {
        placeMoves(
                "e6", "d6", "c7", "d7", "c8", "b8", "a8", "d8", "e8", "f7",
                "e7", "f8", "g8", "g7", "h6", "h7", "h8", "e3", "e2", "f2",
                "g2", "e1", "d2", "g1", "b7", "a7", "a6", "f6", "g6", "c2",
                "b2", "c1", "c6", "c5", "d3", "a2", "b6", "c3", "a1", "b3",
                "a3", "b4", "b1", "g5", "d1", "f5", "c4", "b5", "a5", "f1",
                "1h", "h2", "a4", "f3", "g3", "f4", "g4", "h5", "h4", "h3");

        assertThat(othello.displayBoard(), is(String.join("\n",
                "1 XXXXXXXX",
                "2 XXXXOXXO",
                "3 XXXXXOOO",
                "4 XXOXXXOX",
                "5 XXOOOOXX",
                "6 XXXOXXXX",
                "7 XXXXXXXX",
                "8 XXXXXXXX",
                "  abcdefgh")));

        assertThat(othello.winner().isPresent(), is(true));
        assertThat(othello.winner().get(), is(Player.X));
        assertThat(othello.score(Player.X), is(52));
        assertThat(othello.score(Player.O), is(12));
    }

    @Test
    public void end_game_with_no_possible_moves_for_both_player_and_winner_is_x() {

        placeMoves("f5", "d6", "c5", "f4", "e3", "f6", "g5", "e6", "e7");

        assertThat(othello.displayBoard(), is(String.join("\n",
                "1 --------",
                "2 --------",
                "3 ----X---",
                "4 ---XXX--",
                "5 --XXXXX-",
                "6 ---XXX--",
                "7 ----X---",
                "8 --------",
                "  abcdefgh")));

        assertThat(othello.winner().isPresent(), is(true));
        assertThat(othello.winner().get(), is(Player.X));
        assertThat(othello.score(Player.X), is(13));
        assertThat(othello.score(Player.O), is(0));
    }

    private void placeMoves(String... coordinates) {
        for (String coordinate : coordinates) {
            othello.placeMove(coordinate);
        }
    }
}