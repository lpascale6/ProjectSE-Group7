/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package command;

import gui.DrawingPane;
import javafx.scene.paint.Color;
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
public class CutShapeCommandTest {
    
    CutShapeCommand cutShapeCommand;
    DrawingPane drawingPane;
    
    public CutShapeCommandTest() {
        drawingPane = new DrawingPane();
    }
    
    @Before
    public void setUp() {

    }


    /**
     * Test of execute method, of class CutShapeCommand.
     */
    @Test
    public void testExecute() throws Exception {
        int size;
        //distX = endX - startX
        //distY = endY - startY
        //line with distX > 0 and distY > 0
        Line line1 = new Line(1.0, 1.0, 2.0, 2.0, Color.BLACK);
        drawingPane.getChildren().add(line1);
        size = drawingPane.getChildren().size();
        cutShapeCommand = new CutShapeCommand(line1, drawingPane);
        cutShapeCommand.execute();
        line1.setStartX(10);
        line1.setStartY(10);
        line1.setEndX(11);
        line1.setEndY(11);
        assertEquals(line1.toString(), drawingPane.getCopiedShape().toString());
        assertFalse(drawingPane.getChildren().contains(line1));
        assertEquals(size - 1, drawingPane.getChildren().size());
        
        //line with startX > endX and startY > endY
        Line line2 = new Line(2.0, 2.0, 1.0, 1.0, Color.BLACK);
        drawingPane.getChildren().add(line2);
        size = drawingPane.getChildren().size();
        cutShapeCommand = new CutShapeCommand(line2, drawingPane);
        cutShapeCommand.execute();
        line2.setStartX(11);
        line2.setStartY(11);
        line2.setEndX(10);
        line2.setEndY(10);
        assertEquals(line2.toString(), drawingPane.getCopiedShape().toString());
        assertFalse(drawingPane.getChildren().contains(line2));
        assertEquals(size - 1, drawingPane.getChildren().size());
        
        //line with startX > endX and startY < endY
        Line line3 = new Line(2.0, 1.0, 1.0, 2.0, Color.BLACK);
        drawingPane.getChildren().add(line3);
        size = drawingPane.getChildren().size();
        cutShapeCommand = new CutShapeCommand(line3, drawingPane);
        cutShapeCommand.execute();
        line3.setStartX(11);
        line3.setStartY(10);
        line3.setEndX(10);
        line3.setEndY(11);
        assertEquals(line3.toString(), drawingPane.getCopiedShape().toString());
        assertFalse(drawingPane.getChildren().contains(line3));
        assertEquals(size - 1, drawingPane.getChildren().size());
        
        //line with startX < endX and startY > endY
        Line line4 = new Line(1.0, 2.0, 2.0, 1.0, Color.BLACK);
        drawingPane.getChildren().add(line4);
        size = drawingPane.getChildren().size();
        cutShapeCommand = new CutShapeCommand(line4, drawingPane);
        cutShapeCommand.execute();
        line4.setStartX(10);
        line4.setStartY(11);
        line4.setEndX(11);
        line4.setEndY(10);
        assertEquals(line4.toString(), drawingPane.getCopiedShape().toString());
        assertFalse(drawingPane.getChildren().contains(line4));
        assertEquals(size - 1, drawingPane.getChildren().size());
        
        //rectangle
        Rectangle rectangle = new Rectangle(1.0, 1.0, 1.0, 1.0, Color.BLACK, Color.WHITE);
        drawingPane.getChildren().add(rectangle);
        size = drawingPane.getChildren().size();
        cutShapeCommand = new CutShapeCommand(rectangle, drawingPane);
        cutShapeCommand.execute();
        rectangle.setX(10.0);
        rectangle.setY(10.0);
        assertEquals(rectangle.toString(), drawingPane.getCopiedShape().toString());
        assertFalse(drawingPane.getChildren().contains(rectangle));
        assertEquals(size - 1, drawingPane.getChildren().size());
        
        //ellipse
        Ellipse ellipse = new Ellipse(1.0, 1.0, 1.0, 1.0, Color.BLACK, Color.WHITE);
        drawingPane.getChildren().add(ellipse);
        size = drawingPane.getChildren().size();
        cutShapeCommand = new CutShapeCommand(ellipse, drawingPane);
        cutShapeCommand.execute();
        ellipse.setCenterX(10 + ellipse.getRadiusX());
        ellipse.setCenterY(10 + ellipse.getRadiusY());
        assertEquals(ellipse.toString(), drawingPane.getCopiedShape().toString());
        assertFalse(drawingPane.getChildren().contains(ellipse));
        assertEquals(size - 1, drawingPane.getChildren().size());
    }

    /**
     * Test of undo method, of class CutShapeCommand.
     */
    @Test
    public void testUndo() throws Exception {
        int size;
        int index;
        //distX = endX - startX
        //distY = endY - startY
        //line with distX > 0 and distY > 0
        Line line1 = new Line(1.0, 1.0, 2.0, 2.0, Color.BLACK);
        drawingPane.getChildren().add(line1);
        size = drawingPane.getChildren().size();
        index = drawingPane.getChildren().indexOf(line1);
        cutShapeCommand = new CutShapeCommand(line1, drawingPane);
        cutShapeCommand.execute();
        line1.setStartX(10);
        line1.setStartY(10);
        line1.setEndX(11);
        line1.setEndY(11);
        assertEquals(line1.toString(), drawingPane.getCopiedShape().toString());
        assertFalse(drawingPane.getChildren().contains(line1));
        assertEquals(size - 1, drawingPane.getChildren().size());
        cutShapeCommand.undo();
        assertEquals(null, drawingPane.getCopiedShape());
        assertTrue(drawingPane.getChildren().contains(line1));
        assertEquals(size, drawingPane.getChildren().size());
        assertEquals(index, drawingPane.getChildren().indexOf(line1));
        
        //line with startX > endX and startY > endY
        Line line2 = new Line(2.0, 2.0, 1.0, 1.0, Color.BLACK);
        drawingPane.getChildren().add(line2);
        size = drawingPane.getChildren().size();
        index = drawingPane.getChildren().indexOf(line2);
        cutShapeCommand = new CutShapeCommand(line2, drawingPane);
        cutShapeCommand.execute();
        line2.setStartX(11);
        line2.setStartY(11);
        line2.setEndX(10);
        line2.setEndY(10);
        assertEquals(line2.toString(), drawingPane.getCopiedShape().toString());
        assertFalse(drawingPane.getChildren().contains(line2));
        assertEquals(size - 1, drawingPane.getChildren().size());
        cutShapeCommand.undo();
        assertEquals(null, drawingPane.getCopiedShape());
        assertTrue(drawingPane.getChildren().contains(line2));
        assertEquals(size, drawingPane.getChildren().size());
        assertEquals(index, drawingPane.getChildren().indexOf(line2));
        
        //line with startX > endX and startY < endY
        Line line3 = new Line(2.0, 1.0, 1.0, 2.0, Color.BLACK);
        drawingPane.getChildren().add(line3);
        size = drawingPane.getChildren().size();
        index = drawingPane.getChildren().indexOf(line3);
        cutShapeCommand = new CutShapeCommand(line3, drawingPane);
        cutShapeCommand.execute();
        line3.setStartX(11);
        line3.setStartY(10);
        line3.setEndX(10);
        line3.setEndY(11);
        assertEquals(line3.toString(), drawingPane.getCopiedShape().toString());
        assertFalse(drawingPane.getChildren().contains(line3));
        assertEquals(size - 1, drawingPane.getChildren().size());
        cutShapeCommand.undo();
        assertEquals(null, drawingPane.getCopiedShape());
        assertTrue(drawingPane.getChildren().contains(line3));
        assertEquals(size, drawingPane.getChildren().size());
        assertEquals(index, drawingPane.getChildren().indexOf(line3));
        
        //line with startX < endX and startY > endY
        Line line4 = new Line(1.0, 2.0, 2.0, 1.0, Color.BLACK);
        drawingPane.getChildren().add(line4);
        size = drawingPane.getChildren().size();
        index = drawingPane.getChildren().indexOf(line4);
        cutShapeCommand = new CutShapeCommand(line4, drawingPane);
        cutShapeCommand.execute();
        line4.setStartX(10);
        line4.setStartY(11);
        line4.setEndX(11);
        line4.setEndY(10);
        assertEquals(line4.toString(), drawingPane.getCopiedShape().toString());
        assertFalse(drawingPane.getChildren().contains(line4));
        assertEquals(size - 1, drawingPane.getChildren().size());
        cutShapeCommand.undo();
        assertEquals(null, drawingPane.getCopiedShape());
        assertTrue(drawingPane.getChildren().contains(line4));
        assertEquals(size, drawingPane.getChildren().size());
        assertEquals(index, drawingPane.getChildren().indexOf(line4));
        
        //rectangle
        Rectangle rectangle = new Rectangle(1.0, 1.0, 1.0, 1.0, Color.BLACK, Color.WHITE);
        drawingPane.getChildren().add(rectangle);
        size = drawingPane.getChildren().size();
        index = drawingPane.getChildren().indexOf(rectangle);
        cutShapeCommand = new CutShapeCommand(rectangle, drawingPane);
        cutShapeCommand.execute();
        rectangle.setX(10.0);
        rectangle.setY(10.0);
        assertEquals(rectangle.toString(), drawingPane.getCopiedShape().toString());
        assertFalse(drawingPane.getChildren().contains(rectangle));
        assertEquals(size - 1, drawingPane.getChildren().size());
        cutShapeCommand.undo();
        assertEquals(null, drawingPane.getCopiedShape());
        assertTrue(drawingPane.getChildren().contains(rectangle));
        assertEquals(size, drawingPane.getChildren().size());
        assertEquals(index, drawingPane.getChildren().indexOf(rectangle));
        
        //ellipse
        Ellipse ellipse = new Ellipse(1.0, 1.0, 1.0, 1.0, Color.BLACK, Color.WHITE);
        drawingPane.getChildren().add(ellipse);
        size = drawingPane.getChildren().size();
        index = drawingPane.getChildren().indexOf(ellipse);
        cutShapeCommand = new CutShapeCommand(ellipse, drawingPane);
        cutShapeCommand.execute();
        ellipse.setCenterX(10 + ellipse.getRadiusX());
        ellipse.setCenterY(10 + ellipse.getRadiusY());
        assertEquals(ellipse.toString(), drawingPane.getCopiedShape().toString());
        assertFalse(drawingPane.getChildren().contains(ellipse));
        assertEquals(size - 1, drawingPane.getChildren().size());
        cutShapeCommand.undo();
        assertEquals(null, drawingPane.getCopiedShape());
        assertTrue(drawingPane.getChildren().contains(ellipse));
        assertEquals(size, drawingPane.getChildren().size());
        assertEquals(index, drawingPane.getChildren().indexOf(ellipse));
    }
    
}
