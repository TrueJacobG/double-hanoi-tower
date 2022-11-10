package hanoi.utils;

import printer.ConsolePrinter;
import printer.Printer;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    private final int height;
    private final Printer printer = new ConsolePrinter();
    private List<List<Block>> sticks = new ArrayList<>();
    private List<int[]> moves = new ArrayList<>();
    private int indexOfNextMove = 0;
    private int step = 0;

    public Solver(int height, List<List<Block>> sticks, List<int[]> moves) {
        this.height = height;
        this.sticks = sticks;
        this.moves = moves;
    }

    public int getHeight(){
        return height;
    }

    public List<List<Block>> getSticks() {
        return sticks;
    }

    public void print() {
        printer.print(sticks, height, step);
    }

    private boolean move(int from, int to) {
        // wygoda pisania
        from -= 1;
        to -= 1;

        int index = -1;
        for (int i = height - 1; i >= 0; i--) {
            if (!sticks.get(from).get(i).isEmpty()) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.printf("ILLEGAL RUCH, brak blokow na stozku %d\n", from);
            return false;
        }

        Block block = sticks.get(from).get(index);
        sticks.get(from).set(index, new Block(block.getSize(), " "));

        int index2 = height;
        for (int i = 0; i < height; i++) {
            if (sticks.get(to).get(i).isEmpty()) {
                index2 = i;
                break;
            }
        }

        if (index2 == height) {
            sticks.get(from).set(index, block);
            System.out.println("ILLEGAL RUCH, brak wolnego miejsca na stozku");
            return false;
        }

        if (index2 != 0 && sticks.get(to).get(index2 - 1).getSize() < block.getSize()) {
            sticks.get(from).set(index, block);
            System.out.println("ILLEGAL RUCH, nie mozna klasc wiekszego na malego");
            return false;
        }

        sticks.get(to).set(index2, block);
        return true;
    }

    public boolean arrange() {
        printer.print(sticks, height, step);
        step++;

        if (indexOfNextMove == moves.size()) {
            System.out.println("KONIEC RUCHOW, nie udalo sie ulozyc wiezy hanoi");
            return true;
        }

        boolean isValidMove = move(moves.get(indexOfNextMove)[0], moves.get(indexOfNextMove)[1]);
        indexOfNextMove++;

        return !isValidMove;
    }

    public void printMoves() {
        printer.printMoves(moves);
    }
}
