package command;

import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import shape.Ellipse;
import shape.Rectangle;

/**
 *
 * @author group7
 */
public class ChangeFillColorCommandTest {
    
    private ChangeFillColorCommand instanceRectangle;
    private ChangeFillColorCommand instanceEllipse;
    
    private Rectangle rectangle;
    private Ellipse ellipse;
    
    private Color newColor;
    private Color previousColor;
    
    public ChangeFillColorCommandTest() {
    }
    
    
    @Before
    public void setUp() {
        //Setting two private attributes for previous fill color and new fill color
        this.previousColor = Color.BLUE;
        this.newColor = Color.GREEN;
        
        //Creating an empty rectangle and ellipse
        Rectangle rectangle = new Rectangle();
        Ellipse ellipse = new Ellipse();

        //Setting the fill color of the shapes
        rectangle.setFillColor(this.previousColor);
        ellipse.setFillColor(this.previousColor);
        
        //Creating a ChangeFillColorCommand with the new fill color for each shape
        this.instanceRectangle = new ChangeFillColorCommand(rectangle, this.newColor);
        this.instanceEllipse = new ChangeFillColorCommand(ellipse, this.newColor);
        
        this.rectangle = rectangle;
        this.ellipse = ellipse;
    }

    /**
     * Test of execute method, of class ChangeFillColorCommand.
     */
    @Test
    public void testExecute() throws Exception {
        System.out.println("execute");
        
        //The expected result is the new fill color of the shapes
        Color expResult = this.newColor;
        
        //Doing the execute method of the two commands
        this.instanceRectangle.execute();
        this.instanceEllipse.execute();
        
        //Saving the result in two different Color variables
        Color resultRectangle = (Color) this.rectangle.getFillColor();
        Color resultEllipse = (Color) this.ellipse.getFillColor();
        
        assertEquals(expResult, resultRectangle);
        assertEquals(expResult, resultEllipse);
    }

    /**
     * Test of undo method, of class ChangeFillColorCommand.
     */
    @Test
    public void testUndo() throws Exception {
        System.out.println("undo");
        
        //Doing the execute method of the two commands
        this.instanceRectangle.execute();
        this.instanceEllipse.execute();
        
        //The expected result is the old fill color of the shapes
        Color expResult = this.previousColor;
        
        //Doing the undo method of the two commands
        this.instanceRectangle.undo();
        this.instanceEllipse.undo();
        
        //Saving the result in two different Color variables
        Color resultRectangle = (Color) this.rectangle.getFillColor();
        Color resultEllipse = (Color) this.ellipse.getFillColor();
        
        assertEquals(expResult, resultRectangle);
        assertEquals(expResult, resultEllipse);
        
    }
    
}
