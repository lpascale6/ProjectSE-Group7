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

    public ChangeFillColorCommand(Shape shape, Color newColor) {
        this.shape = shape;
        this.newColor = newColor;
        this.previousColor = (Color) this.shape.getFill();
    }

    @Override
    public void execute() throws Exception {
        this.shape.setFill(this.newColor);
    }

    @Override
    public void undo() throws Exception {
        this.shape.setFill(this.previousColor);
    }
    
}
