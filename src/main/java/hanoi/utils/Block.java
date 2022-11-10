package hanoi.utils;

import printer.utils.Color;

public class Block {
    private final int size;
    private final String symbol;
    private final String color;
    private final boolean isEmpty;

    public Block(int size, String color) {
        this.size = size;
        this.color = color;
        this.isEmpty = color.equals(" ");
        this.symbol = color.equals(" ") ? " " : "#";
    }

    public int getSize() {
        return size;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getColor() {
        return color;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    @Override
    public String toString() {
        String temp = "";
        temp += color.equals("B") ? Color.BLACK : Color.WHITE;
        for (int i = 0; i < size; i++) {
            temp += symbol;
        }
        temp += Color.END;

        return temp;
    }
}
