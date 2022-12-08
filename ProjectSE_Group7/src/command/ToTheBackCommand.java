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

    /**
     * method that creates a to the back command.
     * @param shape the shape that has to be taken to the back.
     * @param drawingPane the drawing pane where the shape is.
     */
    public ToTheBackCommand(Shape shape, DrawingPane drawingPane) {
        this.shape = shape;
        this.drawingPane = drawingPane;
        this.shapeIndex = drawingPane.getChildren().indexOf(this.shape);
    }
    
    /**
     * method that takes the shape to the back.
     * @throws Exception 
     */
    @Override
    public void execute() throws Exception {
       this.shape.toBack();
    }

    /**
     * method that puts the shape back to its level.
     * @throws Exception 
     */
    @Override
    public void undo() throws Exception {
        this.drawingPane.getChildren().remove(this.shape);
        this.drawingPane.getChildren().add(this.shapeIndex, this.shape);
    }
    
}
