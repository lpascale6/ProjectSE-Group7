/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command;

import gui.DrawingPane;
import javafx.scene.shape.Shape;
import shape.Ellipse;
import shape.Line;
import shape.Rectangle;

/**
 *
 * @author luigi
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
