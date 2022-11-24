package shape;
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
     * @param width width of the ellipse
     * @param height height of the ellipse
     */ 
    public Ellipse(double width, double height) {
        super(width, height);
    }
    
    /**
     * Creates an instance of Ellipse of the given position and size.
     * @param hPosition the x coordinate of the center of the ellipse
     * @param vPosition the y coordinate of the center of the ellipse
     * @param width width of the ellipse
     * @param height height of the ellipse
     */ 
    public Ellipse(double hPosition, double vPosition, double width, double height) {
        super(hPosition, vPosition, width, height);
    }
    
    /**
     * Creates an instance of Ellipse of the given position, size and outline color.
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
     * Creates an instance of Ellipse of the given position, size, outline and fill colors.
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
     * Sets the value of the property centerX (Horizontal position of the center).
     * @param hPosition the x coordinate of the center of the ellipse  
     */ 
    public void setCenterHorizontalPosition(double hPosition) {
        super.setCenterX(hPosition);
    }
    
    /**
     * Returns the value of the property centerX (Horizontal position of the center).
     * @return 
     */
    public double getCenterHorizontalPosition() {
        return super.getCenterX();
    }
    
    /**
     * Sets the value of the property centerY (Vertical position of the center).
     * @param vPosition the y coordinate of the center of the ellipse
     */
    public void setCenterVerticalPosition(double vPosition) {
        super.setCenterY(vPosition);
    }
    
    /**
     * Returns the value of the property centerY (Vertical position of the center).
     * @return 
     */
    public double getCenterVerticalPosition() {
        return super.getCenterY();
    }
    
    /**
     * Sets the value of the property radiusX (width of the ellipse).
     * @param width width of the ellipse
     */
    public void setWidth(double width) {
        super.setRadiusX(width);
    }
    
    /**
     * Returns the value of the property radiusX (width of the ellipse).
     * @return 
     */
    public double getWidth() {
        return super.getRadiusX();
    }
    
    /**
     * Sets the value of the property radiusY (height of the ellipse).
     * @param height height of the ellipse
     */
    public void setHeight(double height) {
        super.setRadiusY(height);
    }
    
    /**
     * Returns the value of the property radiusY (height of the ellipse).
     * @return 
     */
    public double getHeight() {
        return super.getRadiusY();
    }
    
    /**
     * Sets the value of the property stroke (outline color of the ellipse).
     * @param outlineColor outline color of the ellipse
     */
    public void setOutlineColor(Paint outlineColor) {
        super.setStroke(outlineColor);
    }
    
    /**
     * Returns the value of the property stroke (outline color of the ellipse).
     * @return 
     */
    public Paint getOutlineColor() {
        return super.getStroke();
    }
    
    /**
     * Sets the value of the property fill (internal color of the ellipse).
     * @param fillColor internal color of the ellipse
     */
    public void setFillColor(Paint fillColor) {
        super.setFill(fillColor);
    }
    
    /**
     * Returns the value of the property fill (internal color of the ellipse).
     * @return 
     */
    public Paint getFillColor() {
        return super.getFill();
    }
}
