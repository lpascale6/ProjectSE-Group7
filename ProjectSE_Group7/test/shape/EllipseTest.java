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
     * Test of setCenterHorizontalPosition method,
     * of class Ellipse, with zero, positive and negative values.
     */  
    @Test
    public void testSetCenterHorizontalPosition() {
        //zero
        double hPosition = 0.0;
        ellipse.setCenterHorizontalPosition(hPosition);
        assertEquals(hPosition, ellipse.getCenterHorizontalPosition(), 0);   
        //positive
        hPosition = 5.0;
        ellipse.setCenterHorizontalPosition(hPosition);
        assertEquals(hPosition, ellipse.getCenterHorizontalPosition(), 0);   
        //negative
        hPosition = -5.0;
        ellipse.setCenterHorizontalPosition(hPosition);
        assertEquals(hPosition, ellipse.getCenterHorizontalPosition(), 0); 
    }
    
    /**
     * Test of getCenterHorizontalPosition method,
     * of class Ellipse.
     */  
    @Test
    public void testGetCenterHorizontalPosition() {
        double hPosition = 1.0;
        double vPosition = 0.0;
        double width = 0.0;
        double height = 0.0;
        ellipse = new Ellipse(hPosition, vPosition, width, height);
        double expectedHPosition = 1.0;
        assertEquals(expectedHPosition, ellipse.getCenterHorizontalPosition(), 0); 
    }

    /**
     * Test of setCenterVerticalPosition method,
     * of class Ellipse, with zero, positive and negative values.
     */  
    @Test
    public void testSetCenterVerticalPosition() {
        //zero
        double vPosition = 0.0;
        ellipse.setCenterVerticalPosition(vPosition);
        assertEquals(vPosition, ellipse.getCenterVerticalPosition(), 0);
        //positive
        vPosition = 10.0;
        ellipse.setCenterVerticalPosition(vPosition);
        assertEquals(vPosition, ellipse.getCenterVerticalPosition(), 0); 
        //negative
        vPosition = -10.0;
        ellipse.setCenterVerticalPosition(vPosition);
        assertEquals(vPosition, ellipse.getCenterVerticalPosition(), 0);
    }

    /**
     * Test of getCenterVerticalPosition method,
     * of class Ellipse.
     */  
    @Test
    public void testGetCenterVerticalPosition() {
        double hPosition = 0.0;
        double vPosition = 1.0;
        double width = 0.0;
        double height = 0.0;
        ellipse = new Ellipse(hPosition, vPosition, width, height);
        double expectedVPosition = 1.0;
        assertEquals(expectedVPosition, ellipse.getCenterVerticalPosition(), 0); 
    }
    
    /**
     * Test of setWidth method,
     * of class Ellipse, with zero, positive and negative values.
     */  
    @Test
    public void testSetWidth() {
        //zero
        double width = 0.0;
        ellipse.setWidth(width);
        assertEquals(width, ellipse.getWidth(), 0);
        //positive
        width = 15.0;
        ellipse.setWidth(width);
        assertEquals(width, ellipse.getWidth(), 0);
        //negative
        width = -15.0;
        ellipse.setWidth(width);
        assertEquals(width, ellipse.getWidth(), 0);  
    }
    
    /**
     * Test of getWidth method,
     * of class Ellipse.
     */  
    @Test
    public void testGetWidth() {
        double width = 1.0;
        double height = 0.0;
        ellipse = new Ellipse(width, height);
        double expectedWidth = 1.0;
        assertEquals(expectedWidth, ellipse.getWidth(), 0); 
    }

    /**
     * Test of setHeight method, 
     * of class Ellipse, with zero, positive and negative values.
     */
    @Test
    public void testSetHeight() {
        //zero
        double height = 0.0;
        ellipse.setHeight(height);
        assertEquals(height, ellipse.getHeight(), 0);
        //positive
        height = 20.0;
        ellipse.setHeight(height);
        assertEquals(height, ellipse.getHeight(), 0);
        //negative
        height = -20.0;
        ellipse.setHeight(height);
        assertEquals(height, ellipse.getHeight(), 0);
    }
    
    /**
     * Test of getHeight method,
     * of class Ellipse.
     */  
    @Test
    public void testGetHeight() {
        double width = 0.0;
        double height = 1.0;
        ellipse = new Ellipse(width, height);
        double expectedHeight = 1.0;
        assertEquals(expectedHeight, ellipse.getHeight(), 0); 
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
        
        ellipse.setCenterHorizontalPosition(2.0);
        ellipse.setCenterVerticalPosition(4.2);
        ellipse.setWidth(4.7);
        ellipse.setHeight(3.3);
        ellipse.setOutlineColor(Color.BLUE);
        ellipse.setFillColor(Color.GREEN);
        String expRes = "ellipse;2.0;4.2;4.7;3.3;#0000ff;#008000";
        String result = ellipse.toString();
        assertEquals(expRes, result);
    }
}
