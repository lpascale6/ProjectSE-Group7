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

    /**
     * method that creates a to the front command.
     * @param shape the shape that has to be taken to the front.
     * @param drawingPane the drawing pane where the shape is.
     */
    public ToTheFrontCommand(Shape shape, DrawingPane drawingPane) {
        this.shape = shape;
        this.drawingPane = drawingPane;
        this.shapeIndex = drawingPane.getChildren().indexOf(this.shape);
    }
    
    /**
     * method that takes the shape to the front.
     * @throws Exception 
     */
    @Override
    public void execute() throws Exception {
       this.shape.toFront();
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
