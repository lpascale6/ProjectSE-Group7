package shape;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author group7
 */
public class Rectangle extends javafx.scene.shape.Rectangle {

    /**
     * Constructor of Rectangle class
     *
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
    public Rectangle() {
        super();
    }

    /**
     * Constructor of Rectangle class with fill and outer colors as parameters
     *
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
     *
     * @return
     */
    public double getRectangleX() {
        return this.getX();
    }

    /**
     * Method to set the X coordinate of the upper-left corner of the rectangle
     *
     * @param x New X coordinate of the upper-left corner of the rectangle
     */
    public void setRectangleX(double x) {
        this.setX(x);
    }

    /**
     * Method to get the Y coordinate of the upper-left corner of the rectangle
     *
     * @return
     */
    public double getRectangleY() {
        return this.getY();
    }

    /**
     * Method to set the Y coordinate of the upper-left corner of the rectangle
     *
     * @param y New Y coordinate of the upper-left corner of the rectangle
     */
    public void setRectangleY(double y) {
        this.setY(y);
    }

    /**
     * Method to get the width of the rectangle
     *
     * @return
     */
    public double getRectangleWidth() {
        return this.getWidth();
    }

    /**
     * Method to set the width of the rectangle
     *
     * @param width New width of the rectangle
     */
    public void setRectangleWidth(double width) {
        this.setWidth(width);
    }

    /**
     * Method to get the height of the rectangle
     *
     * @return
     */
    public double getRectangleHeight() {
        return this.getHeight();
    }

    /**
     * Method to set the height of the rectangle.
     *
     * @param height New height of the rectangle.
     */
    public void setRectangleHeight(double height) {
        this.setHeight(height);
    }

    /**
     * Method to set the fill color of the rectangle
     *
     * @param fill New fill color of the rectangle
     */
    public void setFillColor(Paint fill) {
        this.setFill(fill);
    }

    /**
     * Method to get the fill color of the rectangle
     *
     * @return
     */
    public Paint getFillColor() {
        return this.getFill();
    }

    /**
     * Method to set the stroke color of the rectangle
     *
     * @param outline New outline color of the rectangle
     */
    public void setOutlineColor(Paint outline) {
        this.setStroke(outline);
    }

    /**
     * Method to get the outline color of the rectangle
     *
     * @return
     */
    public Paint getOutlineColor() {
        return this.getStroke();
    }

    /**
     * Updates the rectangle coordinates by adding x and y.
     *
     * @param x The value to add to rectangle x coordinate.
     * @param y The value to add to rectangle y coordinate.
     */
    public void moveOf(double x, double y) {
        this.setRectangleX(this.getRectangleX() + x);
        this.setRectangleY(this.getRectangleY() + y);
    }

    /**
     * It returns the string representation of the rectangle
     *
     * @return
     */
    @Override
    public String toString() {
        Color outlineColor = (Color) this.getOutlineColor();
        Color fillColor = (Color) this.getFillColor();
        return "rectangle;" + this.getRectangleX() + ";" + this.getRectangleY() + ";" + this.getRectangleWidth() + ";"
                + this.getRectangleHeight() + ";" + outlineColor + ";" + fillColor + ";"
                + this.getScaleX() + ";" + this.getScaleY() + ";" + this.getRotate();
    }
}
