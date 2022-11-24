package projectse_group7;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

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
    @FXML
    private ToggleButton outlineColorToggleButton;
    @FXML
    private ToggleButton fillColorToggleButton;
   
    // toggle group variable, useful for selecting only 
    // once toggle button at a time
    ToggleGroup shapeToggleGroup;
    
    // color selection section variables
    ToggleGroup colorToggleGroup;
    Color selectedOutlineColor = Color.BLACK;
    Color selectedFillColor = Color.WHITE;

    // drawing variables
    Shape creatingShape;
    boolean isDrawing = false;
    

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

        drawingPane.setOnMouseDragged(event -> {

            if (lineToggleButton.isSelected()) {
                if (event.getX() < 0 || event.getY() < 0) {
                    event.consume();
                } else {
                    if (!isDrawing) {
                        Line line = new Line();
                        line.setStartX(event.getX());
                        line.setStartY(event.getY());
                        line.setStroke(selectedOutlineColor);
                        
                        creatingShape = line;
                        //drawingPane.getChildren().add((Shape) line);
                        isDrawing = true;
                    } else {
                        drawingPane.getChildren().remove(creatingShape);
                        Line line = (Line) creatingShape;
                        //Line line = (Line) drawingPane.getChildren().get(drawingPane.getChildren().size() - 1);
                        
                        line.setEndX(event.getX());
                        line.setEndY(event.getY());
                        drawingPane.getChildren().add((Shape) line);
                    }
                }

            } else if (rectangleToggleButton.isSelected()) {
                if (!isDrawing) {
                    Rectangle rectangle = new Rectangle();
                    rectangle.setX(event.getX());
                    rectangle.setY(event.getY());
                    rectangle.setStroke(selectedOutlineColor);
                    rectangle.setFill(selectedFillColor);

                    drawingPane.getChildren().add((Shape) rectangle);
                    isDrawing = true;
                } else {
                    Rectangle rectangle = (Rectangle) drawingPane.getChildren().get(drawingPane.getChildren().size() - 1);
                    rectangle.setWidth(event.getX() - rectangle.getX());
                    rectangle.setHeight(event.getY() - rectangle.getY());
                }

            } else if (ellipseToggleButton.isSelected()) {
                if (!isDrawing) {
                    Ellipse ellipse = new Ellipse();
                    ellipse.setStroke(Color.BLACK);
                    ellipse.setOnMouseEntered(e -> {
                        ellipse.setFill(Color.RED);
                    });
                    ellipse.setOnMouseExited(e -> {
                        ellipse.setFill(Color.WHITE);
                    });
                    ellipse.setCenterX(event.getX());
                    ellipse.setCenterY(event.getY());

                    drawingPane.getChildren().add((Shape) ellipse);
                    isDrawing = true;
                } else {
                    Ellipse ellipse = (Ellipse) drawingPane.getChildren().get(drawingPane.getChildren().size() - 1);
                    ellipse.setRadiusX(event.getX() - ellipse.getCenterX());
                    ellipse.setRadiusY(event.getY() - ellipse.getCenterY());
                }
            }
        });

        drawingPane.setOnMouseReleased(event -> {
            isDrawing = false;
        });

        drawingPane.setOnMouseExited(event -> {
            isDrawing = false;
            event.consume();
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // setting up all toggle buttons
        setupShapeToggleButtons();
        setupColorToggleButtons();
        setupDrawingPane();
    }

    @FXML
    private void setRedColor(ActionEvent event) {
        if(colorToggleGroup.getSelectedToggle() == outlineColorToggleButton) {
            System.out.println("Outline color changed to RED");
            selectedOutlineColor = Color.RED;
        }
        else {
            System.out.println("Fill color changed to RED");
            selectedFillColor = Color.RED;
        }
    }

    @FXML
    private void setBlueColor(ActionEvent event) {
        if(colorToggleGroup.getSelectedToggle() == outlineColorToggleButton) {
            System.out.println("Outline color changed to BLUE");
            selectedOutlineColor = Color.BLUE;
        }
        else {
            System.out.println("Fill color changed to BLUE");
            selectedFillColor = Color.BLUE;
        }
    }

    @FXML
    private void setYellowColor(ActionEvent event) {
        if(colorToggleGroup.getSelectedToggle() == outlineColorToggleButton) {
            System.out.println("Outline color changed to YELLOW");
            selectedOutlineColor = Color.YELLOW;
        }
        else {
            System.out.println("Fill color changed to YELLLOW");
            selectedFillColor = Color.YELLOW;
        }
    }

    @FXML
    private void setBlackColor(ActionEvent event) {
        if(colorToggleGroup.getSelectedToggle() == outlineColorToggleButton) {
            System.out.println("Outline color changed to BLACK");
            selectedOutlineColor = Color.BLACK;
        }
        else {
            System.out.println("Fill color changed to BLACK");
            selectedFillColor = Color.BLACK;
        }
    }

}
