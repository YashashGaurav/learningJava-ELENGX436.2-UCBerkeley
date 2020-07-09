package FinalProject;

import javafx.scene.paint.Color;

import java.util.Random;

public class GameArena {

    public static final Color COLOR = Color.rgb(167, 217, 72);
    private static int gameHighScore;

    private final int pixelSize; // The size of each pixel
    private final int cols; // The number of columns
    private final int rows; // The number of rows

    private Snake snake;
    private Food food;

    public GameArena(final int cols, final int rows, final int pixelSize) {
        this.pixelSize = pixelSize;
        this.rows = rows;
        this.cols = cols;

        // initialize the snake at the centre of the screen
        snake = new Snake(this, new Coordinates(rows / 2, cols / 2));

        // put the food at a random location
        food = new Food(getNewFoodCoordinates());

        // Lets start the game!
        snake.setDirectionRight();
        setGameHighScore(snake.getGrowthScore());
    }

    private Coordinates getNewFoodCoordinates() {
        Random random = new Random();
        Coordinates newFoodCoordinates;
        do {
            newFoodCoordinates = new Coordinates(random.nextInt(rows), random.nextInt(cols));
        } while (snake.getAllCoordinates().contains(newFoodCoordinates));
        return newFoodCoordinates;
    }

    /**
     * This method is called in every cycle of execution.
     */
    public void update() {
        if (food.getCoordinates().equals(snake.getHead())) {
            snake.extend();
            food.setCoordinates(getNewFoodCoordinates());
        } else {
            snake.move();
        }
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public double getWidth() {
        return rows * pixelSize;
    }

    public double getHeight() {
        return cols * pixelSize;
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }

    public static void setGameHighScore(int score) {
        if (gameHighScore < score) {
            gameHighScore = score;
        }
    }

    public static int getGameHighScore() {
        return gameHighScore;
    }
}