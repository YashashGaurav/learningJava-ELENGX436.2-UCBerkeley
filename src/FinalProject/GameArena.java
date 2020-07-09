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

    /**
     * Initialize Game Arena
     * 
     * @param cols      Columns size in pixelSize of the game arena
     * @param rows      Rows size in pixelSize of the game arena
     * @param pixelSize the unit of drawing in the Game Arena
     */
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

    /**
     * Gets new coordinates for new food
     * 
     * @return Coordinates of the new food
     */
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

    /**
     * Column size getter
     * 
     * @return Column size
     */
    public int getCols() {
        return cols;
    }

    /**
     * Row size getter
     * 
     * @return Row size
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets width of the game arena
     * 
     * @return Row size * unit of size
     */
    public double getWidth() {
        return rows * pixelSize;
    }

    /**
     * Gets height of the game arena
     * 
     * @return Column size * unit of size
     */
    public double getHeight() {
        return cols * pixelSize;
    }

    /**
     * Snake getter
     * 
     * @return Snake object
     */
    public Snake getSnake() {
        return snake;
    }

    /**
     * Food getter
     * 
     * @return Food object
     */
    public Food getFood() {
        return food;
    }

    /**
     * Sets a game's high score
     * 
     * @param score candidate score to be set as high-score.
     */
    public static void setGameHighScore(int score) {
        if (gameHighScore < score) {
            gameHighScore = score;
        }
    }

    /**
     * High Score getter
     * 
     * @return Latest high-score.
     */
    public static int getGameHighScore() {
        return gameHighScore;
    }
}