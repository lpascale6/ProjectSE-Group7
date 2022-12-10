package shape;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author group7
 */
public class Line extends javafx.scene.shape.Line {

    /**
     * method to create an empty line segment.
     */
    public Line() {
        super();
    }

    /**
     * method to create a line segment with starting and ending X, Y
     * coordinates.
     *
     * @param startX the starting X coordinate.
     * @param startY the starting Y coordinate.
     * @param endX the ending X coordinate.
     * @param endY the ending Y coordinate.
     */
    public Line(double startX, double startY, double endX, double endY) {
        super(startX, startY, endX, endY);
    }

    /**
     * method to create a line segment with starting and ending X,Y coordinates
     * and chosen color.
     *
     * @param startX the starting X coordinate.
     * @param startY the starting Y coordinate.
     * @param endX the ending X coordinate.
     * @param endY the ending Y coordinate.
     * @param color the chosen color.
     */
    public Line(double startX, double startY, double endX, double endY, Paint color) {
        super(startX, startY, endX, endY);
        this.setLineColor(color);
    }

    /**
     * method to set the starting X coordinate for the line segment.
     *
     * @param startX the new starting X coordinate.
     */
    public void setLineStartingX(double startX) {
        super.setStartX(startX);

    }

    /**
     * method to set the starting Y coordinate for the line segment.
     *
     * @param startY the new starting Y coordinate.
     */
    public void setLineStartingY(double startY) {
        super.setStartY(startY);
    }

    /**
     * method to set the ending X coordinate for the line segment.
     *
     * @param endX the new ending X coordinate.
     */
    public void setLineEndingX(double endX) {
        super.setEndX(endX);
    }

    /**
     * method to set the ending Y coordinate for the line segment.
     *
     * @param endY the new ending Y coordinate.
     */
    public void setLineEndingY(double endY) {
        super.setEndY(endY);
    }

    /**
     * method to get the starting X coordinate for the line segment.
     *
     * @return the current starting X coordinate.
     */
    public double getLineStartingX() {
        return super.getStartX();
    }

    /**
     * method to get the starting Y coordinate for the line segment.
     *
     * @return the current starting Y coordinate.
     */
    public double getLineStartingY() {
        return super.getStartY();
    }

    /**
     * method to get the ending X coordinate for the line segment.
     *
     * @return the current ending X coordinate.
     */
    public double getLineEndingX() {
        return super.getEndX();
    }

    /**
     * method to get the ending Y coordinate for the line segment.
     *
     * @return the current ending Y coordinate.
     */
    public double getLineEndingY() {
        return super.getEndY();
    }

    /**
     * method to set a new color for the line segment.
     *
     * @param color the new color.
     */
    public void setLineColor(Paint color) {
        super.setStroke(color);
    }

    /**
     * method to get the current color for the line segment.
     *
     * @return the current color.
     */
    public Paint getLineColor() {
        return super.getStroke();
    }

    /**
     * Updates the line coordinates by adding x and y.
     *
     * @param x The value to add to line x coordinates.
     * @param y The value to add to line y coordinates.
     */
    public void moveOf(double x, double y) {
        this.setLineStartingX(this.getLineStartingX() + x);
        this.setLineStartingY(this.getLineStartingY() + y);
        this.setLineEndingX(this.getLineEndingX() + x);
        this.setLineEndingY(this.getLineEndingY() + y);
    }

    /**
     * method that returns a String representation of a line segment.
     *
     * @return the String representation of this line segment.
     */
    @Override
    public String toString() {
        String representation = "line;";
        representation += this.getLineStartingX() + ";";
        representation += this.getLineStartingY() + ";";
        representation += this.getLineEndingX() + ";";
        representation += this.getLineEndingY() + ";";

        representation += this.getLineColor() + ";";

        representation += this.getScaleX() + ";";
        representation += this.getScaleY() + ";";
        representation += this.getRotate();

        return representation;
    }
}
