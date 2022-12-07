package state;

import javafx.scene.paint.Color;

/**
 *
 * @author group 7
 */
public interface DrawState {
    
    public void startDrawing(double xStartPoint, double yStartPoint, Color outlineColor, Color fillColor);
    public void draw(double x, double y);
}
