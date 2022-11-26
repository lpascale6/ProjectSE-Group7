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
        this.setColor(color);
    }

    /**
     * method to set the starting X coordinate for the line segment.
     *
     * @param startX the new starting X coordinate.
     */
    public void setStartingX(double startX) {
        super.setStartX(startX);

    }

    /**
     * method to set the starting Y coordinate for the line segment.
     *
     * @param startY the new starting Y coordinate.
     */
    public void setStartingY(double startY) {
        super.setStartY(startY);
    }

    /**
     * method to set the ending X coordinate for the line segment.
     *
     * @param endX the new ending X coordinate.
     */
    public void setEndingX(double endX) {
        super.setEndX(endX);
    }

    /**
     * method to set the ending Y coordinate for the line segment.
     *
     * @param endY the new ending Y coordinate.
     */
    public void setEndingY(double endY) {
        super.setEndY(endY);
    }

    /**
     * method to get the starting X coordinate for the line segment.
     *
     * @return the current starting X coordinate.
     */
    public double getStartingX() {
        return super.getStartX();
    }

    /**
     * method to get the starting Y coordinate for the line segment.
     *
     * @return the current starting Y coordinate.
     */
    public double getStartingY() {
        return super.getStartY();
    }

    /**
     * method to get the ending X coordinate for the line segment.
     *
     * @return the current ending X coordinate.
     */
    public double getEndingX() {
        return super.getEndX();
    }

    /**
     * method to get the ending Y coordinate for the line segment.
     *
     * @return the current ending Y coordinate.
     */
    public double getEndingY() {
        return super.getEndY();
    }

    /**
     * method to set a new color for the line segment.
     *
     * @param color the new color.
     */
    public void setColor(Paint color) {
        super.setStroke(color);
    }

    /**
     * method to get the current color for the line segment.
     *
     * @return the current color.
     */
    public Paint getColor() {
        return super.getStroke();
    }

    /**
     * method that returns a String representation of a line segment.
     *
     * @return the String representation of this line segment.
     */
    @Override
    public String toString() {
        String representation = "line;";
        representation += this.getStartingX() + ";";
        representation += this.getStartingY() + ";";
        representation += this.getEndingX() + ";";
        representation += this.getEndingY() + ";";
        
        representation += toHexString((Color)this.getColor());

        return representation;
    }
    
    /**
     * method that takes a Color and returns a string with the corresponding Hex representation.
     * @param color the color to get the hex representation for.
     * @return 
     */
     private static String toHexString(Color color) {
        int r = ((int) Math.round(color.getRed()     * 255));
        int g = ((int) Math.round(color.getGreen()   * 255));
        int b = ((int) Math.round(color.getBlue()    * 255));

        return String.format("#%02x%02x%02x", r, g, b).toLowerCase(); 
    }

}