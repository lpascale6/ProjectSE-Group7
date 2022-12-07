package command;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import shape.Ellipse;
import shape.Line;
import shape.Rectangle;

/**
 *
 * @author paolo
 */
public class HorizontalMirrorShapeCommandTest {
    
    //Variables for test shapes
    private Line line1;
    private Line line2;
    private Line line3;
    private Line line4;
    private Rectangle rectangle;
    private Ellipse ellipse;
    
    //Variables for commands
    private HorizontalMirrorShapeCommand instanceLine1;
    private HorizontalMirrorShapeCommand instanceLine2;
    private HorizontalMirrorShapeCommand instanceLine3;
    private HorizontalMirrorShapeCommand instanceLine4;
    private HorizontalMirrorShapeCommand instanceRectangle;
    private HorizontalMirrorShapeCommand instanceEllipse;
    
    public HorizontalMirrorShapeCommandTest() {
    }
    

    /**
     * Set up the variables before testing.
     */
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
        
        this.instanceLine1 = new HorizontalMirrorShapeCommand(line1);
        this.instanceLine2 = new HorizontalMirrorShapeCommand(line2);
        this.instanceLine3 = new HorizontalMirrorShapeCommand(line3);
        this.instanceLine4 = new HorizontalMirrorShapeCommand(line4);
        
        this.instanceRectangle = new HorizontalMirrorShapeCommand(rectangle);
        this.instanceEllipse = new HorizontalMirrorShapeCommand(ellipse);
        
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.line4 = line4;
        this.rectangle = rectangle;
        this.ellipse = ellipse;
    }

    /**
     * Test of execute method, of class HorizontalMirrorShapeCommand.
     */
    @Test
    public void testExecute() throws Exception {
        System.out.println("execute");
        
        double expResult = -1.0;
        
        this.instanceLine1.execute();
        this.instanceLine2.execute();
        this.instanceLine3.execute();
        this.instanceLine4.execute();
        this.instanceRectangle.execute();
        this.instanceEllipse.execute();
        
        assertEquals(expResult, this.line1.getScaleX(), 0);
        assertEquals(expResult, this.line2.getScaleX(), 0);
        assertEquals(expResult, this.line3.getScaleX(), 0);
        assertEquals(expResult, this.line4.getScaleX(), 0);
        assertEquals(expResult, this.rectangle.getScaleX(), 0);
        assertEquals(expResult, this.ellipse.getScaleX(), 0);
        
        expResult = 1.0;
        
        this.instanceLine1.execute();
        this.instanceLine2.execute();
        this.instanceLine3.execute();
        this.instanceLine4.execute();
        this.instanceRectangle.execute();
        this.instanceEllipse.execute();
        
        assertEquals(expResult, this.line1.getScaleX(), 0);
        assertEquals(expResult, this.line2.getScaleX(), 0);
        assertEquals(expResult, this.line3.getScaleX(), 0);
        assertEquals(expResult, this.line4.getScaleX(), 0);
        assertEquals(expResult, this.rectangle.getScaleX(), 0);
        assertEquals(expResult, this.ellipse.getScaleX(), 0);
        
    }

    /**
     * Test of undo method, of class HorizontalMirrorShapeCommand.
     */
    @Test
    public void testUndo() throws Exception {
        System.out.println("undo");
        
        double expResult = 1.0;
        
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
        
        assertEquals(expResult, this.line1.getScaleX(), 0);
        assertEquals(expResult, this.line2.getScaleX(), 0);
        assertEquals(expResult, this.line3.getScaleX(), 0);
        assertEquals(expResult, this.line4.getScaleX(), 0);
        assertEquals(expResult, this.rectangle.getScaleX(), 0);
        assertEquals(expResult, this.ellipse.getScaleX(), 0);
        
        expResult = -1.0;
        
        this.instanceLine1.undo();
        this.instanceLine2.undo();
        this.instanceLine3.undo();
        this.instanceLine4.undo();
        this.instanceRectangle.undo();
        this.instanceEllipse.undo();
        
        assertEquals(expResult, this.line1.getScaleX(), 0);
        assertEquals(expResult, this.line2.getScaleX(), 0);
        assertEquals(expResult, this.line3.getScaleX(), 0);
        assertEquals(expResult, this.line4.getScaleX(), 0);
        assertEquals(expResult, this.rectangle.getScaleX(), 0);
        assertEquals(expResult, this.ellipse.getScaleX(), 0);
    }
    
}
