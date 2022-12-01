/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package command;

import gui.DrawingPane;
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
public class ToTheFrontCommandTest {
    
    Shape shape1;
    int shape1Index;
    Shape shape2;
    Shape shape3;
    DrawingPane drawingPane;
    ToTheFrontCommand toTheFrontCommand;
    
    public ToTheFrontCommandTest() {
    }
    
    @Before
    public void setUp() {
        drawingPane = new DrawingPane();
        shape1 = new Line();
        drawingPane.getChildren().add(shape1);
        shape1Index = drawingPane.getChildren().indexOf(shape1);
        shape2 = new Rectangle();
        drawingPane.getChildren().add(shape2);
        shape3 = new Ellipse();
        drawingPane.getChildren().add(shape3);
        toTheFrontCommand = new ToTheFrontCommand(shape1, drawingPane);
    }
    
    /**
     * Test of execute method, of class ToTheFrontCommand.
     */
    @Test
    public void testExecute() throws Exception {
        toTheFrontCommand.execute();
        assertEquals(drawingPane.getChildren().size() - 1, drawingPane.getChildren().indexOf(shape1));
        
    }

    /**
     * Test of undo method, of class ToTheFrontCommand.
     */
    @Test
    public void testUndo() throws Exception {
        toTheFrontCommand.execute();
        assertEquals(drawingPane.getChildren().size() - 1, drawingPane.getChildren().indexOf(shape1));
        toTheFrontCommand.undo();
        assertEquals(shape1Index, drawingPane.getChildren().indexOf(shape1));
    }
    
}
