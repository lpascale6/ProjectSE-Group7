package command;

import gui.DrawingPane;
import javafx.scene.shape.Shape;
import org.junit.*;
import static org.junit.Assert.*;
import shape.Ellipse;
import shape.Line;
import shape.Rectangle;

/**
 * Test class of the ToTheBackCommand.
 *
 * @author group7
 */
public class ToTheBackCommandTest {

    Shape shape;
    DrawingPane drawingPane;

    @Before
    public void setUp() {
        // creating a new DrawingPane using an empty constructor
        drawingPane = new DrawingPane();
        // creating shapes to insert into the drawing for tests
        Shape shape1 = new Line();
        Shape shape2 = new Line();

        // initializing the shape to be brought back
        shape = new Rectangle();

        Shape shape3 = new Ellipse();
        Shape shape4 = new Ellipse();

        // populating the drawing pane for testing, shape that should change 
        // its level is placed at the center of the children array,
        // its position is 2
        drawingPane.getChildren().addAll(shape1, shape2, shape, shape3, shape4);
    }

    /**
     * Test of execute method, of class ToTheBackCommand.
     */
    @Test
    public void testExecute() throws Exception {
        System.out.println("Testing execute method of ToTheBackCommand class.");

        ToTheBackCommand instance;
        int originalPosition;

        // testing to the back command, the shape after the execute method 
        // should be in position 0
        instance = new ToTheBackCommand(shape, drawingPane);
        originalPosition = drawingPane.getChildren().indexOf(shape);
        instance.execute();

        assertEquals(0, drawingPane.getChildren().indexOf(shape));
        assertNotEquals(originalPosition, drawingPane.getChildren().indexOf(shape));
    }

    /**
     * Test of undo method, of class ToTheBackCommand.
     */
    @Test
    public void testUndo() throws Exception {
        System.out.println("Testing undo method of ToTheBackCommand class.");

        ToTheBackCommand instance;
        int originalPosition;

        // testing the undo method, after the execution the shape should be in 
        // the original position (i.e. the position it was before the first execution
        // of the execute method)
        instance = new ToTheBackCommand(shape, drawingPane);
        originalPosition = drawingPane.getChildren().indexOf(shape);

        instance.execute();
        instance.undo();

        assertEquals(originalPosition, drawingPane.getChildren().indexOf(shape));

    }

}
