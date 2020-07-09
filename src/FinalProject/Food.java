package FinalProject;

import javafx.scene.paint.Color;

public class Food {

    public static final Color COLOR = Color.rgb(244, 55, 6);

    private Coordinates coordinates;

    Food(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}