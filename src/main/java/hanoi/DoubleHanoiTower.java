package hanoi;

import hanoi.exceptions.WrongHeightException;
import hanoi.utils.Block;
import hanoi.utils.Solver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DoubleHanoiTower implements HanoiTower {
    private final Solver solver;
    private boolean isSolved = false;

    public DoubleHanoiTower(int height, String isPrintable) throws WrongHeightException {
        if (height <= 0) {
            throw new WrongHeightException(height);
        }
        height *= 2;
        List<List<Block>> sticks = setUpSticks(height);
        List<int[]> moves = setUpMoves(height);
        solver = new Solver(height, sticks, moves);
    }

    private List<List<Block>> setUpSticks(int height) {
        List<List<Block>> sticks = new ArrayList<>();
        boolean isEmpty = false;

        for (int x = 0; x <= 2; x++) {
            String color = isEmpty ? " " : "B";

            List<Block> temp = new ArrayList<>();
            for (int i = 0; i < height; i++) {
                temp.add(new Block(height - i, color));
                if (isEmpty) {
                    color = " ";
                } else {
                    color = color.equals("B") ? "W" : "B";
                }
                temp.add(new Block(height - i, color));
                if (isEmpty) {
                    color = " ";
                } else {
                    color = color.equals("B") ? "W" : "B";
                }
            }
            sticks.add(temp);
            isEmpty = true;
        }

        return sticks;
    }

    private List<int[]> setUpMoves(int height) {
        List<int[]> moves = new ArrayList<>();
        prevHanoi(moves, height);
        return moves;
    }

    private void prevHanoi(List<int[]> moves, int height) {
        while (height > 1) {
            if (height % 2 == 0) {
                doubleHanoi(moves, height - 1, 1, 3, 2);
            } else {
                doubleHanoi(moves, height - 1, 2, 3, 1);
            }
            height--;
        }
    }

    private void doubleHanoi(List<int[]> moves, int height, int a, int b, int c) {
        if (height == 1) {
            moves.add(new int[]{a, c});
        } else {
            doubleHanoi(moves, height - 1, a, c, b);
            doubleHanoi(moves, 1, a, b, c);
            doubleHanoi(moves, height - 1, b, a, c);
        }
    }

    @Override
    public void solve() {
        while (!isSolved) {
            boolean errorOccurred = solver.arrange();
            if (errorOccurred) {
                return;
            }
            checkIfCompleted();
        }
        solver.print();
    }

    @Override
    public void printMoves() {
        solver.printMoves();
    }

    private void checkIfCompleted() {
        int completedSticks = 0;
        for (int i = 0; i < solver.getSticks().size(); i++) {
            List<Block> stick = solver.getSticks().get(i);

            boolean fullBlack = stick.stream().map(Block::getColor).collect(Collectors.joining("")).contains("BBB");
            boolean fullWhite = stick.stream().map(Block::getColor).collect(Collectors.joining("")).contains("WWW");

            if (fullBlack || fullWhite) {
                completedSticks++;
            }
        }

        if (completedSticks == 2) {
            isSolved = true;
        }
    }
}
