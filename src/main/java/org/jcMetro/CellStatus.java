package org.jcMetro;

public enum CellStatus {
    X, O, Empty;

    public String displayValue() {
        if (this == Empty){
            return "-";
        }
        return this.name();
    }
}
