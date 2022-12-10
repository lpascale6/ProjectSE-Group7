package state;

import command.AddShapeCommand;
import command.Invoker;
import gui.DrawingPane;
import javafx.scene.paint.Color;
import shape.Rectangle;

/**
 *
 * @author group7
 */
public class DrawRectangleState implements DrawState {

    private DrawingPane drawingPane;
    private Invoker invoker;
    private Rectangle creatingRectangle;
    private double xStartPoint;
    private double yStartPoint;

    /**
     * Constructor method of DrawRectangleState.
     * @param drawingPane 
     */
    public DrawRectangleState(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
        invoker = Invoker.getInstance();
    }

    /**
     * Starts setting up a possible Rectangle shape and insert the shape in the
     * drawing.
     *
     * @param xStartPoint The starting x coordinate of the Rectangle.
     * @param yStartPoint The starting y coordinate of the Rectangle.
     * @param outlineColor The outline color of the Rectangle.
     * @param fillColor The fill color of the Rectangle.
     */
    @Override
   public void startDrawing(double xStartPoint, double yStartPoint, Color outlineColor, Color fillColor) {
        this.xStartPoint = xStartPoint;
        this.yStartPoint = yStartPoint;

        this.creatingRectangle = new Rectangle(xStartPoint, yStartPoint, 0, 0);
        this.creatingRectangle.setStroke(outlineColor);
        this.creatingRectangle.setFill(fillColor);
        this.creatingRectangle.setStrokeWidth(DrawingPane.strokeWidth);

        AddShapeCommand addShapeCommand = new AddShapeCommand(this.drawingPane, this.creatingRectangle);
        try {
            this.invoker.execute(addShapeCommand);
        } catch (Exception ex) {
        }
    }

    /**
     * Draws the Rectangle in the drawing.
     *
     * @param x The ending x coordinate of the Rectangle.
     * @param y The ending y coordinate of the Rectangle.
     */
    @Override
    public void draw(double x, double y) {
        if (x < xStartPoint) {
            this.creatingRectangle.setRectangleX(x);
        } else {
            this.creatingRectangle.setRectangleX(xStartPoint);
        }

        // if the y coordinate is before the y vertex coordinate, 
        // it becomes the new y coordinate for the vertex
        if (y < yStartPoint) {
            this.creatingRectangle.setRectangleY(y);
        } else {
            this.creatingRectangle.setRectangleY(yStartPoint);
        }

        this.creatingRectangle.setRectangleWidth(Math.abs(xStartPoint - x));
        this.creatingRectangle.setRectangleHeight(Math.abs(yStartPoint - y));
    }

}
