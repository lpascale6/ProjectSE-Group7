package gui;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

/**
 *
 * @author group7
 */
public class GridManager {

    public static void updateGrid(DrawingPane drawingPane, Slider gridSlider, CheckBox gridCheckBox) {
        
        double size = gridSlider.getValue();
        if (!gridCheckBox.isSelected() || size < 4) {
            size = 0;
        }
        double gridSize = drawingPane.getGridSize();
        if (gridSize != size) {
            if (size <= 0) {
                drawingPane.setBackground(new Background(drawingPane.getBackgroundFill1()));
            } else {
                Paint bg2 = patternTransparent(size);
                BackgroundFill backgroundFill2 = new BackgroundFill(bg2, null, null);
                drawingPane.setBackground(new Background(drawingPane.getBackgroundFill1(), backgroundFill2));
            }
            gridSize = size;
        }
    }

    private static ImagePattern patternTransparent(double size) {
        Canvas canvas = new Canvas();
        canvas.setHeight(size);
        canvas.setWidth(size);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, size, size);
        gc.setFill(Color.BLACK);
        //gc.setLineWidth(2);
        gc.strokeLine(0, 0, 0, size);
        gc.strokeLine(1, 0, size, 0);

        SnapshotParameters sp = new SnapshotParameters();
        sp.setFill(Color.TRANSPARENT);
        WritableImage image = canvas.snapshot(sp, null);
        return new ImagePattern(image, 0, 0, size, size, false);
    }
}
