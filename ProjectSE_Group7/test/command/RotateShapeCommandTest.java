package command;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import shape.Ellipse;
import shape.Line;
import shape.Rectangle;

/**
 *
 * @author group7
 */
public class RotateShapeCommandTest {
    
    //Variables for shapes to rotate
    private Line line1;
    private Line line2;
    private Line line3;
    private Line line4;
    private Rectangle rectangle;
    private Ellipse ellipse;
    
    //Variables for Rotate Command
    private RotateShapeCommand instanceLine1;
    private RotateShapeCommand instanceLine2;
    private RotateShapeCommand instanceLine3;
    private RotateShapeCommand instanceLine4;
    private RotateShapeCommand instanceRectangle;
    private RotateShapeCommand instanceEllipse;
    
    //Variable for angle to rotate
    private double angleToRotate;
    
    public RotateShapeCommandTest() {
    }
    
    @Before
    public void setUp() {
        
        //distX = endX - startX
        //distY = endY - startY
        
        //Line with distX > 0 & distY > 0
        Line line1 = new Line(2.0, 2.0, 5.0, 5.0);
        
        //Line with distX < 0 & distY < 0
        Line line2 = new Line(5.0, 5.0, 2.0, 2.0);
        
        //Line with distX > 0 & distY < 0
        Line line3 = new Line(2.0, 5.0, 5.0, 2.0);
        
        //Line with distX < 0 & distY > 0
        Line line4 = new Line(5.0, 2.0, 2.0, 5.0);
        
        Rectangle rectangle = new Rectangle(2.0, 5.0, 10.0, 4.0);
        Ellipse ellipse = new Ellipse(2.0, 5.0, 10.0, 4.0);
        
        this.angleToRotate = 45.0;
        
        this.instanceLine1 = new RotateShapeCommand(line1, this.angleToRotate);
        this.instanceLine2 = new RotateShapeCommand(line2, this.angleToRotate);
        this.instanceLine3 = new RotateShapeCommand(line3, this.angleToRotate);
        this.instanceLine4 = new RotateShapeCommand(line4, this.angleToRotate);
        
        this.instanceRectangle = new RotateShapeCommand(rectangle, this.angleToRotate);
        this.instanceEllipse = new RotateShapeCommand(ellipse, this.angleToRotate);
        
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.line4 = line4;
        this.rectangle = rectangle;
        this.ellipse = ellipse;
    }
    
    /**
     * Test of execute method, of class RotateShapeCommand.
     * @throws java.lang.Exception
     */
    @Test
    public void testExecute() throws Exception {
        System.out.println("execute");
        
        double expResult = this.angleToRotate;
        
        this.instanceLine1.execute();
        this.instanceLine2.execute();
        this.instanceLine3.execute();
        this.instanceLine4.execute();
        this.instanceRectangle.execute();
        this.instanceEllipse.execute();
        
        assertEquals(expResult, this.line1.getRotate(), 0);
        assertEquals(expResult, this.line2.getRotate(), 0);
        assertEquals(expResult, this.line3.getRotate(), 0);
        assertEquals(expResult, this.line4.getRotate(), 0);
        assertEquals(expResult, this.rectangle.getRotate(), 0);
        assertEquals(expResult, this.ellipse.getRotate(), 0);
        
        //Test of execute method with a negative angle value
        this.angleToRotate = -45.0;
        
        this.instanceLine1 = new RotateShapeCommand(this.line1, this.angleToRotate);
        this.instanceLine2 = new RotateShapeCommand(this.line2, this.angleToRotate);
        this.instanceLine3 = new RotateShapeCommand(this.line3, this.angleToRotate);
        this.instanceLine4 = new RotateShapeCommand(this.line4, this.angleToRotate);
        this.instanceRectangle = new RotateShapeCommand(this.rectangle, this.angleToRotate);
        this.instanceEllipse = new RotateShapeCommand(this.ellipse, this.angleToRotate);
        
        expResult = this.angleToRotate;
        
        this.instanceLine1.execute();
        this.instanceLine2.execute();
        this.instanceLine3.execute();
        this.instanceLine4.execute();
        this.instanceRectangle.execute();
        this.instanceEllipse.execute();
        
        assertEquals(expResult, this.line1.getRotate(), 0);
        assertEquals(expResult, this.line2.getRotate(), 0);
        assertEquals(expResult, this.line3.getRotate(), 0);
        assertEquals(expResult, this.line4.getRotate(), 0);
        assertEquals(expResult, this.rectangle.getRotate(), 0);
        assertEquals(expResult, this.ellipse.getRotate(), 0);
    }

    /**
     * Test of undo method, of class RotateShapeCommand.
     */
    @Test
    public void testUndo() throws Exception {
        System.out.println("undo");
        
        double expResult = 0.0;
        
        this.instanceLine1.execute();
        this.instanceLine2.execute();
        this.instanceLine3.execute();
        this.instanceLine4.execute();
        this.instanceRectangle.execute();
        this.instanceEllipse.execute();
        
        this.instanceLine1.undo();
        this.instanceLine2.undo();
        this.instanceLine3.undo();
        this.instanceLine4.undo();
        this.instanceRectangle.undo();
        this.instanceEllipse.undo();
        
        assertEquals(expResult, this.line1.getRotate(), 0);
        assertEquals(expResult, this.line2.getRotate(), 0);
        assertEquals(expResult, this.line3.getRotate(), 0);
        assertEquals(expResult, this.line4.getRotate(), 0);
        assertEquals(expResult, this.rectangle.getRotate(), 0);
        assertEquals(expResult, this.ellipse.getRotate(), 0);
        
    }
    
}
