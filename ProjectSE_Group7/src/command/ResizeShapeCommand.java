package command;

import javafx.scene.shape.Shape;
import shape.Border;
import shape.Ellipse;
import shape.Line;
import shape.Rectangle;

/**
 *
 * @author group7
 */
public class ResizeShapeCommand implements Command {

    private Shape selectedShape;
    private Border border;

    private double oldWidth;
    private double oldHeight;

    private double oldStartingXPoint;
    private double oldStartingYPoint;
    private double oldEndingXPoint;
    private double oldEndingYPoint;
    
    private double oldX;
    private double oldY;

    public ResizeShapeCommand(Shape selectedShape, Border border) {
        this.selectedShape = selectedShape;
        this.border = border;
        
         if(this.selectedShape.getClass() == Line.class){
            Line lineToResize = (Line) this.selectedShape;
            this.oldStartingXPoint = lineToResize.getLineStartingX();
            this.oldStartingYPoint = lineToResize.getLineStartingY();
            this.oldEndingXPoint = lineToResize.getLineEndingX();
            this.oldEndingYPoint = lineToResize.getLineEndingY();
            
        }else if(this.selectedShape.getClass() == Rectangle.class){
            Rectangle rectangleToResize = (Rectangle) this.selectedShape;
            this.oldWidth = rectangleToResize.getRectangleWidth();
            this.oldHeight = rectangleToResize.getRectangleHeight();
            this.oldX = rectangleToResize.getRectangleX();
            this.oldY = rectangleToResize.getRectangleY();
            
        }else if(this.selectedShape.getClass() == Ellipse.class){
            Ellipse ellipseToResize = (Ellipse) this.selectedShape;
            this.oldWidth = ellipseToResize.getEllipseRadiusX();
            this.oldHeight = ellipseToResize.getEllipseRadiusY();
            this.oldX = ellipseToResize.getEllipseCenterX();
            this.oldY = ellipseToResize.getEllipseCenterY();
        }

    }

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
            // Resize command
            rectangle.setRectangleWidth(border.getRectangleWidth());
            rectangle.setRectangleHeight(border.getRectangleHeight());




        } else if (selectedShape.getClass() == Ellipse.class) {
            Ellipse ellipse = (Ellipse) selectedShape;



           ellipse.setEllipseCenterX(border.getRectangleX() + border.getRectangleWidth() / 2);
            ellipse.setEllipseCenterY(border.getRectangleY() + border.getRectangleHeight() / 2);



           // Resize command
            ellipse.setEllipseRadiusX(border.getRectangleWidth() / 2);
            ellipse.setEllipseRadiusY(border.getRectangleHeight() / 2);



           



       }

    }

    @Override
    public void undo() throws Exception {

        if (this.selectedShape.getClass() == Line.class) {
            Line lineToResize = (Line) this.selectedShape;
            lineToResize.setLineStartingX(this.oldStartingXPoint);
            lineToResize.setLineStartingY(this.oldStartingYPoint);
            lineToResize.setLineEndingX(this.oldEndingXPoint);
            lineToResize.setLineEndingY(this.oldEndingYPoint);

        } else if (this.selectedShape.getClass() == Rectangle.class) {
            Rectangle rectangleToResize = (Rectangle) this.selectedShape;
            rectangleToResize.setRectangleWidth(this.oldWidth);
            rectangleToResize.setRectangleHeight(this.oldHeight);

            rectangleToResize.setRectangleX(this.oldX);
            rectangleToResize.setRectangleY(this.oldY);

        } else if (this.selectedShape.getClass() == Ellipse.class) {
            Ellipse ellipseToResize = (Ellipse) this.selectedShape;
            ellipseToResize.setEllipseRadiusX(this.oldWidth);
            ellipseToResize.setEllipseRadiusY(this.oldHeight);

            ellipseToResize.setEllipseCenterX(this.oldX);
            ellipseToResize.setEllipseCenterY(this.oldY);

        }
    }

}
