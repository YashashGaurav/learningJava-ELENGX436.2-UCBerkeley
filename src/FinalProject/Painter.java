package FinalProject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Painter {

    static final int PIXEL_SIZE = 10;

    /**
     * Paint the whole game
     * 
     * @param gameArena       Game Arena to be painted
     * @param graphicsContext Graphics Context fo the application
     */
    public static void paint(GameArena gameArena, GraphicsContext graphicsContext) {

        // Paint the GameArena
        graphicsContext.setFill(GameArena.COLOR);
        graphicsContext.fillRect(0, 0, gameArena.getWidth(), gameArena.getHeight());

        // Paint the Food
        graphicsContext.setFill(Food.COLOR);
        paintCoordinates(gameArena.getFood().getCoordinates(), graphicsContext);

        // Paint the Snake
        Snake snake = gameArena.getSnake();
        graphicsContext.setFill(Snake.COLOR);
        snake.getAllCoordinates().forEach(Coordinates -> paintCoordinates(Coordinates, graphicsContext));
        if (!snake.isSafe()) {
            graphicsContext.setFill(Snake.DEAD);
            paintCoordinates(snake.getHead(), graphicsContext);
        }

        // The score
        graphicsContext.setFill(Color.CRIMSON);
        GameArena.setGameHighScore(snake.getGrowthScore());
        graphicsContext.fillText("Score : " + snake.getGrowthScore() + " : HighScore: " + GameArena.getGameHighScore(),
                10, 490);
    }

    /**
     * Paint the passed coordinates
     * 
     * @param Coordinates     Coordinates to be painted
     * @param graphicsContext Graphics context of the application
     */
    private static void paintCoordinates(Coordinates Coordinates, GraphicsContext graphicsContext) {
        graphicsContext.fillRect(Coordinates.getX() * PIXEL_SIZE, Coordinates.getY() * PIXEL_SIZE, PIXEL_SIZE,
                PIXEL_SIZE);
    }

    /**
     * Paints the Reset Message
     * 
     * @param graphicsContext Graphics Context of the application
     */
    public static void paintResetMessage(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.DARKORCHID);
        graphicsContext.fillText("Hit RETURN to reset.", 10, 20);
    }
}