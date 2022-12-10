package state;

import command.AddShapeCommand;
import command.Invoker;
import gui.DrawingPane;
import javafx.scene.paint.Color;
import shape.Line;

/**
 *
 * @author group7
 */
public class DrawLineState implements DrawState {

    private DrawingPane drawingPane;
    private Invoker invoker;
    private Line creatingLine;

    /**
     * Constructor method of DrawLineState.
     * @param drawingPane 
     */
    public DrawLineState(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
        invoker = Invoker.getInstance();
    }

    /**
     * Starts setting up a possible Line shape and insert the shape in the
     * drawing.
     *
     * @param xStartPoint The starting x coordinate of the Line.
     * @param yStartPoint The starting y coordinate of the Line.
     * @param outlineColor The outline color of the Line.
     * @param fillColor The fill color of the Line (null).
     */
    @Override
    public void startDrawing(double xStartPoint, double yStartPoint, Color outlineColor, Color fillColor) {
        this.creatingLine = new Line(xStartPoint, yStartPoint, xStartPoint, yStartPoint);
        this.creatingLine.setStroke(outlineColor);
        this.creatingLine.setStrokeWidth(DrawingPane.strokeWidth);

        AddShapeCommand addShapeCommand = new AddShapeCommand(this.drawingPane, this.creatingLine);
        try {
            this.invoker.execute(addShapeCommand);
        } catch (Exception ex) {
        }
    }

    /**
     * Draws the Line in the drawing.
     *
     * @param x The ending x coordinate of the Line.
     * @param y The ending y coordinate of the Line.
     */
    @Override
    public void draw(double x, double y) {
        this.creatingLine.setLineEndingX(x);
        this.creatingLine.setLineEndingY(y);
    }

}
