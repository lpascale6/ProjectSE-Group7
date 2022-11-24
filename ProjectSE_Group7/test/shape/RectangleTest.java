package shape;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.junit.*;
import static org.junit.Assert.*;
/**
 *
 * @author group7
 */
public class RectangleTest {
    
    private Rectangle instance;
    
    public RectangleTest() {
    }
    
    @Before
    public void setUp() {
        instance = new Rectangle();
    }

    /**
     * Test of getX_ method, of class Rectangle.
     */
    @Test
    public void testGetX_() {
        System.out.println("getX_");
        
        //Test setting a positive x's value
        double x = 3.0;
        instance.setX_(x);
        double result = instance.getX_();
        assertEquals(x, result, 0);
        
        
        //Test setting 0.0 as x's value
        x = 3.0;
        instance.setX_(x);
        result = instance.getX_();
        assertEquals(x, result, 0);
        
        //Test setting a negative as x's value
        x = -3.0;
        instance.setX_(x);
        result = instance.getX_();
        assertEquals(x, result, 0);
    }

    /**
     * Test of setX_ method, of class Rectangle.
     */
    @Test
    public void testSetX_() {
        System.out.println("setX_");
        
        //Test with positive x's value
        double x = 3.0;
        instance.setX_(x);
        assertEquals(x, instance.getX_(), 0);
        
        //Test with 0.0 as x's value
        x = 0.0;
        instance.setX_(x);
        assertEquals(x, instance.getX_(), 0);
        
        //Test with negative value as x's value
        x = -3.0;
        instance.setX_(x);
        assertEquals(x, instance.getX_(), 0);
    }

    /**
     * Test of getY_ method, of class Rectangle.
     */
    @Test
    public void testGetY_() {
        System.out.println("getY_");
        
        //Test setting a positive y's value
        double y = 3.0;
        instance.setY_(y);
        double result = instance.getY_();
        assertEquals(y, result, 0);
        
        //Test setting 0.0 as y's value
        y = 3.0;
        instance.setY_(y);
        result = instance.getY_();
        assertEquals(y, result, 0);
        
        //Test setting a negative as y's value
        y = -3.0;
        instance.setY_(y);
        result = instance.getY_();
        assertEquals(y, result, 0);
        
    }

    /**
     * Test of setY_ method, of class Rectangle.
     */
    @Test
    public void testSetY_() {
        System.out.println("setY_");
        
        //Test with positive y's value
        double y = 3.0;
        instance.setY_(y);
        assertEquals(y, instance.getY_(), 0);
        
        //Test with 0.0 as y's value
        y = 0.0;
        instance.setY_(y);
        assertEquals(y, instance.getY_(), 0);
        
        //Test with negative x's value
        y = -3.0;
        instance.setY_(y);
        assertEquals(y, instance.getY_(), 0);

    }

    /**
     * Test of getWidth_ method, of class Rectangle.
     */
    @Test
    public void testGetWidth_() {
        System.out.println("getWidth_");
        
        //Test setting a positive width's value
        double width = 3.0;
        instance.setWidth_(width);
        double result = instance.getWidth_();
        assertEquals(width, result, 0);
        
        //Test setting 0.0 as width's value
        width = 3.0;
        instance.setWidth_(width);
        result = instance.getWidth_();
        assertEquals(width, result, 0);
        
        //Test setting a negative as width's value
        width = -3.0;
        instance.setWidth_(width);
        result = instance.getWidth_();
        assertEquals(width, result, 0);
    }

    /**
     * Test of setWidth_ method, of class Rectangle.
     */
    @Test
    public void testSetWidth_() {
        System.out.println("setWidth_");
        
        //Test with positive width's value
        double width = 3.0;
        instance.setWidth_(width);
        assertEquals(width, instance.getWidth_(), 0);
        
        //Test with 0.0 as width's value
        width = 0.0;
        instance.setWidth_(width);
        assertEquals(width, instance.getWidth_(), 0);
        
        //Test with negative width's value
        width = -3.0;
        instance.setWidth_(width);
        assertEquals(width, instance.getWidth_(), 0);
    }

    /**
     * Test of getHeight_ method, of class Rectangle.
     */
    @Test
    public void testGetHeight_() {
        System.out.println("getHeight_");
        
        //Test setting a positive heigtht's value
        double height = 3.0;
        instance.setHeight_(height);
        double result = instance.getHeight_();
        assertEquals(height, result, 0);
        
        //Test setting 0.0 as height's value
        height = 3.0;
        instance.setHeight_(height);
        result = instance.getHeight_();
        assertEquals(height, result, 0);
        
        //Test setting a negative as y's value
        height = -3.0;
        instance.setHeight_(height);
        result = instance.getHeight_();
        assertEquals(height, result, 0);
    }

    /**
     * Test of setHeight_ method, of class Rectangle.
     */
    @Test
    public void testSetHeight_() {
        System.out.println("setHeight_");
        
        //Test with positive height's value
        double height = 3.0;
        instance.setHeight_(height);
        assertEquals(height, instance.getHeight_(), 0);
        
        //Test with 0.0 as height's value
        height = 0.0;
        instance.setHeight_(height);
        assertEquals(height, instance.getHeight_(), 0);
        
        //Test with negative height's value
        height = -3.0;
        instance.setHeight_(height);
        assertEquals(height, instance.getHeight_(), 0);
    }

    /**
     * Test of setFillColor method, of class Rectangle.
     */
    @Test
    public void testSetFillColor() {
        System.out.println("setFillColor");
        
        //Test with null fill color
        Paint fill = null;
        instance.setFillColor(fill);
        assertEquals(fill, instance.getFillColor());
        
        //Test with blue fill color
        fill = Color.BLUE;
        instance.setFillColor(fill);
        assertEquals(fill, instance.getFillColor());
    }

    /**
     * Test of getFillColor method, of class Rectangle.
     */
    @Test
    public void testGetFillColor() {
        System.out.println("getFillColor");
        
        //Test with null fill color
        Paint color = null;
        instance.setFillColor(color);
        Paint result = instance.getFillColor();
        assertEquals(color, result);
        
        //Test with blue fill color
        color = Color.BLUE;
        instance.setFillColor(color);
        result = instance.getFillColor();
        assertEquals(color, result);
    }

    /**
     * Test of setOutlineColor method, of class Rectangle.
     */
    @Test
    public void testSetOutlineColor() {
        System.out.println("setOutlineColor");
        
        //Test with null outline color
        Paint outline = null;
        instance.setOutlineColor(outline);
        assertEquals(outline, instance.getOutlineColor());
        
        //Test with blue outline color
        outline = Color.BLUE;
        instance.setOutlineColor(outline);
        assertEquals(outline, instance.getOutlineColor());
    }

    /**
     * Test of getOutlineColor method, of class Rectangle.
     */
    @Test
    public void testGetOutlineColor() {
        System.out.println("getOutlineColor");
        
        //Test with null outline color
        Paint color = null;
        instance.setOutlineColor(color);
        Paint result = instance.getOutlineColor();
        assertEquals(color, result);
        
        //Test with blue outline color
        color = Color.BLUE;
        instance.setOutlineColor(color);
        result = instance.getOutlineColor();
        assertEquals(color, result);
    }
    
}
