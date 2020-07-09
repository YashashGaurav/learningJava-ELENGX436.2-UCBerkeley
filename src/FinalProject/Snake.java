package FinalProject;

import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

public class Snake {

    public static final Color COLOR = Color.rgb(80, 118, 249);
    public static final Color DEAD = Color.BLACK;
    public static final int DEFAULT_INITIAL_SIZE = 8;

    private GameArena gameArena;
    private int length;
    private boolean isSafe;
    private List<Coordinates> allCoordinates;
    private Coordinates headCoordinates;
    private int xVelocity;
    private int yVelocity;

    /**
     * The constructor of the snake. It takes the head coordinates and the Game
     * Arena that it lives (and dies) in.
     * 
     * @param gameArena
     * @param initialHeadCoordinates
     */
    public Snake(GameArena gameArena, Coordinates initialHeadCoordinates) {
        this.gameArena = gameArena;
        setSnake(initialHeadCoordinates);
    }

    private void setSnake(Coordinates initialHeadCoordinates) {
        this.allCoordinates = new LinkedList<>();
        this.headCoordinates = new Coordinates(initialHeadCoordinates.getX() - DEFAULT_INITIAL_SIZE,
                initialHeadCoordinates.getY());
        this.allCoordinates.add(headCoordinates);

        this.length = 1;
        for (int i = 1; i < DEFAULT_INITIAL_SIZE; i++) {
            growTo(this.headCoordinates.translate(1, 0));
        }

        this.isSafe = true;
        this.xVelocity = 0;
        this.yVelocity = 0;
    }

    /**
     * This method is called after food has been consumed. It increases the length
     * of the snake by one.
     *
     * @param coordinates The Coordinates where the food was and would now be the
     *                    new location for the head.
     */
    private void growTo(Coordinates coordinates) {
        length++;
        checkAndAdd(coordinates);
    }

    /**
     * Called during every update. It gets rid of the oldest coordinates and adds
     * the given position.
     *
     * @param coordinates The new pixel coordinates to add.
     */
    private void shiftTo(Coordinates coordinates) {
        // The head goes to the new location
        checkAndAdd(coordinates);
        // The last/oldest position is dropped
        allCoordinates.remove(0);
    }

    /**
     * Checks for an intersection and marks the "safe" flag accordingly.
     *
     * @param coordinate The new Point to move to.
     */
    private void checkAndAdd(Coordinates coordinates) {
        coordinates = wrap(coordinates);
        isSafe &= !allCoordinates.contains(coordinates);
        allCoordinates.add(coordinates);
        headCoordinates = coordinates;
    }

    /**
     * Checks and applies if the snake coordinates need to wrap to the other side of
     * the Game Arena
     * 
     * @param coordinates Coordinates to check for wrapping
     * @return Wrapped coordinates
     */
    private Coordinates wrap(Coordinates coordinates) {
        int x = coordinates.getX();
        int y = coordinates.getY();
        if (x >= gameArena.getRows())
            x = 0;
        if (y >= gameArena.getCols())
            y = 0;
        if (x < 0)
            x = gameArena.getRows() - 1;
        if (y < 0)
            y = gameArena.getCols() - 1;
        return new Coordinates(x, y);
    }

    /**
     * @return All the coordinates occupied by the snake.
     */
    public List<Coordinates> getAllCoordinates() {
        return allCoordinates;
    }

    /**
     * @return {@code true} if the Snake hasn't run into itself yet.
     */
    public boolean isSafe() {
        return isSafe || length == DEFAULT_INITIAL_SIZE;
    }

    /**
     * @return The location of the head of the Snake.
     */
    public Coordinates getHead() {
        return headCoordinates;
    }

    /**
     * @return if the snake is not moving
     */
    private boolean isStill() {
        return xVelocity == 0 & yVelocity == 0;
    }

    /**
     * Make the snake move one square in it's current direction.
     */
    public void move() {
        if (!isStill()) {
            shiftTo(headCoordinates.translate(xVelocity, yVelocity));
        }
    }

    /**
     * Make the snake extend/grow to the square where it's headed.
     */
    public void extend() {
        if (!isStill()) {
            growTo(headCoordinates.translate(xVelocity, yVelocity));
        }
    }

    /**
     * Sets snakes direction to UP
     */
    public void setDirectionUp() {
        if (yVelocity == 1 && length > 1)
            return;
        xVelocity = 0;
        yVelocity = -1;
    }

    /**
     * Sets snakes direction to DOWN
     */
    public void setDirectionDown() {
        if (yVelocity == -1 && length > 1)
            return;
        xVelocity = 0;
        yVelocity = 1;
    }

    /**
     * Sets snakes direction to LEFT
     */
    public void setDirectionLeft() {
        if (xVelocity == 1 && length > 1)
            return;
        xVelocity = -1;
        yVelocity = 0;
    }

    /**
     * Sets snakes direction to RIGHT
     */
    public void setDirectionRight() {
        if (xVelocity == -1 && length > 1)
            return;
        xVelocity = 1;
        yVelocity = 0;
    }

    /**
     * @return length difference since game started * 100
     */
    public int getGrowthScore() {
        return (length - DEFAULT_INITIAL_SIZE) * 100;
    }
}
