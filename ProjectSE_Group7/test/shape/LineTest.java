package shape;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author group7
 */
public class LineTest {
    
    public LineTest() {
    }

    
    
    @Before
    public void setUpClass() {
    }

    
   

    /**
     * Test of setStartingX method, of class Line.
     */
    @Test
    public void testSetStartingX() {
        
        Line instance = new Line();
        
        System.out.println("setStartingX 0.0");
        double expResult = 0.0;
        instance.setStartingX(expResult);
        double result = instance.getStartingX();
        assertEquals(expResult, result, 0);
        
        
        System.out.println("setStartingX 10.5");
        expResult = 10.5;
        instance.setStartingX(expResult);
        result = instance.getStartingX();
        assertEquals(expResult, result, 0);
        
         
        System.out.println("setStartingX -5.2");
        expResult = -5.2;
        instance.setStartingX(expResult);
        result = instance.getStartingX();
        assertEquals(expResult, result, 0);
        
    }

    /**
     * Test of setStartingY method, of class Line.
     */
    @Test
    public void testSetStartingY() {
        
        Line instance = new Line();
        
        System.out.println("setStartingY 0.0");
        double expResult = 0.0;
        instance.setStartingY(expResult);
        double result = instance.getStartingY();
        assertEquals(expResult, result, 0);
        
        
        System.out.println("setStartingY 10.5");
        expResult = 10.5;
        instance.setStartingY(expResult);
        result = instance.getStartingY();
        assertEquals(expResult, result, 0);
        
         
        System.out.println("setStartingY -5.2");
        expResult = -5.2;
        instance.setStartingY(expResult);
        result = instance.getStartingY();
        assertEquals(expResult, result, 0);
        
    }

    /**
     * Test of setEndingX method, of class Line.
     */
    @Test
    public void testSetEndingX() {
        
        Line instance = new Line();
    
        System.out.println("setEndingX 0.0");
        double expResult = 0.0;
        instance.setEndingX(expResult);
        double result = instance.getEndingX();
        assertEquals(expResult, result, 0);
        
        
        System.out.println("setEndingX 10.5");
        expResult = 10.5;
        instance.setEndingX(expResult);
        result = instance.getEndingX();
        assertEquals(expResult, result, 0);
        
         
        System.out.println("setEndingX -5.2");
        expResult = -5.2;
        instance.setEndingX(expResult);
        result = instance.getEndingX();
        assertEquals(expResult, result, 0);
        
    }

    /**
     * Test of setEndingY method, of class Line.
     */
    @Test
    public void testSetEndingY() {
        
        Line instance = new Line();
      
        System.out.println("setEndingY 0.0");
        double expResult = 0.0;
        instance.setEndingY(expResult);
        double result = instance.getEndingY();
        assertEquals(expResult, result, 0);
        
        
        System.out.println("setEndingY 10.5");
        expResult = 10.5;
        instance.setEndingY(expResult);
        result = instance.getEndingY();
        assertEquals(expResult, result, 0);
        
         
        System.out.println("setEndingY -5.2");
        expResult = -5.2;
        instance.setEndingY(expResult);
        result = instance.getEndingY();
        assertEquals(expResult, result, 0);
        
    }

    /**
     * Test of getStartingX method, of class Line.
     */
    @Test
    public void testGetStartingX() {
        
        System.out.println("getStartingX 0.0");
        double expResult = 0.0;
        Line instance = new Line(expResult, 1.0, 10.0, 12.3);
        double result = instance.getStartingX();
        assertEquals(expResult, result, 0);
        
        
        System.out.println("getStartingX 10.5");
        expResult = 10.5;
        instance = new Line(expResult, 2.0, 12.0, 13.3);
        result = instance.getStartingX();
        assertEquals(expResult, result, 0);
        
         
        System.out.println("getStartingX -5.2");
        expResult = -5.2;
        instance = new Line(expResult, -3.0, 13.0, 10.3);
        result = instance.getStartingX();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of getStartingY method, of class Line.
     */
    @Test
    public void testGetStartingY() {
        
        System.out.println("getStartingY 0.0");
        double expResult = 0.0;
        Line instance = new Line(1.1, expResult, 10.0, 12.3);
        double result = instance.getStartingY();
        assertEquals(expResult, result, 0);
        
        
        System.out.println("getStartingY 10.5");
        expResult = 10.5;
        instance = new Line(1.2, expResult, 14.0, 11.3);
        result = instance.getStartingY();
        assertEquals(expResult, result, 0);
        
        
        System.out.println("getStartingY -5.2");
        expResult = -5.2;
        instance = new Line(-3.1, expResult, 9.0, 8.3);
        result = instance.getStartingY();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of getEndingX method, of class Line.
     */
    @Test
    public void testGetEndingX() {
        
        System.out.println("getEndingX 0.0");
        double expResult = 0.0;
        Line instance = new Line(1.1, 2.3, expResult, 12.3);
        double result = instance.getEndingX();
        assertEquals(expResult, result, 0);
        
        
        System.out.println("getEndingX 10.5");
        expResult = 10.5;
        instance = new Line(0.1, 2.2, expResult, 12.5);
        result = instance.getEndingX();
        assertEquals(expResult, result, 0);
        
        
        System.out.println("getEndingX -5.2");
        expResult = -5.2;
        instance = new Line(3.5, 2.3, expResult, -4.3);
        result = instance.getEndingX();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of getEndingY method, of class Line.
     */
    @Test
    public void testGetEndingY() {
        
        System.out.println("getEndingY 0.0");
        double expResult = 0.0;
        Line instance = new Line(-10.2, -7.3, 1.0, expResult);
        double result = instance.getEndingY();
        assertEquals(expResult, result, 0);
        
        
        System.out.println("getEndingY 10.5");
        expResult = 10.5;
        instance = new Line(1.1, 2.3, 9.2, expResult);
        result = instance.getEndingY();
        assertEquals(expResult, result, 0);
        
          
        System.out.println("getEndingY -5.2");
        expResult = -5.2;
        instance = new Line(5.2, 7.3, -4.4, expResult);
        result = instance.getEndingY();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of setColor method, of class Line.
     */
    @Test
    public void testSetColor() {
        
        Line instance = new Line();
        
        System.out.println("setColor red");
        Paint expResult = Color.RED ;
        instance.setColor(expResult);
        Paint result = instance.getColor();
        assertEquals(expResult, result);
        
        
        System.out.println("setColor blue");
        expResult = Color.BLUE ;
        instance.setColor(expResult);
        result = instance.getColor();
        assertEquals(expResult, result);
        
        
        System.out.println("setColor green");
        expResult = Color.GREEN ;
        instance.setColor(expResult);
        result = instance.getColor();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getColor method, of class Line.
     */
    @Test
    public void testGetColor() {
   
        System.out.println("getColor red");
        Paint expResult = Color.RED ;
        Line instance = new Line(0.0, 0.0, 5.5, 5.5, expResult);
        Paint result = instance.getColor();
        assertEquals(expResult, result);
        
        
        System.out.println("getColor blue");
        expResult = Color.BLUE ;
        instance = new Line(1.1, 2.2, 13.3, 14.4, expResult);
        result = instance.getColor();
        assertEquals(expResult, result);
        
        
        System.out.println("getColor green");
        expResult = Color.GREEN ;
        instance = new Line(2.0, 4.0, 12.5, 15.2, expResult);
        result = instance.getColor();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Line.
     */
    @Test
    public void testToString() {
        System.out.println("toString 0.0 0.0 10.0 10.0 #ffffff");
        Double expLineStartX = 0.0;
        Double expLineStartY = 0.0;
        Double expLineEndX = 10.0;
        Double expLineEndY = 10.0;
        String expLineColorStr = "#ffffff";
        Paint expLineColor = Paint.valueOf(expLineColorStr);
        
        String expRepresentation = "line;" + expLineStartX + ";" + expLineStartY + ";" + expLineEndX + ";" + expLineEndY + ";" + expLineColorStr;
        Line resLine = new Line(expLineStartX, expLineStartY, expLineEndX, expLineEndY, expLineColor);
        String resRepresentation = resLine.toString();
        
        assertEquals(expRepresentation, resRepresentation);
        
        
        System.out.println("toString -5.3 -10.2 15.1 9.7 #0a0f0d");
        expLineStartX = -5.3;
        expLineStartY = -10.2;
        expLineEndX = 15.1;
        expLineEndY = 9.7;
        expLineColorStr = "#0a0f0d";
        expLineColor = Paint.valueOf(expLineColorStr);
        
        expRepresentation = "line;" + expLineStartX + ";" + expLineStartY + ";" + expLineEndX + ";" + expLineEndY + ";" + expLineColorStr;
        resLine = new Line(expLineStartX, expLineStartY, expLineEndX, expLineEndY, expLineColor);
        resRepresentation = resLine.toString();
        
        assertEquals(expRepresentation, resRepresentation);
    }
    
}
