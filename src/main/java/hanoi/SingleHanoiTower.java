package hanoi;

import hanoi.exceptions.WrongHeightException;
import hanoi.utils.Block;
import hanoi.utils.Solver;

import java.util.ArrayList;
import java.util.List;

public class SingleHanoiTower implements HanoiTower {
    private final Solver solver;
    private boolean isSolved = false;

    public SingleHanoiTower(int height, String isPrintable) throws WrongHeightException {
        if(height <= 0){
            throw new WrongHeightException(height);
        }

        List<List<Block>> sticks = setUpSticks(height);
        List<int[]> moves = setUpMoves(height);
        solver = new Solver(height, sticks, moves);
    }

    private List<List<Block>> setUpSticks(int height) {
        List<List<Block>> sticks = new ArrayList<>();
        boolean isEmpty = false;
        for (int x = 0; x <= 2; x++) {
            String color = isEmpty ? " " : "W";

            List<Block> temp = new ArrayList<>();
            for (int i = 0; i < height; i++) {
                temp.add(new Block(height - i, color));
                if (isEmpty) {
                    color = " ";
                } else {
                    color = "W";
                }
            }
            sticks.add(temp);
            isEmpty = true;
        }
        return sticks;
    }

    private List<int[]> setUpMoves(int height) {
        List<int[]> moves = new ArrayList<>();
        hanoi(moves, height, 1, 2, 3);
        return moves;
    }

    private void hanoi(List<int[]> moves, int height, int a, int b, int c) {
        if (height > 0) {
            hanoi(moves, height - 1, a, c, b);
            List<Integer> temp = new ArrayList<>();
            moves.add(new int[]{a, b});
            hanoi(moves, height - 1, c, b, a);
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
        if (solver.getSticks().get(1).stream().noneMatch(Block::isEmpty)) {
            isSolved = true;
        }
    }
}
