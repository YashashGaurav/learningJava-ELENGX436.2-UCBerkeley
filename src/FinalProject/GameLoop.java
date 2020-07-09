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

    public void stop() {
        running = false;
    }

    public boolean isKeyPressed() {
        return isKeyPressed;
    }

    public void setIsKeyPressed() {
        isKeyPressed = true;
    }

    public void resume() {
        paused = false;
    }

    public void pause() {
        paused = true;
    }

    public boolean isPaused() {
        return paused;
    }

    public int getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }
}