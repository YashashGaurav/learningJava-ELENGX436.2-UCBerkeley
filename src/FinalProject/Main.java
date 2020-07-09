package FinalProject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int PIXEL_SIZE = 10;
    private static final int COLS = 50;
    private static final int ROWS = 50;

    private GameLoop gameLoop;
    private GameArena gameArena;
    private GraphicsContext graphicsContext;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(COLS * PIXEL_SIZE, ROWS * PIXEL_SIZE);
        graphicsContext = canvas.getGraphicsContext2D();

        canvas.setFocusTraversable(true);
        bindKeys(canvas);

        reset();

        root.getChildren().add(canvas);

        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setTitle("The Humble Snake Game");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setScene(scene);
        primaryStage.show();

        (new Thread(gameLoop)).start();
    }

    /**
     * Binds the arrow keys to snake movement
     * 
     * @param canvas the application canvas
     */
    private void bindKeys(Canvas canvas) {
        canvas.setOnKeyPressed(e -> {
            Snake snake = gameArena.getSnake();
            if (gameLoop.isKeyPressed()) {
                return;
            }
            switch (e.getCode()) {
                case UP:
                    snake.setDirectionUp();
                    break;
                case DOWN:
                    snake.setDirectionDown();
                    break;
                case LEFT:
                    snake.setDirectionLeft();
                    break;
                case RIGHT:
                    snake.setDirectionRight();
                    break;
                case ENTER:
                    if (gameLoop.isPaused()) {
                        reset();
                        (new Thread(gameLoop)).start();
                    }
                default:
                    break;
            }
        });
    }

    /**
     * Resets everything anew.
     */
    private void reset() {
        gameArena = new GameArena(COLS, ROWS, PIXEL_SIZE);
        gameLoop = new GameLoop(gameArena, graphicsContext);
        Painter.paint(gameArena, graphicsContext);
    }
}