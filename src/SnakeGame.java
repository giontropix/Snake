import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class SnakeGame {
    enum Move {TOP, BOTTOM, LEFT, RIGHT}
    enum Status {IN_GAME, LOSE, WIN}
    int[][] grid;
    private Move forbiddenMove = Move.LEFT;
    private Status currentStatus = Status.IN_GAME;
    LinkedList<Coord> snake = new LinkedList<>();
    private final int m;
    private final int n;

    public SnakeGame(int n, int m) throws Exception {
        if (n < 5 || m < 5) {
            throw new Exception("La griglia è troppo piccola!!!");
        }
        this.n = n;
        this.m = m;
        this.grid = new int[n][m];
        snake.add(new Coord(1, 1));
        snake.addFirst(new Coord(1, 2));
        generateApple();
    }

    public void generateApple(){
        int x = ThreadLocalRandom.current().nextInt(n);
        int y = ThreadLocalRandom.current().nextInt(m);
        if(!this.snake.contains(new Coord(x, y)))
            grid[x][y] = 1;
        else
            generateApple();
    }

    public void eatApple(Coord coord){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 1 && coord.getX() == i && coord.getY() == j){
                    snake.addLast(new Coord(i, j));
                    grid[i][j] = 0;
                    generateApple();
                }
            }
        }
        win();
    }

    public void win(){
        if(snake.size() == m*n)
            currentStatus = Status.WIN;
    }

    public Status getCurrentStatus() {
        return currentStatus;
    }

    public void move(Move m) {
        if (m == this.forbiddenMove) return;
        Coord head = this.snake.getFirst();
        eatApple(head);
        if (m == Move.RIGHT) {
            if (head.getY()+ 1 > this.m - 1 || this.snake.contains(new Coord(head.getX(), head.getY()+ 1))) {
                this.currentStatus = Status.LOSE;
                return;
            }
            this.snake.addFirst(new Coord(head.getX(), head.getY() + 1));
            this.forbiddenMove = Move.LEFT;
        } else if (m == Move.LEFT) {
            if (head.getY()- 1 < 0 || this.snake.contains(new Coord(head.getX(), head.getY() - 1))) {
                this.currentStatus = Status.LOSE;
                return;
            }
            this.snake.addFirst(new Coord(head.getX(), head.getY() - 1));
            this.forbiddenMove = Move.RIGHT;
        } else if (m == Move.TOP) {
            if (head.getX()- 1 < 0 || this.snake.contains(new Coord(head.getX() - 1, head.getY()))) {
                this.currentStatus = Status.LOSE;
                return;
            }
            this.snake.addFirst(new Coord(head.getX() - 1, head.getY()));
            this.forbiddenMove = Move.BOTTOM;
        } else if (m == Move.BOTTOM) {
            if (head.getX()+ 1 > this.n - 1 || this.snake.contains(new Coord(head.getX() + 1, head.getY()))) {
                this.currentStatus = Status.LOSE;
                return;
            }
            this.snake.addFirst(new Coord(head.getX() + 1, head.getY()));
            this.forbiddenMove = Move.TOP;
        }
        this.snake.removeLast();
    }
    
    public String toString() {
        String result =  this.currentStatus + "\n";
        for (int x = 0; x < this.grid.length; x++) {
            result += "[";
            for (int y = 0; y <this.grid[x].length; y++) {
                if (this.snake.contains(new Coord(x, y)))
                    result += "[\u001B[32mO\u001B[0m]";
                else if (grid[x][y] == 1)
                    result += "[\u001B[31mO\u001B[0m]";
                else
                    result += "[ ]";
            }
            result += "]\n";
        }
        return result;
    }

}