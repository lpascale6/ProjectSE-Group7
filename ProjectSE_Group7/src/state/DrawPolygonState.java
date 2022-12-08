package state;

import command.AddShapeCommand;
import command.Invoker;
import gui.DrawingPane;
import javafx.scene.paint.Color;
import shape.Polygon;

/**
 *
 * @author group7
 */
public class DrawPolygonState implements DrawState {

    private DrawingPane drawingPane;
    private Invoker invoker;
    private Polygon creatingPolygon;

    public DrawPolygonState(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
        this.invoker = Invoker.getInstance();
    }

    /**
     * Starts setting up a possible Polygon shape and insert the shape in the
     * drawing.
     *
     * @param xStartPoint The starting x coordinate of the Polygon.
     * @param yStartPoint The starting y coordinate of the Polygon.
     * @param outlineColor The outline color of the Polygon.
     * @param fillColor The fill color of the Polygon.
     */
    @Override
    public void startDrawing(double xStartPoint, double yStartPoint, Color outlineColor, Color fillColor) {
        this.creatingPolygon = new Polygon(xStartPoint, yStartPoint);
        this.creatingPolygon.setOutlineColor(outlineColor);
        this.creatingPolygon.setFillColor(fillColor);
        this.creatingPolygon.setStrokeWidth(DrawingPane.strokeWidth);

        AddShapeCommand addShapeCommand = new AddShapeCommand(this.drawingPane, this.creatingPolygon);
        try {
            invoker.execute(addShapeCommand);
        } catch (Exception ex) {
        }
    }

    /**
     * Draw the Polygon in the drawing by adding x and y as new point.
     *
     * @param x The x coordinate of the new point of the Polygon.
     * @param y The y coordinate of the new point of the Polygon.
     */
    @Override
    public void draw(double x, double y) {
        this.creatingPolygon.getPoints().addAll(x, y);
    }

}
