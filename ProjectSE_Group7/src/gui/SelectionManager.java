package gui;

import command.Invoker;
import command.MoveShapeCommand;
import command.ResizeShapeCommand;
import static gui.DrawingPane.strokeWidth;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;
import shape.Border;
import shape.Ellipse;
import shape.Line;
import shape.Polygon;
import shape.Rectangle;
import shape.Text;

/**
 *
 * @author group7
 */
public class SelectionManager {

    private static Shape selectedShape;
    private static Group bordersGroup;
    private static boolean isMoving;
    private static boolean isResizing;
    private static double xStartPoint;
    private static double yStartPoint;
    private static double xEndingPoint;
    private static double yEndingPoint;
    private static double totalDeltaX = 0;
    private static double totalDeltaY = 0;
    private static ToggleButton selectShapeToggleButton;

    /**
     * Method to select a shape.
     * @param drawingPane the drawing pane which the shape to select belongs
     * @param shapeToSelect the shape to select
     */
    public static void selectShape(DrawingPane drawingPane, Shape shapeToSelect) {
        // if there was already a selected shape, we reset it to its previous settings
        selectedShape = drawingPane.getSelectedShape();
        bordersGroup = drawingPane.getBordersGroup();
        selectShapeToggleButton = drawingPane.getSelectShapeToggleButton();

        deselectShape(drawingPane, selectedShape);
        drawingPane.setSelectedShape(shapeToSelect);

        selectedShape = drawingPane.getSelectedShape();
        drawingPane.setOldSelectedShapeRotation(selectedShape.getRotate());
        selectedShape.setRotate(0);
        

        drawingPane.setOutlineColorImage((Color) selectedShape.getStroke());
        drawingPane.setOutlineColor((Color) selectedShape.getStroke());

        if (selectedShape.getClass() != Line.class) {
            drawingPane.setFillColorImage((Color) selectedShape.getFill());
            drawingPane.setFillColor((Color) selectedShape.getFill());
        }
        isMoving = false;
        totalDeltaX = 0;
        totalDeltaY = 0;

        setupBorders(drawingPane, selectedShape);
        drawingPane.setIsShapeSelected(true);
    }

    /**
     * Method to setup the animated borders of the shape.
     * @param drawingPane the drawing pane which the selected shape belongs
     * @param selectedShape the selected shape
     */
    private static void setupBorders(DrawingPane drawingPane, Shape selectedShape) {
        Border border = new Border(selectedShape.getLayoutBounds().getMinX(), selectedShape.getLayoutBounds().getMinY(), selectedShape.getLayoutBounds().getWidth(), selectedShape.getLayoutBounds().getHeight());
        border.setStrokeType(StrokeType.OUTSIDE);
        border.setFillColor(Color.TRANSPARENT);
        border.setStroke(Color.DARKCYAN);
        border.setStrokeWidth(strokeWidth);
        border.setCursor(Cursor.MOVE);
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
                double deltaX = event.getX() - xEndingPoint;
                double deltaY = event.getY() - yEndingPoint;

                // this controll is for checking if the coordinate 
                // is out of the borders of the drawingPane
                if (border.getRectangleX() + deltaX > 0
                        && border.getRectangleY() + deltaY > 0
                        && border.getRectangleX() + deltaX + border.getRectangleWidth() < (drawingPane.getWidth())
                        && border.getRectangleY() + deltaY + border.getRectangleHeight() < (drawingPane.getHeight())) {

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
                    } else if (selectedShape.getClass() == Text.class){
                        Text text = (Text) selectedShape;
                        text.moveOf(deltaX, deltaY);
                        moveBordersGroup(bordersGroup, deltaX, deltaY);
                        xEndingPoint = event.getX();
                        yEndingPoint = event.getY();
                    } else if (selectedShape.getClass() == Polygon.class) {
                        Polygon polygon = (Polygon) selectedShape;
                        polygon.moveOf(deltaX, deltaY);
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
                } else if (selectedShape.getClass() == Text.class){
                    Text text = (Text) selectedShape;
                    text.moveOf(-totalDeltaX, -totalDeltaY);
                } else if (selectedShape.getClass() == Polygon.class) {
                    Polygon polygon = (Polygon) selectedShape;
                    polygon.moveOf(-totalDeltaX, -totalDeltaY);
                }

                MoveShapeCommand moveShapeCommand = new MoveShapeCommand(selectedShape, totalDeltaX, totalDeltaY);
                try {
                    Invoker invoker = Invoker.getInstance();
                    invoker.execute(moveShapeCommand);
                    selectShape(drawingPane, selectedShape);
                } catch (Exception ex) {
                }
            }
        });

        // setting up corner borders
        Border topLeftBorder = setupTopLeftBorder(drawingPane, border);
        Border topRightBorder = setupTopRightBorder(drawingPane, border);
        Border bottomLeftBorder = setupBottomLeftBorder(drawingPane, border);
        Border bottomRightBorder = setupBottomRightBorder(drawingPane, border);
        
        // setting up edge borders
        Border topBorder = setupTopBorder(drawingPane, border);
        Border rightBorder = setupRightBorder(drawingPane, border);
        Border leftBorder = setupLeftBorder(drawingPane, border);
        Border bottomBorder = setupBottomBorder(drawingPane, border);
        
        bordersGroup = new Group(border, topLeftBorder, topRightBorder, bottomLeftBorder, bottomRightBorder, topBorder, bottomBorder, rightBorder, leftBorder);
        bordersGroup.setRotate(selectedShape.getRotate());

        bordersGroup.setScaleX(selectedShape.getScaleX());
        bordersGroup.setScaleY(selectedShape.getScaleY());

        drawingPane.setBordersGroup(bordersGroup);
        drawingPane.getChildren().add(bordersGroup);

    }

    /**
     * Method to setup the top edge of the border (to help stretching).
     * @param drawingPane the drawing pane which the selected shape belongs
     * @param border the border of the selected shape
     * @return 
     */
    private static Border setupTopBorder(DrawingPane drawingPane, Border border) {
        Border topBorder = new Border(selectedShape.getLayoutBounds().getMinX() + selectedShape.getLayoutBounds().getWidth()/2 - 5,
                selectedShape.getLayoutBounds().getMinY() - 5, 10, 10);

        topBorder.setOutlineColor(Color.DARKCYAN);
        topBorder.setFillColor(Color.DARKCYAN);
        topBorder.setStrokeType(StrokeType.OUTSIDE);
        topBorder.setCursor(Cursor.N_RESIZE);

        drawingPane.setTopBorder(topBorder);

        double yPosition = border.getRectangleY();
        double startingHeight = border.getRectangleHeight();

        // topleft corner event handlers
        topBorder.setOnMousePressed(event -> {
            event.consume();
        });

        topBorder.setOnDragDetected(event -> {
            if (selectShapeToggleButton.isSelected()) {
                isResizing = true;
            }
        });

        topBorder.setOnMouseDragged(event -> {
            if (selectShapeToggleButton.isSelected() && isResizing) {
                double x = event.getX();
                double y = event.getY();

                if (x > (strokeWidth / 2)
                        && y > (strokeWidth / 2)
                        && x < (drawingPane.getWidth() - (strokeWidth / 2))
                        && y < (drawingPane.getHeight() - (strokeWidth / 2))) {
                    // to check if the resized shape is too small
                    if (border.getRectangleWidth() > 10 && border.getRectangleHeight() > 10) {

                        if (startingHeight - (y - yPosition) > 10) {
                            border.setRectangleY(y);
                            topBorder.setRectangleY(y - 5);
                            drawingPane.getTopRightBorder().setRectangleY(y - 5);
                            drawingPane.getTopLeftBorder().setRectangleY(y - 5);
                            drawingPane.getRightBorder().setRectangleY(y + border.getRectangleHeight()/2 - 5);
                            drawingPane.getLeftBorder().setRectangleY(y + border.getRectangleHeight()/2 - 5);
                            border.setRectangleHeight(startingHeight - (y - yPosition));
                        }
                        
                    }
                }
            }
        });

        topBorder.setOnMouseReleased(event -> {
            resizeSelectedShape(drawingPane, selectedShape, border);
        });

        return topBorder;
    }
    
    /**
     * Method to setup the bottom edge of the border (to help stretching).
     * @param drawingPane the drawing pane which the selected shape belongs
     * @param border the border of the selected shape
     * @return 
     */
    private static Border setupBottomBorder(DrawingPane drawingPane, Border border) {

        Border bottomBorder = new Border(selectedShape.getLayoutBounds().getMinX() + selectedShape.getLayoutBounds().getWidth()/2 - 5,
                selectedShape.getLayoutBounds().getMinY() + selectedShape.getLayoutBounds().getHeight() - 5, 10, 10);

        bottomBorder.setCursor(Cursor.S_RESIZE);
        bottomBorder.setOutlineColor(Color.DARKCYAN);
        bottomBorder.setFillColor(Color.DARKCYAN);
        bottomBorder.setStrokeType(StrokeType.OUTSIDE);

        drawingPane.setBottomBorder(bottomBorder);

        double yPosition = border.getRectangleY() + border.getRectangleHeight();
        double startingHeight = border.getRectangleHeight();

        // topleft corner event handlers
        bottomBorder.setOnMousePressed(event -> {
            event.consume();
        });

        bottomBorder.setOnDragDetected(event -> {
            if (selectShapeToggleButton.isSelected()) {
                isResizing = true;
            }
        });

        bottomBorder.setOnMouseDragged(event -> {
            if (selectShapeToggleButton.isSelected() && isResizing) {
                double x = event.getX();
                double y = event.getY();

                if (x > (strokeWidth / 2)
                        && y > (strokeWidth / 2)
                        && x < (drawingPane.getWidth() - (strokeWidth / 2))
                        && y < (drawingPane.getHeight() - (strokeWidth / 2))) {
                    // to check if the resized shape is too small
                    if (border.getRectangleWidth() > 10 && border.getRectangleHeight() > 10) {
                        if (startingHeight + (y - yPosition) > 10) {
                            border.setRectangleHeight(startingHeight + (y - yPosition));
                            bottomBorder.setRectangleY(y - 5);
                            drawingPane.getBottomLeftBorder().setRectangleY(y - 5);
                            drawingPane.getBottomRightBorder().setRectangleY(y - 5);
                            drawingPane.getRightBorder().setRectangleY(y - border.getRectangleHeight()/2 - 5);
                            drawingPane.getLeftBorder().setRectangleY(y - border.getRectangleHeight()/2 - 5);
                        }

                    }
                }
            }
        });

        bottomBorder.setOnMouseReleased(event -> {
            resizeSelectedShape(drawingPane, selectedShape, border);
        });

        return bottomBorder;
    }
    
    /**
     * Method to setup the right edge of the border (to help stretching).
     * @param drawingPane the drawing pane which the selected shape belongs
     * @param border the border of the selected shape
     * @return 
     */
    private static Border setupRightBorder(DrawingPane drawingPane, Border border) {

        Border rightBorder = new Border(selectedShape.getLayoutBounds().getMinX() + selectedShape.getLayoutBounds().getWidth() - 5,
                selectedShape.getLayoutBounds().getMinY() + selectedShape.getLayoutBounds().getHeight()/2 - 5, 10, 10);

        rightBorder.setCursor(Cursor.E_RESIZE);
        rightBorder.setOutlineColor(Color.DARKCYAN);
        rightBorder.setFillColor(Color.DARKCYAN);
        rightBorder.setStrokeType(StrokeType.OUTSIDE);

        drawingPane.setRightBorder(rightBorder);

        double xPosition = border.getRectangleX() + border.getRectangleWidth();
        double startingWidth = border.getRectangleWidth();

        // topleft corner event handlers
        rightBorder.setOnMousePressed(event -> {
            event.consume();
        });

        rightBorder.setOnDragDetected(event -> {
            if (selectShapeToggleButton.isSelected()) {
                isResizing = true;
            }
        });

        rightBorder.setOnMouseDragged(event -> {
            if (selectShapeToggleButton.isSelected() && isResizing) {
                double x = event.getX();
                double y = event.getY();

                if (x > (strokeWidth / 2)
                        && y > (strokeWidth / 2)
                        && x < (drawingPane.getWidth() - (strokeWidth / 2))
                        && y < (drawingPane.getHeight() - (strokeWidth / 2))) {
                    // to check if the resized shape is too small
                    if (border.getRectangleWidth() > 10 && border.getRectangleHeight() > 10) {

                        if (startingWidth + (x - xPosition) > 10) {
                            border.setRectangleWidth(startingWidth + (x - xPosition));
                            rightBorder.setRectangleX(x - 5);
                            drawingPane.getTopBorder().setRectangleX(x - border.getRectangleWidth()/2 - 5);
                            drawingPane.getBottomBorder().setRectangleX(x - border.getRectangleWidth()/2 - 5);
                            drawingPane.getTopRightBorder().setRectangleX(x - 5);
                            drawingPane.getBottomRightBorder().setRectangleX(x - 5);
                        }

                    }
                }
            }
        });

        rightBorder.setOnMouseReleased(event -> {
            resizeSelectedShape(drawingPane, selectedShape, border);
        });

        return rightBorder;
    }
    
    /**
     * Method to setup the left edge of the border (to help stretching).
     * @param drawingPane the drawing pane which the selected shape belongs
     * @param border the border of the selected shape
     * @return 
     */
    private static Border setupLeftBorder(DrawingPane drawingPane, Border border) {
        Border leftBorder = new Border(selectedShape.getLayoutBounds().getMinX() - 5,
                selectedShape.getLayoutBounds().getMinY() + selectedShape.getLayoutBounds().getHeight()/2 - 5, 10, 10);

        leftBorder.setCursor(Cursor.W_RESIZE);
        leftBorder.setOutlineColor(Color.DARKCYAN);
        leftBorder.setFillColor(Color.DARKCYAN);
        leftBorder.setStrokeType(StrokeType.OUTSIDE);

        drawingPane.setLeftBorder(leftBorder);

        double xPosition = border.getRectangleX();
        double startingWidth = border.getRectangleWidth();

        // topleft corner event handlers
        leftBorder.setOnMousePressed(event -> {
            event.consume();
        });

        leftBorder.setOnDragDetected(event -> {
            if (selectShapeToggleButton.isSelected()) {
                isResizing = true;
            }
        });

        leftBorder.setOnMouseDragged(event -> {
            if (selectShapeToggleButton.isSelected() && isResizing) {
                double x = event.getX();
                double y = event.getY();

                if (x > (strokeWidth / 2)
                        && y > (strokeWidth / 2)
                        && x < (drawingPane.getWidth() - (strokeWidth / 2))
                        && y < (drawingPane.getHeight() - (strokeWidth / 2))) {
                    // to check if the resized shape is too small
                    if (border.getRectangleWidth() > 10 && border.getRectangleHeight() > 10) {

                        if (startingWidth - (x - xPosition) > 10) {
                            border.setRectangleX(x);
                            border.setRectangleWidth(startingWidth - (x - xPosition));
                            leftBorder.setRectangleX(x - 5);
                            drawingPane.getTopBorder().setRectangleX(x + border.getRectangleWidth()/2 - 5);
                            drawingPane.getBottomBorder().setRectangleX(x + border.getRectangleWidth()/2 - 5);
                            drawingPane.getTopLeftBorder().setRectangleX(x - 5);
                            drawingPane.getBottomLeftBorder().setRectangleX(x - 5);
                        }

                    }
                }
            }
        });

        leftBorder.setOnMouseReleased(event -> {

            resizeSelectedShape(drawingPane, selectedShape, border);
        });

        return leftBorder;
    }
    
    /**
     * Method to setup the top left corner of the border (to help resizing).
     * @param drawingPane the drawing pane which the selected shape belongs
     * @param border the border of the selected shape
     * @return 
     */
    private static Border setupTopLeftBorder(DrawingPane drawingPane, Border border) {
        Border topLeftBorder = new Border(selectedShape.getLayoutBounds().getMinX() - 5,
                selectedShape.getLayoutBounds().getMinY() - 5, 10, 10);

        topLeftBorder.setOutlineColor(Color.DARKCYAN);
        topLeftBorder.setFillColor(Color.DARKCYAN);
        topLeftBorder.setStrokeType(StrokeType.OUTSIDE);
        topLeftBorder.setCursor(Cursor.NW_RESIZE);

        drawingPane.setTopLeftBorder(topLeftBorder);

        double xPosition = border.getRectangleX();
        double yPosition = border.getRectangleY();
        double startingWidth = border.getRectangleWidth();
        double startingHeight = border.getRectangleHeight();

        // topleft corner event handlers
        topLeftBorder.setOnMousePressed(event -> {
            event.consume();
        });

        topLeftBorder.setOnDragDetected(event -> {
            if (selectShapeToggleButton.isSelected()) {
                isResizing = true;
            }
        });

        topLeftBorder.setOnMouseDragged(event -> {
            if (selectShapeToggleButton.isSelected() && isResizing) {
                double x = event.getX();
                double y = event.getY();

                if (x > (strokeWidth / 2)
                        && y > (strokeWidth / 2)
                        && x < (drawingPane.getWidth() - (strokeWidth / 2))
                        && y < (drawingPane.getHeight() - (strokeWidth / 2))) {
                    // to check if the resized shape is too small
                    if (border.getRectangleWidth() > 10 && border.getRectangleHeight() > 10) {

                        if (startingWidth - (x - xPosition) > 10) {
                            border.setRectangleX(x);
                            topLeftBorder.setRectangleX(x - 5);
                            drawingPane.getBottomLeftBorder().setRectangleX(x - 5);
                            drawingPane.getLeftBorder().setRectangleX(x - 5);
                            border.setRectangleWidth(startingWidth - (x - xPosition));
                            drawingPane.getTopBorder().setRectangleX(x + border.getRectangleWidth()/2 - 5);
                            drawingPane.getBottomBorder().setRectangleX(x + border.getRectangleWidth()/2 - 5);
                        }

                        if (startingHeight - (y - yPosition) > 10) {
                            border.setRectangleY(y);
                            topLeftBorder.setRectangleY(y - 5);
                            drawingPane.getTopRightBorder().setRectangleY(y - 5);
                            drawingPane.getTopBorder().setRectangleY(y - 5);
                            drawingPane.getRightBorder().setRectangleY(y + border.getRectangleHeight()/2 - 5);
                            drawingPane.getLeftBorder().setRectangleY(y + border.getRectangleHeight()/2 - 5);
                            border.setRectangleHeight(startingHeight - (y - yPosition));
                        }
                        
                    }
                }
            }
        });

        topLeftBorder.setOnMouseReleased(event -> {
            resizeSelectedShape(drawingPane, selectedShape, border);
        });

        return topLeftBorder;
    }

    /**
     * Method to setup the top right corner of the border (to help resizing).
     * @param drawingPane the drawing pane which the selected shape belongs
     * @param border the border of the selected shape
     * @return 
     */
    private static Border setupTopRightBorder(DrawingPane drawingPane, Border border) {
        Border topRightBorder = new Border(selectedShape.getLayoutBounds().getMinX() + selectedShape.getLayoutBounds().getWidth() - 5,
                selectedShape.getLayoutBounds().getMinY() - 5, 10, 10);

        topRightBorder.setCursor(Cursor.NE_RESIZE);
        topRightBorder.setOutlineColor(Color.DARKCYAN);
        topRightBorder.setFillColor(Color.DARKCYAN);
        topRightBorder.setStrokeType(StrokeType.OUTSIDE);

        drawingPane.setTopRightBorder(topRightBorder);

        double xPosition = border.getRectangleX() + border.getRectangleWidth();
        double yPosition = border.getRectangleY();
        double startingWidth = border.getRectangleWidth();
        double startingHeight = border.getRectangleHeight();

        // topleft corner event handlers
        topRightBorder.setOnMousePressed(event -> {
            event.consume();
        });

        topRightBorder.setOnDragDetected(event -> {
            if (selectShapeToggleButton.isSelected()) {
                isResizing = true;
            }
        });

        topRightBorder.setOnMouseDragged(event -> {
            if (selectShapeToggleButton.isSelected() && isResizing) {
                double x = event.getX();
                double y = event.getY();

                if (x > (strokeWidth / 2)
                        && y > (strokeWidth / 2)
                        && x < (drawingPane.getWidth() - (strokeWidth / 2))
                        && y < (drawingPane.getHeight() - (strokeWidth / 2))) {
                    // to check if the resized shape is too small
                    if (border.getRectangleWidth() > 10 && border.getRectangleHeight() > 10) {

                        if (startingWidth + (x - xPosition) > 10) {
                            border.setRectangleWidth(startingWidth + (x - xPosition));
                            topRightBorder.setRectangleX(x - 5);
                            drawingPane.getBottomRightBorder().setRectangleX(x - 5);
                            drawingPane.getRightBorder().setRectangleX(x - 5);
                            drawingPane.getTopBorder().setRectangleX(x - border.getRectangleWidth()/2 - 5);
                            drawingPane.getBottomBorder().setRectangleX(x - border.getRectangleWidth()/2 - 5);
                        }
                        if (startingHeight - (y - yPosition) > 10) {
                            border.setRectangleY(y);
                            topRightBorder.setRectangleY(y - 5);
                            drawingPane.getTopLeftBorder().setRectangleY(y - 5);
                            drawingPane.getTopBorder().setRectangleY(y - 5);
                            drawingPane.getRightBorder().setRectangleY(y + border.getRectangleHeight()/2 - 5);
                            drawingPane.getLeftBorder().setRectangleY(y + border.getRectangleHeight()/2 - 5);
                            border.setRectangleHeight(startingHeight - (y - yPosition));
                        }

                    }
                }
            }
        });

        topRightBorder.setOnMouseReleased(event -> {
            resizeSelectedShape(drawingPane, selectedShape, border);
        });

        return topRightBorder;
    }

    /**
     * Method to setup the bottom left corner of the border (to help resizing).
     * @param drawingPane the drawing pane which the selected shape belongs
     * @param border the border of the selected shape
     * @return 
     */
    private static Border setupBottomLeftBorder(DrawingPane drawingPane, Border border) {
        Border bottomLeftBorder = new Border(selectedShape.getLayoutBounds().getMinX() - 5,
                selectedShape.getLayoutBounds().getMinY() + selectedShape.getLayoutBounds().getHeight() - 5, 10, 10);

        bottomLeftBorder.setCursor(Cursor.SW_RESIZE);
        bottomLeftBorder.setOutlineColor(Color.DARKCYAN);
        bottomLeftBorder.setFillColor(Color.DARKCYAN);
        bottomLeftBorder.setStrokeType(StrokeType.OUTSIDE);

        drawingPane.setBottomLeftBorder(bottomLeftBorder);

        double xPosition = border.getRectangleX();
        double yPosition = border.getRectangleY() + border.getRectangleHeight();
        double startingWidth = border.getRectangleWidth();
        double startingHeight = border.getRectangleHeight();

        // topleft corner event handlers
        bottomLeftBorder.setOnMousePressed(event -> {
            event.consume();
        });

        bottomLeftBorder.setOnDragDetected(event -> {
            if (selectShapeToggleButton.isSelected()) {
                isResizing = true;
            }
        });

        bottomLeftBorder.setOnMouseDragged(event -> {
            if (selectShapeToggleButton.isSelected() && isResizing) {
                double x = event.getX();
                double y = event.getY();

                if (x > (strokeWidth / 2)
                        && y > (strokeWidth / 2)
                        && x < (drawingPane.getWidth() - (strokeWidth / 2))
                        && y < (drawingPane.getHeight() - (strokeWidth / 2))) {
                    // to check if the resized shape is too small
                    if (border.getRectangleWidth() > 10 && border.getRectangleHeight() > 10) {

                        if (startingWidth - (x - xPosition) > 10) {
                            border.setRectangleX(x);
                            border.setRectangleWidth(startingWidth - (x - xPosition));
                            bottomLeftBorder.setRectangleX(x - 5);
                            drawingPane.getTopBorder().setRectangleX(x + border.getRectangleWidth()/2 - 5);
                            drawingPane.getBottomBorder().setRectangleX(x + border.getRectangleWidth()/2 - 5);
                            drawingPane.getTopLeftBorder().setRectangleX(x - 5);
                            drawingPane.getLeftBorder().setRectangleX(x - 5);
                        }
                        if (startingHeight + (y - yPosition) > 10) {
                            border.setRectangleHeight(startingHeight + (y - yPosition));
                            bottomLeftBorder.setRectangleY(y - 5);
                            drawingPane.getBottomRightBorder().setRectangleY(y - 5);
                            drawingPane.getBottomBorder().setRectangleY(y - 5);
                            drawingPane.getRightBorder().setRectangleY(y - border.getRectangleHeight()/2 - 5);
                            drawingPane.getLeftBorder().setRectangleY(y - border.getRectangleHeight()/2 - 5);
                        }

                    }
                }
            }
        });

        bottomLeftBorder.setOnMouseReleased(event -> {

            resizeSelectedShape(drawingPane, selectedShape, border);
        });

        return bottomLeftBorder;
    }

    /**
     * Method to setup the bottom right corner of the border (to help resizing).
     * @param drawingPane the drawing pane which the selected shape belongs
     * @param border the border of the selected shape
     * @return 
     */
    private static Border setupBottomRightBorder(DrawingPane drawingPane, Border border) {

        Border bottomRightBorder = new Border(selectedShape.getLayoutBounds().getMinX() + selectedShape.getLayoutBounds().getWidth() - 5,
                selectedShape.getLayoutBounds().getMinY() + selectedShape.getLayoutBounds().getHeight() - 5, 10, 10);

        bottomRightBorder.setCursor(Cursor.SE_RESIZE);
        bottomRightBorder.setOutlineColor(Color.DARKCYAN);
        bottomRightBorder.setFillColor(Color.DARKCYAN);
        bottomRightBorder.setStrokeType(StrokeType.OUTSIDE);

        drawingPane.setBottomRightBorder(bottomRightBorder);

        double xPosition = border.getRectangleX() + border.getRectangleWidth();
        double yPosition = border.getRectangleY() + border.getRectangleHeight();
        double startingWidth = border.getRectangleWidth();
        double startingHeight = border.getRectangleHeight();

        // topleft corner event handlers
        bottomRightBorder.setOnMousePressed(event -> {
            event.consume();
        });

        bottomRightBorder.setOnDragDetected(event -> {
            if (selectShapeToggleButton.isSelected()) {
                isResizing = true;
            }
        });

        bottomRightBorder.setOnMouseDragged(event -> {
            if (selectShapeToggleButton.isSelected() && isResizing) {
                double x = event.getX();
                double y = event.getY();

                if (x > (strokeWidth / 2)
                        && y > (strokeWidth / 2)
                        && x < (drawingPane.getWidth() - (strokeWidth / 2))
                        && y < (drawingPane.getHeight() - (strokeWidth / 2))) {
                    // to check if the resized shape is too small
                    if (border.getRectangleWidth() > 10 && border.getRectangleHeight() > 10) {

                        if (startingWidth + (x - xPosition) > 10) {
                            border.setRectangleWidth(startingWidth + (x - xPosition));
                            bottomRightBorder.setRectangleX(x - 5);
                            drawingPane.getTopBorder().setRectangleX(x - border.getRectangleWidth()/2 - 5);
                            drawingPane.getBottomBorder().setRectangleX(x - border.getRectangleWidth()/2 - 5);
                            drawingPane.getTopRightBorder().setRectangleX(x - 5);
                            drawingPane.getRightBorder().setRectangleX(x - 5);
                        }
                        if (startingHeight + (y - yPosition) > 10) {
                            border.setRectangleHeight(startingHeight + (y - yPosition));
                            bottomRightBorder.setRectangleY(y - 5);
                            drawingPane.getBottomLeftBorder().setRectangleY(y - 5);
                            drawingPane.getBottomBorder().setRectangleY(y - 5);
                            drawingPane.getRightBorder().setRectangleY(y - border.getRectangleHeight()/2 - 5);
                            drawingPane.getLeftBorder().setRectangleY(y - border.getRectangleHeight()/2 - 5);
                        }

                    }
                }
            }
        });

        bottomRightBorder.setOnMouseReleased(event -> {
            resizeSelectedShape(drawingPane, selectedShape, border);
        });

        return bottomRightBorder;
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
    private static void moveBordersGroup(Group group, double x, double y) {
        for (Node node : group.getChildren()) {
            Border border = (Border) node;
            border.moveOf(x, y);
        }
    }

    /**
     * Resizes the selected shape and deselects it.
     */
    private static void resizeSelectedShape(DrawingPane drawingPane, Shape selectedShape, Border border) {
        isResizing = false;

        ResizeShapeCommand resizeShapeCommand = new ResizeShapeCommand(selectedShape, border);
        try {
            Invoker invoker = Invoker.getInstance();
            invoker.execute(resizeShapeCommand);
            
        } catch (Exception ex) {
        }

        selectShape(drawingPane, selectedShape);
    }

    /**
     * Method to deselect the selected shape.
     * @param drawingPane the drawing pane which the selected shape belongs 
     * @param selectedShape the selected shape
     */
    public static void deselectShape(DrawingPane drawingPane, Shape selectedShape) {
        if (drawingPane.getSelectedShape() != null) {
            selectedShape.setRotate(drawingPane.getOldSelectedShapeRotation());
            drawingPane.setSelectedShape(null);
            drawingPane.getChildren().remove(drawingPane.getBordersGroup());
            drawingPane.setShapeBorder(null);
            drawingPane.setIsShapeSelected(false);
        }
    }

}
