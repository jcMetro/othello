package org.jcMetro;

import java.util.Objects;

public class Cell {

    public final int rowIndex;
    public final int colIndex;

    public static Cell cell(int rowIndex, int colIndex){
        return new Cell(rowIndex, colIndex);
    }

    private Cell(int rowIndex, int colIndex) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return rowIndex == cell.rowIndex &&
                colIndex == cell.colIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, colIndex);
    }
}
