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

    public ChangeOutlineColorCommand(Shape shape, Color newColor) {
        this.shape = shape;
        this.newColor = newColor;
        this.previousColor = (Color) this.shape.getStroke();
    }

    @Override
    public void execute() throws Exception {
        this.shape.setStroke(this.newColor);
    }

    @Override
    public void undo() throws Exception {
        this.shape.setStroke(this.previousColor);
    }
    
}
