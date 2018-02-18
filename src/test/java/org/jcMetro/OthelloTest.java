package org.jcMetro;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class OthelloTest {

    @Test
    public void initial_state() {
        Othello othello = new Othello();

        assertThat(othello.displayBoard(), is(String.join("\n",
                "1 --------",
                "2 --------",
                "3 --------",
                "4 ---OX---",
                "5 ---XO---",
                "6 --------",
                "7 --------",
                "8 --------",
                "  abcdefgh")));

        assertThat(othello.currentPlayer(), is(Player.X));
        assertThat(othello.displayValidMoves(), is(Sets.newHashSet("d3", "c4", "f5", "e6")));
    }

    @Test
    public void first_move() {
        Othello othello = new Othello();

        othello.placeMove("d3");

        assertThat(othello.displayBoard(), is(String.join("\n",
                "1 --------",
                "2 --------",
                "3 ---X----",
                "4 ---XX---",
                "5 ---XO---",
                "6 --------",
                "7 --------",
                "8 --------",
                "  abcdefgh")));

        assertThat(othello.currentPlayer(), is(Player.O));
        assertThat(othello.displayValidMoves(), is(Sets.newHashSet("c3", "c5", "e3")));
    }

    @Test
    public void second_move() {
        Othello othello = new Othello();

        othello.placeMove("d3");
        othello.placeMove("c5");

        assertThat(othello.displayBoard(), is(String.join("\n",
                "1 --------",
                "2 --------",
                "3 ---X----",
                "4 ---XX---",
                "5 --OOO---",
                "6 --------",
                "7 --------",
                "8 --------",
                "  abcdefgh")));

        assertThat(othello.currentPlayer(), is(Player.X));
        assertThat(othello.displayValidMoves(), is(Sets.newHashSet("b6", "c6", "d6", "e6", "f6")));
    }


}