package command;

import gui.DrawingPane;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import shape.Ellipse;
import shape.Line;
import shape.Rectangle;

/**
 *
 * @author group7
 */
public class LoadCommand implements Command {

    File file;
    DrawingPane drawingPane;

    /**
     * method that creates a load command.
     *
     * @param file File from which to get the data to reconstruct the drawing.
     * @param drawingPane Pane on which to show the shapes saved in the file.
     */
    public LoadCommand(File file, DrawingPane drawingPane) {
        this.file = file;
        this.drawingPane = drawingPane;
    }

    @Override
    public void execute() throws Exception {

        ArrayList<Shape> shapeList = new ArrayList<Shape>();  //create a list to store all reconstructed shapes before adding them to the drawing pane.

        BufferedReader reader;

        reader = new BufferedReader(new FileReader(this.file)); //read from file.
        String line = reader.readLine(); // read first line.

        while (line != null) {  //until the end of the current line execute the following commands:
            String[] data = line.split(";");  //split read line to get shape data.
            String shapeType = data[0];     //get shape type: line, rectangle or ellipse.

            if ("line".equals(shapeType) && data.length == 6) {  //if shape type is line, reconstruct a line segment.
                Double startX = Double.parseDouble(data[1]);
                Double startY = Double.parseDouble(data[2]);
                Double endX = Double.parseDouble(data[3]);
                Double endY = Double.parseDouble(data[4]);
                Paint color = Paint.valueOf(data[5]);

                Line lineShape = new Line(startX, startY, endX, endY, color);
                lineShape.setStrokeWidth(3);
                lineShape.setOnMouseClicked(e -> {
                    drawingPane.selectShape(e);
                });
                shapeList.add(lineShape);  //add reconstructed line segment to shape list.

            } else if ("rectangle".equals(shapeType) && data.length == 7) { //if shape type is rectangle, reconstruct rectangle.
                Double x = Double.parseDouble(data[1]);
                Double y = Double.parseDouble(data[2]);
                Double width = Double.parseDouble(data[3]);
                Double height = Double.parseDouble(data[4]);
                Paint outer = Paint.valueOf(data[5]);
                Paint fill = Paint.valueOf(data[6]);

                Rectangle rectangleShape = new Rectangle(x, y, width, height, fill, outer);
                rectangleShape.setStrokeWidth(3);
                rectangleShape.setOnMouseClicked(e -> {
                    drawingPane.selectShape(e);
                });
                shapeList.add(rectangleShape); //add reconstructed rectangle to shape list.

            } else if ("ellipse".equals(shapeType) && data.length == 7) { //if shape type is ellipse, reconstruct ellipse.
                Double hPosition = Double.parseDouble(data[1]);
                Double vPosition = Double.parseDouble(data[2]);
                Double width = Double.parseDouble(data[3]);
                Double height = Double.parseDouble(data[4]);
                Paint outlineColor = Paint.valueOf(data[5]);
                Paint fillColor = Paint.valueOf(data[6]);

                Ellipse ellipseShape = new Ellipse(hPosition, vPosition, width, height, outlineColor, fillColor);
                ellipseShape.setOnMouseClicked(e -> {
                    drawingPane.selectShape(e);
                });
                ellipseShape.setStrokeWidth(3);
                
                shapeList.add(ellipseShape); //add reconstructed ellipse to shape list.

            } else if ("border".equals(shapeType) && data.length == 7) { //if shape type is border, reconstruct nothing.
                //reconstructs nothing
            } else { //throw expection if shape type is different from the expected ones or if the formatting of the line is wrong.
                throw new Exception("Shape representation not correctly formatted.");
            }

            line = reader.readLine(); //read next line from file.
        }
        reader.close(); //close file reader.

        this.drawingPane.clearDrawing();
        this.drawingPane.getChildren().addAll(shapeList); //add all reconstructed shapes to drawing pane.
    }

    @Override
    public void undo() throws Exception {
       return;
    }

}
