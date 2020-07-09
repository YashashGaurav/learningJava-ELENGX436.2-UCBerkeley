package FinalProject;

public class Coordinates {

    private final int x; // The X coordinate
    private final int y; // The Y coordinate

    Coordinates(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return The X coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * @return The Y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * @param dx The change in x.
     * @param dy The change in y.
     * @return A new Point which is the result of translation of this point.
     */
    public Coordinates translate(int dx, int dy) {
        return new Coordinates(x + dx, y + dy);
    }

    /**
     * @param other The "other" point to compare against.
     * @return {@code true} if the other Object is an instance of Point and has the
     *         same coordinates.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Coordinates))
            return false;
        Coordinates coordinates = (Coordinates) other;
        return x == coordinates.x & y == coordinates.y;
    }

    public String toString() {
        return x + ", " + y;
    }
}