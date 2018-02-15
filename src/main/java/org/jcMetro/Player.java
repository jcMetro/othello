package org.jcMetro;

public enum Player {
    X, O;

    public char symbol(){
        return this.name().charAt(0);
    }
}
