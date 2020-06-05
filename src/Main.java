import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        SnakeGame g = new SnakeGame(6,6);
        System.out.println(g);
        do {
            System.out.println("Premi 8 per andare sopra");
            System.out.println("Premi 4 per andare a sinistra");
            System.out.println("Premi 2 per andare sotto");
            System.out.println("Premi 6 per andare a destra");
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            switch (choice){
                case 2: g.move(SnakeGame.Move.BOTTOM);
                break;
                case 8: g.move(SnakeGame.Move.TOP);
                break;
                case 4: g.move(SnakeGame.Move.LEFT);
                break;
                case 6: g.move(SnakeGame.Move.RIGHT);
                break;
            }
            System.out.println(g);
        } while (g.getCurrentStatus() == SnakeGame.Status.IN_GAME);

    }
}
