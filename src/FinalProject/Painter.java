package FinalProject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Painter {

    static final int PIXEL_SIZE = 10;

    public static void paint(GameArena gameArena, GraphicsContext gc) {

        // Paint the GameArena
        gc.setFill(GameArena.COLOR);
        gc.fillRect(0, 0, gameArena.getWidth(), gameArena.getHeight());

        // Paint the Food
        gc.setFill(Food.COLOR);
        paintCoordinates(gameArena.getFood().getCoordinates(), gc);

        // Paint the Snake
        Snake snake = gameArena.getSnake();
        gc.setFill(Snake.COLOR);
        snake.getAllCoordinates().forEach(Coordinates -> paintCoordinates(Coordinates, gc));
        if (!snake.isSafe()) {
            gc.setFill(Snake.DEAD);
            paintCoordinates(snake.getHead(), gc);

        }

        // The score
        gc.setFill(Color.CRIMSON);
        GameArena.setGameHighScore(snake.getGrowthScore());
        gc.fillText("Score : " + snake.getGrowthScore() + " : HighScore: " + GameArena.getGameHighScore(), 10, 490);
    }

    private static void paintCoordinates(Coordinates Coordinates, GraphicsContext gc) {
        gc.fillRect(Coordinates.getX() * PIXEL_SIZE, Coordinates.getY() * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
    }

    public static void paintResetMessage(GraphicsContext gc) {
        gc.setFill(Color.DARKORCHID);
        gc.fillText("Hit RETURN to reset.", 10, 20);
    }
}