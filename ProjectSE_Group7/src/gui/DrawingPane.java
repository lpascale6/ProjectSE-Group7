package gui;

import command.ChangeFillColorCommand;
import command.ChangeOutlineColorCommand;
import command.DeleteShapeCommand;
import command.AddShapeCommand;
import command.Invoker;
import command.ToTheBackCommand;
import command.ToTheFrontCommand;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;
import shape.Border;
import shape.Ellipse;
import shape.Line;
import shape.Rectangle;

/**
 *
 * @author group7
 */
public class DrawingPane extends Pane {

    Invoker invoker;

    Shape creatingShape;
    private boolean isDrawing = false;
    private double xStartPoint;
    private double yStartPoint;
    private double strokeWidth = 3;

    private Color selectedOutlineColor = Color.BLACK;
    private Color selectedFillColor = Color.WHITE;

    private ToggleButton lineToggleButton;
    private ToggleButton rectangleToggleButton;
    private ToggleButton ellipseToggleButton;
    private ToggleButton selectShapeToggleButton;

    private Circle outlineColorImage;
    private Circle fillColorImage;

    SimpleBooleanProperty isShapeSelected;
    private Shape selectedShape;
    private Border border;

    /**
     * Empty constructor of the DrawingPane class for test.
     */
    public DrawingPane() {
        super();
        invoker = Invoker.getInstance();
    }

    /**
     * DrawingPane constructor, it sets up all the attibututes and functions the
     * object needs.
     *
     * @param invoker The invoker of the command operations.
     * @param lineToggleButton Toggle button object that indicates if the user
     * wants to draw a line.
     * @param rectangleToggleButton Toggle button object that indicates if the
     * user wants to draw a rectangle.
     * @param ellipseToggleButton Toggle button object that indicates if the
     * user wants to draw a ellipse.
     * @param selectShapeToggleButton Toggle button object that indicates if the
     * user wants to perform operations on the shapes.
     * @param outlineColorImage Cirlce image that represents the selected
     * outline color.
     * @param fillColorImage Cirlce image that represents the selected fill
     * color.
     */
    public DrawingPane(Invoker invoker, ToggleButton lineToggleButton, ToggleButton rectangleToggleButton, ToggleButton ellipseToggleButton, ToggleButton selectShapeToggleButton, Circle outlineColorImage, Circle fillColorImage) {
        super();
        this.invoker = invoker;
        this.lineToggleButton = lineToggleButton;
        this.rectangleToggleButton = rectangleToggleButton;
        this.ellipseToggleButton = ellipseToggleButton;
        this.selectShapeToggleButton = selectShapeToggleButton;
        this.outlineColorImage = outlineColorImage;
        this.fillColorImage = fillColorImage;
        this.setPrefSize(990, 615);
        this.setStyle("-fx-background-color:white;"
                + "-fx-border-color:grey;"
                + "-fx-border-radius:5;");
        setup();
        lineToggleButton.setOnAction(event -> deselectShape());
        rectangleToggleButton.setOnAction(event -> deselectShape());
        ellipseToggleButton.setOnAction(event -> deselectShape());

        isShapeSelected = new SimpleBooleanProperty(false);
        ContextMenu manageShape = new ContextMenu();

        // setting up delete menu item and its operations
        MenuItem deleteMenuItem = new MenuItem("Delete");
        deleteMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN));
        deleteMenuItem.disableProperty().bind(isShapeSelected.not());
        deleteMenuItem.setOnAction(event -> {
            DeleteShapeCommand deleteShapeCommand = new DeleteShapeCommand(selectedShape, this);
            try {
                invoker.execute(deleteShapeCommand);
                deselectShape();
            } catch (Exception ex) {
            }
        });

        // setting up toFront menu item and its operations
        MenuItem toFrontMenuItem = new MenuItem("To the front");
        toFrontMenuItem.disableProperty().bind(isShapeSelected.not());
        toFrontMenuItem.setOnAction(event -> {
            ToTheFrontCommand toTheFrontCommand = new ToTheFrontCommand(selectedShape, this);
            try {
                invoker.execute(toTheFrontCommand);
            } catch (Exception ex) {
            }
        });

        // setting up toBack menu item and its operations
        MenuItem toBackMenuItem = new MenuItem("To the back");
        toBackMenuItem.disableProperty().bind(isShapeSelected.not());
        toBackMenuItem.setOnAction(event -> {
            ToTheBackCommand toTheBackCommand = new ToTheBackCommand(selectedShape, this);
            try {
                invoker.execute(toTheBackCommand);
            } catch (Exception ex) {
            }
        });

        // adding all the menu items in the manage shape ContextMenu
        manageShape.getItems().addAll(deleteMenuItem, toFrontMenuItem, toBackMenuItem);
        this.setOnContextMenuRequested(event -> {
            if (selectShapeToggleButton.isSelected()) {
                manageShape.show(this.getScene().getWindow(), event.getScreenX(), event.getScreenY());
            }
        });
    }

    /**
     * Sets the selectedOutlineColor to the new color passed as argument.
     *
     * @param color The new outline color
     */
    public void setOutlineColor(Color color) {
        this.selectedOutlineColor = color;
    }

    /**
     * Sets the selectedFillColor to the new color passed as argument.
     *
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
                    if (event.isSecondaryButtonDown()) {
                        try {
                            invoker.undo();
                        } catch (Exception ex) {
                        }
                        return;
                    }
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
                if (selectShapeToggleButton.isSelected()) {
                    // if there was already a selected shape, we reset it to its previous settings
                    if (selectedShape != null) {
                        this.getChildren().remove(border);
                    }
                    selectedShape = null;

                    isShapeSelected.set(false);
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
        creatingShape.setStroke(this.selectedOutlineColor);
        creatingShape.setStrokeWidth(this.strokeWidth);
        //DrawLineCommand 
        AddShapeCommand addShapeCommand = new AddShapeCommand(this, creatingShape);
        try {
            invoker.execute(addShapeCommand);
        } catch (Exception ex) {
        }
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
        AddShapeCommand addShapeCommand = new AddShapeCommand(this, creatingShape);
        try {
            invoker.execute(addShapeCommand);
        } catch (Exception ex) {
        }
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
        AddShapeCommand addShapeCommand = new AddShapeCommand(this, creatingShape);
        try {
            invoker.execute(addShapeCommand);
        } catch (Exception ex) {
        }
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

    /**
     * Selects a shape and creates a border around it.
     *
     * @param e The mouse event that generated the call to selectShape.
     */
    public void selectShape(MouseEvent e) {
        if (selectShapeToggleButton.isSelected()) {
            Shape shape = (Shape) e.getSource();
            // if there was already a selected shape, we reset it to its previous settings
            if (selectedShape != null) {
                this.getChildren().remove(border);
            }
            selectedShape = shape;
            outlineColorImage.setFill(selectedShape.getStroke());
            setOutlineColor((Color) selectedShape.getStroke());
            if (selectedShape.getClass() != Line.class) {
                fillColorImage.setFill(selectedShape.getFill());
                setFillColor((Color) selectedShape.getFill());
            }
            border = new Border(selectedShape.getLayoutBounds().getMinX(), selectedShape.getLayoutBounds().getMinY(), selectedShape.getLayoutBounds().getWidth(), selectedShape.getLayoutBounds().getHeight());
            border.setStrokeType(StrokeType.OUTSIDE);
            border.setFillColor(Color.TRANSPARENT);
            border.setStroke(Color.DARKCYAN);
            border.setStrokeWidth(strokeWidth);
            border.getStrokeDashArray().addAll(15d, 10d);

            // this part is to create the border animation
            double maxOffset
                    = border.getStrokeDashArray().stream().reduce(0d, (a, b) -> a + b);
            Timeline timeLine = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(border.strokeDashOffsetProperty(), 0, Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(2), new KeyValue(border.strokeDashOffsetProperty(), maxOffset, Interpolator.LINEAR))
            );
            timeLine.setCycleCount(Timeline.INDEFINITE);
            timeLine.play();

            this.getChildren().add(border);

            isShapeSelected.set(true);
        }
    }

    /**
     * Deselects the selected shape, if there is one.
     */
    public void deselectShape() {
        if (selectedShape != null) {
            selectedShape = null;
            this.getChildren().remove(border);
            border = null;
            isShapeSelected.set(false);
        }
    }

    /**
     * Clears the drawing by removing all the shapes in it and by removing all
     * the performed operations in the invoker.
     */
    public void clearDrawing() {
        this.getChildren().clear();
        invoker.clearStack();
        selectedShape = null;
        border = null;
    }

    /**
     * When a shape is selected, it changes the shape outline color to the one
     * selected.
     *
     * @param outlineColor The new outline color of the selected shape.
     */
    public void changeSelectedShapeOutlineColor(Color outlineColor) {
        if (selectedShape != null) {
            ChangeOutlineColorCommand changeOutlineColorCommand = new ChangeOutlineColorCommand(selectedShape, outlineColor);
            try {
                invoker.execute(changeOutlineColorCommand);
            } catch (Exception ex) {
            }
        }
    }

    /**
     * When a shape is selected, it changes the shape fill color to the one
     * selected.
     *
     * @param outlineColor The new fill color of the selected shape.
     */
    public void changeSelectedShapeFillColor(Color fillColor) {
        if (selectedShape != null) {
            ChangeFillColorCommand changeFillColorCommand = new ChangeFillColorCommand(selectedShape, fillColor);
            try {
                invoker.execute(changeFillColorCommand);
            } catch (Exception ex) {
            }
        }
    }

}
