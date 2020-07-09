package FinalProject;

import javafx.scene.paint.Color;

public class Food {

    public static final Color COLOR = Color.rgb(244, 55, 6);

    private Coordinates coordinates;

    /**
     * Creates a new food object
     * 
     * @param coordinates Coordinates of the new food
     */
    Food(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Coordinates object of the food
     * 
     * @return Coordinates object of the food
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Food's coordinates setter
     * 
     * @param coordinates Coordinates to set to.
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}