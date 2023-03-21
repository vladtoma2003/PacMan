import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        InputData data = new InputData();
        data.inputRead(scan);

        Game game = new Game();
        game.game(data);

//        System.out.println(data.getMoves());
        /* e ok sa mutati citirea intr-o alta functie */

//        System.out.println("Atentie! coordonatele din input si output sunt de la 1 la N");
        /* e ok sa mutati scrierea intr-o alta functie */

    }
}

class InputData {
    private int n;
    private Ghost PacMan;
    private int R;
    private ArrayList<Ghost> redGhosts;
    private int A;
    private ArrayList<Ghost> blueGhosts;
    private int M;
    private ArrayList<String> moves;

    public void inputRead(Scanner scan) {
        n = scan.nextInt();
        PacMan = new Ghost();
        PacMan.X = scan.nextInt();
        PacMan.Y = scan.nextInt();

        redGhosts = new ArrayList<>();
        R = scan.nextInt();
        for (int i = 0; i < R; ++i) {
            Ghost red = new Ghost();
            red.X = scan.nextInt();
            red.Y = scan.nextInt();
            red.isRed = true;
            redGhosts.add(red);
        }

        A = scan.nextInt();
        blueGhosts = new ArrayList<>();
        for (int i = 0; i < A; ++i) {
            Ghost blue = new Ghost();
            blue.X = scan.nextInt();
            blue.Y = scan.nextInt();
            blue.isRed = false;
            blueGhosts.add(blue);
        }

        M = scan.nextInt();
        moves = new ArrayList<>();
        for (int i = 0; i < M; ++i) {
            moves.add(scan.next());
        }
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public Ghost getPacMan() {
        return PacMan;
    }

    public void setPacMan(Ghost pacMan) {
        PacMan = pacMan;
    }

    public int getR() {
        return R;
    }

    public void setR(int r) {
        R = r;
    }

    public ArrayList<Ghost> getRedGhosts() {
        return redGhosts;
    }

    public void setRedGhosts(ArrayList<Ghost> redGhosts) {
        this.redGhosts = redGhosts;
    }

    public int getA() {
        return A;
    }

    public void setA(int a) {
        A = a;
    }

    public ArrayList<Ghost> getBlueGhosts() {
        return blueGhosts;
    }

    public void setBlueGhosts(ArrayList<Ghost> blueGhosts) {
        this.blueGhosts = blueGhosts;
    }

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }

    public ArrayList<String> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<String> moves) {
        this.moves = moves;
    }
}

class Ghost {
    int X;
    int Y;
    boolean forward = true;
    boolean isRed;

    public boolean sameSpace(Ghost other) {
        return other.X == this.X && other.Y == this.Y;
    }

    public void move(InputData data) {
        if (forward && isRed) {
            if (X - 1 == 0) {
                forward = false;
                ++X;
            } else {
                --X;
            }
        } else if (!forward && isRed) {
            if (X + 1 == data.getN()) {
                forward = true;
                --X;
            } else {
                ++X;
            }
        } else if (forward && !isRed) {
            if (Y - 1 == 0) {
                forward = false;
                ++Y;
            } else {
                --Y;
            }
        } else {
            if (Y + 1 == 0) {
                forward = true;
                --Y;
            } else {
                ++Y;
            }
        }
    }

    public void pacMove(InputData data, String move) {
        switch (move) {
            case "U":
                if (data.getPacMan().Y + 1 == data.getN()) {
                    break;
                }
                ++data.getPacMan().Y;
                break;
            case "D":
                if (data.getPacMan().Y - 1 == 0) {
                    break;
                }
                --data.getPacMan().Y;
                break;
            case "L":
                if (data.getPacMan().X - 1 == 0) {
                    break;
                }
                --data.getPacMan().X;
                break;
            case "R":
                if (data.getPacMan().X + 1 == data.getN()) {
                    break;
                }
                ++data.getPacMan().X;
                break;
        }
    }
}

class Game {
    boolean gameGoing = true;
    public void game(InputData data) {
        for (int i = 0; i < data.getM() && gameGoing; ++i) {
            data.getPacMan().pacMove(data, data.getMoves().get(i));
            for (int j = 0; j < data.getA(); ++j) {
                data.getBlueGhosts().get(j).move(data);
                if (data.getBlueGhosts().get(j).X == data.getPacMan().X
                        && data.getBlueGhosts().get(j).Y == data.getPacMan().Y) {
                    gameGoing = false;
                    break;
                }
            }
            for (int j = 0; j < data.getR(); ++j) {
                data.getRedGhosts().get(j).move(data);
                if (data.getRedGhosts().get(j).X == data.getPacMan().X
                        && data.getRedGhosts().get(j).Y == data.getPacMan().Y) {
                    gameGoing = false;
                    break;
                }
            }
        }
        System.out.println(data.getPacMan().X + " " + data.getPacMan().Y);
        for (int i = data.getN(); i > 0; --i) {
            for (int j = 0; j < data.getN(); ++j) {
                for (var k : data.getBlueGhosts()) {
                    if (k.X == j && k.Y == i) {
                        System.out.println("B " + k.X + " " + k.Y);
                    }
                }
                for (var k : data.getRedGhosts()) {
                    if (k.X == j && k.Y == i) {
                        System.out.println("R " + k.X + " " + k.Y);
                    }
                }
            }
        }
    }
}
/* puteti adauga oricate clase doriti, fara modificatorul public */