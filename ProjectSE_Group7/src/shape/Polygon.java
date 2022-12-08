package shape;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author group7
 */
public class Polygon extends javafx.scene.shape.Polygon {

    public Polygon() {
        super();
    }

    public Polygon(double xStartPoint, double yStartPoint) {
        super(xStartPoint, yStartPoint);
    }

    /**
     * Updates the polygon coordinates by adding x and y.
     *
     * @param x The value to add to polygon x coordinate.
     * @param y The value to add to polygon y coordinate.
     */
    public void moveOf(double x, double y) {
        int i = 0;
        ObservableList<Double> points = this.getPoints();

        for (double point : points) {
            if (i % 2 == 0) {
                points.set(i, point + x);
            } else {
                points.set(i, point + y);
            }
            i++;
        }
    }

    /**
     * Returns the list of points the polygon is composed.
     *
     * @return The list of points that compose the polygon.
     */
    public ObservableList<Double> getPolygonPoints() {
        return this.getPoints();
    }
    
    public void setPolygonPoints(ArrayList<Double> points) {
        this.getPoints().setAll(points);
    }

    /**
     * Adds the points passed as argument to the polygon points array.
     *
     * @param points The points to add to the polygon.
     */
    public void addPoints(double... points) {
        for (double point : points) {
            this.getPoints().add(point);
        }
    }

    /**
     * Sets the value of the property stroke (outline color of the polygon).
     *
     * @param outlineColor Outline color of the polygon.
     */
    public void setOutlineColor(Paint outlineColor) {
        super.setStroke(outlineColor);
    }

    /**
     * Returns the value of the property stroke (outline color of the polygon).
     *
     * @return The value of the Stroke property.
     */
    public Paint getOutlineColor() {
        return super.getStroke();
    }

    /**
     * Sets the value of the property fill (internal color of the polygon).
     *
     * @param fillColor Internal color of the ellipse.
     */
    public void setFillColor(Paint fillColor) {
        super.setFill(fillColor);
    }

    /**
     * Returns the value of the property fill (internal color of the polygon).
     *
     * @return The value of the Fill property.
     */
    public Paint getFillColor() {
        return super.getFill();
    }

    /**
     * Returns a string representing the polygon object.
     *
     * @return A string representing the polygon object.
     */
    @Override
    public String toString() {
        String outlineColor = toHexString((Color) this.getOutlineColor());
        String fillColor = toHexString((Color) this.getFillColor());
        String listOfPoints = "";
        for (double point : this.getPoints()) {
            listOfPoints += point + " ";
        }
        listOfPoints = listOfPoints.trim();
        double scaleX = this.getScaleX();
        double scaleY = this.getScaleY();
        double rotation = this.getRotate();

        // polygon;[listOfPoints];outlineColor;fillColor;scaleX;scaleY;rotation
        return "polygon;" + listOfPoints + ";" + outlineColor + ";" + fillColor + ";" + scaleX + ";" + scaleY + ";" + rotation;
    }

    private static String toHexString(Color color) {
        int r = ((int) Math.round(color.getRed() * 255));
        int g = ((int) Math.round(color.getGreen() * 255));
        int b = ((int) Math.round(color.getBlue() * 255));

        return String.format("#%02x%02x%02x", r, g, b).toLowerCase();

    }
}
