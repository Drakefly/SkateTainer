import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by marin on 12/05/2018.
 */
public class FlatTrick {
    public int lvl = 0;
    private String nom;
    private int rotaFlip; // 0 = Pas de flip  1 = Flip  -1 = Heel  2 = Double flip...
    private int rotaShov; // 0 = Pas de shove 1 = Back Shov  -1 = Front shov..
    private int rotaSkateur; // Idem
    private boolean pop; // true = trick popé
    private Stance stance;

    public FlatTrick(int rotaFlip, int rotaShov, int rotaSkateur, Stance stance) {
        this.pop = true;
        this.rotaFlip = rotaFlip;
        this.rotaShov = rotaShov;
        this.rotaSkateur = rotaSkateur;
        this.stance = stance;
        this.nom = calculerNom();
    }

    public static void main(String[] args) {
        TreeSet<FlatTrick> flatTricks = new TreeSet<FlatTrick>(new Comparator<FlatTrick>() {
            @Override
            public int compare(FlatTrick o1, FlatTrick o2) {
                int x = (Math.abs(o2.rotaFlip) + Math.abs(o2.rotaSkateur) + Math.abs(o2.rotaShov)) - (Math.abs(o1.rotaFlip) + Math.abs(o1.rotaSkateur) + Math.abs(o1.rotaShov));
                if (x != 0) return x;
                int max1, max2;
                max2 = Math.max(o2.rotaFlip, o2.rotaSkateur);
                max2 = Math.max(max2, o2.rotaSkateur);
                max1 = Math.max(o1.rotaFlip, o1.rotaSkateur);
                max1 = Math.max(max1, o1.rotaSkateur);
                if (max2 - max1 != 0) return max2 - max1;
                if (o1.rotaSkateur == o1.rotaShov && o2.rotaSkateur != o2.rotaShov)
                    return (Math.abs(o2.calculerNom().length() - o1.calculerNom().length()));//o2estplusdur
                if (o2.rotaSkateur == o2.rotaShov && o1.rotaSkateur != o1.rotaShov)
                    return -(Math.abs(o2.calculerNom().length() - o1.calculerNom().length()));
                int y = (o2.calculerNom().length() - o1.calculerNom().length());
                if (y != 0) return y;
                return o2.calculerNom().compareTo(o1.calculerNom());
            }
        });
        for (int i = -4; i <= 4; i++) {//rotaflip
            for (int j = -4; j <= 4; j++) {//rotashov
                for (int k = -4; k <= 4; k++) {//rotaSkateur
                    for (Stance s : Stance.values()) {//Stance
                        flatTricks.add(new FlatTrick(i, j, k, s));
                    }
                }
            }
        }
        System.out.println(new FlatTrick(1, 0, 0, Stance.SWITCH));
    }

    private String calculerNom() {
        String s = "";
        switch (stance) {
            case FACKIE:
                s += "Fackie ";
                break;
            case NOLLIE:
                s += "Nollie ";
                break;
            case SWITCH:
                s += "Switch ";
                break;
        }
        if (pop) {
            if (rotaFlip == 0) {
                if (rotaShov == 0) {
                    if (stance != Stance.NOLLIE) s += "Ollie ";
                    if (rotaSkateur == 0) return s;
                    else {
                        s += "Body Varial ";
                        if (Math.abs(rotaSkateur) >= 2) s += Integer.toString(180 * Math.abs(rotaSkateur)) + " ";

                        if (rotaSkateur > 0) return s + "Back";
                        else return s + "Front";
                    }

                } else {
                    if (rotaSkateur == 0) {
                        if (Math.abs(rotaShov) >= 2) s += Integer.toString(180 * Math.abs(rotaShov)) + " ";
                        if (rotaShov < 0) s += "Front ";
                        return s + "Pop Shovit";
                    } else {
                        if (rotaShov == rotaSkateur) {
                            s += Integer.toString(180 * Math.abs(rotaSkateur));
                            if (rotaSkateur > 0) return s + " Back";
                            else return s + " Front";
                        }

                        if (rotaShov * rotaSkateur > 0) {
                            if (rotaShov > 0) s += "Back ";
                            else s += "Front ";
                            if (Math.abs(rotaShov) > Math.abs(rotaSkateur) && Math.abs(rotaShov - rotaSkateur) == 1) {
                                if (Math.abs(rotaSkateur) == 1) return s + "Bigspin";
                                else if (Math.abs(rotaSkateur) == 2) return s + "Gazelle";
                                else return s + Integer.toString(180 * Math.abs(rotaSkateur)) + " Bigspin";
                            } else if (Math.abs(rotaShov) > Math.abs(rotaSkateur) && Math.abs(rotaShov - rotaSkateur) == 2) {
                                if (Math.abs(rotaSkateur) == 1) return s + "Biggerspin";
                                else return s + Integer.toString(180 * Math.abs(rotaSkateur)) + " Biggerspin";
                            } else {
                                if (Math.abs(rotaShov) >= 2) s += Integer.toString(180 * Math.abs(rotaShov)) + " ";
                                s += "Pop Shovit ";
                                s += "Body Varial ";
                                if (Math.abs(rotaSkateur) >= 2)
                                    s += Integer.toString(180 * Math.abs(rotaSkateur)) + " ";
                                if (rotaSkateur < 0) s += "Front";
                                else s += "Back";
                                return s;
                            }
                        } else {
                            if (Math.abs(rotaShov) >= 2) s += Integer.toString(180 * Math.abs(rotaShov)) + " ";
                            if (rotaShov < 0) s += "Front ";
                            s += "Pop Shovit ";
                            s += "Body Varial ";
                            if (Math.abs(rotaSkateur) >= 2) s += Integer.toString(180 * Math.abs(rotaSkateur)) + " ";
                            if (rotaSkateur < 0) s += "Front ";
                            else s += "Back ";
                            return s;
                        }

                    }
                }
            } else {
                if (rotaSkateur == 0) {
                    if (rotaFlip > 0) {
                        if (rotaShov > 0) {
                            if (rotaShov == 1) s += "Varial ";
                            else if (rotaShov == 2) s += "Tre ";
                            else s += Integer.toString(180 * rotaShov) + " ";
                            s = nbRota(s);
                            s += "Flip ";
                            return s;
                        } else {
                            if (rotaShov <= -2) s += Integer.toString(180 * (Math.abs(rotaShov))) + " ";
                            else if (rotaFlip == 1) return s + "Flip ";
                            if (rotaShov != 0) s += "Hard ";
                            s = nbRota(s);
                            s += "Flip ";
                            return s;
                        }
                    } else {
                        if (rotaShov > 0) {
                            if (rotaShov == 1) s += "Inward ";
                            else s += Integer.toString(180 * Math.abs(rotaShov)) + " Inward ";
                            s = nbRota(s);
                            s += "Heelflip ";
                            return s;
                        } else {
                            if (rotaShov == 0) ;
                            else if (rotaShov == -1) s += "Varial ";
                            else if (rotaShov == -2) s += "Laser ";
                            else if (rotaShov != 0) s += Integer.toString(180 * Math.abs(rotaShov)) + " ";
                            s = nbRota(s);
                            s += "Heelflip ";
                            return s;
                        }
                    }
                } else {
                    if (rotaShov == rotaSkateur) {
                        s = nbRota(s);
                        if (rotaFlip > 0) s += "Flip ";
                        else s += "Heelflip ";
                        if (rotaSkateur != 0) s += Integer.toString(180 * Math.abs(rotaSkateur));
                        if (rotaSkateur > 0) return s + " Back";
                        else return s + " Front";
                    } else if (rotaShov * rotaSkateur > 0) {
                        if (rotaShov > 0) s += "Back ";
                        else s += "Front ";
                        if (Math.abs(rotaShov) > Math.abs(rotaSkateur) && Math.abs(rotaShov - rotaSkateur) == 1) {
                            if (Math.abs(rotaSkateur) == 1) s += "Bigspin ";
                            else if (Math.abs(rotaSkateur) == 2) s += "Gazelle ";
                            else s += Integer.toString(180 * Math.abs(rotaSkateur)) + " Bigspin ";
                            s = nbRota(s);
                            if (rotaFlip > 0) s += "Flip ";
                            else s += "Heelflip ";
                            return s;
                        } else if (Math.abs(rotaShov) > Math.abs(rotaSkateur) && Math.abs(rotaShov - rotaSkateur) == 2) {
                            if (Math.abs(rotaSkateur) == 1) s += "Biggerspin ";
                            else s += Integer.toString(180 * Math.abs(rotaSkateur)) + " Biggerspin ";
                            s = nbRota(s);
                            if (rotaFlip > 0) s += "Flip ";
                            else s += "Heelflip ";
                            return s;
                        } else {
                            s = jcp(s);
                            s += "Body Varial ";
                            if (Math.abs(rotaSkateur) >= 2)
                                s += Integer.toString(180 * Math.abs(rotaSkateur)) + " ";
                            if (rotaSkateur < 0) s += "Front";
                            else s += "Back";
                            return s;
                        }
                    } else {
                        s = jcp(s);
                        s += "Body Varial ";
                        if (Math.abs(rotaSkateur) >= 2) s += Integer.toString(180 * Math.abs(rotaSkateur)) + " ";
                        if (rotaSkateur < 0) s += "Front ";
                        else s += "Back ";
                        return s;
                    }

                }
            }
        } else {
            return "bite";
        }
    }

    private String nbRota(String s) {
        switch (Math.abs(rotaFlip)) {
            case 2:
                s += "Double ";
                break;
            case 3:
                s += "Triple ";
                break;
            case 4:
                s += "Quad ";
                break;
        }
        return s;
    }

    private String jcp(String s) {
        if (rotaFlip > 0) {
            if (rotaShov > 0) {
                if (rotaShov == 1) s += "Varial ";
                else if (rotaShov == 2) s += "Tre ";
                else s += Integer.toString(180 * Math.abs(rotaShov)) + " ";
                s = nbRota(s);
                s += "Flip ";
            } else {
                if (rotaShov <= -2) s += Integer.toString(180 * Math.abs(rotaShov)) + " ";
                if (rotaFlip == 1) s += "Hardflip ";
                else {
                    s += "Hard ";
                    s = nbRota(s);
                    s += "Flip ";
                }
            }
        } else {
            if (rotaShov > 0) {
                if (rotaShov == 1) s += "Inward ";
                else s += Integer.toString(180 * Math.abs(rotaShov)) + " Inward ";
                s = nbRota(s);
                s += "Heelflip ";
            } else {
                if (rotaShov == -1) s += "Varial ";
                else if (rotaShov == -2) s += "Laser ";
                else if (rotaShov != 0) s += Integer.toString(180 * Math.abs(rotaShov)) + " ";
                s = nbRota(s);
                s += "Heelflip ";
            }
        }
        return s;
    }

    public String toString() {
        return nom;
    }

}
