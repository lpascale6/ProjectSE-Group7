package command;

import javafx.scene.paint.Color;
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
public class ChangeOutlineColorCommandTest {
    
    private ChangeOutlineColorCommand instanceLine;
    private ChangeOutlineColorCommand instanceRectangle;
    private ChangeOutlineColorCommand instanceEllipse;
    
    private Line line;
    private Rectangle rectangle;
    private Ellipse ellipse;
    
    private Color newColor;
    private Color previousColor;
    
    public ChangeOutlineColorCommandTest() {
    }
    
    
    @Before
    public void setUp() {
        //Setting two private attributes for previous outlin color and new outline color
        this.previousColor = Color.BLUE;
        this.newColor = Color.GREEN;
        
        //Creating an empty line, rectangle and ellipse
        Line line = new Line();
        Rectangle rectangle = new Rectangle();
        Ellipse ellipse = new Ellipse();

        //Setting the outline color of the shapes
        line.setLineColor(this.previousColor);
        rectangle.setOutlineColor(this.previousColor);
        ellipse.setOutlineColor(this.previousColor);
        
        //Creating a ChangeOutlineColorCommand with the new outline color for each shape
        this.instanceLine = new ChangeOutlineColorCommand(line, this.newColor);
        this.instanceRectangle = new ChangeOutlineColorCommand(rectangle, this.newColor);
        this.instanceEllipse = new ChangeOutlineColorCommand(ellipse, this.newColor);
        
        this.line = line;
        this.rectangle = rectangle;
        this.ellipse = ellipse;
    }


    /**
     * Test of execute method, of class ChangeOutlineColorCommand.
     */
    @Test
    public void testExecute() throws Exception {
        System.out.println("execute");
        
        //The expected result is the new outline color of the shapes
        Color expResult = this.newColor;
        
        //Doing the execute method of the three commands
        this.instanceLine.execute();
        this.instanceRectangle.execute();
        this.instanceEllipse.execute();
        
        //Saving the result in three different Color variables
        Color resultLine = (Color) this.line.getLineColor();
        Color resultRectangle = (Color) this.rectangle.getOutlineColor();
        Color resultEllipse = (Color) this.ellipse.getOutlineColor();
        
        assertEquals(expResult, resultLine);
        assertEquals(expResult, resultRectangle);
        assertEquals(expResult, resultEllipse);
    }

    /**
     * Test of undo method, of class ChangeOutlineColorCommand.
     */
    @Test
    public void testUndo() throws Exception {
        System.out.println("undo");
        
        //Doing the execute method of the three commands
        this.instanceLine.execute();
        this.instanceRectangle.execute();
        this.instanceEllipse.execute();
        
        //The expected result is the old outline color of the shapes
        Color expResult = this.previousColor;
        
        //Doing the undo method of the three commands
        this.instanceLine.undo();
        this.instanceRectangle.undo();
        this.instanceEllipse.undo();
        
        //Saving the result in three different Color variables
        Color resultLine = (Color) this.line.getLineColor();
        Color resultRectangle = (Color) this.rectangle.getOutlineColor();
        Color resultEllipse = (Color) this.ellipse.getOutlineColor();
        
        assertEquals(expResult, resultLine);
        assertEquals(expResult, resultRectangle);
        assertEquals(expResult, resultEllipse);
    }
    
}
