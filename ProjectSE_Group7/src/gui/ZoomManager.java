package gui;

import javafx.scene.transform.Scale;

/**
 *
 * @author group7
 */
public class ZoomManager {

    public static void zoomIn(DrawingPane drawingPane){
        drawingPane.getTransforms().add(new Scale(2, 2, 0, 0));
    }
    
    public static void zoomOut(DrawingPane drawingPane){
        if(drawingPane.getTransforms().size() > 1){
            drawingPane.getTransforms().remove(drawingPane.getTransforms().size() - 1);
        }
    }
    
}
