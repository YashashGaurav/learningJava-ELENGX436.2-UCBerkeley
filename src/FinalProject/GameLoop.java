package FinalProject;

import javafx.scene.canvas.GraphicsContext;

public class GameLoop implements Runnable {

    private final GameArena gameArena;
    private final GraphicsContext graphicsContext;
    private final float interval;
    private int frameRate;
    private boolean running;
    private boolean paused;
    private boolean isKeyPressed;

    /**
     * Initializes the looper class for the game
     * 
     * @param gameArena       the game stage/arena itself
     * @param graphicsContext GraphicsContext of the application
     */
    public GameLoop(final GameArena gameArena, final GraphicsContext graphicsContext) {
        this.gameArena = gameArena;
        this.graphicsContext = graphicsContext;
        frameRate = 20;
        interval = 1000.0f / frameRate; // 1000 ms in a second

        running = true;
        paused = false;
        isKeyPressed = false;
    }

    @Override
    /**
     * Runnable's run method
     */
    public void run() {
        while (running && !paused) {
            // Time the update and paint calls
            float time = System.currentTimeMillis();

            isKeyPressed = false;
            gameArena.update();
            Painter.paint(gameArena, graphicsContext);

            if (!gameArena.getSnake().isSafe()) {
                pause();
                Painter.paintResetMessage(graphicsContext);
                break;
            }

            time = System.currentTimeMillis() - time;

            // Adjust the timing correctly
            if (time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException ignore) {
                }
            }
        }
    }

    /**
     * stops the Game Loop
     */
    public void stop() {
        running = false;
    }

    /**
     * @return if any key is pressed
     */
    public boolean isKeyPressed() {
        return isKeyPressed;
    }

    /**
     * Sets KeyPressed state state tracker to true
     */
    public void setIsKeyPressed() {
        isKeyPressed = true;
    }

    /**
     * Resumes a paused game.
     */
    public void resume() {
        paused = false;
    }

    /**
     * Pauses the game.
     */
    public void pause() {
        paused = true;
    }

    /**
     * @return If the game is paused.
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * Exposes the frameRate of the game loop
     * 
     * @return Frame Rate
     */
    public int getFrameRate() {
        return frameRate;
    }

    /**
     * Set a different frame rate
     * 
     * @param frameRate Frame rate to set to.
     */
    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }
}