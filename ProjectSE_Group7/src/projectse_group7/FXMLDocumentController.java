package projectse_group7;

import command.Invoker;
import command.LoadCommand;
import command.SaveCommand;
import gui.DrawingPane;
import gui.GridManager;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;

/**
 *
 * @author group7
 */
public class FXMLDocumentController implements Initializable {

    // invoker that contains and executes all the operations on the drawing
    private Invoker invoker;
    @FXML
    private MenuBar menuBar;
    @FXML
    private ScrollPane scrollPane;

    // toggle group variable, useful for selecting only 
    // one toggle button at a time
    ToggleGroup shapeToggleGroup;
    @FXML
    private ToggleButton lineToggleButton;
    @FXML
    private ToggleButton rectangleToggleButton;
    @FXML
    private ToggleButton ellipseToggleButton;
    @FXML
    private ToggleButton selectShapeToggleButton;
    @FXML
    private ToggleButton polygonToggleButton;
    @FXML
    private ToggleButton textToggleButton;

    // color selection section variables
    ToggleGroup colorToggleGroup;
    @FXML
    private ToggleButton outlineColorToggleButton;
    @FXML
    private ToggleButton fillColorToggleButton;
    @FXML
    private Circle outlineColorImage;
    @FXML
    private Circle fillColorImage;

    // drawing variables
    private DrawingPane drawingPane;
    @FXML
    private MenuItem undoMenuItem;
    @FXML
    private CheckBox gridCheckBox;
    @FXML
    private Slider gridSlider;

    /**
     * Sets up all the toggle buttons in the "Shape selection" section by
     * creating a toggle group.
     */
    private void setupShapeToggleButtons() {
        // creating a toggle group and inserting all the toggle buttons in it
        shapeToggleGroup = new ToggleGroup();
        lineToggleButton.setToggleGroup(shapeToggleGroup);
        rectangleToggleButton.setToggleGroup(shapeToggleGroup);
        ellipseToggleButton.setToggleGroup(shapeToggleGroup);
        selectShapeToggleButton.setToggleGroup(shapeToggleGroup);
        polygonToggleButton.setToggleGroup(shapeToggleGroup);
        textToggleButton.setToggleGroup(shapeToggleGroup);

        // to make sure that a toggle button in the group is always selected
        shapeToggleGroup.selectedToggleProperty().addListener((obsValue, oldValue, newValue) -> {
            if (newValue == null) {
                oldValue.setSelected(true);
            }
        });
    }

    /**
     * Change the selected toggle button color to the one passed as argument, it
     * also updates the color variable.
     *
     * @param color The new selected color.
     */
    private void changeToggleButtonColor(Color color) {
        if (colorToggleGroup.getSelectedToggle() == outlineColorToggleButton) {
            outlineColorImage.setFill(color);
            drawingPane.setOutlineColor(color);
            drawingPane.changeSelectedShapeOutlineColor(color);
        } else {
            fillColorImage.setFill(color);
            drawingPane.setFillColor(color);
            drawingPane.changeSelectedShapeFillColor(color);
        }
    }

    /**
     * Sets up all the toggle buttons in the "Color selection" section by
     * creating a toggle group.
     */
    private void setupColorToggleButtons() {
        colorToggleGroup = new ToggleGroup();
        outlineColorToggleButton.setToggleGroup(colorToggleGroup);
        fillColorToggleButton.setToggleGroup(colorToggleGroup);

        // to make sure that a toggle button in the group is always selected
        colorToggleGroup.selectedToggleProperty().addListener((obsValue, oldValue, newValue) -> {
            if (newValue == null) {
                oldValue.setSelected(true);
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        invoker = Invoker.getInstance();

        // setting up the drawing pane
        drawingPane = new DrawingPane(invoker, lineToggleButton, rectangleToggleButton, ellipseToggleButton, selectShapeToggleButton, polygonToggleButton, textToggleButton, outlineColorImage, fillColorImage, gridSlider, gridCheckBox);
        gridCheckBox.selectedProperty().addListener((v, o, n) -> {
            gridSlider.setDisable(!n.booleanValue());
            GridManager.updateGrid(drawingPane, gridSlider, gridCheckBox);
        });
        gridSlider.valueProperty().addListener((v, o, n) -> {
            GridManager.updateGrid(drawingPane, gridSlider, gridCheckBox);
        });
        scrollPane.setContent(drawingPane);

        // to abilitate the user to use ctrl+z shortcut
        undoMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));

        // setting up all toggle buttons
        setupShapeToggleButtons();
        setupColorToggleButtons();
    }

    /**
     * Changes the selected toggle button color to black and updates the
     * selected color variable.
     *
     * @param event The event created when the button is clicked.
     */
    @FXML
    private void setBlackColor(ActionEvent event) {
        changeToggleButtonColor(Color.BLACK);
    }

    /**
     * Changes the selected toggle button color to grey and updates the selected
     * color variable.
     *
     * @param event The event created when the button is clicked.
     */
    @FXML
    private void setGreyColor(ActionEvent event) {
        changeToggleButtonColor(Color.GREY);
    }

    /**
     * Changes the selected toggle button color to white and updates the
     * selected color variable.
     *
     * @param event The event created when the button is clicked.
     */
    @FXML
    private void setWhiteColor(ActionEvent event) {
        changeToggleButtonColor(Color.WHITE);
    }

    /**
     * Changes the selected toggle button color to blue and updates the selected
     * color variable.
     *
     * @param event The event created when the button is clicked.
     */
    @FXML
    private void setBlueColor(ActionEvent event) {
        changeToggleButtonColor(Color.BLUE);
    }

    /**
     * Changes the selected toggle button color to red and updates the selected
     * color variable.
     *
     * @param event The event created when the button is clicked.
     */
    @FXML
    private void setRedColor(ActionEvent event) {
        changeToggleButtonColor(Color.RED);
    }

    /**
     * Changes the selected toggle button color to orange and updates the
     * selected color variable.
     *
     * @param event The event created when the button is clicked.
     */
    @FXML
    private void setOrangeColor(ActionEvent event) {
        changeToggleButtonColor(Color.ORANGE);
    }

    /**
     * Changes the selected toggle button color to yellow and updates the
     * selected color variable.
     *
     * @param event The event created when the button is clicked.
     */
    @FXML
    private void setYellowColor(ActionEvent event) {
        changeToggleButtonColor(Color.YELLOW);
    }

    /**
     * Changes the selected toggle button color to green and updates the
     * selected color variable.
     *
     * @param event The event created when the button is clicked.
     */
    @FXML
    private void setGreenColor(ActionEvent event) {
        changeToggleButtonColor(Color.GREEN);
    }

    /**
     * Saves the current state of the drawing into a file chosen by the user.
     *
     * @param event The event created when the load menu item is clicked.
     */
    @FXML
    private void saveDrawing(ActionEvent event) {
        // setting up the file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as...");

        // setting up extension filter to save only a .txt file
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File selectedFile = fileChooser.showSaveDialog(menuBar.getScene().getWindow());

        if (selectedFile != null) {

            // creating a list containing all the shapes of the drawing
            ArrayList<Shape> shapeList = new ArrayList<>();
            drawingPane.deselectShape();
            for (Object shape : drawingPane.getChildren().toArray()) {
                shapeList.add((Shape) shape);
            }
            SaveCommand saveCommand = new SaveCommand(selectedFile, shapeList);
            try {
                saveCommand.execute();
            } catch (Exception ex) {
                // an error occurred during the save operation
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error while saving the file");
                alert.setHeaderText("Error");
                alert.setContentText("An error occurred while trying to save the file");
                alert.showAndWait();
            }

        }
    }

    /**
     * Loads a drawing from a file chosen by the user.
     *
     * @param event The event created when the load menu item is clicked.
     */
    @FXML
    private void loadDrawing(ActionEvent event) {
        // setting up the file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load from...");

        // setting up extension filter to save only a .txt file
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File selectedFile = fileChooser.showOpenDialog(menuBar.getScene().getWindow());

        if (selectedFile != null) {
            LoadCommand loadCommand = new LoadCommand(selectedFile, drawingPane);
            try {
                drawingPane.deselectShape();
                loadCommand.execute();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("File loaded successfully");
                alert.setHeaderText("Information");
                alert.setContentText("The file has been loaded successfully");
                alert.showAndWait();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error while loading from file");
                alert.setHeaderText("Error");
                alert.setContentText("An error occurred while trying to load the file: wrong file representation");
                alert.showAndWait();
            }
        }
    }

    /**
     * Removes all the shapes from the drawing.
     *
     * @param event The event created when the clear drawing menu item is
     * clicked.
     */
    @FXML
    private void newDrawing(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Clear Drawing");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("After clearing the drawing you will never be able to go back.");
        if (alert.showAndWait().get() == ButtonType.OK) {
            drawingPane.clearDrawing();
        }

    }

    /**
     * Undoes the last performed operation on the drawing.
     *
     * @param event The event created when the undo menu item is clicked or when
     * CTRL+Z is pressed.
     */
    @FXML
    private void undo(ActionEvent event) {

        if (!invoker.getStack().isEmpty()) {
            try {
                invoker.undo();
                drawingPane.deselectShape();
            } catch (Exception ex) {
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No operations to undo");
            alert.setHeaderText("There are no operations to undo.");
            alert.setContentText("");
            alert.showAndWait();

        }
    }

}
