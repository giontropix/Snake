import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainGui extends Application {
    int row = 10;
    int column = 10;
    SnakeGame game = new SnakeGame(this.row, this.column);
    private final String bgPath = "\\src\\gioconda.jpg";

    public MainGui() throws Exception {
    }

    private final int GAMEHEIGHT = this.row*50;
    private final int GAMEWEIGHT = this.column*50;
    private AnchorPane pane;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Sucuni");
        createContent(stage);
    }

    private void createContent(Stage stage) {
        Group route = new Group(); //ammucchiata di oggetti
        ArrayList<Circle> snake = new ArrayList<>();
        for (int i = 0; i < game.getSnake().size(); i++) {
            Circle partOfSnake = new Circle(25);
            partOfSnake.setCenterX(game.getSnake().get(i).getX()*50+25);//COME SE COMPENSASSIMO IL RAGGIO
            partOfSnake.setCenterY(game.getSnake().get(i).getY()*50+25);
            snake.add(partOfSnake);
        }
        for (Circle partOfSnake:snake) {
            route.getChildren().addAll(partOfSnake);
        }
        Circle fruit = new Circle(25);
        fruit.setCenterX(game.getFruit().getX()*50+25);
        fruit.setCenterY(game.getFruit().getY()*50+25);
        fruit.setFill(Color.RED);
        route.getChildren().addAll(fruit);
        this.pane = new AnchorPane();
        this.pane.getChildren().addAll(route);
        createBackground();
        Scene scene = new Scene(this.pane, this.GAMEHEIGHT, this.GAMEWEIGHT);
        final Box keyboardNode = new Box();
        keyboardNode.setFocusTraversable(true);
        keyboardNode.requestFocus();
        keyboardNode.setOnKeyPressed(this::handle);
        route.getChildren().addAll(keyboardNode);
        stage.setScene(scene);
        stage.show();
        AnimationTimer animator = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(game.getCurrentStatus() == SnakeGame.Status.IN_GAME) {
                    game.move(game.getLastMove());
                    for (int i = 0; i < game.getSnake().size(); i++) {
                        snake.get(i).setCenterX(game.getSnake().get(i).getY() * 50 + 25);
                        snake.get(i).setCenterY(game.getSnake().get(i).getX() * 50 + 25);
                        if(game.getSnake().size() > snake.size()) {
                            Circle newPieace = new Circle(25);
                            snake.add(newPieace);
                            route.getChildren().add(newPieace);
                        }
                    }
                    fruit.setCenterX(game.getFruit().getY() * 50 + 25);
                    fruit.setCenterY(game.getFruit().getX() * 50 + 25);
                    try {
                        TimeUnit.MILLISECONDS.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        animator.start();
    }

    public void handle(KeyEvent arg0) {
            if (arg0.getCode() == KeyCode.UP) {
                game.move(SnakeGame.Move.TOP);
            } else if (arg0.getCode() == KeyCode.DOWN) {
                game.move(SnakeGame.Move.BOTTOM);
            } else if (arg0.getCode() == KeyCode.LEFT) {
                game.move(SnakeGame.Move.LEFT);
            } else if (arg0.getCode() == KeyCode.RIGHT) {
                game.move(SnakeGame.Move.RIGHT);
            }
    }

    private void createBackground(){
        System.out.println(bgPath);
        System.out.println(createFilePath(bgPath));
        File file = new File(createFilePath(bgPath));
        javafx.scene.image.Image img = new Image(file.getAbsoluteFile().toURI().toString());
        BackgroundImage bgImg = new BackgroundImage(img,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(bgImg));
    }

    private String createFilePath(String path){
        String finalPath = new File("").getAbsolutePath();
        return finalPath + path;
    }
}
