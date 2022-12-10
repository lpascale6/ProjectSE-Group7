package shape;

import javafx.scene.paint.Color;

/**
 *
 * @author group7
 */
public class Border extends Rectangle {

    /**
     * Constructor of Border class
     *
     * @param x X coordinate of the upper-left corner of the rectangle
     * @param y Y coordinate of the upper-left corner of the rectangle
     * @param width Width of the rectangle
     * @param height Height of the rectangle
     */
    public Border(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    /**
     * It returns the string representation of the border
     *
     * @return
     */
    @Override
    public String toString() {
        Color outlineColor = (Color) this.getOutlineColor();
        Color fillColor = (Color) this.getFillColor();
        return "border;" + this.getRectangleX() + ";" + this.getRectangleY() + ";" + this.getRectangleWidth() + ";"
                + this.getRectangleHeight() + ";" + outlineColor + ";" + fillColor;
    }
}
