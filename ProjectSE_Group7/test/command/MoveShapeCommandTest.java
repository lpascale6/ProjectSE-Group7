package command;

import javafx.scene.shape.Shape;
import org.junit.*;
import static org.junit.Assert.*;
import shape.Ellipse;
import shape.Line;
import shape.Rectangle;

/**
 *
 * @author group7
 */
public class MoveShapeCommandTest {
    Shape shapeToMove;
    
    @Before
    public void setUp() {
        
    }

    /**
     * Test of execute method, of class MoveShapeCommand.
     */
    @Test
    public void testExecute() throws Exception {
        System.out.println("Testing execute method of MoveShapeCommand class.");
        
        MoveShapeCommand instance;
        
        // testing for move of a line shape, with casual coordinates
        shapeToMove = new Line(64, 64, 50, 50);
        instance = new MoveShapeCommand(shapeToMove, 128, 64);
        instance.execute();
        
        Line line = (Line) shapeToMove;
        assertEquals(192, line.getLineStartingX(), 0);
        assertEquals(128, line.getLineStartingY(), 0);
        
        // testing for move of a rectangle shape, with coordinates equals to 0
        shapeToMove = new Rectangle(32, 32, 10, 20);
        instance = new MoveShapeCommand(shapeToMove, 0, 0);
        instance.execute();
        
        Rectangle rectangle = (Rectangle) shapeToMove;
        assertEquals(32, rectangle.getRectangleX(), 0);
        assertEquals(32, rectangle.getRectangleY(), 0);
        
        // testing for move of a ellipse shape, with negative coordinates
        shapeToMove = new Ellipse(128, 32, 50, 75);
        instance = new MoveShapeCommand(shapeToMove, -64, -128);
        instance.execute();
        
        Ellipse ellipse = (Ellipse) shapeToMove;
        assertEquals(64, ellipse.getEllipseCenterX(), 0);
        assertEquals(-96, ellipse.getEllipseCenterY(), 0);
    }

    /**
     * Test of undo method, of class MoveShapeCommand.
     */
    @Test
    public void testUndo() throws Exception {
        System.out.println("Testing undo method of MoveShapeCommand class.");
        
        MoveShapeCommand instance;
        
        // testing for move of a line shape, with casual coordinates
        shapeToMove = new Line(64, 64, 50, 50);
        instance = new MoveShapeCommand(shapeToMove, 128, 64);
        instance.execute();
        instance.undo();
        
        Line line = (Line) shapeToMove;
        assertEquals(64, line.getLineStartingX(), 0);
        assertEquals(64, line.getLineStartingY(), 0);
        assertEquals(50, line.getLineEndingX(), 0);
        assertEquals(50, line.getLineEndingY(), 0);
        
        // testing for move of a rectangle shape, with coordinates equals to 0
        shapeToMove = new Rectangle(32, 32, 10, 20);
        instance = new MoveShapeCommand(shapeToMove, 12.34, -43.21);
        instance.execute();
        instance.undo();
        
        Rectangle rectangle = (Rectangle) shapeToMove;
        assertEquals(32, rectangle.getRectangleX(), 0);
        assertEquals(32, rectangle.getRectangleY(), 0);
        
        // testing for move of a ellipse shape, with negative coordinates
        shapeToMove = new Ellipse(128, 32, 50, 75);
        instance = new MoveShapeCommand(shapeToMove, -64, -128);
        instance.execute();
        instance.undo();
        
        Ellipse ellipse = (Ellipse) shapeToMove;
        assertEquals(128, ellipse.getEllipseCenterX(), 0);
        assertEquals(32, ellipse.getEllipseCenterY(), 0);

    }
    
}
