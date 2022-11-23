package projectse_group7;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

/**
 *
 * @author group7
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ToggleButton lineToggleButton;
    @FXML
    private ToggleButton rectangleToggleButton;
    @FXML
    private ToggleButton ellipseToggleButton;
    @FXML
    private Pane drawingPane;
    
    // toggle group variable, useful for selecting only 
    // once toggle button at a time
    ToggleGroup shapeToggleGroup;

    /**
     * Sets up all the toggle buttons in the "Shape selection" section 
     * by creating a toggle group.
     */
    private void setupShapeToggleButtons() {
        // creating a toggle group and inserting all the toggle buttons in it
        shapeToggleGroup = new ToggleGroup();
        lineToggleButton.setToggleGroup(shapeToggleGroup);
        rectangleToggleButton.setToggleGroup(shapeToggleGroup);
        ellipseToggleButton.setToggleGroup(shapeToggleGroup);
        
        // to make sure that a toggle button in the group is always selected
        shapeToggleGroup.selectedToggleProperty().addListener((obsValue, oldValue, newValue) -> {
            if (newValue == null)
                oldValue.setSelected(true);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // setting up all toggle buttons
        setupShapeToggleButtons();
    }

}
