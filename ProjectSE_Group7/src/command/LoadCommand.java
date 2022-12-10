package command;

import gui.DrawingPane;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import shape.Ellipse;
import shape.Line;
import shape.Polygon;
import shape.Rectangle;
import shape.Text;

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

    /**
     * Method that takes a list of shapes from a file and adds them to the
     * drawing pane.
     *
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {

        ArrayList<Shape> shapeList = new ArrayList<Shape>();  //create a list to store all reconstructed shapes before adding them to the drawing pane.

        BufferedReader reader;

        reader = new BufferedReader(new FileReader(this.file)); //read from file.
        String line = reader.readLine(); // read first line.

        while (line != null) {  //until the end of the current line execute the following commands:
            String[] data = line.split(";");  //split read line to get shape data.
            String shapeType = data[0];     //get shape type: line, rectangle or ellipse.

            if ("line".equals(shapeType) && data.length == 9) {  //if shape type is line, reconstruct a line segment.
                Double startX = Double.parseDouble(data[1]);
                Double startY = Double.parseDouble(data[2]);
                Double endX = Double.parseDouble(data[3]);
                Double endY = Double.parseDouble(data[4]);
                Paint color = Paint.valueOf(data[5]);
                double scaleX = Double.parseDouble(data[6]);
                double scaleY = Double.parseDouble(data[7]);
                double rotate = Double.parseDouble(data[8]);

                Line lineShape = new Line(startX, startY, endX, endY, color);
                lineShape.setStrokeWidth(3);
                lineShape.setScaleX(scaleX);
                lineShape.setScaleY(scaleY);
                lineShape.setRotate(rotate);
                lineShape.setOnMouseClicked(e -> {
                    drawingPane.selectShape((Shape) e.getSource());
                });
                shapeList.add(lineShape);  //add reconstructed line segment to shape list.

            } else if ("rectangle".equals(shapeType) && data.length == 10) { //if shape type is rectangle, reconstruct rectangle.
                Double x = Double.parseDouble(data[1]);
                Double y = Double.parseDouble(data[2]);
                Double width = Double.parseDouble(data[3]);
                Double height = Double.parseDouble(data[4]);
                Paint outer = Paint.valueOf(data[5]);
                Paint fill = Paint.valueOf(data[6]);
                double scaleX = Double.parseDouble(data[7]);
                double scaleY = Double.parseDouble(data[8]);
                double rotate = Double.parseDouble(data[9]);

                Rectangle rectangleShape = new Rectangle(x, y, width, height, fill, outer);
                rectangleShape.setStrokeWidth(3);
                rectangleShape.setScaleX(scaleX);
                rectangleShape.setScaleY(scaleY);
                rectangleShape.setRotate(rotate);
                rectangleShape.setOnMouseClicked(e -> {
                    drawingPane.selectShape((Shape) e.getSource());
                });
                shapeList.add(rectangleShape); //add reconstructed rectangle to shape list.

            } else if ("ellipse".equals(shapeType) && data.length == 10) { //if shape type is ellipse, reconstruct ellipse.
                Double hPosition = Double.parseDouble(data[1]);
                Double vPosition = Double.parseDouble(data[2]);
                Double width = Double.parseDouble(data[3]);
                Double height = Double.parseDouble(data[4]);
                Paint outlineColor = Paint.valueOf(data[5]);
                Paint fillColor = Paint.valueOf(data[6]);
                double scaleX = Double.parseDouble(data[7]);
                double scaleY = Double.parseDouble(data[8]);
                double rotate = Double.parseDouble(data[9]);

                Ellipse ellipseShape = new Ellipse(hPosition, vPosition, width, height, outlineColor, fillColor);
                ellipseShape.setOnMouseClicked(e -> {
                    drawingPane.selectShape((Shape) e.getSource());
                });
                ellipseShape.setStrokeWidth(3);
                ellipseShape.setScaleX(scaleX);
                ellipseShape.setScaleY(scaleY);
                ellipseShape.setRotate(rotate);

                shapeList.add(ellipseShape); //add reconstructed ellipse to shape list.

            } else if ("text".equals(shapeType) && data.length == 10){
                Double x = Double.parseDouble(data[1]);
                Double y = Double.parseDouble(data[2]);
                String string = data[3];
                int fontSize = Integer.parseInt(data[4]);
                Paint outlineColor = Paint.valueOf(data[5]);
                Paint fillColor = Paint.valueOf(data[6]);
                double scaleX = Double.parseDouble(data[7]);
                double scaleY = Double.parseDouble(data[8]);
                double rotate = Double.parseDouble(data[9]);
                
                Text textShape = new Text(x, y, string, fontSize, outlineColor, fillColor);
                textShape.setOnMouseClicked(e -> {
                    drawingPane.selectShape((Shape) e.getSource());
                });
                
                textShape.setScaleX(scaleX);
                textShape.setScaleY(scaleY);
                textShape.setRotate(rotate);
                shapeList.add(textShape);
                
                
            } else if ("polygon".equals(shapeType) && data.length == 7) {
                Polygon polygonShape = new Polygon();
                polygonShape.setOnMouseClicked(e -> {
                    drawingPane.selectShape((Shape) e.getSource());
                });

                String[] pointsList = data[1].split("\\s+");
                if (pointsList.length % 2 != 0) {
                    throw new Exception("Shape representation not correctly formatted.");
                }
                for (String point : pointsList) {
                    polygonShape.getPoints().add(Double.parseDouble(point));
                }
                Paint outlineColor = Paint.valueOf(data[2]);
                Paint fillColor = Paint.valueOf(data[3]);
                double scaleX = Double.parseDouble(data[4]);
                double scaleY = Double.parseDouble(data[5]);
                double rotate = Double.parseDouble(data[6]);

                polygonShape.setStroke(outlineColor);
                polygonShape.setFill(fillColor);
                polygonShape.setStrokeWidth(3);
                polygonShape.setScaleX(scaleX);
                polygonShape.setScaleY(scaleY);
                polygonShape.setRotate(rotate);

                shapeList.add(polygonShape);
            } else if ("text".equals(shapeType)) {
                // TO ADD
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

    /**
     * Load command cannot be undone.
     *
     * @throws Exception
     */
    @Override
    public void undo() throws Exception {
    }

}
