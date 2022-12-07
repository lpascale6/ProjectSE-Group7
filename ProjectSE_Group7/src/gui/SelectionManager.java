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
import shape.Rectangle;

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

    public static void selectShape(DrawingPane drawingPane, Shape shapeToSelect) {
        // if there was already a selected shape, we reset it to its previous settings
        selectedShape = drawingPane.getSelectedShape();
        bordersGroup = drawingPane.getBordersGroup();
        selectShapeToggleButton = drawingPane.getSelectShapeToggleButton();

        deselectShape(drawingPane, selectedShape);
        drawingPane.setSelectedShape(shapeToSelect);
        
        selectedShape = drawingPane.getSelectedShape();

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
                double x = event.getX();
                double y = event.getY();
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

        bordersGroup = new Group(border, topLeftBorder, topRightBorder, bottomLeftBorder, bottomRightBorder);

        drawingPane.setBordersGroup(bordersGroup);
        drawingPane.getChildren().add(bordersGroup);

    }

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
                            border.setRectangleWidth(startingWidth - (x - xPosition));
                        }

                        if (startingHeight - (y - yPosition) > 10) {
                            border.setRectangleY(y);
                            topLeftBorder.setRectangleY(y - 5);
                            drawingPane.getTopRightBorder().setRectangleY(y - 5);
                            border.setRectangleHeight(startingHeight - (y - yPosition));
                        }
                        // moving borders

                    }
                }
            }
        });

        topLeftBorder.setOnMouseReleased(event -> {
            resizeSelectedShape(drawingPane, selectedShape, border);
        });
        
        return topLeftBorder;
    }

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
                        }
                        if (startingHeight - (y - yPosition) > 10) {
                            border.setRectangleY(y);
                            topRightBorder.setRectangleY(y - 5);
                            drawingPane.getTopLeftBorder().setRectangleY(y - 5);
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
                            drawingPane.getTopLeftBorder().setRectangleX(x - 5);
                        }
                        if (startingHeight + (y - yPosition) > 10) {
                            border.setRectangleHeight(startingHeight + (y - yPosition));
                            bottomLeftBorder.setRectangleY(y - 5);
                            drawingPane.getBottomRightBorder().setRectangleY(y - 5);
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
                            drawingPane.getTopRightBorder().setRectangleX(x - 5);
                        }
                        if (startingHeight + (y - yPosition) > 10) {
                            border.setRectangleHeight(startingHeight + (y - yPosition));
                            bottomRightBorder.setRectangleY(y - 5);
                            drawingPane.getBottomLeftBorder().setRectangleY(y - 5);
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
    
    public static void deselectShape(DrawingPane drawingPane, Shape selectedShape) {
        if (drawingPane.getSelectedShape() != null) {
            drawingPane.setSelectedShape(null);
            drawingPane.getChildren().remove(drawingPane.getBordersGroup());
            drawingPane.setShapeBorder(null);
            drawingPane.setIsShapeSelected(false);
        }
    }

}
