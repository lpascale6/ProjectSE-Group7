package command;

import gui.DrawingPane;
import javafx.scene.shape.Shape;

/**
 *
 * @author group7
 */
public class ToTheBackCommand implements Command{
    private Shape shape;
    private DrawingPane drawingPane;
    private int shapeIndex;

    public ToTheBackCommand(Shape shape, DrawingPane drawingPane) {
        this.shape = shape;
        this.drawingPane = drawingPane;
        this.shapeIndex = drawingPane.getChildren().indexOf(this.shape);
    }
    
    @Override
    public void execute() throws Exception {
       this.shape.toBack();
    }

    @Override
    public void undo() throws Exception {
        this.drawingPane.getChildren().remove(this.shape);
        this.drawingPane.getChildren().add(this.shapeIndex, this.shape);
    }
    
}
