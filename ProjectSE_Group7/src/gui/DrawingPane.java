package gui;

import command.*;
import javafx.animation.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
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
    private double xEndingPoint;
    private double yEndingPoint;
    private final double strokeWidth = 3;

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

    private Shape copiedShape = null;

    // move and resize 
    private boolean isMoving = false;
    double totalDeltaX = 0;
    double totalDeltaY = 0;
    private boolean isResizing = false;
    private Group bordersGroup;
    private Border border;
    private Border topLeftBorder;
    private Border topRightBorder;
    private Border bottomLeftBorder;
    private Border bottomRightBorder;

    //grid
    double gridSize = -1;
    Paint bg1 = Paint.valueOf("linear-gradient(from 0.0% 0.0% to 0.0% 100.0%, 0xffffff 0.0%, 0xffffff 100.0%)");
    BackgroundFill backgroundFill1 = new BackgroundFill(bg1, null, null);
    Canvas canvas = new Canvas();
    SnapshotParameters sp = new SnapshotParameters();

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
    public DrawingPane(Invoker invoker, ToggleButton lineToggleButton, ToggleButton rectangleToggleButton, ToggleButton ellipseToggleButton, ToggleButton selectShapeToggleButton, Circle outlineColorImage, Circle fillColorImage, Slider gridSlider, CheckBox gridCheckBox) {
        super();
        this.invoker = invoker;
        this.lineToggleButton = lineToggleButton;
        this.rectangleToggleButton = rectangleToggleButton;
        this.ellipseToggleButton = ellipseToggleButton;
        this.selectShapeToggleButton = selectShapeToggleButton;
        this.outlineColorImage = outlineColorImage;
        this.fillColorImage = fillColorImage;
        this.setPrefSize(1240, 718);
        this.setBackground(new Background(backgroundFill1));
        this.setStyle("-fx-border-color:grey;"
                + "-fx-border-radius:5;");
        setup();
        lineToggleButton.setOnAction(event -> deselectShape());
        rectangleToggleButton.setOnAction(event -> deselectShape());
        ellipseToggleButton.setOnAction(event -> deselectShape());

        isShapeSelected = new SimpleBooleanProperty(false);
        ContextMenu manageShape = new ContextMenu();

        // setting up delete menu item and its operations
        MenuItem deleteMenuItem = new MenuItem("Delete");
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
                deselectShape();
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
                deselectShape();
            } catch (Exception ex) {
            }
        });

        // setting up copy menu item and its operations
        MenuItem copyMenuItem = new MenuItem("Copy");
        copyMenuItem.disableProperty().bind(isShapeSelected.not());
        copyMenuItem.setOnAction(event -> {
            CopyShapeCommand copyShapeCommand = new CopyShapeCommand(this.selectedShape, this);
            try {
                invoker.execute(copyShapeCommand);
            } catch (Exception ex) {
            }
        });

        // setting up cut menu item and its operations
        MenuItem cutMenuItem = new MenuItem("Cut");
        cutMenuItem.disableProperty().bind(isShapeSelected.not());
        cutMenuItem.setOnAction(event -> {
            CutShapeCommand cutShapeCommand = new CutShapeCommand(this.selectedShape, this);
            try {
                invoker.execute(cutShapeCommand);
            } catch (Exception ex) {
            }
        });

        // setting up paste menu item and its operations
        MenuItem pasteMenuItem = new MenuItem("Paste");
        pasteMenuItem.setOnAction(event -> {
            PasteShapeCommand pasteShapeCommand = new PasteShapeCommand(this);
            try {
                invoker.execute(pasteShapeCommand);
            } catch (Exception ex) {
            }
        });

        // adding all the menu items in the manage shape ContextMenu
        manageShape.getItems().addAll(deleteMenuItem, toFrontMenuItem, toBackMenuItem, copyMenuItem, cutMenuItem, pasteMenuItem);
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
                        this.getChildren().remove(bordersGroup);
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
            border.setCursor(Cursor.MOVE);

            border.setOnMousePressed(event -> {
                event.consume();
            });

            border.setOnDragDetected(event -> {
                if (selectShapeToggleButton.isSelected()) {
                    isMoving = true;
                    xStartPoint = event.getX();
                    yStartPoint = event.getY();
                    xEndingPoint = event.getX();
                    yEndingPoint = event.getY();
                    totalDeltaX = 0;
                    totalDeltaY = 0;
                }

            });

            border.setOnMouseDragged(event -> {
                if (selectShapeToggleButton.isSelected() && isMoving) {
                    double x = event.getX();
                    double y = event.getY();
                    double deltaX = event.getX() - xEndingPoint;
                    double deltaY = event.getY() - yEndingPoint;

                    // this controll is for checking if the coordinate 
                    // is out of the borders of the drawingPane
                    if (border.getRectangleX() + deltaX > 0
                            && border.getRectangleY() + deltaY > 0
                            && border.getRectangleX() + deltaX + border.getRectangleWidth() < (this.getWidth())
                            && border.getRectangleY() + deltaY + border.getRectangleHeight() < (this.getHeight())) {

                        if (selectedShape.getClass() == Line.class) {
                            Line line = (Line) selectedShape;
                            line.moveOf(deltaX, deltaY);
                            moveBordersGroup(bordersGroup, deltaX, deltaY);
                            xEndingPoint = event.getX();
                            yEndingPoint = event.getY();
                        } else if (selectedShape.getClass() == Rectangle.class) {
                            Rectangle rectangle = (Rectangle) selectedShape;
                            rectangle.moveOf(deltaX, deltaY);
                            moveBordersGroup(bordersGroup, deltaX, deltaY);
                            xEndingPoint = event.getX();
                            yEndingPoint = event.getY();
                        } else if (selectedShape.getClass() == Ellipse.class) {
                            Ellipse ellipse = (Ellipse) selectedShape;
                            ellipse.moveOf(deltaX, deltaY);
                            moveBordersGroup(bordersGroup, deltaX, deltaY);
                            xEndingPoint = event.getX();
                            yEndingPoint = event.getY();
                        }

                        totalDeltaX = xEndingPoint - xStartPoint;
                        totalDeltaY = yEndingPoint - yStartPoint;
                    }

                    event.consume();
                }
            }
            );

            border.setOnMouseReleased(event -> {
                if (isMoving) {
                    isMoving = false;
                    if (selectedShape.getClass() == Line.class) {
                        Line line = (Line) selectedShape;
                        line.moveOf(-totalDeltaX, -totalDeltaY);
                    } else if (selectedShape.getClass() == Rectangle.class) {
                        Rectangle rectangle = (Rectangle) selectedShape;
                        rectangle.moveOf(-totalDeltaX, -totalDeltaY);
                    } else if (selectedShape.getClass() == Ellipse.class) {
                        Ellipse ellipse = (Ellipse) selectedShape;
                        ellipse.moveOf(-totalDeltaX, -totalDeltaY);
                    }

                    MoveShapeCommand moveShapeCommand = new MoveShapeCommand(selectedShape, totalDeltaX, totalDeltaY);
                    try {
                        invoker.execute(moveShapeCommand);
                    } catch (Exception ex) {
                    }
                }
            });

            // creating four corners of the border 
            topLeftBorder = new Border(selectedShape.getLayoutBounds().getMinX() - 5, selectedShape.getLayoutBounds().getMinY() - 5, 10, 10);
            topRightBorder = new Border(selectedShape.getLayoutBounds().getMinX() + selectedShape.getLayoutBounds().getWidth() - 5, selectedShape.getLayoutBounds().getMinY() - 5, 10, 10);
            bottomLeftBorder = new Border(selectedShape.getLayoutBounds().getMinX() - 5, selectedShape.getLayoutBounds().getMinY() + selectedShape.getLayoutBounds().getHeight() - 5, 10, 10);
            bottomRightBorder = new Border(selectedShape.getLayoutBounds().getMinX() + selectedShape.getLayoutBounds().getWidth() - 5, selectedShape.getLayoutBounds().getMinY() + selectedShape.getLayoutBounds().getHeight() - 5, 10, 10);
            topLeftBorder.setCursor(Cursor.NW_RESIZE);
            topRightBorder.setCursor(Cursor.NE_RESIZE);
            bottomLeftBorder.setCursor(Cursor.SW_RESIZE);
            bottomRightBorder.setCursor(Cursor.SE_RESIZE);

            topLeftBorder.setOutlineColor(Color.DARKCYAN);
            topRightBorder.setOutlineColor(Color.DARKCYAN);
            bottomLeftBorder.setOutlineColor(Color.DARKCYAN);
            bottomRightBorder.setOutlineColor(Color.DARKCYAN);
            topLeftBorder.setFillColor(Color.DARKCYAN);
            topRightBorder.setFillColor(Color.DARKCYAN);
            bottomLeftBorder.setFillColor(Color.DARKCYAN);
            bottomRightBorder.setFillColor(Color.DARKCYAN);

            topLeftBorder.setStrokeType(StrokeType.OUTSIDE);
            topRightBorder.setStrokeType(StrokeType.OUTSIDE);
            bottomLeftBorder.setStrokeType(StrokeType.OUTSIDE);
            bottomRightBorder.setStrokeType(StrokeType.OUTSIDE);

            // topleft corner event handlers
            topLeftBorder.setOnMousePressed(event -> {
                event.consume();
            });

            topLeftBorder.setOnDragDetected(event -> {
                if (selectShapeToggleButton.isSelected()) {
                    isResizing = true;
                    xStartPoint = event.getX();
                    yStartPoint = event.getY();
                }
            });

            topLeftBorder.setOnMouseDragged(event -> {
                if (selectShapeToggleButton.isSelected() && isResizing) {
                    if (selectedShape.getClass() == Line.class) {

                    } else if (selectedShape.getClass() == Rectangle.class) {
                        Rectangle rectangle = (Rectangle) selectedShape;
                        rectangle.setRectangleWidth(rectangle.getRectangleWidth() - (xStartPoint - event.getX()));
                        rectangle.setRectangleHeight(rectangle.getRectangleHeight() - (yStartPoint - event.getY()));
                    } else if (selectedShape.getClass() == Ellipse.class) {

                    }
                }
            });

            topLeftBorder.setOnMouseReleased(event -> {
                isResizing = false;
            });

            bordersGroup = new Group(border, topLeftBorder, topRightBorder, bottomLeftBorder, bottomRightBorder);

            // this part is to create the border animation
            double maxOffset
                    = border.getStrokeDashArray().stream().reduce(0d, (a, b) -> a + b);
            Timeline timeLine = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(border.strokeDashOffsetProperty(), 0, Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(2), new KeyValue(border.strokeDashOffsetProperty(), maxOffset, Interpolator.LINEAR))
            );
            timeLine.setCycleCount(Timeline.INDEFINITE);
            timeLine.play();

            this.getChildren().add(bordersGroup);

            isShapeSelected.set(true);
        }
    }

    /**
     * Moves all the components of the group passed as argument.
     *
     * @param group The group to move.
     * @param x The delta x to add to the position of each component of the
     * group.
     * @param y The delta y to add to the position of each component of the
     * group.
     */
    private void moveBordersGroup(Group group, double x, double y) {
        for (Node node : group.getChildren()) {
            Border border = (Border) node;
            border.moveOf(x, y);
        }
    }

    /**
     * Deselects the selected shape, if there is one.
     */
    public void deselectShape() {
        if (selectedShape != null) {
            selectedShape = null;
            this.getChildren().remove(bordersGroup);
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
     * @param fillColor The new fill color of the selected shape.
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

    /**
     * Returns the copied shape.
     *
     * @return The copied shape.
     */
    public Shape getCopiedShape() {
        return copiedShape;
    }

    /**
     * Sets the copied shape attribute to the one passed as argument.
     *
     * @param copiedShape The new copied shape.
     */
    public void setCopiedShape(Shape copiedShape) {
        this.copiedShape = copiedShape;
    }

    public void updateGrid(Slider gridSlider, CheckBox gridCheckBox) {
        double size = gridSlider.getValue();
        if (!gridCheckBox.isSelected() || size < 4) {
            size = 0;
        }
        if (gridSize != size) {
            if (size <= 0) {
                this.setBackground(new Background(backgroundFill1));
            } else {
                Paint bg2 = patternTransparent(size);
                BackgroundFill backgroundFill2 = new BackgroundFill(bg2, null, null);
                this.setBackground(new Background(backgroundFill1, backgroundFill2));
            }
            gridSize = size;
        }
    }

    private ImagePattern patternTransparent(double size) {
        canvas.setHeight(size);
        canvas.setWidth(size);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, size, size);
        gc.setFill(Color.BLACK);
        //gc.setLineWidth(2);
        gc.strokeLine(0, 0, 0, size);
        gc.strokeLine(1, 0, size, 0);
        sp.setFill(Color.TRANSPARENT);
        WritableImage image = canvas.snapshot(sp, null);
        return new ImagePattern(image, 0, 0, size, size, false);
    }
}
