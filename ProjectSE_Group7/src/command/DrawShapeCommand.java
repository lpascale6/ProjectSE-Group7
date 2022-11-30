package command;

import gui.DrawingPane;
import javafx.scene.shape.Shape;

/**
 *
 * @author group7
 */
public class DrawShapeCommand implements Command{
    private DrawingPane drawingPane;
    private Shape creatingShape;
    
    
    public DrawShapeCommand(DrawingPane drawingPane, Shape creatingShape) {
        this.drawingPane = drawingPane;
        this.creatingShape = creatingShape;
    }

    @Override
    public void execute() throws Exception {
        this.creatingShape.setOnMouseClicked(e -> {
            this.drawingPane.selectShape(e);
        });
        this.drawingPane.getChildren().add(this.creatingShape);
    }

    @Override
    public void undo() throws Exception {
        this.drawingPane.getChildren().remove(this.creatingShape);
        this.drawingPane.deselectShape();
    }
}
