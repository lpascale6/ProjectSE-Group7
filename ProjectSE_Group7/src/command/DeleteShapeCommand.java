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

    public DeleteShapeCommand(Shape shapeToDelete, DrawingPane drawingPane) {
        this.shapeToDelete = shapeToDelete;
        this.drawingPane = drawingPane;
    }

    @Override
    public void execute() throws Exception {
        this.shapeIndex = this.drawingPane.getChildren().indexOf(shapeToDelete);
        this.drawingPane.getChildren().remove(this.shapeToDelete);
    }

    @Override
    public void undo() throws Exception {
        this.drawingPane.getChildren().add(shapeIndex, shapeToDelete);
    }
}
