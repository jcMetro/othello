package org.jcMetro;

public enum Player {
    X, O;

    public Player opponent(){
        return this == X  ? O : X;
    }

    public CellStatus cellStatus(){
        return CellStatus.valueOf(this.name());
    }
}
