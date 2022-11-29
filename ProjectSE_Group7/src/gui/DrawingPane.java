package gui;

import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import shape.Ellipse;
import shape.Line;
import shape.Rectangle;

/**
 *
 * @author group7
 */
public class DrawingPane extends Pane{

    Shape creatingShape;
    private boolean isDrawing = false;
    private double xStartPoint;
    private double yStartPoint;
    private double strokeWidth = 3;
    
    private Color selectedOutlineColor = Color.BLACK;
    private Color selectedFillColor = Color.WHITE;
    
    private final ToggleButton lineToggleButton;
    private final ToggleButton rectangleToggleButton;
    private final ToggleButton ellipseToggleButton;
    private final ToggleButton selectShapeToggleButton;

    /**
     * DrawingPane constructor, it sets up all the attibututes and functions the object needs. 
     * @param lineToggleButton Toggle button object that indicates if the user wants to draw a line.
     * @param rectangleToggleButton Toggle button object that indicates if the user wants to draw a rectangle.
     * @param ellipseToggleButton Toggle button object that indicates if the user wants to draw a ellipse.
     */
    public DrawingPane(ToggleButton lineToggleButton, ToggleButton rectangleToggleButton, ToggleButton ellipseToggleButton, ToggleButton selectShapeToggleButton) {
        this.lineToggleButton = lineToggleButton;
        this.rectangleToggleButton = rectangleToggleButton;
        this.ellipseToggleButton = ellipseToggleButton;
        this.selectShapeToggleButton = selectShapeToggleButton;
        this.setPrefSize(990, 615);
        this.setStyle("-fx-background-color:white");
        this.setStyle("-fx-border-color:grey");
        this.setStyle("-fx-border-radius:5");
        this.setup();
    }

    /**
     * Sets the selectedOutlineColor to the new color passed as argument.
     * @param color The new outline color
     */
    public void setOutlineColor(Color color) {
        this.selectedOutlineColor = color;
    }
    
    /**
     * Sets the selectedFillColor to the new color passed as argument.
     * @param color The new fill color
     */
    public void setFillColor(Color color) {
        this.selectedFillColor = color;
    }
    
    /**
     * Sets up all the mouse event useful for drawing on this pane.
     */
    private void setup() {
        // setting up the event called on the drawingPane when the 
        // mouse button has been pressed
        this.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
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
            }
        });

        // setting up the event called on the drawingPane when the mouse 
        // click has been released
        this.setOnMouseReleased(event -> {
            if (event.isPrimaryButtonDown()) {
                if (lineToggleButton.isSelected() || rectangleToggleButton.isSelected() || ellipseToggleButton.isSelected()) {
                    isDrawing = false;
                    // if the starting coordinate of the shape corresponds to the ending coordinate,
                    // the shape is removed from the drawingPane
                    if (event.getX() == xStartPoint && event.getY() == yStartPoint) {
                        this.getChildren().remove(this.getChildren().size() - 1);
                    }
                }
            }
        });

        // setting up the event called on the drawingPane when the mouse 
        // is dragged over the drawingPane
        this.setOnMouseDragged(event -> {
            if (event.isPrimaryButtonDown()) {
                // if the user is drawing, it keeps updating the shape the user is 
                // creating and checks if the mouse is out of the border of the drawingPane
                if (isDrawing) {
                    double x = event.getX();
                    double y = event.getY();

                    // this controll is for checking if the coordinate 
                    // is out of the borders of the drawingPane
                    if (x > (strokeWidth / 2)
                            && y > (strokeWidth / 2)
                            && x < (this.getWidth() - (strokeWidth / 2))
                            && y < (this.getHeight() - (strokeWidth / 2))) {
                        if (lineToggleButton.isSelected()) {
                            drawLine(x, y);
                        } else if (rectangleToggleButton.isSelected()) {
                            drawRectangle(x, y);
                        } else if (ellipseToggleButton.isSelected()) {
                            drawEllipse(x, y);
                        }
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
        creatingShape.setOnMouseEntered(e -> {
            if (selectShapeToggleButton.isSelected()){
                Shape shape = (Shape) e.getSource();
                shape.setStroke(Color.BLUE);
            }
        });
        creatingShape.setOnMouseExited(e -> {
            if (selectShapeToggleButton.isSelected()){
                Shape shape = (Shape) e.getSource();
                shape.setStroke(selectedOutlineColor);
            }
        });
        creatingShape.setOnMouseClicked(e -> {
            if (selectShapeToggleButton.isSelected()){
                Shape shape = (Shape) e.getSource();
                //TODO: funzione select
                System.out.println("Cliccato");
            }
        });
        this.getChildren().add(creatingShape);
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
        creatingShape.setOnMouseEntered(e -> {
            if (selectShapeToggleButton.isSelected()){
                Shape shape = (Shape) e.getSource();
                shape.setStroke(Color.BLUE);
            }
        });
        creatingShape.setOnMouseExited(e -> {
            if (selectShapeToggleButton.isSelected()){
                Shape shape = (Shape) e.getSource();
                shape.setStroke(selectedOutlineColor);
            }
        });
        creatingShape.setOnMouseClicked(e -> {
            if (selectShapeToggleButton.isSelected()){
                Shape shape = (Shape) e.getSource();
                //TODO: funzione select
                System.out.println("Cliccato");
            }
        });
        this.getChildren().add(creatingShape);
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
        
        creatingShape.setOnMouseEntered(e -> {
            if (selectShapeToggleButton.isSelected()){
                Shape shape = (Shape) e.getSource();
                shape.setStroke(Color.BLUE);
                shape.setStrokeDashOffset(5);
            }
        });
        creatingShape.setOnMouseExited(e -> {
            if (selectShapeToggleButton.isSelected()){
                Shape shape = (Shape) e.getSource();
                shape.setStroke(selectedOutlineColor);
            }
        });
        creatingShape.setOnMouseClicked(e -> {
            if (selectShapeToggleButton.isSelected()){
                Shape shape = (Shape) e.getSource();
                //TODO: funzione select
                System.out.println("Cliccato");
            }
        });
        this.getChildren().add(creatingShape);
    }

    /**
     * Draws the Line in the drawing.
     *
     * @param x The ending x coordinate of the Line.
     * @param y The ending y coordinate of the Line.
     */
    private void drawLine(double x, double y) {
        Line line = (Line) creatingShape;
        line.setLineEndingX(x);
        line.setLineEndingY(y);
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
            rectangle.setRectangleX(x);
        } else {
            rectangle.setRectangleX(xStartPoint);
        }

        // if the y coordinate is before the y vertex coordinate, 
        // it becomes the new y coordinate for the vertex
        if (y < yStartPoint) {
            rectangle.setRectangleY(y);
        } else {
            rectangle.setRectangleY(yStartPoint);
        }

        rectangle.setRectangleWidth(Math.abs(xStartPoint - x));
        rectangle.setRectangleHeight(Math.abs(yStartPoint - y));
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
            ellipse.setEllipseCenterX((x + xStartPoint) / 2);
        } else {
            ellipse.setEllipseCenterX((x + xStartPoint) / 2);
        }

        // if the y coordinate is before the y vertex coordinate, 
        // it becomes the new y coordinate for the vertex
        if (y < yStartPoint) {
            ellipse.setEllipseCenterY((y + yStartPoint) / 2);
        } else {
            ellipse.setEllipseCenterY((y + yStartPoint) / 2);
        }

        ellipse.setEllipseRadiusX(Math.abs(xStartPoint - x) / 2);
        ellipse.setEllipseRadiusY(Math.abs(yStartPoint - y) / 2);
    }
}
