package command;

import gui.DrawingPane;
import javafx.scene.shape.Shape;
import org.junit.*;
import static org.junit.Assert.*;
import shape.Ellipse;
import shape.Line;
import shape.Polygon;
import shape.Rectangle;

/**
 * Test class of the AddShapeCommand.
 *
 * @author group7
 */
public class AddShapeCommandTest {

    private DrawingPane drawingPane;
    private Shape creatingShape;

    /**
     * Setup method, it is called before each test.
     */
    @Before
    public void setUp() {
        // creating a new DrawingPane using an empty constructor
        drawingPane = new DrawingPane();
        // creating shapes to insert into the drawing for tests
        Shape shape1 = new Line();
        Shape shape2 = new Line();
        Shape shape3 = new Rectangle();
        Shape shape4 = new Rectangle();
        Shape shape5 = new Ellipse();
        Shape shape6 = new Ellipse();

        // populating the drawing pane for testing
        drawingPane.getChildren().addAll(shape1, shape2, shape3, shape4, shape5, shape6);
    }

    /**
     * Test of execute method, of class AddShapeCommand.
     */
    @Test
    public void testExecute() throws Exception {
        System.out.println("Testing execute method of AddShapeCommand class.");

        AddShapeCommand instance;
        // testing for the insertion of a Line Shape
        creatingShape = new Line();
        instance = new AddShapeCommand(drawingPane, creatingShape);
        instance.execute();
        // if the line is not contained in the drawing, the test fails
        assertTrue(drawingPane.getChildren().contains(creatingShape));
        // the position of the added line should be the last in the children array, if not the test fails
        assertEquals(drawingPane.getChildren().indexOf(creatingShape), drawingPane.getChildren().size() - 1);

        // testing for the insertion of a Rectangle Shape
        creatingShape = new Rectangle();
        instance = new AddShapeCommand(drawingPane, creatingShape);
        instance.execute();
        // if the rectangle is not contained in the drawing, the test fails
        assertTrue(drawingPane.getChildren().contains(creatingShape));
        // the position of the added rectangle should be the last in the children array, if not the test fails
        assertEquals(drawingPane.getChildren().indexOf(creatingShape), drawingPane.getChildren().size() - 1);

        // testing for the insertion of a Ellipse Shape
        creatingShape = new Ellipse();
        instance = new AddShapeCommand(drawingPane, creatingShape);
        instance.execute();
        // if the ellipse is not contained in the drawing, the test fails
        assertTrue(drawingPane.getChildren().contains(creatingShape));
        // the position of the added ellipse should be the last in the children array, if not the test fails
        assertEquals(drawingPane.getChildren().indexOf(creatingShape), drawingPane.getChildren().size() - 1);
        
        // testing for the insertion of a Polygon Shape
        creatingShape = new Polygon();
        instance = new AddShapeCommand(drawingPane, creatingShape);
        instance.execute();
        // if the ellipse is not contained in the drawing, the test fails
        assertTrue(drawingPane.getChildren().contains(creatingShape));
        // the position of the added ellipse should be the last in the children array, if not the test fails
        assertEquals(drawingPane.getChildren().indexOf(creatingShape), drawingPane.getChildren().size() - 1);
        
    }

    /**
     * Test of undo method, of class AddShapeCommand.
     */
    @Test
    public void testUndo() throws Exception {
        System.out.println("Testing undo method of AddShapeCommand class.");

        AddShapeCommand instance;
        int size;

        // testing undo for the removal of a Line Shape
        creatingShape = new Line();
        instance = new AddShapeCommand(drawingPane, creatingShape);
        // executes the command, it adds the linee to the drawing
        instance.execute();
        // actual size of the children array
        size = drawingPane.getChildren().size();
        // undo the operation by removing the inserted line from the drawing
        instance.undo();

        assertFalse(drawingPane.getChildren().contains(creatingShape));
        assertEquals(size - 1, drawingPane.getChildren().size());

        // testing undo for the removal of a Rectangle Shape
        creatingShape = new Rectangle();
        instance = new AddShapeCommand(drawingPane, creatingShape);
        // executes the command, it adds the rectangle to the drawing
        instance.execute();
        // actual size of the children array
        size = drawingPane.getChildren().size();
        // undo the operation by removing the inserted rectangle from the drawing
        instance.undo();

        assertFalse(drawingPane.getChildren().contains(creatingShape));
        assertEquals(size - 1, drawingPane.getChildren().size());

        // testing undo for the removal of a Ellipse Shape
        creatingShape = new Ellipse();
        instance = new AddShapeCommand(drawingPane, creatingShape);
        // executes the command, it adds the ellipse to the drawing
        instance.execute();
        // actual size of the children array
        size = drawingPane.getChildren().size();
        // undo the operation by removing the inserted ellipse from the drawing
        instance.undo();

        assertFalse(drawingPane.getChildren().contains(creatingShape));
        assertEquals(size - 1, drawingPane.getChildren().size());
        
        // testing undo for the removal of a Polygon Shape
        creatingShape = new Polygon();
        instance = new AddShapeCommand(drawingPane, creatingShape);
        // executes the command, it adds the ellipse to the drawing
        instance.execute();
        // actual size of the children array
        size = drawingPane.getChildren().size();
        // undo the operation by removing the inserted ellipse from the drawing
        instance.undo();

        assertFalse(drawingPane.getChildren().contains(creatingShape));
        assertEquals(size - 1, drawingPane.getChildren().size());

    }

}
