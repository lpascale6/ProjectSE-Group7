package gui;

import command.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import shape.Border;
import state.*;

/**
 *
 * @author group7
 */
public class DrawingPane extends Pane {

    Invoker invoker;

    DrawState drawState = new DrawLineState(this);
    private boolean isDrawing = false;
    private double xStartPoint;
    private double yStartPoint;
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

    SimpleBooleanProperty isShapeCopied;
    private Shape copiedShape = null;

    // move and resize 
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
    public DrawingPane(Invoker invoker, ToggleButton lineToggleButton, ToggleButton rectangleToggleButton, ToggleButton ellipseToggleButton, ToggleButton selectShapeToggleButton, ToggleButton polygonToggleButton, ToggleButton textToggleButton, Circle outlineColorImage, Circle fillColorImage, Slider gridSlider, CheckBox gridCheckBox) {
        super();
        this.invoker = invoker;

        // setting up line toggle button
        this.lineToggleButton = lineToggleButton;
        this.lineToggleButton.setOnAction(event -> {
            drawState = new DrawLineState(this);
            deselectShape();
        });

        // setting up rectangle toggle button
        this.rectangleToggleButton = rectangleToggleButton;
        this.rectangleToggleButton.setOnAction(event -> {
            drawState = new DrawRectangleState(this);
            deselectShape();
        });

        // setting up ellipse toggle button
        this.ellipseToggleButton = ellipseToggleButton;
        this.ellipseToggleButton.setOnAction(event -> {
            drawState = new DrawEllipseState(this);
            deselectShape();
        });

        this.selectShapeToggleButton = selectShapeToggleButton;
        this.polygonToggleButton = polygonToggleButton;
        this.textToggleButton = textToggleButton;
        
        this.outlineColorImage = outlineColorImage;
        this.fillColorImage = fillColorImage;
        // setting up drawing pane style
        // setting up drawing pane style
        this.setPrefSize(2048, 1024);
        this.scaleXProperty().bind(scale);
        this.scaleYProperty().bind(scale);
        this.setBackground(new Background(backgroundFill1));
        this.setStyle("-fx-border-color:grey;"
                + "-fx-border-radius:5;");

        setup();

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
        
        // setting up vertical mirrorring menu item and its operations
        MenuItem rotateMenuItem = new MenuItem("Rotate");
        rotateMenuItem.disableProperty().bind(isShapeSelected.not());
        rotateMenuItem.setOnAction(event -> {
        TextInputDialog td = new TextInputDialog();
        td.setTitle("Choose angle");
        td.setHeaderText("Enter angle to rotate");
        Optional<String> result = td.showAndWait();
        
        try{
            double angle = Double.parseDouble(result.get());
            RotateShapeCommand rotateShapeCommand = new RotateShapeCommand(this.selectedShape, angle);
            invoker.execute(rotateShapeCommand);
            this.getBordersGroup().setRotate(angle);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("");
            alert.showAndWait();
        }
        });
        
        // adding all the menu items in the manage shape ContextMenu
        manageShape.getItems().addAll(deleteMenuItem, toFrontMenuItem, toBackMenuItem, copyMenuItem, cutMenuItem, pasteMenuItem, hMirrorMenuItem, vMirrorMenuItem, rotateMenuItem);
        this.setOnContextMenuRequested(event -> {
            if (selectShapeToggleButton.isSelected()) {
                manageShape.show(this.getScene().getWindow(), event.getScreenX(), event.getScreenY());
            }
        });
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

                    drawState.startDrawing(xStartPoint, yStartPoint, selectedOutlineColor, selectedFillColor);
                }
                if (selectShapeToggleButton.isSelected()) {
                    // if there was already a selected shape, we reset it to its previous settings
                    deselectShape();
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
                        drawState.draw(x, y);
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

    public void setFillColorImage(Color color) {
        fillColorImage.setFill(color);
    }

    public Group getBordersGroup() {
        return this.bordersGroup;
    }

    public void setBordersGroup(Group bordersGroup) {
        this.bordersGroup = bordersGroup;
    }

    public Border getShapeBorder() {
        return border;
    }

    public void setShapeBorder(Border border) {
        this.border = border;
    }

    public Border getTopLeftBorder() {
        return topLeftBorder;
    }

    public void setTopLeftBorder(Border topLeftBorder) {
        this.topLeftBorder = topLeftBorder;
    }

    public Border getTopRightBorder() {
        return topRightBorder;
    }

    public void setTopRightBorder(Border topRightBorder) {
        this.topRightBorder = topRightBorder;
    }

    public Border getBottomLeftBorder() {
        return bottomLeftBorder;
    }

    public void setBottomLeftBorder(Border bottomLeftBorder) {
        this.bottomLeftBorder = bottomLeftBorder;
    }

    public Border getBottomRightBorder() {
        return bottomRightBorder;
    }

    public void setBottomRightBorder(Border bottomRightBorder) {
        this.bottomRightBorder = bottomRightBorder;
    }

    public Shape getSelectedShape() {
        return this.selectedShape;
    }

    public void setSelectedShape(Shape shape) {
        selectedShape = shape;
    }

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

    public ToggleButton getSelectShapeToggleButton() {
        return selectShapeToggleButton;
    }

    public BackgroundFill getBackgroundFill1() {
        return backgroundFill1;
    }

    public double getGridSize() {
        return gridSize;
    }

    public void setGridSize(double gridSize) {
        this.gridSize = gridSize;
    }

}
