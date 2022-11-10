package printer;

import hanoi.utils.Block;
import printer.utils.Color;

import java.util.Collections;
import java.util.List;

public class ConsolePrinter implements Printer {
    public void print(List<List<Block>> sticks, int height, int step) {
        System.out.println();
        System.out.println(Color.RED + String.join("", Collections.nCopies((height + 2) * 2 * 3, "#")) + Color.END);
        System.out.println();
        System.out.printf("Step: %d\n", step);

        for (int i = 0; i < height; i++) {
            String spaces1 = "";
            String spaces2 = "";
            String spaces3 = "";

            Block block1 = sticks.get(0).get(height - 1 - i);
            Block block2 = sticks.get(1).get(height - 1 - i);
            Block block3 = sticks.get(2).get(height - 1 - i);

            for (int j = 0; j < Math.abs(height - block1.getSize()); j++) {
                spaces1 += " ";
            }

            for (int j = 0; j < Math.abs(height - block2.getSize()); j++) {
                spaces2 += " ";
            }

            for (int j = 0; j < Math.abs(height - block3.getSize()); j++) {
                spaces3 += " ";
            }

            printLine(block1, spaces1, block2, spaces2, block3, spaces3);
        }

        System.out.println();
        System.out.println(Color.RED + String.join("", Collections.nCopies((height + 2) * 2 * 3, "#")) + Color.END);
        System.out.println();
    }

    private void printLine(Block block1, String spaces1, Block block2, String spaces2, Block block3, String spaces3) {
        System.out.println(spaces1 + block1 + block1 + spaces1 + spaces2 + block2 + block2 + spaces2 + spaces3 + block3 + block3 + spaces3);
    }

    public void printMoves(List<int[]> moves){
        System.out.println("MOVES:");
        for(int[] move : moves){
            System.out.printf("%d %d\n", move[0], move[1]);
        }
    }
}
