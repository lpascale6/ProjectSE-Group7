package command;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.shape.Shape;
import shape.Border;
import shape.Ellipse;
import shape.Line;
import shape.Polygon;
import shape.Rectangle;
import shape.Text;

/**
 *
 * @author group7
 */
public class ResizeShapeCommand implements Command {

    private Shape selectedShape;
    private Border border;

    private double oldWidth;
    private double oldHeight;
    private int oldFontSize;
    private double oldStartingXPoint;
    private double oldStartingYPoint;
    private double oldEndingXPoint;
    private double oldEndingYPoint;

    private double oldX;
    private double oldY;

    private ArrayList<Double> oldPoints;

    /**
     * Constructor of ResizeShapeCommand class.
     *
     * @param selectedShape the selected shape
     * @param border the border of the selected shape
     */
    public ResizeShapeCommand(Shape selectedShape, Border border) {
        this.selectedShape = selectedShape;
        this.border = border;

        if (this.selectedShape.getClass() == Line.class) {
            Line lineToResize = (Line) this.selectedShape;
            this.oldStartingXPoint = lineToResize.getLineStartingX();
            this.oldStartingYPoint = lineToResize.getLineStartingY();
            this.oldEndingXPoint = lineToResize.getLineEndingX();
            this.oldEndingYPoint = lineToResize.getLineEndingY();

        } else if (this.selectedShape.getClass() == Rectangle.class) {
            Rectangle rectangleToResize = (Rectangle) this.selectedShape;
            this.oldWidth = rectangleToResize.getRectangleWidth();
            this.oldHeight = rectangleToResize.getRectangleHeight();
            this.oldX = rectangleToResize.getRectangleX();
            this.oldY = rectangleToResize.getRectangleY();

        } else if (this.selectedShape.getClass() == Ellipse.class) {
            Ellipse ellipseToResize = (Ellipse) this.selectedShape;
            this.oldWidth = ellipseToResize.getEllipseRadiusX();
            this.oldHeight = ellipseToResize.getEllipseRadiusY();
            this.oldX = ellipseToResize.getEllipseCenterX();
            this.oldY = ellipseToResize.getEllipseCenterY();
            
        } else if (this.selectedShape.getClass() == Text.class) {
            Text textToResize = (Text) this.selectedShape;
            this.oldX = textToResize.getTextX();
            this.oldY = textToResize.getTextY();
            this.oldFontSize = textToResize.getTextFontSize();
            
        } else if (this.selectedShape.getClass() == Polygon.class) {
            Polygon polygonToResize = (Polygon) this.selectedShape;
            
            ObservableList<Double> pointsList = polygonToResize.getPoints();
            this.oldPoints = new ArrayList<>();
            
            for(int i = 0; i < pointsList.size(); i += 2) {
                this.oldPoints.add(pointsList.get(i));
                this.oldPoints.add(pointsList.get(i + 1));
            }
            
            this.oldX = polygonToResize.getLayoutBounds().getMinX();
            this.oldY = polygonToResize.getLayoutBounds().getMinY();
            this.oldWidth = polygonToResize.getLayoutBounds().getWidth();
            this.oldHeight = polygonToResize.getLayoutBounds().getHeight();
        }

    }

    /**
     * Method that does the resize of a shape.
     *
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        if (selectedShape.getClass() == Line.class) {
            Line line = (Line) selectedShape;

            double aX = line.getLineStartingX();
            double aY = line.getLineStartingY();
            double bX = line.getLineEndingX();
            double bY = line.getLineEndingY();

            double borderX = border.getRectangleX();
            double borderY = border.getRectangleY();
            double borderWidth = border.getRectangleWidth();
            double borderHeight = border.getRectangleHeight();

            if (aX < bX && aY < bY) {
                // starting coordinate is the topleft corner of the border
                line.setLineStartingX(borderX);
                line.setLineStartingY(borderY);
                line.setLineEndingX(borderX + borderWidth);
                line.setLineEndingY(borderY + borderHeight);
            } else if (aX < bX && aY > bY) {
                // starting coordinate is the bottomleft corner of the border
                line.setLineStartingX(borderX);
                line.setLineStartingY(borderY + borderHeight);
                line.setLineEndingX(borderX + borderWidth);
                line.setLineEndingY(borderY);
            } else if (aX > bX && aY < bY) {
                // starting coordinate is the topright corner of the border
                line.setLineStartingX(borderX + borderWidth);
                line.setLineStartingY(borderY);
                line.setLineEndingX(borderX);
                line.setLineEndingY(borderY + borderHeight);
            } else if (aX > bX && aY > bY) {
                // starting coordinate is the bottomright corner of the border
                line.setLineStartingX(borderX + borderWidth);
                line.setLineStartingY(borderY + borderHeight);
                line.setLineEndingX(borderX);
                line.setLineEndingY(borderY);
            }

        } else if (selectedShape.getClass() == Rectangle.class) {
            Rectangle rectangle = (Rectangle) selectedShape;

            rectangle.setRectangleX(border.getRectangleX());
            rectangle.setRectangleY(border.getRectangleY());
            
            rectangle.setRectangleWidth(border.getRectangleWidth());
            rectangle.setRectangleHeight(border.getRectangleHeight());

        } else if (selectedShape.getClass() == Ellipse.class) {
            Ellipse ellipse = (Ellipse) selectedShape;

            ellipse.setEllipseCenterX(border.getRectangleX() + border.getRectangleWidth() / 2);
            ellipse.setEllipseCenterY(border.getRectangleY() + border.getRectangleHeight() / 2);
            
            ellipse.setEllipseRadiusX(border.getRectangleWidth() / 2);
            ellipse.setEllipseRadiusY(border.getRectangleHeight() / 2);
            
        } else if(selectedShape.getClass() == Text.class){
            Text text = (Text) selectedShape;
            double borderWidth = border.getRectangleWidth();
            double borderHeight = border.getRectangleHeight();
            double heightRatio = borderHeight / text.getTextHeight();
            double widthRatio = borderWidth / text.getTextWidth();
            int newFontSize = this.oldFontSize;
            if (heightRatio < widthRatio){
               newFontSize = (int) (text.getTextFontSize() * heightRatio); 
            } else {
               newFontSize = (int) (text.getTextFontSize() * widthRatio);
            }
            text.setTextFontSize(newFontSize);
           
        } else if (this.selectedShape.getClass() == Polygon.class) {
            Polygon polygon = (Polygon) this.selectedShape;
            
            double widthRatio = border.getRectangleWidth() / this.oldWidth;
            double heightRatio = border.getRectangleHeight() / this.oldHeight;
            
            // finding for each vertex of the polygon its new coordinate, 
            // mantaining the proportion 
            for (int i = 0; i < this.oldPoints.size(); i += 2) {
                
                double newX = (polygon.getPoints().get(i) - oldX) * widthRatio + border.getRectangleX();
                double newY = (polygon.getPoints().get(i + 1) - oldY) * heightRatio + border.getRectangleY();
 
                polygon.getPoints().set(i, newX);
                polygon.getPoints().set(i + 1, newY);
            }
        }

    }

    /**
     * Method that undoes the resize of a shape.
     *
     * @throws Exception
     */
    @Override
    public void undo() throws Exception {

        //Restore the old size of the line
        if (this.selectedShape.getClass() == Line.class) {
            Line lineToResize = (Line) this.selectedShape;
            lineToResize.setLineStartingX(this.oldStartingXPoint);
            lineToResize.setLineStartingY(this.oldStartingYPoint);
            lineToResize.setLineEndingX(this.oldEndingXPoint);
            lineToResize.setLineEndingY(this.oldEndingYPoint);
            //Restore the old size of the rectangle
        } else if (this.selectedShape.getClass() == Rectangle.class) {
            Rectangle rectangleToResize = (Rectangle) this.selectedShape;
            rectangleToResize.setRectangleWidth(this.oldWidth);
            rectangleToResize.setRectangleHeight(this.oldHeight);

            rectangleToResize.setRectangleX(this.oldX);
            rectangleToResize.setRectangleY(this.oldY);
            //Restore the old size of the ellipse
        } else if (this.selectedShape.getClass() == Ellipse.class) {
            Ellipse ellipseToResize = (Ellipse) this.selectedShape;
            ellipseToResize.setEllipseRadiusX(this.oldWidth);
            ellipseToResize.setEllipseRadiusY(this.oldHeight);

            ellipseToResize.setEllipseCenterX(this.oldX);
            ellipseToResize.setEllipseCenterY(this.oldY);

        } else if(this.selectedShape.getClass() == Text.class){
            Text textToResize = (Text) this.selectedShape;
            textToResize.setTextX(this.oldX);
            textToResize.setTextY(this.oldY);
            textToResize.setTextFontSize(this.oldFontSize);
            
        } else if (this.selectedShape.getClass() == Polygon.class) {
            Polygon polygon = (Polygon) this.selectedShape;
            // restores all the vertex the polygon had before the resizing
            polygon.getPoints().clear();
            polygon.getPoints().addAll(this.oldPoints);
        }
    }

}
