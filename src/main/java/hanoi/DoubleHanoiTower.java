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
            for (int i = 0; i < height/2; i++) {
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
        height -= 2;
        doubleHanoi(moves, height, 1, 2, 3);
        moves.add(new int[]{1, 3});

        while (height > 2) {
            height -= 2;
            doubleHanoi(moves, height, 2, 3, 1);
            moves.add(new int[]{2, 1});
            doubleHanoi(moves, height, 3, 1, 2);
            moves.add(new int[]{2, 3});
            height -= 2;
            doubleHanoi(moves, height, 1, 2, 3);
            moves.add(new int[]{1, 3});
        }

        if (height > 0) {
            moves.add(new int[]{2, 1});
            moves.add(new int[]{2, 3});
        }
    }

    private void doubleHanoi(List<int[]> moves, int height, int a, int b, int c) {
        if (height > 0) {
            doubleHanoi(moves, height - 2, a, c, b);
            moves.add(new int[]{a, b});
            moves.add(new int[]{a, b});
            doubleHanoi(moves, height - 2, c, b, a);
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

            boolean fullBlack = stick.stream().map(Block::getColor).collect(Collectors.joining("")).contains(new String(new char[solver.getHeight()/2]).replace("\0", "B"));
            boolean fullWhite = stick.stream().map(Block::getColor).collect(Collectors.joining("")).contains(new String(new char[solver.getHeight()/2]).replace("\0", "W"));

            if (fullBlack || fullWhite) {
                completedSticks++;
            }
        }

        if (completedSticks == 2) {
            isSolved = true;
        }
    }
}
