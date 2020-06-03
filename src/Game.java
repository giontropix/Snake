import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    Snake[][] grid;
    ArrayList Coords = new ArrayList();
    public Game(int row, int column){
        grid = new Snake[row][column];
        filler();
        insert(Snake.Type.Snake);
        insert(Snake.Type.Mouse);
    }

    public void filler(){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                this.grid[i][j] = new Snake(Snake.Type.Empty);
            }
        }
    }

    public void insert(Snake.Type type){
        int row = ThreadLocalRandom.current().nextInt(0, grid.length);
        int column = ThreadLocalRandom.current().nextInt(0, grid[row].length);
        if(this.grid[row][column].getType() == Snake.Type.Empty)
            this.grid[row][column].setType(type);
    }

    public boolean canSwitch(int i, int j){
        return (i < grid.length) && (j < grid[0].length) && ((i >= 0) && (j >= 0));
    }

    public void checkSnake(){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j].getType() == Snake.Type.Snake)
                    Coords.add(i, j);
            }
        }
    }

    public void moveSnake(int row, int column){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(this.grid[i][j].getType() == Snake.Type.Snake) {
                    if (this.canSwitch(i + row, j + column)) {
                        if (this.grid[i + row][j + column].getType() == Snake.Type.Empty) {
                            this.grid[i + row][j + column].setType(Snake.Type.Snake);
                            this.grid[i][j].setType(Snake.Type.Empty);
                            return;
                        }
                        if (this.grid[i + row][j + column].getType() == Snake.Type.Mouse) {
                            this.grid[i + row][j + column].setType(Snake.Type.Snake);
                            this.grid[i][j].setType(Snake.Type.Snake);
                            return;
                        }
                    }
                }
            }
        }
    }

    public void eating(int row, int column, int rowBack, int columnBack){
        if(grid[row][column].getType() == Snake.Type.Mouse)
            grid[rowBack][columnBack].setType(Snake.Type.Snake);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < grid.length ; i++) {
            if(i == 0)
                result.append("\t");
            if (i > 9)
                result.append(" ").append(i);
            else
                result.append("  ").append(i);
            if(i == grid.length - 1)
                result.append("\n");
        }
        for (int x = 0; x < this.grid.length; x++) {
            result.append(x).append("\t").append("[");
            for(int y = 0; y < this.grid[x].length; y++) {
                result.append("[").append(grid[x][y]).append("]");
            }
            result.append("]\n");
        }
        return result.toString();
    }
}
