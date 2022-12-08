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
     * Test of setLineStartingX method, of class Line.
     */
    @Test
    public void testSetLineStartingX() {
        
        Line instance = new Line();
        
        System.out.println("setStartingX 0.0");
        double expResult = 0.0;
        instance.setLineStartingX(expResult);
        double result = instance.getLineStartingX();
        assertEquals(expResult, result, 0);
        
        
        System.out.println("setStartingX 10.5");
        expResult = 10.5;
        instance.setLineStartingX(expResult);
        result = instance.getLineStartingX();
        assertEquals(expResult, result, 0);
        
         
        System.out.println("setStartingX -5.2");
        expResult = -5.2;
        instance.setLineStartingX(expResult);
        result = instance.getLineStartingX();
        assertEquals(expResult, result, 0);
        
    }

    /**
     * Test of setLineStartingY method, of class Line.
     */
    @Test
    public void testSetLineStartingY() {
        
        Line instance = new Line();
        
        System.out.println("setStartingY 0.0");
        double expResult = 0.0;
        instance.setLineStartingY(expResult);
        double result = instance.getLineStartingY();
        assertEquals(expResult, result, 0);
        
        
        System.out.println("setStartingY 10.5");
        expResult = 10.5;
        instance.setLineStartingY(expResult);
        result = instance.getLineStartingY();
        assertEquals(expResult, result, 0);
        
         
        System.out.println("setStartingY -5.2");
        expResult = -5.2;
        instance.setLineStartingY(expResult);
        result = instance.getLineStartingY();
        assertEquals(expResult, result, 0);
        
    }

    /**
     * Test of setLineEndingX method, of class Line.
     */
    @Test
    public void testSetLineEndingX() {
        
        Line instance = new Line();
    
        System.out.println("setEndingX 0.0");
        double expResult = 0.0;
        instance.setLineEndingX(expResult);
        double result = instance.getLineEndingX();
        assertEquals(expResult, result, 0);
        
        
        System.out.println("setEndingX 10.5");
        expResult = 10.5;
        instance.setLineEndingX(expResult);
        result = instance.getLineEndingX();
        assertEquals(expResult, result, 0);
        
         
        System.out.println("setEndingX -5.2");
        expResult = -5.2;
        instance.setLineEndingX(expResult);
        result = instance.getLineEndingX();
        assertEquals(expResult, result, 0);
        
    }

    /**
     * Test of setLineEndingY method, of class Line.
     */
    @Test
    public void testSetLineEndingY() {
        
        Line instance = new Line();
      
        System.out.println("setEndingY 0.0");
        double expResult = 0.0;
        instance.setLineEndingY(expResult);
        double result = instance.getLineEndingY();
        assertEquals(expResult, result, 0);
        
        
        System.out.println("setEndingY 10.5");
        expResult = 10.5;
        instance.setLineEndingY(expResult);
        result = instance.getLineEndingY();
        assertEquals(expResult, result, 0);
        
         
        System.out.println("setEndingY -5.2");
        expResult = -5.2;
        instance.setLineEndingY(expResult);
        result = instance.getLineEndingY();
        assertEquals(expResult, result, 0);
        
    }

    /**
     * Test of getLineStartingX method, of class Line.
     */
    @Test
    public void testGetLineStartingX() {
        
        System.out.println("getStartingX 0.0");
        double expResult = 0.0;
        Line instance = new Line(expResult, 1.0, 10.0, 12.3);
        double result = instance.getLineStartingX();
        assertEquals(expResult, result, 0);
        
        
        System.out.println("getStartingX 10.5");
        expResult = 10.5;
        instance = new Line(expResult, 2.0, 12.0, 13.3);
        result = instance.getLineStartingX();
        assertEquals(expResult, result, 0);
        
         
        System.out.println("getStartingX -5.2");
        expResult = -5.2;
        instance = new Line(expResult, -3.0, 13.0, 10.3);
        result = instance.getLineStartingX();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of getLineStartingY method, of class Line.
     */
    @Test
    public void testGetLineStartingY() {
        
        System.out.println("getStartingY 0.0");
        double expResult = 0.0;
        Line instance = new Line(1.1, expResult, 10.0, 12.3);
        double result = instance.getLineStartingY();
        assertEquals(expResult, result, 0);
        
        
        System.out.println("getStartingY 10.5");
        expResult = 10.5;
        instance = new Line(1.2, expResult, 14.0, 11.3);
        result = instance.getLineStartingY();
        assertEquals(expResult, result, 0);
        
        
        System.out.println("getStartingY -5.2");
        expResult = -5.2;
        instance = new Line(-3.1, expResult, 9.0, 8.3);
        result = instance.getLineStartingY();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of getLineEndingX method, of class Line.
     */
    @Test
    public void testGetLineEndingX() {
        
        System.out.println("getEndingX 0.0");
        double expResult = 0.0;
        Line instance = new Line(1.1, 2.3, expResult, 12.3);
        double result = instance.getLineEndingX();
        assertEquals(expResult, result, 0);
        
        
        System.out.println("getEndingX 10.5");
        expResult = 10.5;
        instance = new Line(0.1, 2.2, expResult, 12.5);
        result = instance.getLineEndingX();
        assertEquals(expResult, result, 0);
        
        
        System.out.println("getEndingX -5.2");
        expResult = -5.2;
        instance = new Line(3.5, 2.3, expResult, -4.3);
        result = instance.getLineEndingX();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of getLineEndingY method, of class Line.
     */
    @Test
    public void testGetLineEndingY() {
        
        System.out.println("getEndingY 0.0");
        double expResult = 0.0;
        Line instance = new Line(-10.2, -7.3, 1.0, expResult);
        double result = instance.getLineEndingY();
        assertEquals(expResult, result, 0);
        
        
        System.out.println("getEndingY 10.5");
        expResult = 10.5;
        instance = new Line(1.1, 2.3, 9.2, expResult);
        result = instance.getLineEndingY();
        assertEquals(expResult, result, 0);
        
          
        System.out.println("getEndingY -5.2");
        expResult = -5.2;
        instance = new Line(5.2, 7.3, -4.4, expResult);
        result = instance.getLineEndingY();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of setLineColor method, of class Line.
     */
    @Test
    public void testSetLineColor() {
        
        Line instance = new Line();
        
        System.out.println("setColor red");
        Paint expResult = Color.RED ;
        instance.setLineColor(expResult);
        Paint result = instance.getLineColor();
        assertEquals(expResult, result);
        
        
        System.out.println("setColor blue");
        expResult = Color.BLUE ;
        instance.setLineColor(expResult);
        result = instance.getLineColor();
        assertEquals(expResult, result);
        
        
        System.out.println("setColor green");
        expResult = Color.GREEN ;
        instance.setLineColor(expResult);
        result = instance.getLineColor();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getLineColor method, of class Line.
     */
    @Test
    public void testGetLineColor() {
   
        System.out.println("getColor red");
        Paint expResult = Color.RED ;
        Line instance = new Line(0.0, 0.0, 5.5, 5.5, expResult);
        Paint result = instance.getLineColor();
        assertEquals(expResult, result);
        
        
        System.out.println("getColor blue");
        expResult = Color.BLUE ;
        instance = new Line(1.1, 2.2, 13.3, 14.4, expResult);
        result = instance.getLineColor();
        assertEquals(expResult, result);
        
        
        System.out.println("getColor green");
        expResult = Color.GREEN ;
        instance = new Line(2.0, 4.0, 12.5, 15.2, expResult);
        result = instance.getLineColor();
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
        double scaleX = -1.0;
        double scaleY = 1.0;
        double rotate = 34.0;
        
        String expRepresentation = "line;" + expLineStartX + ";" + expLineStartY + ";" + expLineEndX + ";" + expLineEndY + ";" + expLineColorStr + ";" + scaleX + ";" + scaleY + ";" + rotate;
        Line resLine = new Line(expLineStartX, expLineStartY, expLineEndX, expLineEndY, expLineColor);
        resLine.setScaleX(scaleX);
        resLine.setScaleY(scaleY);
        resLine.setRotate(rotate);
        String resRepresentation = resLine.toString();
        
        assertEquals(expRepresentation, resRepresentation);
        
        
        System.out.println("toString -5.3 -10.2 15.1 9.7 #0a0f0d");
        expLineStartX = -5.3;
        expLineStartY = -10.2;
        expLineEndX = 15.1;
        expLineEndY = 9.7;
        expLineColorStr = "#0a0f0d";
        expLineColor = Paint.valueOf(expLineColorStr);
        scaleX = 1.0;
        scaleY = -1.0;
        rotate = -45.0;
        
        expRepresentation = "line;" + expLineStartX + ";" + expLineStartY + ";" + expLineEndX + ";" + expLineEndY + ";" + expLineColorStr + ";" + scaleX + ";" + scaleY + ";" + rotate;
        resLine = new Line(expLineStartX, expLineStartY, expLineEndX, expLineEndY, expLineColor);
        resLine.setScaleX(scaleX);
        resLine.setScaleY(scaleY);
        resLine.setRotate(rotate);
        resRepresentation = resLine.toString();
        
        assertEquals(expRepresentation, resRepresentation);
    }
    
}
