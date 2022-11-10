package printer;

import hanoi.utils.Block;

import java.util.List;

public interface Printer {
    void print(List<List<Block>> sticks, int height, int step);

    void printMoves(List<int[]> moves);
}
