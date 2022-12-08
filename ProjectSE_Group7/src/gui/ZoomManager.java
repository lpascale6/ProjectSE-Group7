package gui;

import javafx.scene.control.Alert;
import javafx.scene.transform.Scale;

/**
 *
 * @author group7
 */
public class ZoomManager {

    /**
     * Method to increase the zoom.
     * @param drawingPane the drawing pane to zoom
     */
    public static void zoomIn(DrawingPane drawingPane){
        if(drawingPane.getTransforms().size() > 0){
            drawingPane.getTransforms().remove(drawingPane.getTransforms().size() - 1);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION");
            alert.setHeaderText("You cannot zoom in more than this.");
            alert.setContentText("");
            alert.showAndWait();
        }
    }
    
    /**
     * Method to reduce the zoom.
     * @param drawingPane the drawing pane to zoom
     */
    public static void zoomOut(DrawingPane drawingPane){
        if(drawingPane.getTransforms().size() <= 9){
            drawingPane.getTransforms().add(new Scale(1/1.13, 1/1.13, 0, 0));
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION");
            alert.setHeaderText("You cannot zoom out more than this.");
            alert.setContentText("");
            alert.showAndWait();
        }
        
    }
    
}
