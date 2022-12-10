package gui;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author group7
 */
public class ZoomManager {

    private static DoubleProperty scale = new SimpleDoubleProperty(1.0);
    private static double drawingPaneWidth;
    private static double drawingPaneHeight;
    private static ScrollBar horizontalScrollBar;
    private static ScrollBar verticalScrollBar;

    public static void setupZoomManager(AnchorPane anchorPane, DrawingPane drawingPane) {
        ZoomManager.setDrawingPaneWidth(drawingPane.getPrefWidth());
        ZoomManager.setDrawingPaneHeight(drawingPane.getPrefHeight());

        anchorPane.setOnScroll(event -> {
            if (scale.get() > 1.0) {
                if (event.isShiftDown()) {
                    // horizontal scroll
                    if (event.getDeltaX() > 0) {
                        horizontalScrollBar.decrement();
                    } else {
                        horizontalScrollBar.increment();
                    }
                } else {
                    // vertical scroll
                    if (event.getDeltaY() > 0) {
                        verticalScrollBar.decrement();
                    } else {
                        verticalScrollBar.increment();
                    }
                }
            }

        });

        // - drawing pane / scale
        // 
        // setting up horizontal scroll bar
        horizontalScrollBar = new ScrollBar();
        // setting up style
        horizontalScrollBar.setPrefSize(1190, 20);
        horizontalScrollBar.visibleProperty().bind(scale.greaterThan(1.0));
        horizontalScrollBar.setOrientation(Orientation.HORIZONTAL);
        horizontalScrollBar.setLayoutY(drawingPaneHeight - horizontalScrollBar.getPrefHeight());

        horizontalScrollBar.setVisibleAmount(drawingPaneWidth / scale.get());
        horizontalScrollBar.setUnitIncrement(10);
        horizontalScrollBar.setMin(0);
        horizontalScrollBar.setMax(drawingPaneWidth / 2);
        horizontalScrollBar.setValue(0);

        horizontalScrollBar.valueProperty().addListener((observable, oldValue, newValue) -> {
            drawingPane.setTranslateX(-newValue.doubleValue());
        });

        verticalScrollBar = new ScrollBar();
        verticalScrollBar.setPrefSize(20, 605);
        verticalScrollBar.visibleProperty().bind(scale.greaterThan(1.0));
        verticalScrollBar.setOrientation(Orientation.VERTICAL);

        verticalScrollBar.setLayoutX(drawingPaneWidth - verticalScrollBar.getPrefWidth());
        verticalScrollBar.setUnitIncrement(1);

        verticalScrollBar.setMin(0);
        verticalScrollBar.setMax(drawingPaneHeight);
        verticalScrollBar.setValue(0);
        verticalScrollBar.valueProperty().addListener((observable, oldValue, newValue) -> {
            drawingPane.setTranslateY(-newValue.doubleValue());
        });

        // adding scroll bars to the anchor pane
        anchorPane.getChildren().add(horizontalScrollBar);
        anchorPane.getChildren().add(verticalScrollBar);

        // binding the scale of the drawing pane to the scale observable value
        drawingPane.scaleXProperty().bind(scale);
        drawingPane.scaleYProperty().bind(scale);
    }

    /**
     * Method to increase the zoom.
     *
     * @param drawingPane the drawing pane to zoom
     */
    public static void zoomIn(DrawingPane drawingPane) {
        double scaleValue = scale.get();
        
        if (scaleValue < 2.0) {
            // getting old values to zoom where focused
            double horizontalOldValue = horizontalScrollBar.getValue() / scaleValue;
            double verticalOldValue = verticalScrollBar.getValue() / scaleValue;
            
            // updating scaleValue
            scaleValue += 0.1;
            scale.set(scaleValue);
            
            // updating horizontal scroll bar
            horizontalScrollBar.setUnitIncrement(drawingPaneWidth / 10);
            horizontalScrollBar.setVisibleAmount((drawingPaneWidth / 2) / scale.get());
            horizontalScrollBar.setMax(drawingPaneWidth / 2 * scaleValue);
            horizontalScrollBar.setMin(-drawingPaneWidth / 2 * scaleValue);
            horizontalScrollBar.setValue(horizontalOldValue * scaleValue);

            // updating vertical scroll bar
            verticalScrollBar.setUnitIncrement(drawingPaneHeight / 10);
            verticalScrollBar.setVisibleAmount(drawingPaneHeight / scale.get());
            verticalScrollBar.setMax(drawingPaneHeight / 2 * scaleValue);
            verticalScrollBar.setMin(-drawingPaneHeight / 2 * scaleValue);
            verticalScrollBar.setValue(verticalOldValue * scaleValue);
        }

    }

    /**
     * Method to reduce the zoom.
     *
     * @param drawingPane the drawing pane to zoom
     */
    public static void zoomOut(DrawingPane drawingPane) {
        double scaleValue = scale.get();

        if (scaleValue > 0.7) {
            
            // updating scaleValue
            scaleValue -= 0.1;
            scale.set(scaleValue);
            
            // getting old values to zoom where focused
            double horizontalOldValue = horizontalScrollBar.getValue() / scaleValue;
            double verticalOldValue = verticalScrollBar.getValue() / scaleValue;
            
            // updating horizontal scroll bar
            horizontalScrollBar.setUnitIncrement(drawingPaneWidth / 10);
            horizontalScrollBar.setVisibleAmount((drawingPaneWidth / 2) / scale.get());
            horizontalScrollBar.setMax(drawingPaneWidth / 2 * scaleValue);
            horizontalScrollBar.setMin(-drawingPaneWidth / 2 * scaleValue);
            horizontalScrollBar.setValue(horizontalOldValue * scaleValue);

            // updating vertical scroll bar
            verticalScrollBar.setUnitIncrement(drawingPaneHeight / 10);
            verticalScrollBar.setVisibleAmount(drawingPaneHeight / scale.get());
            verticalScrollBar.setMax(drawingPaneHeight / 2 * scaleValue);
            verticalScrollBar.setMin(-drawingPaneHeight / 2 * scaleValue);
            verticalScrollBar.setValue(verticalOldValue * scaleValue);
            
            if (scaleValue == 1.0) {
                drawingPane.setTranslateX(0.0);
                drawingPane.setTranslateY(0.0);
                horizontalScrollBar.setValue(0.0);
                verticalScrollBar.setValue(0.0);
            }
        }

    }

    public static DoubleProperty getScale() {
        return scale;
    }

    public static void setScale(DoubleProperty scale) {
        ZoomManager.scale = scale;
    }

    public static double getDrawingPaneWidth() {
        return drawingPaneWidth;
    }

    public static void setDrawingPaneWidth(double drawingPaneWidth) {
        ZoomManager.drawingPaneWidth = drawingPaneWidth;
    }

    public static double getDrawingPaneHeight() {
        return drawingPaneHeight;
    }

    public static void setDrawingPaneHeight(double drawingPaneHeight) {
        ZoomManager.drawingPaneHeight = drawingPaneHeight;

    }

}
