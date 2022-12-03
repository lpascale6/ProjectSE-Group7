/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package command;

import gui.DrawingPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shape.Ellipse;
import shape.Line;
import shape.Rectangle;

/**
 *
 * @author luigi
 */
public class CopyShapeCommandTest {

    CopyShapeCommand copyShapeCommand;
    DrawingPane drawingPane;

    public CopyShapeCommandTest() {
    }

    @Before
    public void setUp() {
        drawingPane = new DrawingPane();

    }

    /**
     * Test of execute method, of class CopyShapeCommand.
     */
    @Test
    public void testExecute() throws Exception {
        //distX = endX - startX
        //distY = endY - startY
        //line with distX > 0 and distY > 0
        Line line1 = new Line(1.0, 1.0, 2.0, 2.0, Color.BLACK);
        copyShapeCommand = new CopyShapeCommand(line1, drawingPane);
        copyShapeCommand.execute();
        line1.setStartX(10);
        line1.setStartY(10);
        line1.setEndX(11);
        line1.setEndY(11);
        assertEquals(line1.toString(), drawingPane.getCopiedShape().toString());

        //line with startX > endX and startY > endY
        Line line2 = new Line(2.0, 2.0, 1.0, 1.0, Color.BLACK);
        copyShapeCommand = new CopyShapeCommand(line2, drawingPane);
        copyShapeCommand.execute();
        line2.setStartX(11);
        line2.setStartY(11);
        line2.setEndX(10);
        line2.setEndY(10);
        assertEquals(line2.toString(), drawingPane.getCopiedShape().toString());

        //line with startX > endX and startY < endY
        Line line3 = new Line(2.0, 1.0, 1.0, 2.0, Color.BLACK);
        copyShapeCommand = new CopyShapeCommand(line3, drawingPane);
        copyShapeCommand.execute();
        line3.setStartX(11);
        line3.setStartY(10);
        line3.setEndX(10);
        line3.setEndY(11);
        assertEquals(line3.toString(), drawingPane.getCopiedShape().toString());

        //line with startX < endX and startY > endY
        Line line4 = new Line(1.0, 2.0, 2.0, 1.0, Color.BLACK);
        copyShapeCommand = new CopyShapeCommand(line4, drawingPane);
        copyShapeCommand.execute();
        line4.setStartX(10);
        line4.setStartY(11);
        line4.setEndX(11);
        line4.setEndY(10);
        assertEquals(line4.toString(), drawingPane.getCopiedShape().toString());

        //rectangle
        Rectangle rectangle = new Rectangle(1.0, 1.0, 1.0, 1.0, Color.BLACK, Color.WHITE);
        copyShapeCommand = new CopyShapeCommand(rectangle, drawingPane);
        copyShapeCommand.execute();
        rectangle.setX(10.0);
        rectangle.setY(10.0);
        assertEquals(rectangle.toString(), drawingPane.getCopiedShape().toString());

        //ellipse
        Ellipse ellipse = new Ellipse(1.0, 1.0, 1.0, 1.0, Color.BLACK, Color.WHITE);
        copyShapeCommand = new CopyShapeCommand(ellipse, drawingPane);
        copyShapeCommand.execute();
        ellipse.setCenterX(10 + ellipse.getRadiusX());
        ellipse.setCenterY(10 + ellipse.getRadiusY());
        assertEquals(ellipse.toString(), drawingPane.getCopiedShape().toString());
    }

    /**
     * Test of undo method, of class CopyShapeCommand.
     */
    @Test
    public void testUndo() throws Exception {
    }

}
