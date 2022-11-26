package shape;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author group7
 */

public class Rectangle extends javafx.scene.shape.Rectangle{
    
    
    /**
     * Constructor of Rectangle class
     * @param x X coordinate of the upper-left corner of the rectangle
     * @param y Y coordinate of the upper-left corner of the rectangle
     * @param width Width of the rectangle
     * @param height Height of the rectangle
     */
    public Rectangle(double x, double y, double width, double height) {
        super(x, y, width, height);
    }
    
    /**
     * Empty constructor of Rectangle class
     */
    public Rectangle(){
        super();
    }

    /**
     * Constructor of Rectangle class with fill and outer colors as parameters
     * @param x X coordinate of the upper-left corner of the rectangle
     * @param y Y coordinate of the upper-left corner of the rectangle
     * @param width Width of the rectangle
     * @param height Height of the rectangle
     * @param fill Fill color of the rectangle
     * @param outer Outer color of the rectangle
     */
    public Rectangle(double x, double y, double width, double height, Paint fill, Paint outer) {
        super(x, y, width, height);
        this.setFill(fill);
        this.setStroke(outer);   
    }

    /**
     * Method to get the X coordinate of the upper-left corner of the rectangle
     * @return 
     */
    public double getX_() {
        return this.getX();
    }

    /**
     * Method to set the X coordinate of the upper-left corner of the rectangle
     * @param x New X coordinate of the upper-left corner of the rectangle
     */
    public void setX_(double x) {
        this.setX(x);
    }


    /**
     * Method to get the Y coordinate of the upper-left corner of the rectangle
     * @return 
     */
    public double getY_() {
        return this.getY();
    }


    /**
     * Method to set the Y coordinate of the upper-left corner of the rectangle
     * @param y New Y coordinate of the upper-left corner of the rectangle
     */
    public void setY_(double y) {
        this.setY(y);
    }

    /**
     * Method to get the width of the rectangle
     * @return 
     */
    public double getWidth_() {
        return this.getWidth();
    }

    /**
     * Method to set the width of the rectangle
     * @param width New width of the rectangle
     */
    public void setWidth_(double width) {
        this.setWidth(width);
    }

    /**
     * Method to get the height of the rectangle
     * @return 
     */
    public double getHeight_() {
        return this.getHeight();
    }
    
    
    /**
     * Method to set the height of the rectangle.
     * @param height New height of the rectangle.
     */
    public void setHeight_(double height) {
        this.setHeight(height);
    }
    
    /**
     * Method to set the fill color of the rectangle
     * @param fill New fill color of the rectangle
     */
    public void setFillColor(Paint fill){
        this.setFill(fill);
    }
    
    /**
     * Method to get the fill color of the rectangle
     * @return 
     */
    public Paint getFillColor(){
        return this.getFill();
    }
    
    /**
     * Method to set the stroke color of the rectangle
     * @param outline New outline color of the rectangle
     */
    public void setOutlineColor(Paint outline){
        this.setStroke(outline);
    }
    
    /**
     * Method to get the outline color of the rectangle
     * @return 
     */
    public Paint getOutlineColor(){
        return this.getStroke();
    }

    /**
     * It returns the string representation of the rectangle
     * @return 
     */
    @Override
    public String toString() {
        Color outlineColor = (Color) this.getOutlineColor();
        Color fillColor = (Color) this.getFillColor();
        return "rectangle;" + this.getX_() + ";" + this.getY_() + ";" + this.getWidth_() + ";" 
                + this.getHeight_() + ";" + toHexString(outlineColor) + ";" + toHexString(fillColor);
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
