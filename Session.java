import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

public class Session {
    public TreeSet<FlatTrick> flatTricksConnu;
    public TreeSet<FlatTrick> flatTricksParfait;
    public TreeSet<FlatTrick> flatTricksAAprendre;

    public Session(TreeSet<FlatTrick> flatTricks) {//Devra diviser le treeSet en 3
        Iterator<FlatTrick> iterator = flatTricks.descendingIterator();
        int i = 0;
        FlatTrick a;
        FlatTrick m;
        while (iterator.hasNext()) {
            m = a = iterator.next();
            if (a.lvl < 9) {
                flatTricksParfait = (TreeSet<FlatTrick>) flatTricks.tailSet(a);
                m = a;
            }
            if (a.lvl < 0) {
                flatTricksConnu = (TreeSet<FlatTrick>) flatTricks.subSet(m, a);
                flatTricksAAprendre = (TreeSet<FlatTrick>) flatTricks.headSet(a);
                break;
            }
            i++;
            iterator.next();
        }
    }

    public Session() {

    }

    public void sess() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(tirrageFacile());
    }

    /**
     * @return un tricksparfait
     */
    private FlatTrick tirrageFacile() {
        return getFlatTrick(flatTricksParfait);
    }

    private FlatTrick tirrageMoyen() {
        return getFlatTrick(flatTricksConnu);
    }

    private FlatTrick tirrageDur() {
        return null;
    }

    private FlatTrick getFlatTrick(TreeSet<FlatTrick> flatTricks) {
        Random rand = new Random();
        int n = rand.nextInt(flatTricks.size());//Donne l'indice d'un trick random des tricks connus
        Iterator iterator = flatTricks.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            if (i == n) return (FlatTrick) iterator.next();
            i++;
            iterator.next();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Session{" +
                "flatTricksConnu=" + flatTricksConnu +
                '}';
    }
}

