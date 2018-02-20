package org.jcMetro;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.jcMetro.Cell.cell;
import static org.junit.Assert.*;

public class CellTest {

    @Test
    public void move_direction_to_another_cell() {
        Cell cell = cell(3, 3);

        assertThat(cell.moveTo(Direction.North), is(cell(2,3)));
        assertThat(cell.moveTo(Direction.NorthEast), is(cell(2,4)));
        assertThat(cell.moveTo(Direction.East), is(cell(3,4)));
        assertThat(cell.moveTo(Direction.SouthEast), is(cell(4,4)));
        assertThat(cell.moveTo(Direction.South), is(cell(4,3)));
        assertThat(cell.moveTo(Direction.SouthWest), is(cell(4,2)));
        assertThat(cell.moveTo(Direction.West), is(cell(3,2)));
        assertThat(cell.moveTo(Direction.NorthWest), is(cell(2,2)));
    }
}