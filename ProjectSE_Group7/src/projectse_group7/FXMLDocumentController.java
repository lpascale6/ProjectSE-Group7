package projectse_group7;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import shape.*;

/**
 *
 * @author group7
 */
public class FXMLDocumentController implements Initializable {

    // toggle group variable, useful for selecting only 
    // one toggle button at a time
    ToggleGroup shapeToggleGroup;
    @FXML
    private ToggleButton lineToggleButton;
    @FXML
    private ToggleButton rectangleToggleButton;
    @FXML
    private ToggleButton ellipseToggleButton;

    // color selection section variables
    ToggleGroup colorToggleGroup;
    Color selectedOutlineColor = Color.BLACK;
    Color selectedFillColor = Color.WHITE;
    @FXML
    private ToggleButton outlineColorToggleButton;
    @FXML
    private ToggleButton fillColorToggleButton;
    @FXML
    private Circle outlineColorImage;
    @FXML
    private Circle fillColorImage;

    // drawing variables
    Shape creatingShape;
    boolean isDrawing = false;
    double xStartPoint;
    double yStartPoint;
    @FXML
    private Pane drawingPane;

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
            selectedOutlineColor = color;
        } else {
            fillColorImage.setFill(color);
            selectedFillColor = color;
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

    private void setupDrawingPane() {
        drawingPane.setOnMousePressed(event -> {
            
                if (lineToggleButton.isSelected() || rectangleToggleButton.isSelected() || ellipseToggleButton.isSelected()) {
                    isDrawing = true;
                    xStartPoint = event.getX();
                    yStartPoint = event.getY();

                    if (lineToggleButton.isSelected()) {
                        creatingShape = new Line(xStartPoint, yStartPoint, xStartPoint, yStartPoint);
                        creatingShape.setStroke(selectedOutlineColor);
                        creatingShape.setStrokeWidth(5);
                        drawingPane.getChildren().add(creatingShape);
                    } else if (rectangleToggleButton.isSelected()) {
                        creatingShape = new Rectangle(xStartPoint, yStartPoint, 0, 0);
                        creatingShape.setStroke(selectedOutlineColor);
                        creatingShape.setFill(selectedFillColor);
                        creatingShape.setStrokeWidth(5);
                        drawingPane.getChildren().add(creatingShape);
                    } else if (ellipseToggleButton.isSelected()) {
                        creatingShape = new Ellipse(xStartPoint, yStartPoint, 0, 0);
                        creatingShape.setStroke(selectedOutlineColor);
                        creatingShape.setFill(selectedFillColor);
                        creatingShape.setStrokeWidth(5);
                        drawingPane.getChildren().add(creatingShape);
                    }
                }
            
        });

        drawingPane.setOnMouseReleased(event -> {
            if (lineToggleButton.isSelected() || rectangleToggleButton.isSelected() || ellipseToggleButton.isSelected()) {
                isDrawing = false;
                // if the starting coordinate of the shape corresponds to the ending coordinate,
                // the shape is removed from the drawingPane
                if (event.getX() == xStartPoint && event.getY() == yStartPoint) {
                    drawingPane.getChildren().remove(drawingPane.getChildren().size() - 1);
                }
            }
        });

        drawingPane.setOnMouseDragged(event -> {
            
                if (isDrawing) {
                    double x = event.getX();
                    double y = event.getY();
                    
                    if (x > 0 && y > 0 && x < drawingPane.getWidth() && y < drawingPane.getHeight()) {
                        if (lineToggleButton.isSelected()) {
                            Line line = (Line) creatingShape;
                            line.setEndingX(x);
                            line.setEndY(y);
                        } else if (rectangleToggleButton.isSelected()) {
                            Rectangle rectangle = (Rectangle) creatingShape;
                            // if the x coordinate is before the x vertex coordinate, 
                            // it becomes the new x coordinate for the vertex
                            if (x < xStartPoint) {
                                rectangle.setX_(x);
                            } else {
                                rectangle.setX_(xStartPoint);
                            }

                            // if the y coordinate is before the y vertex coordinate, 
                            // it becomes the new y coordinate for the vertex
                            if (y < yStartPoint) {
                                rectangle.setY_(y);
                            } else {
                                rectangle.setY_(yStartPoint);
                            }

                            rectangle.setWidth_(Math.abs(xStartPoint - x));
                            rectangle.setHeight_(Math.abs(yStartPoint - y));
                        } else if (ellipseToggleButton.isSelected()) {
                            Ellipse ellipse = (Ellipse) creatingShape;
                            //double xVertex = ellipse.getCenterHorizontalPosition() - ellipse.getWidth() / 2;
                            //double yVertex = ellipse.getCenterVerticalPosition() - ellipse.getHeight() / 2;
                            
                            if (x < xStartPoint) {
                                ellipse.setCenterHorizontalPosition((x + xStartPoint) / 2);
                            } else {
                                ellipse.setCenterHorizontalPosition((x + xStartPoint) / 2);
                            }

                            // if the y coordinate is before the y vertex coordinate, 
                            // it becomes the new y coordinate for the vertex
                            if (y < yStartPoint) {
                                ellipse.setCenterVerticalPosition((y + yStartPoint) / 2);
                            } else {
                                ellipse.setCenterVerticalPosition((y + yStartPoint) / 2);
                            }

                            ellipse.setWidth(Math.abs(xStartPoint - x) / 2);
                            ellipse.setHeight(Math.abs(yStartPoint - y) / 2);
                        }
                    }

                }
            
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // setting up all toggle buttons
        setupShapeToggleButtons();
        setupColorToggleButtons();
        setupDrawingPane();
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

}
