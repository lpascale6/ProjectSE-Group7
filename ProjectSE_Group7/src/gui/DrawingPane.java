package gui;

import command.*;
import java.util.NoSuchElementException;
import java.util.Optional;
import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import shape.Border;
import shape.Polygon;
import state.*;

/**
 *
 * @author group7
 */
public class DrawingPane extends Pane {

    Invoker invoker;

    DrawState drawState = new DrawLineState(this);
    private boolean isDrawing = false;
    private boolean isDrawingAPolygon = false;
    private double xStartPoint;
    private double yStartPoint;
    TextField textTextField;
    ComboBox<Integer> fontDimensionComboBox;

    public final static double strokeWidth = 3;

    private Color selectedOutlineColor = Color.BLACK;
    private Color selectedFillColor = Color.WHITE;

    private ToggleButton lineToggleButton;
    private ToggleButton rectangleToggleButton;
    private ToggleButton ellipseToggleButton;
    private ToggleButton selectShapeToggleButton;
    private ToggleButton polygonToggleButton;
    private ToggleButton textToggleButton;

    private Circle outlineColorImage;
    private Circle fillColorImage;

    SimpleBooleanProperty isShapeSelected;
    private Shape selectedShape;
    private double oldSelectedShapeRotation;

    SimpleBooleanProperty isShapeCopied;
    private Shape copiedShape = null;

    // move and resize 
    private Group bordersGroup;
    private Border border;
    private Border topBorder;
    private Border bottomBorder;
    private Border rightBorder;
    private Border leftBorder;
    private Border topLeftBorder;
    private Border topRightBorder;
    private Border bottomLeftBorder;
    private Border bottomRightBorder;

    //grid
    double gridSize = -1;
    Paint bg1 = Paint.valueOf("linear-gradient(from 0.0% 0.0% to 0.0% 100.0%, 0xffffff 0.0%, 0xffffff 100.0%)");
    BackgroundFill backgroundFill1 = new BackgroundFill(bg1, null, null);

    DoubleProperty scale = new SimpleDoubleProperty(1);

    /**
     * Empty constructor of the DrawingPane class for test.
     */
    public DrawingPane() {
        super();
        invoker = Invoker.getInstance();
        isShapeCopied = new SimpleBooleanProperty(false);
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
    public DrawingPane(Invoker invoker, ToggleButton lineToggleButton, ToggleButton rectangleToggleButton, ToggleButton ellipseToggleButton, ToggleButton selectShapeToggleButton, ToggleButton polygonToggleButton, ToggleButton textToggleButton, Circle outlineColorImage, Circle fillColorImage, Slider gridSlider, CheckBox gridCheckBox, TextField textTextField, ComboBox<Integer> fontDimensionComboBox) {
        super();
        this.invoker = invoker;

        this.textTextField = textTextField;
        this.fontDimensionComboBox = fontDimensionComboBox;

        // setting up line toggle button
        this.lineToggleButton = lineToggleButton;
        this.lineToggleButton.setOnAction(event -> {
            drawState = new DrawLineState(this);
            checkPolygonCreation();
            deselectShape();
        });

        // setting up rectangle toggle button
        this.rectangleToggleButton = rectangleToggleButton;
        this.rectangleToggleButton.setOnAction(event -> {
            drawState = new DrawRectangleState(this);
            checkPolygonCreation();
            deselectShape();
        });

        // setting up ellipse toggle button
        this.ellipseToggleButton = ellipseToggleButton;
        this.ellipseToggleButton.setOnAction(event -> {
            drawState = new DrawEllipseState(this);
            checkPolygonCreation();
            deselectShape();
        });

        this.selectShapeToggleButton = selectShapeToggleButton;
        this.selectShapeToggleButton.setOnAction(event -> {
            checkPolygonCreation();
        });

        this.polygonToggleButton = polygonToggleButton;
        this.polygonToggleButton.setOnAction(event -> {
            drawState = new DrawPolygonState(this);
            checkPolygonCreation();
            deselectShape();
        });

        // setting up text toggle button
        this.textToggleButton = textToggleButton;
        this.textToggleButton.setOnAction(event -> {

            drawState = new DrawTextState(this);
            checkPolygonCreation();
            deselectShape();
        });

        this.outlineColorImage = outlineColorImage;
        this.fillColorImage = fillColorImage;

        // setting up drawing pane style
        this.setPrefSize(1190, 625);
        this.scaleXProperty().bind(scale);
        this.scaleYProperty().bind(scale);
        this.setBackground(new Background(backgroundFill1));
        this.setStyle("-fx-border-color:grey;"
                + "-fx-border-radius:5;");

        setupEventsHandlers();

        isShapeSelected = new SimpleBooleanProperty(false);
        isShapeCopied = new SimpleBooleanProperty(false);
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

        // setting up horizontal mirrorring menu item and its operations
        MenuItem hMirrorMenuItem = new MenuItem("Mirror horizontally");
        hMirrorMenuItem.disableProperty().bind(isShapeSelected.not());
        hMirrorMenuItem.setOnAction(event -> {
            HorizontalMirrorShapeCommand hMirrorShapeCommand = new HorizontalMirrorShapeCommand(this.selectedShape);
            try {
                invoker.execute(hMirrorShapeCommand);
            } catch (Exception ex) {
            }
        });

        // setting up vertical mirrorring menu item and its operations
        MenuItem vMirrorMenuItem = new MenuItem("Mirror vertically");
        vMirrorMenuItem.disableProperty().bind(isShapeSelected.not());
        vMirrorMenuItem.setOnAction(event -> {
            VerticalMirrorShapeCommand vMirrorShapeCommand = new VerticalMirrorShapeCommand(this.selectedShape);
            try {
                invoker.execute(vMirrorShapeCommand);
            } catch (Exception ex) {
            }
        });

        // setting up rotation menu item and its operations
        MenuItem rotateMenuItem = new MenuItem("Rotate");
        rotateMenuItem.disableProperty().bind(isShapeSelected.not());
        rotateMenuItem.setOnAction(event -> {
            TextInputDialog td = new TextInputDialog();
            td.setTitle("Choose rotation angle");
            td.setHeaderText("Enter the rotation angle of the shape (degree)");
            Optional<String> result = td.showAndWait();

            try {
                double angle = Double.parseDouble(result.get());
                RotateShapeCommand rotateShapeCommand = new RotateShapeCommand(this.selectedShape, angle);
                invoker.execute(rotateShapeCommand);
                setOldSelectedShapeRotation(selectedShape.getRotate());
                SelectionManager.deselectShape(this, selectedShape);
            } catch (NoSuchElementException noSuchElementException) {
            } catch (Exception exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid input");
                alert.setHeaderText("Rotation angle inserted is not valid");
                alert.setContentText("Rotation angle must contain only numeric values");
                alert.showAndWait();
            }
        });

        // setting up copy menu item and its operations
        MenuItem copyMenuItem = new MenuItem("Copy");
        copyMenuItem.disableProperty().bind(isShapeSelected.not());
        copyMenuItem.setOnAction(event -> {
            CopyShapeCommand copyShapeCommand = new CopyShapeCommand(this.selectedShape, this);
            try {
                copyShapeCommand.execute();
            } catch (Exception ex) {
            }
            isShapeCopied.set(true);

        });

        // setting up cut menu item and its operations
        MenuItem cutMenuItem = new MenuItem("Cut");
        cutMenuItem.disableProperty().bind(isShapeSelected.not());
        cutMenuItem.setOnAction(event -> {
            CutShapeCommand cutShapeCommand = new CutShapeCommand(this.selectedShape, this);
            try {
                invoker.execute(cutShapeCommand);
                isShapeCopied.set(true);
            } catch (Exception ex) {
            }
        });

        // setting up paste menu item and its operations
        MenuItem pasteMenuItem = new MenuItem("Paste");
        pasteMenuItem.disableProperty().bind(isShapeCopied.not());
        pasteMenuItem.setOnAction(event -> {
            PasteShapeCommand pasteShapeCommand = new PasteShapeCommand(this);
            try {
                invoker.execute(pasteShapeCommand);
            } catch (Exception ex) {
            }
        });

        // adding all the menu items in the manage shape ContextMenu
        manageShape.getItems().addAll(deleteMenuItem, toFrontMenuItem, toBackMenuItem, hMirrorMenuItem, vMirrorMenuItem, rotateMenuItem, copyMenuItem, cutMenuItem, pasteMenuItem);
        this.setOnContextMenuRequested(event -> {
            if (selectShapeToggleButton.isSelected()) {
                manageShape.show(this.getScene().getWindow(), event.getScreenX(), event.getScreenY());
            }
        });
    }

    /**
     * Sets up all the mouse event useful for drawing on this pane.
     */
    private void setupEventsHandlers() {
        // setting up the event called on the drawingPane when the 
        // mouse button has been pressed
        this.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                // starts to draw only if a shape toggle button is selected
                if (lineToggleButton.isSelected() || rectangleToggleButton.isSelected() || ellipseToggleButton.isSelected() || textToggleButton.isSelected()) {
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

                    drawState.startDrawing(xStartPoint, yStartPoint, selectedOutlineColor, selectedFillColor);

                } else if (polygonToggleButton.isSelected()) {
                    isDrawingAPolygon = true;
                    double x = event.getX();
                    double y = event.getY();

                    if (isDrawing) {
                        drawState.draw(x, y);
                    } else {
                        isDrawing = true;
                        drawState.startDrawing(x, y, selectedOutlineColor, selectedFillColor);

                    }

                } else if (selectShapeToggleButton.isSelected()) {
                    // if there was already a selected shape, we reset it to its previous settings
                    deselectShape();
                }
            } else if (event.isSecondaryButtonDown()) {
                if (polygonToggleButton.isSelected() && isDrawing) {
                    isDrawing = false;
                    isDrawingAPolygon = false;

                    Polygon createdPolygon = (Polygon) this.getChildren().get(this.getChildren().size() - 1);
                    // if the polygon doesn't have at least three vertex, it gets deleted from the drawing
                    if (createdPolygon.getPolygonPoints().size() < 6) {
                        try {
                            invoker.undo();
                        } catch (Exception ex) {
                        }
                    }
                }
            }

        });

        // setting up the event called on the drawingPane when the mouse 
        // is dragged over the drawingPane
        this.setOnMouseDragged(event -> {
            if (event.isPrimaryButtonDown()) {
                if (lineToggleButton.isSelected() || rectangleToggleButton.isSelected() || ellipseToggleButton.isSelected()) {
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
                            drawState.draw(x, y);
                        }
                    }
                }
            }
        });

        // setting up the event called on the drawingPane when the mouse 
        // click has been released
        this.setOnMouseReleased(event -> {
            if (lineToggleButton.isSelected() || rectangleToggleButton.isSelected() || ellipseToggleButton.isSelected()) {
                isDrawing = false;
                // if the starting coordinate of the shape corresponds to the ending coordinate,
                // the shape is removed from the drawingPane
                if (event.getX() == xStartPoint && event.getY() == yStartPoint) {
                    try {
                        invoker.undo();
                    } catch (Exception ex) {
                    }
                }
            }

        });
    }

    /**
     * Method to check if the created arbitraty polygon is valid.
     */
    public void checkPolygonCreation() {
        deselectShape();
        if (isDrawing && isDrawingAPolygon) {
            Polygon createdPolygon = (Polygon) this.getChildren().get(this.getChildren().size() - 1);
            // if the polygon doesn't have at least three vertex, it gets deleted from the drawing
            if (createdPolygon.getPolygonPoints().size() < 6) {
                try {
                    invoker.undo();
                } catch (Exception ex) {
                }
            }
        }
        isDrawing = false;
        isDrawingAPolygon = false;
    }

    /**
     * Selects a shape and creates a border around it.
     *
     * @param shape The Shape to select.
     */
    public void selectShape(Shape shape) {
        if (selectShapeToggleButton.isSelected()) {
            SelectionManager.selectShape(this, shape);
        }
    }

    /**
     * Deselects the selected shape, if there is one.
     */
    public void deselectShape() {
        SelectionManager.deselectShape(this, selectedShape);
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

    // ----- getters and setters -----
    /**
     * Sets the selectedOutlineColor to the new color passed as argument.
     *
     * @param color The new outline color
     */
    public void setOutlineColor(Color color) {
        this.selectedOutlineColor = color;
    }

    /**
     * Sets the selectedOutlineColor Image to the new color passed as argument.
     *
     * @param color The new outline color
     */
    public void setOutlineColorImage(Color color) {
        outlineColorImage.setFill(color);
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
     * Sets the selectedFillColor Image to the new color passed as argument.
     *
     * @param color The new outline color
     */
    public void setFillColorImage(Color color) {
        fillColorImage.setFill(color);
    }

    /**
     * Returns the value of bordersGroup.
     *
     * @return The value of bordersGroup.
     */
    public Group getBordersGroup() {
        return this.bordersGroup;
    }

    /**
     * Sets the bordersGroup to the value passed as argument.
     *
     * @param bordersGroup The value to set.
     */
    public void setBordersGroup(Group bordersGroup) {
        this.bordersGroup = bordersGroup;
    }

    /**
     * Returns the value of border.
     *
     * @return The value of border.
     */
    public Border getShapeBorder() {
        return border;
    }

    /**
     * Sets the border to the value passed as argument.
     *
     * @param border The value to set.
     */
    public void setShapeBorder(Border border) {
        this.border = border;
    }

    /**
     * Returns the value of topLeftBorder.
     *
     * @return The value of topLeftBorder.
     */
    public Border getTopLeftBorder() {
        return topLeftBorder;
    }

    /**
     * Sets the topBorder to the value passed as argument.
     *
     * @param topBorder The value to set.
     */
    public void setTopBorder(Border topBorder) {
        this.topBorder = topBorder;
    }

    /**
     * Returns the value of topBorder.
     *
     * @return
     */
    public Border getTopBorder() {
        return this.topBorder;
    }

    /**
     * Sets the bottomBorder to the value passed as argument.
     *
     * @param bottomBorder The value to set.
     */
    public void setBottomBorder(Border bottomBorder) {
        this.bottomBorder = bottomBorder;
    }

    /**
     * Returns the value of bottomBorder.
     *
     * @return
     */
    public Border getBottomBorder() {
        return this.bottomBorder;
    }

    /**
     * Sets the rightBorder to the value passed as argument.
     *
     * @param rightBorder The value to set.
     */
    public void setRightBorder(Border rightBorder) {
        this.rightBorder = rightBorder;
    }

    /**
     * Returns the value of rightBorder.
     *
     * @return
     */
    public Border getRightBorder() {
        return this.rightBorder;
    }

    /**
     * Sets the leftBorder to the value passed as argument.
     *
     * @param leftBorder The value to set.
     */
    public void setLeftBorder(Border leftBorder) {
        this.leftBorder = leftBorder;
    }

    /**
     * Returns the value of leftBorder.
     *
     * @return
     */
    public Border getLeftBorder() {
        return this.leftBorder;
    }

    /**
     * Sets the topLeftBorder to the value passed as argument.
     *
     * @param topLeftBorder The value to set.
     */
    public void setTopLeftBorder(Border topLeftBorder) {
        this.topLeftBorder = topLeftBorder;
    }

    /**
     * Returns the value of topRightBorder.
     *
     * @return The value of topRightBorder.
     */
    public Border getTopRightBorder() {
        return topRightBorder;
    }

    /**
     * Sets the topRightBorder to the value passed as argument.
     *
     * @param topRightBorder The value to set.
     */
    public void setTopRightBorder(Border topRightBorder) {
        this.topRightBorder = topRightBorder;
    }

    /**
     * Returns the value of bottomLeftBorder.
     *
     * @return The value of bottomLeftBorder.
     */
    public Border getBottomLeftBorder() {
        return bottomLeftBorder;
    }

    /**
     * Sets the bottomLeftBorder to the value passed as argument.
     *
     * @param bottomLeftBorder The value to set.
     */
    public void setBottomLeftBorder(Border bottomLeftBorder) {
        this.bottomLeftBorder = bottomLeftBorder;
    }

    /**
     * Returns the value of bottomRightBorder.
     *
     * @return The value of bottomRightBorder.
     */
    public Border getBottomRightBorder() {
        return bottomRightBorder;
    }

    /**
     * Sets the bottomRightBorder to the value passed as argument.
     *
     * @param bottomRightBorder The value to set.
     */
    public void setBottomRightBorder(Border bottomRightBorder) {
        this.bottomRightBorder = bottomRightBorder;
    }

    /**
     * Returns the value of selectedShape.
     *
     * @return The value of selectedShape.
     */
    public Shape getSelectedShape() {
        return this.selectedShape;
    }

    /**
     * Sets the selected shape attribute to the one passed as argument.
     *
     * @param shape The new selected shape.
     */
    public void setSelectedShape(Shape shape) {
        selectedShape = shape;
    }

    /**
     * Returns the value of oldSelectedShapeRotation.
     *
     * @return The value of oldSelectedShapeRotation.
     */
    public double getOldSelectedShapeRotation() {
        return this.oldSelectedShapeRotation;
    }

    /**
     * Sets the value shape of oldSelectedShapeRotation.
     *
     * @param value The new value.
     */
    public void setOldSelectedShapeRotation(double value) {
        oldSelectedShapeRotation = value;
    }

    /**
     * Sets the value of isShapeSelected to the value passed as argument.
     *
     * @param value The value to set.
     */
    public void setIsShapeSelected(boolean value) {
        this.isShapeSelected.set(value);
    }

    // ----- copiedShape getter and setter -----
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

    // ----- isShapeCopied getter and setter -----
    /**
     * Returns the value of isShapeCopied.
     *
     * @return The value of isShapeCopied.
     */
    public boolean getIsShapeCopied() {
        return isShapeCopied.get();
    }

    /**
     * Sets the value of isShapedCopied to the value passed as argument.
     *
     * @param bool The value to set.
     */
    public void setIsShapeCopied(boolean bool) {
        isShapeCopied.set(bool);
    }

    /**
     * Returns the value of selectShapeToggleButton.
     *
     * @return The value of selectShapeToggleButton.
     */
    public ToggleButton getSelectShapeToggleButton() {
        return selectShapeToggleButton;
    }

    /**
     * Returns the value of backgroundFill1.
     *
     * @return The value of backgroundFill1.
     */
    public BackgroundFill getBackgroundFill1() {
        return backgroundFill1;
    }

    /**
     * Returns the value of gridSize.
     *
     * @return The value of gridSize.
     */
    public double getGridSize() {
        return gridSize;
    }

    /**
     * Sets the value of gridSize to the value passed as argument.
     *
     * @param gridSize The value to set.
     */
    public void setGridSize(double gridSize) {
        this.gridSize = gridSize;
    }

    /**
     * Returns the value of textTextField.
     *
     * @return The value of textTextField.
     */
    public TextField getTextTextField() {
        return textTextField;
    }

    /**
     * Returns the value of fontDimensionComboBox.
     *
     * @return The value of fontDimensionComboBox.
     */
    public ComboBox<Integer> getFontDimensionComboBox() {
        return fontDimensionComboBox;
    }

}
