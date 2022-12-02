package shape;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author group7
 */
public class Ellipse extends javafx.scene.shape.Ellipse {

    /**
     * Creates an empty instance of Ellipse.
     */
    public Ellipse() {
        super();
    }

    /**
     * Creates an instance of Ellipse of the given size.
     *
     * @param width width of the ellipse
     * @param height height of the ellipse
     */
    public Ellipse(double width, double height) {
        super(width, height);
    }

    /**
     * Creates an instance of Ellipse of the given position and size.
     *
     * @param hPosition the x coordinate of the center of the ellipse
     * @param vPosition the y coordinate of the center of the ellipse
     * @param width width of the ellipse
     * @param height height of the ellipse
     */
    public Ellipse(double hPosition, double vPosition, double width, double height) {
        super(hPosition, vPosition, width, height);
    }

    /**
     * Creates an instance of Ellipse of the given position, size and outline
     * color.
     *
     * @param hPosition the x coordinate of the center of the ellipse
     * @param vPosition the y coordinate of the center of the ellipse
     * @param width width of the ellipse
     * @param height height of the ellipse
     * @param outlineColor the outline color of the shape
     */
    public Ellipse(double hPosition, double vPosition, double width, double height, Paint outlineColor) {
        super(hPosition, vPosition, width, height);
        this.setStroke(outlineColor);
    }

    /**
     * Creates an instance of Ellipse of the given position, size, outline and
     * fill colors.
     *
     * @param hPosition the x coordinate of the center of the ellipse
     * @param vPosition the y coordinate of the center of the ellipse
     * @param width width of the ellipse
     * @param height height of the ellipse
     * @param outlineColor the outline color of the shape
     * @param fillColor the internal color of the shape
     */
    public Ellipse(double hPosition, double vPosition, double width, double height, Paint outlineColor, Paint fillColor) {
        super(hPosition, vPosition, width, height);
        this.setStroke(outlineColor);
        this.setFill(fillColor);
    }

    /**
     * Sets the value of the property centerX (Horizontal position of the
     * center).
     *
     * @param hPosition the x coordinate of the center of the ellipse
     */
    public void setEllipseCenterX(double hPosition) {
        super.setCenterX(hPosition);
    }

    /**
     * Returns the value of the property centerX (Horizontal position of the
     * center).
     *
     * @return
     */
    public double getEllipseCenterX() {
        return super.getCenterX();
    }

    /**
     * Sets the value of the property centerY (Vertical position of the center).
     *
     * @param vPosition the y coordinate of the center of the ellipse
     */
    public void setEllipseCenterY(double vPosition) {
        super.setCenterY(vPosition);
    }

    /**
     * Returns the value of the property centerY (Vertical position of the
     * center).
     *
     * @return
     */
    public double getEllipseCenterY() {
        return super.getCenterY();
    }

    /**
     * Sets the value of the property radiusX (width of the ellipse).
     *
     * @param width width of the ellipse
     */
    public void setEllipseRadiusX(double width) {
        super.setRadiusX(width);
    }

    /**
     * Returns the value of the property radiusX (width of the ellipse).
     *
     * @return
     */
    public double getEllipseRadiusX() {
        return super.getRadiusX();
    }

    /**
     * Sets the value of the property radiusY (height of the ellipse).
     *
     * @param height height of the ellipse
     */
    public void setEllipseRadiusY(double height) {
        super.setRadiusY(height);
    }

    /**
     * Returns the value of the property radiusY (height of the ellipse).
     *
     * @return
     */
    public double getEllipseRadiusY() {
        return super.getRadiusY();
    }

    /**
     * Sets the value of the property stroke (outline color of the ellipse).
     *
     * @param outlineColor outline color of the ellipse
     */
    public void setOutlineColor(Paint outlineColor) {
        super.setStroke(outlineColor);
    }

    /**
     * Returns the value of the property stroke (outline color of the ellipse).
     *
     * @return
     */
    public Paint getOutlineColor() {
        return super.getStroke();
    }

    /**
     * Sets the value of the property fill (internal color of the ellipse).
     *
     * @param fillColor internal color of the ellipse
     */
    public void setFillColor(Paint fillColor) {
        super.setFill(fillColor);
    }

    /**
     * Returns the value of the property fill (internal color of the ellipse).
     *
     * @return
     */
    public Paint getFillColor() {
        return super.getFill();
    }

    /**
     * Updates the ellipse coordinates by adding x and y.
     *
     * @param x The value to add to ellipse x coordinate.
     * @param y The value to add to ellipse y coordinate.
     */
    public void moveOf(double x, double y) {
        this.setEllipseCenterX(this.getEllipseCenterX() + x);
        this.setEllipseCenterY(this.getEllipseCenterY() + y);
    }

    /**
     * It returns the string representation of the ellipse
     *
     * @return
     */
    @Override
    public String toString() {
        Color outlineColor = (Color) this.getOutlineColor();
        Color fillColor = (Color) this.getFillColor();
        return "ellipse;" + this.getEllipseCenterX() + ";" + this.getEllipseCenterY() + ";"
                + this.getEllipseRadiusX() + ";" + this.getEllipseRadiusY() + ";" + toHexString(outlineColor) + ";" + toHexString(fillColor);
    }

    private static String toHexString(Color color) {
        int r = ((int) Math.round(color.getRed() * 255));
        int g = ((int) Math.round(color.getGreen() * 255));
        int b = ((int) Math.round(color.getBlue() * 255));

        return String.format("#%02x%02x%02x", r, g, b).toLowerCase();

    }
}
