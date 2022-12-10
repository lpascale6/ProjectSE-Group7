package state;

import javafx.scene.paint.Color;

/**
 *
 * @author group 7
 */
public interface DrawState {
    
    /**
     * Generic startDrawing method to be implemented in the implementing subclasses. 
     * @param xStartPoint
     * @param yStartPoint
     * @param outlineColor
     * @param fillColor
     */
    public void startDrawing(double xStartPoint, double yStartPoint, Color outlineColor, Color fillColor);
    
    /**
     * Generic draw method to be implemented in the implementing subclasses. 
     * @param x
     * @param y
     */
    public void draw(double x, double y);
}
