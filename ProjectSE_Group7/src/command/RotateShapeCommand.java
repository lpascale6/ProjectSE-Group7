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

    /**
     * Constructor method of class RotateShapeCommand.
     *
     * @param selectedShape the shape to rotate
     * @param angleToRotate the angle value for the rotation
     */
    public RotateShapeCommand(Shape selectedShape, double angleToRotate) {
        this.selectedShape = selectedShape;
        this.angleToRotate = angleToRotate;
        this.oldAngle = selectedShape.getRotate();
    }
    
    /**
     * method that rotates a shape of the chosen angle.
     * @throws Exception 
     */
    @Override
    public void execute() throws Exception {
        this.selectedShape.setRotate(this.angleToRotate);
    }

    /**
     * method that undoes the rotation of a shape.
     *
     * @throws Exception
     */
    @Override
    public void undo() throws Exception {
        this.selectedShape.setRotate(this.oldAngle);
    }
    
}
