import hanoi.DoubleHanoiTower;
import hanoi.HanoiTower;
import hanoi.SingleHanoiTower;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args == null || args.length < 3) {
            System.out.println("Prosze podac potrzebne informacje:");
            System.out.println("- typ wiezy (single lub double)");
            System.out.println("- wysokosc (ilosc krazkow)");
            System.out.println("    w przypadku wiezy dwukolorowej wysokosc bedzie dublowana");
            System.out.println("    np. wysokosc = 3 -> 3 krazki biale, 3 krazki czarne");
            System.out.println("- czy chcesz zobaczyc potrzebne kroki?");
            System.out.println();
            System.out.println("przyklady uzycia:");
            System.out.println("single 3 yes");
            System.out.println("double 4 no");
            return;
        }

        HanoiTower tower;
        if (args[0].equals("single")) {
            tower = new SingleHanoiTower(new Integer(args[1]), args[2]);
        } else {
            tower = new DoubleHanoiTower(new Integer(args[1]), args[2]);
        }

        tower.solve();

        if(args[2].equals("yes")){
            tower.printMoves();
        }
    }
}
