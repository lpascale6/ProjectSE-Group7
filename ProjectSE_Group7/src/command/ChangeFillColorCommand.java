package command;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 *
 * @author group7
 */
public class ChangeFillColorCommand implements Command{
    Shape shape;
    Color newColor;
    Color previousColor;

    /**
     * method that creates a change fill color command.
     * @param shape the shape that has to get its fill color changed.
     * @param newColor the new value for the shape fill color.
     */
    public ChangeFillColorCommand(Shape shape, Color newColor) {
        this.shape = shape;
        this.newColor = newColor;
        this.previousColor = (Color) this.shape.getFill();
    }

    /**
     * method that changes the shape fill color.
     * @throws Exception 
     */
    @Override
    public void execute() throws Exception {
        this.shape.setFill(this.newColor);
    }

    /**
     * method that sets the shape fill color to the previous value.
     * @throws Exception 
     */
    @Override
    public void undo() throws Exception {
        this.shape.setFill(this.previousColor);
    }
    
}
