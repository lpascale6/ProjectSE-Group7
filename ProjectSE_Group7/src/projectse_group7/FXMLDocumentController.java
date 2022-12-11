package projectse_group7;

import command.Invoker;
import command.LoadCommand;
import command.SaveCommand;
import gui.DrawingPane;
import gui.GridManager;
import gui.ZoomManager;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
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
    private MenuItem undoMenuItem;

    // toggle group variable, useful for selecting only 
    // one toggle button at a time
    ToggleGroup shapeToggleGroup;

    // Shapes Selection section variables
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

    // Colors section variables
    ToggleGroup colorToggleGroup;
    @FXML
    private ToggleButton outlineColorToggleButton;
    @FXML
    private ToggleButton fillColorToggleButton;
    @FXML
    private Circle outlineColorImage;
    @FXML
    private Circle fillColorImage;
    @FXML
    private ColorPicker colorPicker;

    // drawing variables
    private DrawingPane drawingPane;

    // Tools section variables
    @FXML
    private CheckBox gridCheckBox;
    @FXML
    private Slider gridSlider;
    @FXML
    private Label zoomLabel;
    @FXML
    private Button zoomIn;
    @FXML
    private Button zoomOut;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private MenuItem saveMenuItem;
    @FXML
    private MenuItem loadMenuItem;
    @FXML
    private MenuItem newDrawingMenuItem;

    // Text Setup section variables
    @FXML
    private TextField textTextField;
    @FXML
    private ComboBox<Integer> fontDimensionComboBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        invoker = Invoker.getInstance();

        drawingPane = new DrawingPane(invoker, lineToggleButton, rectangleToggleButton,
                ellipseToggleButton, selectShapeToggleButton, polygonToggleButton,
                textToggleButton, outlineColorImage, fillColorImage, gridSlider,
                gridCheckBox, textTextField, fontDimensionComboBox);
        anchorPane.getChildren().add(drawingPane);

        setupGridManager();
        setupZoomManager();
        setupAccelerators();
        setupShapeToggleButtons();
        setupColorToggleButtons();
        setupComboBox();
    }

    private void setupComboBox() {
        for (int i = 8; i <= 100; i++) {
            fontDimensionComboBox.getItems().add(i);
        }
        fontDimensionComboBox.getSelectionModel().selectFirst();
    }

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
     * Sets up the drawing pane for the grid functionality.
     */
    private void setupGridManager() {
        gridCheckBox.selectedProperty().addListener((v, o, n) -> {
            gridSlider.setDisable(!n.booleanValue());
            GridManager.updateGrid(drawingPane, gridSlider, gridCheckBox);
        });
        gridSlider.valueProperty().addListener((v, o, n) -> {
            GridManager.updateGrid(drawingPane, gridSlider, gridCheckBox);
        });
    }

    /**
     * Sets up the drawing pane for the zoom functionality.
     */
    private void setupZoomManager() {
        ZoomManager.setupZoomManager(anchorPane, drawingPane, zoomLabel);
        DoubleProperty scale = ZoomManager.getScale();
        zoomIn.disableProperty().bind(scale.greaterThanOrEqualTo(2.0));
        zoomOut.disableProperty().bind(scale.lessThanOrEqualTo(0.7));
    }

    /**
     * Sets up shortcuts to use menu items.
     */
    private void setupAccelerators() {
        // to abilitate the user to use ctrl+z shortcut
        undoMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
        // to abilitate the user to use ctrl+s shortcut
        saveMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        // to abilitate the user to use ctrl+l shortcut
        loadMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN));
        // to abilitate the user to use ctrl+n shortcut
        newDrawingMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
    }

    /**
     * Sets up the handle method of the color picker and all the toggle buttons
     * in the "Color selection" section by creating a toggle group.
     */
    private void setupColorToggleButtons() {
        colorPicker.setOnAction(event -> {
            changeToggleButtonColor(colorPicker.getValue());
        });
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
        drawingPane.checkPolygonCreation();

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

    /**
     * Method to increase the zoom.
     *
     * @param event
     */
    @FXML
    private void zoomIn(ActionEvent event) {
        ZoomManager.zoomIn(drawingPane);
    }

    /**
     * Method to reduce the zoom.
     *
     * @param event
     */
    @FXML
    private void zoomOut(ActionEvent event) {
        ZoomManager.zoomOut(drawingPane);
    }

    /**
     * Shows to the user the steps to draw a Line.
     *
     * @param event The event generated by the menu item.
     */
    @FXML
    private void helpLine(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("How to draw a line");
        alert.setContentText("If you want to draw a line, select the appropriate toggle button in the \"Shapes Selection\" section."
                + "\nAfter that, left click and drag on the drawing pad to insert the desired line."
                + "\nWhen you're satisfied, release the left mouse button to complete the creation.");
        alert.showAndWait();
    }

    /**
     * Shows to the user the steps to draw a Rectangle.
     *
     * @param event The event generated by the menu item.
     */
    @FXML
    private void helpRectangle(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("How to draw a rectangle");
        alert.setContentText("If you want to draw a rectangle, select the appropriate toggle button in the \"Shapes Selection\" section."
                + "\nAfter that, left click and drag on the drawing pad to insert the desired rectangle."
                + "\nWhen you're satisfied, release the left mouse button to complete the creation.");
        alert.showAndWait();
    }

    /**
     * Shows to the user the steps to draw an Ellipse.
     *
     * @param event The event generated by the menu item.
     */
    @FXML
    private void helpEllipse(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("How to draw an ellipse");
        alert.setContentText("If you want to draw a ellipse, select the appropriate toggle button in the \"Shapes Selection\" section."
                + "\nAfter that, left click and drag on the drawing pad to insert the desired ellipse."
                + "\nWhen you're satisfied, release the left mouse button to complete the creation.");
        alert.showAndWait();
    }

    /**
     * Shows to the user the steps to draw a Polygon.
     *
     * @param event The event generated by the menu item.
     */
    @FXML
    private void helpPolygon(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("How to draw a polygon");
        alert.setContentText("If you want to draw a polygon, select the appropriate toggle button in the \"Shapes Selection\" section."
                + "\nAfter that, click on the drawing pad to insert a new vertex of the polygon until you're done."
                + "\nWhen you're satisfied, click the right mouse button to finish drawing."
                + "\nNote that by inserting only two vertices, the figure will not be inserted.");
        alert.showAndWait();
    }

    /**
     * Shows to the user the steps to insert a Text.
     *
     * @param event The event generated by the menu item.
     */
    @FXML
    private void helpText(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("How to insert text");
        alert.setContentText("If you want to put text in the drawing box, select the appropriate toggle button in the \"Shapes Selection\" section."
                + "\nSubsequently, move to the \"Text Setup\" section to choose the font to use and the text to insert and, optionally, choose the color in the \"Colors\" section."
                + "\nAfter performing these steps, click on the drawing pad and the text you composed earlier will appear."
                + "\nNow you can change its color, size, rotate it, mirror it and so on.");
        alert.showAndWait();
    }

}
