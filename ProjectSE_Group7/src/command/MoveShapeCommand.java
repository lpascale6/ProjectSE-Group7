package command;

import javafx.scene.shape.Shape;
import shape.*;

/**
 * MoveShapeCommand implements Command interface, it performs the move of a
 * shape to a new position.
 *
 * @author group7
 */
public class MoveShapeCommand implements Command {

    private Shape shapeToMove;
    private double deltaX;
    private double deltaY;

    /**
     * Constructor of MoveShapeCommand class.
     *
     * @param shapeToMove The shape to move,
     * @param deltaX The deltaX the shape has to move.
     * @param deltaY The deltaY the shape has to move.
     */
    public MoveShapeCommand(Shape shapeToMove, double deltaX, double deltaY) {
        this.shapeToMove = shapeToMove;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    /**
     * It moves the shape by deltaX and deltaY.
     *
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        if (shapeToMove.getClass() == Line.class) {
            Line line = (Line) shapeToMove;
            line.moveOf(deltaX, deltaY);
        } else if (shapeToMove.getClass() == Rectangle.class) {
            Rectangle rectangle = (Rectangle) shapeToMove;
            rectangle.moveOf(deltaX, deltaY);
        } else if (shapeToMove.getClass() == Ellipse.class) {
            Ellipse ellipse = (Ellipse) shapeToMove;
            ellipse.moveOf(deltaX, deltaY);
        } else if (shapeToMove.getClass() == Polygon.class) {
            Polygon polygon = (Polygon) shapeToMove;
            polygon.moveOf(deltaX, deltaY);
        }
    }

    /**
     * It relocate the shape to the position it was before the move.
     *
     * @throws Exception
     */
    @Override
    public void undo() throws Exception {
        if (shapeToMove.getClass() == Line.class) {
            Line line = (Line) shapeToMove;
            line.moveOf(-deltaX, -deltaY);
        } else if (shapeToMove.getClass() == Rectangle.class) {
            Rectangle rectangle = (Rectangle) shapeToMove;
            rectangle.moveOf(-deltaX, -deltaY);
        } else if (shapeToMove.getClass() == Ellipse.class) {
            Ellipse ellipse = (Ellipse) shapeToMove;
            ellipse.moveOf(-deltaX, -deltaY);
        } else if (shapeToMove.getClass() == Polygon.class) {
            Polygon polygon = (Polygon) shapeToMove;
            polygon.moveOf(-deltaX, -deltaY);
        }
    }

}
