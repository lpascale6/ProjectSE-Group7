package command;

import gui.DrawingPane;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.shape.Shape;
import shape.Ellipse;
import shape.Line;
import shape.Polygon;
import shape.Rectangle;
import shape.Text;

/**
 *
 * @author group7
 */
public class CopyShapeCommand implements Command {

    private Shape selectedShape;
    private DrawingPane drawingPane;
    private double startPoint = 10;

    /**
     * Constructor method of class CopyShapeCommand.
     *
     * @param selectedShape the shape to copy
     * @param drawingPane the drawing Pane of the selected shape and where to
     * put the copied shape
     */
    public CopyShapeCommand(Shape selectedShape, DrawingPane drawingPane) {
        this.selectedShape = selectedShape;
        this.drawingPane = drawingPane;
    }

    /**
     * method that copies a shape (saves it in copiedShape of drawingPane).
     *
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        if (this.selectedShape.getClass() == Line.class) {
            Line line = (Line) this.selectedShape;
            Line shapeToCopy = new Line();
            double distX, distY;
            distX = line.getEndX() - line.getStartX();
            distY = line.getEndY() - line.getStartY();
            if (distX < 0 && distY > 0) {
                shapeToCopy.setStartX(this.startPoint + Math.abs(distX));
                shapeToCopy.setStartY(this.startPoint);
            } else if (distX < 0 && distY < 0) {
                shapeToCopy.setStartX(this.startPoint + Math.abs(distX));
                shapeToCopy.setStartY(this.startPoint + Math.abs(distY));
            } else if (distX > 0 && distY > 0) {
                shapeToCopy.setStartX(this.startPoint);
                shapeToCopy.setStartY(this.startPoint);
            } else if (distX > 0 && distY < 0) {
                shapeToCopy.setStartX(this.startPoint);
                shapeToCopy.setStartY(this.startPoint + Math.abs(distY));
            }
            shapeToCopy.setEndX(shapeToCopy.getStartX() + distX);
            shapeToCopy.setEndY(shapeToCopy.getStartY() + distY);
            shapeToCopy.setStroke(line.getStroke());
            shapeToCopy.setStrokeWidth(3);
            shapeToCopy.setFill(line.getFill());
            shapeToCopy.setScaleX(line.getScaleX());
            shapeToCopy.setScaleY(line.getScaleY());
            shapeToCopy.setRotate(line.getRotate());
            this.drawingPane.setCopiedShape(shapeToCopy);
        } else if (this.selectedShape.getClass() == Rectangle.class) {
            Rectangle rectangle = (Rectangle) this.selectedShape;
            Rectangle shapeToCopy = new Rectangle();
            shapeToCopy.setX(this.startPoint);
            shapeToCopy.setY(this.startPoint);
            shapeToCopy.setWidth(rectangle.getWidth());
            shapeToCopy.setHeight(rectangle.getHeight());
            shapeToCopy.setStroke(rectangle.getStroke());
            shapeToCopy.setStrokeWidth(3);
            shapeToCopy.setFill(rectangle.getFill());
            shapeToCopy.setScaleX(rectangle.getScaleX());
            shapeToCopy.setScaleY(rectangle.getScaleY());
            shapeToCopy.setRotate(rectangle.getRotate());
            this.drawingPane.setCopiedShape(shapeToCopy);
        } else if (this.selectedShape.getClass() == Ellipse.class) {
            Ellipse ellipse = (Ellipse) this.selectedShape;
            Ellipse shapeToCopy = new Ellipse();
            shapeToCopy.setCenterX(this.startPoint + ellipse.getRadiusX());
            shapeToCopy.setCenterY(this.startPoint + ellipse.getRadiusY());
            shapeToCopy.setRadiusX(ellipse.getRadiusX());
            shapeToCopy.setRadiusY(ellipse.getRadiusY());
            shapeToCopy.setStroke(ellipse.getStroke());
            shapeToCopy.setStrokeWidth(3);
            shapeToCopy.setFill(ellipse.getFill());
            shapeToCopy.setScaleX(ellipse.getScaleX());
            shapeToCopy.setScaleY(ellipse.getScaleY());
            shapeToCopy.setRotate(ellipse.getRotate());
            this.drawingPane.setCopiedShape(shapeToCopy);

        } else if (this.selectedShape.getClass() == Text.class){
            Text text = (Text) this.selectedShape;
            Text shapeToCopy = new Text();
            shapeToCopy.setX(this.startPoint);
            shapeToCopy.setY(this.startPoint + text.getTextHeight()/2);
            shapeToCopy.setTextString(text.getTextString());
            shapeToCopy.setTextFontSize(text.getTextFontSize());
            shapeToCopy.setStroke(text.getStroke());
            shapeToCopy.setFill(text.getFill());
            shapeToCopy.setScaleX(text.getScaleX());
            shapeToCopy.setScaleY(text.getScaleY());
            shapeToCopy.setRotate(text.getRotate());
            this.drawingPane.setCopiedShape(shapeToCopy);
        } else if (this.selectedShape.getClass() == Polygon.class) {
            Polygon polygon = (Polygon) this.selectedShape;
            Polygon shapeToCopy = new Polygon();

            ObservableList<Double> pointsList = polygon.getPolygonPoints();
            ArrayList<Double> points = new ArrayList<>();

            // copying all the points of the copied polygon
            for (Double point : pointsList) {
                points.add(point);
            }

            shapeToCopy.setPolygonPoints(points);

            shapeToCopy.setStrokeWidth(3);
            shapeToCopy.setOutlineColor(polygon.getOutlineColor());
            shapeToCopy.setFillColor(polygon.getFillColor());
            shapeToCopy.setScaleX(polygon.getScaleX());
            shapeToCopy.setScaleY(polygon.getScaleY());
            shapeToCopy.setRotate(polygon.getRotate());

            // to make the polygon selectable
            shapeToCopy.setOnMouseClicked(e -> {
                this.drawingPane.selectShape((Shape) e.getSource());
            });

            this.drawingPane.setCopiedShape(shapeToCopy);
        }
    }

    /**
     * method that undoes the copy of a shape.
     *
     * @throws Exception
     */
    @Override
    public void undo() throws Exception {
    }

}
