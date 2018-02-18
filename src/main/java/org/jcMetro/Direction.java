package org.jcMetro;

public enum Direction {
    North(-1, 0),
    NorthEast(-1,1),
    East(0,1),
    SouthEast(1,1),
    South(1, 0),
    SouthWest(1,-1),
    West(0,-1),
    NorthWest(-1,-1);

    public final int rowStep;
    public final int colStep;

    Direction(int rowStep, int colStep) {
        this.rowStep = rowStep;
        this.colStep = colStep;
    }
}
