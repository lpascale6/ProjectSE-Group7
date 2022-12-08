package command;

import javafx.scene.shape.Shape;

/**
 *
 * @author group7
 */
public class VerticalMirrorShapeCommand implements Command{
    
    private Shape selectedShape;

    /**
    * Constructor method of class VerticalMirrorShapeCommand.
    *
     * @param selectedShape the shape to vertically mirror
    */
    public VerticalMirrorShapeCommand(Shape selectedShape){
        this.selectedShape = selectedShape;

    }
    
    /**
     * Method that does the vertical mirroring of a shape
     * @throws Exception 
     */
    @Override
    public void execute() throws Exception {
        
        //if scaleY is 1.0, then I set it to -1.0 and viceversa
        if(this.selectedShape.getScaleY() == 1.0)
            this.selectedShape.setScaleY(-1.0);
        else
            this.selectedShape.setScaleY(1.0);
        
    }

    /**
     * Method that undoes the vertical mirroring of a shape
     * @throws Exception 
     */
    @Override
    public void undo() throws Exception {
        
        //if scaleY is 1.0, then I set it to -1.0 and viceversa
        if(this.selectedShape.getScaleY() == 1.0)
            this.selectedShape.setScaleY(-1.0);
        else
            this.selectedShape.setScaleY(1.0);
    }
    
}
