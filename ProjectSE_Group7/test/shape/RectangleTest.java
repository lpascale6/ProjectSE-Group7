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
     * Test of getRectangleX method, of class Rectangle.
     */
    @Test
    public void testGetRectangleX() {
        System.out.println("getRectangleX");
        
        //Test setting a positive x's value
        double x = 3.0;
        instance.setRectangleX(x);
        double result = instance.getRectangleX();
        assertEquals(x, result, 0);
        
        
        //Test setting 0.0 as x's value
        x = 3.0;
        instance.setRectangleX(x);
        result = instance.getRectangleX();
        assertEquals(x, result, 0);
        
        //Test setting a negative as x's value
        x = -3.0;
        instance.setRectangleX(x);
        result = instance.getRectangleX();
        assertEquals(x, result, 0);
    }

    /**
     * Test of setRectangleX method, of class Rectangle.
     */
    @Test
    public void testSetRectangleX() {
        System.out.println("setRectangleX");
        
        //Test with positive x's value
        double x = 3.0;
        instance.setRectangleX(x);
        assertEquals(x, instance.getRectangleX(), 0);
        
        //Test with 0.0 as x's value
        x = 0.0;
        instance.setRectangleX(x);
        assertEquals(x, instance.getRectangleX(), 0);
        
        //Test with negative value as x's value
        x = -3.0;
        instance.setRectangleX(x);
        assertEquals(x, instance.getRectangleX(), 0);
    }

    /**
     * Test of getRectangleY method, of class Rectangle.
     */
    @Test
    public void testGetRectangleY() {
        System.out.println("getRectangleY");
        
        //Test setting a positive y's value
        double y = 3.0;
        instance.setRectangleY(y);
        double result = instance.getRectangleY();
        assertEquals(y, result, 0);
        
        //Test setting 0.0 as y's value
        y = 3.0;
        instance.setRectangleY(y);
        result = instance.getRectangleY();
        assertEquals(y, result, 0);
        
        //Test setting a negative as y's value
        y = -3.0;
        instance.setRectangleY(y);
        result = instance.getRectangleY();
        assertEquals(y, result, 0);
        
    }

    /**
     * Test of setRectangleY method, of class Rectangle.
     */
    @Test
    public void testSetRectangleY() {
        System.out.println("setRectangleY");
        
        //Test with positive y's value
        double y = 3.0;
        instance.setRectangleY(y);
        assertEquals(y, instance.getRectangleY(), 0);
        
        //Test with 0.0 as y's value
        y = 0.0;
        instance.setRectangleY(y);
        assertEquals(y, instance.getRectangleY(), 0);
        
        //Test with negative x's value
        y = -3.0;
        instance.setRectangleY(y);
        assertEquals(y, instance.getRectangleY(), 0);

    }

    /**
     * Test of getRectangleWidth method, of class Rectangle.
     */
    @Test
    public void testGetRectangleWidth() {
        System.out.println("getRectangleWidth");
        
        //Test setting a positive width's value
        double width = 3.0;
        instance.setRectangleWidth(width);
        double result = instance.getRectangleWidth();
        assertEquals(width, result, 0);
        
        //Test setting 0.0 as width's value
        width = 3.0;
        instance.setRectangleWidth(width);
        result = instance.getRectangleWidth();
        assertEquals(width, result, 0);
        
        //Test setting a negative as width's value
        width = -3.0;
        instance.setRectangleWidth(width);
        result = instance.getRectangleWidth();
        assertEquals(width, result, 0);
    }

    /**
     * Test of setRectangleWidth method, of class Rectangle.
     */
    @Test
    public void testSetRectangleWidth() {
        System.out.println("setRectangleWidth");
        
        //Test with positive width's value
        double width = 3.0;
        instance.setRectangleWidth(width);
        assertEquals(width, instance.getRectangleWidth(), 0);
        
        //Test with 0.0 as width's value
        width = 0.0;
        instance.setRectangleWidth(width);
        assertEquals(width, instance.getRectangleWidth(), 0);
        
        //Test with negative width's value
        width = -3.0;
        instance.setRectangleWidth(width);
        assertEquals(width, instance.getRectangleWidth(), 0);
    }

    /**
     * Test of getRectangleHeight method, of class Rectangle.
     */
    @Test
    public void testGetRectangleHeight() {
        System.out.println("getRectangleHeight");
        
        //Test setting a positive heigtht's value
        double height = 3.0;
        instance.setRectangleHeight(height);
        double result = instance.getRectangleHeight();
        assertEquals(height, result, 0);
        
        //Test setting 0.0 as height's value
        height = 3.0;
        instance.setRectangleHeight(height);
        result = instance.getRectangleHeight();
        assertEquals(height, result, 0);
        
        //Test setting a negative as y's value
        height = -3.0;
        instance.setRectangleHeight(height);
        result = instance.getRectangleHeight();
        assertEquals(height, result, 0);
    }

    /**
     * Test of setRectangleHeight method, of class Rectangle.
     */
    @Test
    public void testSetRectangleHeight() {
        System.out.println("setRectangleHeight");
        
        //Test with positive height's value
        double height = 3.0;
        instance.setRectangleHeight(height);
        assertEquals(height, instance.getRectangleHeight(), 0);
        
        //Test with 0.0 as height's value
        height = 0.0;
        instance.setRectangleHeight(height);
        assertEquals(height, instance.getRectangleHeight(), 0);
        
        //Test with negative height's value
        height = -3.0;
        instance.setRectangleHeight(height);
        assertEquals(height, instance.getRectangleHeight(), 0);
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
    
    /**
     * Test of toString method, of class Rectangle.
     */
    @Test
    public void testToString(){
        System.out.println("toString");
        
        instance.setRectangleX(2.0);
        instance.setRectangleY(4.2);
        instance.setRectangleWidth(4.7);
        instance.setRectangleHeight(3.3);
        instance.setOutlineColor(Color.BLUE);
        instance.setFillColor(Color.GREEN);
        instance.setScaleX(-1.0);
        instance.setScaleY(1.0);
        instance.setRotate(-33.0);
        String expRes = "rectangle;2.0;4.2;4.7;3.3;0x0000ffff;0x008000ff;-1.0;1.0;-33.0";
        String result = instance.toString();
        assertEquals(expRes, result);
    }
}
