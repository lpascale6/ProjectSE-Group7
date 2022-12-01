package command;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 *
 * @author group7
 */
public class ChangeOutlineColorCommand implements Command{
    Shape shape;
    Color newColor;
    Color previousColor;

    /**
     * method that creates a change outline color command.
     * @param shape the shape that has to get its outline color changed.
     * @param newColor the new value for the shape outline color.
     */
    public ChangeOutlineColorCommand(Shape shape, Color newColor) {
        this.shape = shape;
        this.newColor = newColor;
        this.previousColor = (Color) this.shape.getStroke();
    }

    /**
     * method that changes the shape outline color.
     * @throws Exception 
     */
    @Override
    public void execute() throws Exception {
        this.shape.setStroke(this.newColor);
    }

    /**
     * method that sets the shape outline color to the previous value.
     * @throws Exception 
     */
    @Override
    public void undo() throws Exception {
        this.shape.setStroke(this.previousColor);
    }
    
}
