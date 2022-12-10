package state;

import command.AddShapeCommand;
import command.Invoker;
import gui.DrawingPane;
import javafx.scene.paint.Color;
import shape.Ellipse;

/**
 *
 * @author group7
 */
public class DrawEllipseState implements DrawState {

    private DrawingPane drawingPane;
    private Invoker invoker;
    private Ellipse creatingEllipse;
    private double xStartPoint;
    private double yStartPoint;

    /**
     * Constructor method of DrawEllipseState.
     * @param drawingPane 
     */
    public DrawEllipseState(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
        invoker = Invoker.getInstance();
    }

    /**
     * Starts setting up a possible Ellipse shape and insert the shape in the
     * drawing.
     *
     * @param xStartPoint The starting x coordinate of the Ellipse.
     * @param yStartPoint The starting y coordinate of the Ellipse.
     * @param outlineColor The outline color of the Ellipse.
     * @param fillColor The fill color of the Ellipse.
     */
    @Override
    public void startDrawing(double xStartPoint, double yStartPoint, Color outlineColor, Color fillColor) {
        this.xStartPoint = xStartPoint;
        this.yStartPoint = yStartPoint;

        this.creatingEllipse = new Ellipse(xStartPoint, yStartPoint, 0, 0);
        this.creatingEllipse.setStroke(outlineColor);
        this.creatingEllipse.setFill(fillColor);
        this.creatingEllipse.setStrokeWidth(DrawingPane.strokeWidth);
        AddShapeCommand addShapeCommand = new AddShapeCommand(this.drawingPane, this.creatingEllipse);
        try {
            invoker.execute(addShapeCommand);
        } catch (Exception ex) {
        }
    }

    /**
     * Draws the Ellipse in the drawing.
     *
     * @param x The ending x coordinate of the imaginary rectangle bounding the
     * Ellipse.
     * @param y The ending y coordinate of the imaginary rectangle bounding the
     * Ellipse.
     */
    @Override
    public void draw(double x, double y) {
        // if the x coordinate is before the x vertex coordinate, 
        // it becomes the new x coordinate for the vertex
        if (x < xStartPoint) {
            this.creatingEllipse.setEllipseCenterX((x + xStartPoint) / 2);
        } else {
            this.creatingEllipse.setEllipseCenterX((x + xStartPoint) / 2);
        }

        // if the y coordinate is before the y vertex coordinate, 
        // it becomes the new y coordinate for the vertex
        if (y < yStartPoint) {
            this.creatingEllipse.setEllipseCenterY((y + yStartPoint) / 2);
        } else {
            this.creatingEllipse.setEllipseCenterY((y + yStartPoint) / 2);
        }

        this.creatingEllipse.setEllipseRadiusX(Math.abs(xStartPoint - x) / 2);
        this.creatingEllipse.setEllipseRadiusY(Math.abs(yStartPoint - y) / 2);
    }

}
