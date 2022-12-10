package shape;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author group7
 */
public class EllipseTest {
    
    private Ellipse ellipse;

    /**
     * The set up of the test, 
     * contains the operations that must be done only once before the test starts;
     * It only contains the creation of an empty object Ellipse.
     */
    @Before
    public void setUp() {
        ellipse = new Ellipse();
    }

    /**
     * Test of setEllipseCenterX method,
 of class Ellipse, with zero, positive and negative values.
     */  
    @Test
    public void testSetEllipseCenterX() {
        //zero
        double hPosition = 0.0;
        ellipse.setEllipseCenterX(hPosition);
        assertEquals(hPosition, ellipse.getEllipseCenterX(), 0);   
        //positive
        hPosition = 5.0;
        ellipse.setEllipseCenterX(hPosition);
        assertEquals(hPosition, ellipse.getEllipseCenterX(), 0);   
        //negative
        hPosition = -5.0;
        ellipse.setEllipseCenterX(hPosition);
        assertEquals(hPosition, ellipse.getEllipseCenterX(), 0); 
    }
    
    /**
     * Test of getEllipseCenterX method,
 of class Ellipse.
     */  
    @Test
    public void testGetEllipseCenterX() {
        double hPosition = 1.0;
        double vPosition = 0.0;
        double width = 0.0;
        double height = 0.0;
        ellipse = new Ellipse(hPosition, vPosition, width, height);
        double expectedHPosition = 1.0;
        assertEquals(expectedHPosition, ellipse.getEllipseCenterX(), 0); 
    }

    /**
     * Test of setEllipseCenterY method,
 of class Ellipse, with zero, positive and negative values.
     */  
    @Test
    public void testSetEllipseCenterY() {
        //zero
        double vPosition = 0.0;
        ellipse.setEllipseCenterY(vPosition);
        assertEquals(vPosition, ellipse.getEllipseCenterY(), 0);
        //positive
        vPosition = 10.0;
        ellipse.setEllipseCenterY(vPosition);
        assertEquals(vPosition, ellipse.getEllipseCenterY(), 0); 
        //negative
        vPosition = -10.0;
        ellipse.setEllipseCenterY(vPosition);
        assertEquals(vPosition, ellipse.getEllipseCenterY(), 0);
    }

    /**
     * Test of getEllipseCenterY method,
 of class Ellipse.
     */  
    @Test
    public void testGetEllipseCenterY() {
        double hPosition = 0.0;
        double vPosition = 1.0;
        double width = 0.0;
        double height = 0.0;
        ellipse = new Ellipse(hPosition, vPosition, width, height);
        double expectedVPosition = 1.0;
        assertEquals(expectedVPosition, ellipse.getEllipseCenterY(), 0); 
    }
    
    /**
     * Test of setEllipseRadiusX method,
 of class Ellipse, with zero, positive and negative values.
     */  
    @Test
    public void testSetEllipseRadiusX() {
        //zero
        double width = 0.0;
        ellipse.setEllipseRadiusX(width);
        assertEquals(width, ellipse.getEllipseRadiusX(), 0);
        //positive
        width = 15.0;
        ellipse.setEllipseRadiusX(width);
        assertEquals(width, ellipse.getEllipseRadiusX(), 0);
        //negative
        width = -15.0;
        ellipse.setEllipseRadiusX(width);
        assertEquals(width, ellipse.getEllipseRadiusX(), 0);  
    }
    
    /**
     * Test of getEllipseRadiusX method,
 of class Ellipse.
     */  
    @Test
    public void testGetEllipseRadiusX() {
        double width = 1.0;
        double height = 0.0;
        ellipse = new Ellipse(width, height);
        double expectedWidth = 1.0;
        assertEquals(expectedWidth, ellipse.getEllipseRadiusX(), 0); 
    }

    /**
     * Test of setEllipseRadiusY method, 
 of class Ellipse, with zero, positive and negative values.
     */
    @Test
    public void testSetEllipseRadiusY() {
        //zero
        double height = 0.0;
        ellipse.setEllipseRadiusY(height);
        assertEquals(height, ellipse.getEllipseRadiusY(), 0);
        //positive
        height = 20.0;
        ellipse.setEllipseRadiusY(height);
        assertEquals(height, ellipse.getEllipseRadiusY(), 0);
        //negative
        height = -20.0;
        ellipse.setEllipseRadiusY(height);
        assertEquals(height, ellipse.getEllipseRadiusY(), 0);
    }
    
    /**
     * Test of getEllipseRadiusY method,
 of class Ellipse.
     */  
    @Test
    public void testGetEllipseRadiusY() {
        double width = 0.0;
        double height = 1.0;
        ellipse = new Ellipse(width, height);
        double expectedHeight = 1.0;
        assertEquals(expectedHeight, ellipse.getEllipseRadiusY(), 0); 
    }

    /**
     * Test of setOutlineColor method, 
     * of class Ellipse, with null value and two test values.
     */
    @Test
    public void testSetOutlineColor() {
        //null
        Paint outlineColor = null;
        ellipse.setOutlineColor(outlineColor);
        assertEquals(outlineColor, ellipse.getOutlineColor());
        //value 1
        outlineColor = Color.BLUE;
        ellipse.setOutlineColor(outlineColor);
        assertEquals(outlineColor, ellipse.getOutlineColor());
        //value 2
        outlineColor = Color.RED;
        ellipse.setOutlineColor(outlineColor);
        assertEquals(outlineColor, ellipse.getOutlineColor());
    }
    
    /**
     * Test of getOutlineColor method,
     * of class Ellipse.
     */  
    @Test
    public void testGetOutlineColor() {
        double hPosition = 0.0;
        double vposition = 0.0;
        double width = 0.0;
        double height = 0.0;
        Paint outlineColor = Color.BLUE;
        ellipse = new Ellipse(hPosition, vposition, width, height, outlineColor);
        Paint expectedOutlineColor = Color.BLUE;
        assertEquals(expectedOutlineColor, ellipse.getOutlineColor()); 
    }

    /**
     * Test of setFillColor method, 
     * of class Ellipse, with null value and two test values.
     */
    @Test
    public void testSetFillColor() {
        //null
        Paint fillColor = null;
        ellipse.setFillColor(fillColor);
        assertEquals(fillColor, ellipse.getFillColor());
        //value 1
        fillColor = Color.BLUE;
        ellipse.setFillColor(fillColor);
        assertEquals(fillColor, ellipse.getFillColor());
        //value 2
        fillColor = Color.RED;
        ellipse.setFillColor(fillColor);
        assertEquals(fillColor, ellipse.getFillColor());
    }
    
    /**
     * Test of getFillColor method,
     * of class Ellipse.
     */  
    @Test
    public void getFillColor() {
        double hPosition = 0.0;
        double vposition = 0.0;
        double width = 0.0;
        double height = 0.0;
        Paint outlineColor = null;
        Paint fillColor = Color.BLUE;
        ellipse = new Ellipse(hPosition, vposition, width, height, outlineColor, fillColor);
        Paint expectedFillColor = Color.BLUE;
        assertEquals(expectedFillColor, ellipse.getFillColor()); 
    }
    
    /**
     * Test of toString method, of class Rectangle.
     */
    @Test
    public void testToString(){
        System.out.println("toString");
        
        ellipse.setEllipseCenterX(2.0);
        ellipse.setEllipseCenterY(4.2);
        ellipse.setEllipseRadiusX(4.7);
        ellipse.setEllipseRadiusY(3.3);
        ellipse.setOutlineColor(Color.BLUE);
        ellipse.setFillColor(Color.GREEN);
        ellipse.setScaleX(-1.0);
        ellipse.setScaleY(1.0);
        ellipse.setRotate(-67.0);
        String expRes = "ellipse;2.0;4.2;4.7;3.3;0x0000ffff;0x008000ff;-1.0;1.0;-67.0";
        String result = ellipse.toString();
        assertEquals(expRes, result);
    }
}
