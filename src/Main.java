import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game g = new Game(4,4);
        System.out.println(g.toString());
        while (true){
            System.out.println("Premi 8 per andare sopra");
            System.out.println("Premi 4 per andare a sinistra");
            System.out.println("Premi 2 per andare sotto");
            System.out.println("Premi 6 per andare a destra");
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            switch (choice){
                case 2: g.moveSnake(1,0);
                break;
                case 8: g.moveSnake(-1, 0);//ok
                break;
                case 4: g.moveSnake(0, -1);//ok
                break;
                case 6: g.moveSnake(0, 1);//ok
                break;
            }
            System.out.println(g.toString());
        }
    }
}
