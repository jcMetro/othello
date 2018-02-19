package org.jcMetro;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CoordinateTest {

    @Test
    public void parse_string_coordinate_into_3d() {

        List<Coordinate> coordinatesFor3d = Arrays.asList(new Coordinate("d3"), new Coordinate("3d"), new Coordinate("3D"), new Coordinate("D3"));

        for (Coordinate coordinate : coordinatesFor3d) {
            assertThat(coordinate.isValid(), is(true));
            assertThat(coordinate.rowIndex(), is(2));
            assertThat(coordinate.colIndex(), is(3));
        }
    }

    @Test
    public void parse_invalid_coordinates() {
        List<Coordinate> invalidCoordinates = Arrays.asList(new Coordinate("a9"), new Coordinate("dd"), new Coordinate("88"), new Coordinate("HELLO"));

        for (Coordinate coordinate : invalidCoordinates) {
            assertThat(coordinate.isValid(), is(false));
            assertThat(coordinate.rowIndex(), is(-1));
            assertThat(coordinate.colIndex(), is(-1));
        }

    }
}