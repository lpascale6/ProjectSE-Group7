package shape;
import com.sun.javafx.sg.prism.NGShape;
import javafx.scene.paint.Paint;

/**
 *
 * @author group7
 */
public class Line extends javafx.scene.shape.Line{

    /**
     * method to create an empty line segment.
     */
    public Line() {
        super();
    }

    /**
     * method to create a line segment with starting and ending X, Y coordinates. 
     * @param startX the starting X coordinate.
     * @param startY the starting Y coordinate.
     * @param endX the ending X coordinate.
     * @param endY the ending Y coordinate.
     */
    public Line(double startX, double startY, double endX, double endY) {
        super(startX, startY, endX, endY);
    }
    
    /**
     * method to create a line segment with starting and ending X,Y coordinates and chosen color.
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
     * @param startX the new starting X coordinate.
     */
    public void setStartingX(double startX){
        super.setStartX(startX);
        
    }
    
    /**
     * method to set the starting Y coordinate for the line segment.
     * @param startY the new starting Y coordinate.
     */
    public void setStartingY(double startY){
        super.setStartY(startY);
    }
    
    /**
     * method to set the ending X coordinate for the line segment.
     * @param endX the new ending X coordinate.
     */
    public void setEndingX(double endX){
        super.setEndX(endX);
    }
    
    
    /**
     * method to set the ending Y coordinate for the line segment.
     * @param endY the new ending Y coordinate.
     */
    public void setEndingY(double endY){
        super.setEndY(endY);
    }
    
    
    /**
     * method to get the starting X coordinate for the line segment.
     * @return the current starting X coordinate.
     */
    public double getStartingX(){
        return super.getStartX();
    }
    
    
    /**
     * method to get the starting Y coordinate for the line segment.
     * @return the current starting Y coordinate.
     */
    public double getStartingY(){
        return super.getStartY();
    }
    
    
    /**
     * method to get the ending X coordinate for the line segment.
     * @return the current ending X coordinate.
     */
    public double getEndingX(){
        return super.getEndX();
    }
    
    
    /**
     * method to get the ending Y coordinate for the line segment.
     * @return the current ending Y coordinate.
     */
    public double getEndingY(){
        return super.getEndY();
    }
    
    /**
     * method to set a new color for the line segment.
     * @param color the new color.
     */
    public void setColor(Paint color){
        super.setStroke(color);
    }
    
    /**
     * method to get the current color for the line segment.
     * @return the current color.
     */
    public Paint getColor(){
        return super.getStroke();
    }
    
    
    
}

