package command;

import gui.DrawingPane;
import javafx.scene.shape.Shape;

/**
 *
 * @author group7
 */
public class DeleteShapeCommand implements Command {

    private Shape shapeToDelete;
    private DrawingPane drawingPane;
    private int shapeIndex;

    /**
     * Constructor method of class DeleteShapeCommand.
     *
     * @param shapeToDelete the shape to delete
     * @param drawingPane the drawing Pane from which remove the shape
     */
    public DeleteShapeCommand(Shape shapeToDelete, DrawingPane drawingPane) {
        this.shapeToDelete = shapeToDelete;
        this.drawingPane = drawingPane;
    }

    /**
     * method that deletes a shape from the drawingPane.
     * @throws Exception 
     */
    @Override
    public void execute() throws Exception {
        this.shapeIndex = this.drawingPane.getChildren().indexOf(shapeToDelete);
        this.drawingPane.getChildren().remove(this.shapeToDelete);
    }

    /**
     * method that undoes the delete of a shape.
     *
     * @throws Exception
     */
    @Override
    public void undo() throws Exception {
        this.drawingPane.getChildren().add(shapeIndex, shapeToDelete);
    }
}
