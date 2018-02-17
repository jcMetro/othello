package org.jcMetro;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Coordinate {
    private final String input;
    private final boolean valid;
    private final int rowIndex;
    private final int colIndex;

    public Coordinate(String input) {

        this.input = input;

        Pattern pattern = Pattern.compile("(([a-h]|[A-H])([1-8]))|(([1-8])([a-h]|[A-H]))");

        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            valid = true;
            String rowMatchedGroup = matcher.group(1) != null ? matcher.group(3) : matcher.group(5);
            String colMatchedGroup = matcher.group(1) != null ? matcher.group(2) : matcher.group(6);
            rowIndex = rowMatchedGroup.charAt(0) - '1';
            colIndex = colMatchedGroup.toLowerCase().charAt(0) - 'a';
        } else {
            valid = false;
            rowIndex = -1;
            colIndex = -1;
        }
    }

    public int rowIndex() {
        return rowIndex;
    }

    public int colIndex() {
        return colIndex;
    }

    public boolean isValid() {
        return valid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Coordinate{");
        sb.append("input='").append(input).append('\'');
        sb.append(", valid=").append(valid);
        sb.append(", rowIndex=").append(rowIndex);
        sb.append(", colIndex=").append(colIndex);
        sb.append('}');
        return sb.toString();
    }
}

