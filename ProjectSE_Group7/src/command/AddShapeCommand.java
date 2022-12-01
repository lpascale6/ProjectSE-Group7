package command;

import gui.DrawingPane;
import javafx.scene.shape.Shape;

/**
 * AddShapeCommand implements Command interface, it add the shape passed as
 * argument in the constructor in the drawing pane.
 *
 * @author group7
 */
public class AddShapeCommand implements Command {

    private DrawingPane drawingPane;
    private Shape creatingShape;

    /**
     * Constructor method of class AddShapeCommand.
     *
     * @param drawingPane The drawing Pane in which insert the new shape.
     * @param creatingShape The shape to insert.
     */
    public AddShapeCommand(DrawingPane drawingPane, Shape creatingShape) {
        this.drawingPane = drawingPane;
        this.creatingShape = creatingShape;
    }

    /**
     * Add the shape into the drawing.
     *
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        this.creatingShape.setOnMouseClicked(e -> {
            this.drawingPane.selectShape(e);
        });
        this.drawingPane.getChildren().add(this.creatingShape);
    }

    /**
     * Removes the shape from the drawing.
     *
     * @throws Exception
     */
    @Override
    public void undo() throws Exception {
        this.drawingPane.getChildren().remove(this.creatingShape);
        this.drawingPane.deselectShape();
    }
}
