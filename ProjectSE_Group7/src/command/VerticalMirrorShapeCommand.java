package command;

import javafx.scene.shape.Shape;

/**
 *
 * @author group7
 */
public class VerticalMirrorShapeCommand implements Command{
    
    private Shape selectedShape;

    
    public VerticalMirrorShapeCommand(Shape selectedShape){
        this.selectedShape = selectedShape;

    }
    
    @Override
    public void execute() throws Exception {
        
        //if scaleY is 1.0, then I set it to -1.0 and viceversa
        if(this.selectedShape.getScaleY() == 1.0)
            this.selectedShape.setScaleY(-1.0);
        else
            this.selectedShape.setScaleY(1.0);
        
    }

    @Override
    public void undo() throws Exception {
        
        //if scaleY is 1.0, then I set it to -1.0 and viceversa
        if(this.selectedShape.getScaleY() == 1.0)
            this.selectedShape.setScaleY(-1.0);
        else
            this.selectedShape.setScaleY(1.0);
    }
    
}
