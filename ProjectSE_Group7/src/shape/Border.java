package shape;

import javafx.scene.paint.Color;

/**
 *
 * @author group7s
 */
public class Border extends Rectangle{
    
    /**
     * Constructor of Border class
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
     * @return 
     */
    @Override
    public String toString() {
        Color outlineColor = (Color) this.getOutlineColor();
        Color fillColor = (Color) this.getFillColor();
        return "border;" + this.getRectangleX() + ";" + this.getRectangleY() + ";" + this.getRectangleWidth() + ";" 
                + this.getRectangleHeight() + ";" + toHexString(outlineColor) + ";" + toHexString(fillColor);
    }
    
    /**
     * Function that returns a hexadecimal representation of a Color object
     * @param color Input Color object.
     * @return 
     */
    private static String toHexString(Color color) {
        int r = ((int) Math.round(color.getRed()     * 255));
        int g = ((int) Math.round(color.getGreen()   * 255));
        int b = ((int) Math.round(color.getBlue()    * 255));

        return String.format("#%02x%02x%02x", r, g, b).toLowerCase(); 
    }
}
