package org.jcMetro;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class OthelloTest {

    @Test
    public void initial_state() {
        Othello othello = new Othello();

        assertThat(othello.displayBoard(), is(initialState()));
    }

    private String initialState() {
        return String.join("\n",
                "1 --------",
                "2 --------",
                "3 --------",
                "4 ---OX---",
                "5 ---XO---",
                "6 --------",
                "7 --------",
                "8 --------",
                "  abcdefgh");
    }
}