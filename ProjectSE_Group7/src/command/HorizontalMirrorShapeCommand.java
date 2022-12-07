package command;

import javafx.scene.shape.Shape;


/**
 *
 * @author group7
 */
public class HorizontalMirrorShapeCommand implements Command{

    private Shape selectedShape;

    
    public HorizontalMirrorShapeCommand(Shape selectedShape){
        this.selectedShape = selectedShape;

    }
    
    /**
     * Method that does the horizontal mirroring of a shape
     * @throws Exception 
     */
    @Override
    public void execute() throws Exception {
        
        //if scaleX is 1.0, then I set it to -1.0 and viceversa
        if(this.selectedShape.getScaleX() == 1.0)
            this.selectedShape.setScaleX(-1.0);
        else
            this.selectedShape.setScaleX(1.0);
        
    }

    /**
     * Method that undoes the horizontal mirroring of a shape
     * @throws Exception 
     */
    @Override
    public void undo() throws Exception {
        
        //if scaleX is 1.0, then I set it to -1.0 and viceversa
        if(this.selectedShape.getScaleX() == 1.0)
            this.selectedShape.setScaleX(-1.0);
        else
            this.selectedShape.setScaleX(1.0);
    }
    
}
