package command;

import gui.DrawingPane;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import shape.Ellipse;
import shape.Line;
import shape.Polygon;
import shape.Rectangle;

/**
 *
 * @author group7
 */
public class PasteShapeCommand implements Command {

    DrawingPane drawingPane;
    Shape pastedShape;  //shape this command has pasted, needed for undoing paste

    public PasteShapeCommand(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
    }

    /**
     * Method to paste a previously copied shape
     *
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        Shape copiedShape = this.drawingPane.getCopiedShape();      //get copied shape
        if (copiedShape instanceof Line) {       //check if copied shape is a Line
            Line copiedLine = (Line) copiedShape;
            //get all attributes of copied shape
            Double copiedStartX = copiedLine.getLineStartingX();
            Double copiedStartY = copiedLine.getLineStartingY();
            Double copiedEndX = copiedLine.getLineEndingX();
            Double copiedEndY = copiedLine.getLineEndingY();
            Paint copiedColor = copiedLine.getLineColor();
            //create a copy of the copied shape
            Line pastedLine = new Line(copiedStartX, copiedStartY, copiedEndX, copiedEndY, copiedColor);
            pastedLine.setStrokeWidth(3);
            pastedLine.setScaleX(copiedLine.getScaleX());
            pastedLine.setScaleY(copiedLine.getScaleY());
            pastedLine.setRotate(drawingPane.getOldSelectedShapeRotation());

            pastedLine.setOnMouseClicked(e -> {  //make the pasted shape selectable
                this.drawingPane.selectShape((Shape) e.getSource());
            });

            this.drawingPane.getChildren().add(pastedLine);  //add copy to drawingPane
            this.pastedShape = pastedLine;  //save pastedShape to delete it in case the paste operation is undone
        } else if (copiedShape instanceof Rectangle) {     //check if copied shape is a rectangle
            Rectangle copiedRect = (Rectangle) copiedShape;
            //get all attributes of copied shape
            Double copiedX = copiedRect.getRectangleX();
            Double copiedY = copiedRect.getRectangleY();
            Double copiedHeight = copiedRect.getRectangleHeight();
            Double copiedWidth = copiedRect.getRectangleWidth();
            Paint copiedFill = copiedRect.getFillColor();
            Paint copiedOutline = copiedRect.getOutlineColor();
            //create a copu of the rectangle
            Rectangle pastedRect = new Rectangle(copiedX, copiedY, copiedWidth, copiedHeight, copiedFill, copiedOutline);
            pastedRect.setStrokeWidth(3);
            pastedRect.setScaleX(copiedRect.getScaleX());
            pastedRect.setScaleY(copiedRect.getScaleY());
            pastedRect.setRotate(drawingPane.getOldSelectedShapeRotation());
            pastedRect.setOnMouseClicked(e -> {  //make the pasted shape selectable
                this.drawingPane.selectShape((Shape) e.getSource());
            });

            this.drawingPane.getChildren().add(pastedRect); //add the copy to the drawingPane
            this.pastedShape = pastedRect;  //save the pasted shape in case it needs to be deleted for undoing the paste 
        } else if (copiedShape instanceof Ellipse) {  //check if copied shape is an ellipse
            Ellipse copiedEll = (Ellipse) copiedShape;
            //get all parameters of copied ellipse
            Double copiedCenterX = copiedEll.getEllipseCenterX();
            Double copiedCenterY = copiedEll.getEllipseCenterY();
            Double copiedRadiusX = copiedEll.getEllipseRadiusX();
            Double copiedRadiusY = copiedEll.getEllipseRadiusY();
            Paint copiedFill = copiedEll.getFillColor();
            Paint copiedOutline = copiedEll.getOutlineColor();
            //create a copy of the ellipse
            Ellipse pastedEll = new Ellipse(copiedCenterX, copiedCenterY, copiedRadiusX, copiedRadiusY, copiedOutline, copiedFill);
            pastedEll.setStrokeWidth(3);
            pastedEll.setScaleX(copiedEll.getScaleX());
            pastedEll.setScaleY(copiedEll.getScaleY());
            pastedEll.setRotate(drawingPane.getOldSelectedShapeRotation());

            pastedEll.setOnMouseClicked(e -> {  //make the pasted shape selectable
                this.drawingPane.selectShape((Shape) e.getSource());
            });

            this.drawingPane.getChildren().add(pastedEll);  //add the copy to the drawingPane
            this.pastedShape = pastedEll; //save the pastedShape to use it in undo operation
        } else if (copiedShape instanceof Polygon) {
            Polygon copiedPolygon = (Polygon) copiedShape;
            
            ObservableList<Double> pointsList = copiedPolygon.getPolygonPoints();
            ArrayList<Double> pastedPoligonPoints = new ArrayList<>();
            
            // copying all the points of the copied polygon
            for(Double point: pointsList) {
                pastedPoligonPoints.add(point);
            }
            
            // creating a new polygon and setting up all its properties
            Polygon pastedPolygon = new Polygon();
            
            double x = copiedPolygon.getLayoutBounds().getMinX();
            double y = copiedPolygon.getLayoutBounds().getMinY();
            
            pastedPolygon.setPolygonPoints(pastedPoligonPoints);
            pastedPolygon.setStrokeWidth(3);
            pastedPolygon.setOutlineColor(copiedPolygon.getOutlineColor());
            pastedPolygon.setFillColor(copiedPolygon.getFillColor());
            pastedPolygon.setScaleX(copiedPolygon.getScaleX());
            pastedPolygon.setScaleY(copiedPolygon.getScaleY());
            pastedPolygon.setRotate(drawingPane.getOldSelectedShapeRotation());
            
            pastedPolygon.moveOf(-x, -y);
            
            // to make the polygon selectable
            pastedPolygon.setOnMouseClicked(e -> {  
                this.drawingPane.selectShape((Shape) e.getSource());
            });
            
            // insert the copied polygon to the drawing pane and memorize it 
            // if needed in the undo operation
            this.drawingPane.getChildren().add(pastedPolygon);
            this.pastedShape = pastedPolygon;
        }

    }

    /**
     * method that undoes the paste operation by deleting the pasted shape
     *
     * @throws Exception
     */
    @Override
    public void undo() throws Exception {
        this.drawingPane.getChildren().remove(this.pastedShape);
    }

}
