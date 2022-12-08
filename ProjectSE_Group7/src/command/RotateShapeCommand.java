package command;

import javafx.scene.shape.Shape;

/**
 *
 * @author group7
 */
public class RotateShapeCommand implements Command{

    private Shape selectedShape;
    private double angleToRotate;
    private double oldAngle;

    public RotateShapeCommand(Shape selectedShape, double angleToRotate) {
        this.selectedShape = selectedShape;
        this.angleToRotate = angleToRotate;
        this.oldAngle = selectedShape.getRotate();
    }
    
    @Override
    public void execute() throws Exception {
        this.selectedShape.setRotate(this.angleToRotate);
    }

    @Override
    public void undo() throws Exception {
        this.selectedShape.setRotate(this.oldAngle);
    }
    
}
