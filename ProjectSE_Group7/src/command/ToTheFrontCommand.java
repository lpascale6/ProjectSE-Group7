package command;

import gui.DrawingPane;
import javafx.scene.shape.Shape;

/**
 *
 * @author group7
 */
public class ToTheFrontCommand implements Command{
    private Shape shape;
    private DrawingPane drawingPane;
    private int shapeIndex;

    public ToTheFrontCommand(Shape shape, DrawingPane drawingPane) {
        this.shape = shape;
        this.drawingPane = drawingPane;
        this.shapeIndex = drawingPane.getChildren().indexOf(this.shape);
    }
    
    @Override
    public void execute() throws Exception {
       this.shape.toFront();
    }

    @Override
    public void undo() throws Exception {
        this.drawingPane.getChildren().remove(this.shape);
        this.drawingPane.getChildren().add(this.shapeIndex, this.shape);
    }
    
}
