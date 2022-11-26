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
    double strokeWidth = 3.0;   // value chosen as stroke width
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
        // setting up the event called on the drawingPane when the 
        // mouse button has been pressed
        drawingPane.setOnMousePressed(event -> {

            // starts to draw only if a shape toggle button is selected
            if (lineToggleButton.isSelected() || rectangleToggleButton.isSelected() || ellipseToggleButton.isSelected()) {
                isDrawing = true;
                // sets the starting coordinate of the new shape
                xStartPoint = event.getX();
                yStartPoint = event.getY();

                // sets up the new shape by creating a new shape and 
                // adding it to the drawingPane
                if (lineToggleButton.isSelected()) {
                    startDrawingLine(xStartPoint, yStartPoint);
                } else if (rectangleToggleButton.isSelected()) {
                    startDrawingRectangle(xStartPoint, yStartPoint);
                } else if (ellipseToggleButton.isSelected()) {
                    startDrawingEllipse(xStartPoint, yStartPoint);
                }
            }

        });

        // setting up the event called on the drawingPane when the mouse 
        // click has been released
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
        
        // setting up the event called on the drawingPane when the mouse 
        // is dragged over the drawingPane
        drawingPane.setOnMouseDragged(event -> {
            // if the user is drawing, it keeps updating the shape the user is 
            // creating and checks if the mouse is out of the border of the drawingPane
            if (isDrawing) {
                double x = event.getX();
                double y = event.getY();

                if (x > (strokeWidth / 2)
                        && y > (strokeWidth / 2)
                        && x < (drawingPane.getWidth() - (strokeWidth / 2))
                        && y < (drawingPane.getHeight() - (strokeWidth / 2))) {
                    if (lineToggleButton.isSelected()) {
                        drawLine(x, y);
                    } else if (rectangleToggleButton.isSelected()) {
                        drawRectangle(x, y);
                    } else if (ellipseToggleButton.isSelected()) {
                        drawEllipse(x, y);
                    }
                }

            }

        });
    }

    /**
     * Starts setting up a possible Line shape and insert the shape in the
     * drawing.
     *
     * @param x The starting x coordinate of the Line.
     * @param y The starting y coordinate of the Line.
     */
    private void startDrawingLine(double x, double y) {
        creatingShape = new Line(x, y, x, y);
        creatingShape.setStroke(selectedOutlineColor);
        creatingShape.setStrokeWidth(strokeWidth);
        drawingPane.getChildren().add(creatingShape);
    }

    /**
     * Starts setting up a possible Rectangle shape and insert the shape in the
     * drawing.
     *
     * @param x The starting x coordinate of the Rectangle.
     * @param y The starting y coordinate of the Rectangle.
     */
    private void startDrawingRectangle(double x, double y) {
        creatingShape = new Rectangle(x, y, 0, 0);
        creatingShape.setStroke(selectedOutlineColor);
        creatingShape.setFill(selectedFillColor);
        creatingShape.setStrokeWidth(strokeWidth);
        drawingPane.getChildren().add(creatingShape);
    }

    /**
     * Starts setting up a possible Ellipse shape and insert the shape in the
     * drawing.
     *
     * @param x The starting x coordinate of the Ellipse.
     * @param y The starting y coordinate of the Ellipse.
     */
    private void startDrawingEllipse(double x, double y) {
        creatingShape = new Ellipse(x, y, 0, 0);
        creatingShape.setStroke(selectedOutlineColor);
        creatingShape.setFill(selectedFillColor);
        creatingShape.setStrokeWidth(strokeWidth);
        drawingPane.getChildren().add(creatingShape);
    }

    /**
     * Draws the Line in the drawing.
     *
     * @param x The ending x coordinate of the Line.
     * @param y The ending y coordinate of the Line.
     */
    private void drawLine(double x, double y) {
        Line line = (Line) creatingShape;
        line.setEndingX(x);
        line.setEndY(y);
    }

    /**
     * Draws the Rectangle in the drawing.
     *
     * @param x The ending x coordinate of the Rectangle.
     * @param y The ending y coordinate of the Rectangle.
     */
    private void drawRectangle(double x, double y) {
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
    }

    /**
     * Draws the Ellipse in the drawing.
     *
     * @param x The ending x coordinate of the imaginary rectangle bounding the
     * Ellipse.
     * @param y The ending y coordinate of the imaginary rectangle bounding the
     * Ellipse.
     */
    private void drawEllipse(double x, double y) {
        Ellipse ellipse = (Ellipse) creatingShape;

        // if the x coordinate is before the x vertex coordinate, 
        // it becomes the new x coordinate for the vertex
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // setting up all toggle buttons
        setupShapeToggleButtons();
        setupColorToggleButtons();
        // setting up the drawing pane
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

    @FXML
    private void saveDrawing(ActionEvent event) {
        System.out.println("SAVED");
    }

    @FXML
    private void loadDrawing(ActionEvent event) {
        System.out.println("LOADED");
    }

    @FXML
    private void newDrawing(ActionEvent event) {
        drawingPane.getChildren().clear();
    }

}
