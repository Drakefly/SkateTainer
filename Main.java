import java.util.Comparator;
import java.util.TreeSet;

public class Main {
    //Ou les fichiers existe lancement, ou les fichiers n'existent pas et la on lance l'init
    public static void main(String[] args) {
        TreeSet<FlatTrick> flatTricks = new TreeSet<FlatTrick>(new Comparator<FlatTrick>() {
            @Override
            public int compare(FlatTrick o1, FlatTrick o2) {
                if (o1.lvl - o2.lvl != 0) return o2.lvl - o1.lvl;
                return 1;
            }
        });
        flatTricks.add(new FlatTrick(1, 0, 1, Stance.SWITCH));
        flatTricks.add(new FlatTrick(1, 1, 1, Stance.NOLLIE));
        flatTricks.add(new FlatTrick(0, 1, 0, Stance.NORMAL));
        flatTricks.add(new FlatTrick(0, 0, 0, Stance.SWITCH));
        flatTricks.add(new FlatTrick(-2, 0, 0, Stance.NOLLIE));
        flatTricks.add(new FlatTrick(-1, -2, 0, Stance.NORMAL));

        System.out.println(flatTricks.toString());
    }
}
