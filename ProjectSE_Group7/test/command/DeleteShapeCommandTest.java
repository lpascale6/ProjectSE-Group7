package command;

import org.junit.*;
import static org.junit.Assert.*;
import gui.DrawingPane;
import javafx.scene.shape.Shape;
import shape.*;


/**
 * Test class of the DeleteShapeCommand.
 * @author group7
 */
public class DeleteShapeCommandTest {
    
    private DrawingPane drawingPane;
    private Shape shapeToDelete;
    
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
     * Test of execute method, of class DeleteShapeCommand.
     */
    @Test
    public void testExecute() throws Exception {
        System.out.println("Testing execute method of DeleteShapeCommand class.");
        
        DeleteShapeCommand instance;
        
        // testing for the removal of a Shape 
        shapeToDelete = new Line();
        int size;
        
        // adding the shape at the start of the children array
        drawingPane.getChildren().add(0, shapeToDelete);
        instance = new DeleteShapeCommand(shapeToDelete, drawingPane);
        size = drawingPane.getChildren().size();
        instance.execute();
        assertFalse(drawingPane.getChildren().contains(shapeToDelete));
        assertEquals(size - 1, drawingPane.getChildren().size());
        
        // adding the shape in the center of the children array
        drawingPane.getChildren().add(drawingPane.getChildren().size() / 2, shapeToDelete);
        instance = new DeleteShapeCommand(shapeToDelete, drawingPane);
        size = drawingPane.getChildren().size();
        instance.execute();
        assertFalse(drawingPane.getChildren().contains(shapeToDelete));
        assertEquals(size - 1, drawingPane.getChildren().size());
        
        // adding the shape at the end of the children array
        drawingPane.getChildren().add(drawingPane.getChildren().size(), shapeToDelete);
        instance = new DeleteShapeCommand(shapeToDelete, drawingPane);
        size = drawingPane.getChildren().size();
        instance.execute();
        assertFalse(drawingPane.getChildren().contains(shapeToDelete));
        assertEquals(size - 1, drawingPane.getChildren().size());
        
    }

    /**
     * Test of undo method, of class DeleteShapeCommand.
     */
    @Test
    public void testUndo() throws Exception {
        System.out.println("Testing undo method of DeleteShapeCommand class.");
        
        DeleteShapeCommand instance;
        
        shapeToDelete = new Line();
        int size;
        int index;
        
        // adding the shape at the start of the children array, after the undo 
        // the shape should be present in the array and also be in the position 
        // it was before the deletion
        drawingPane.getChildren().add(0, shapeToDelete);
        instance = new DeleteShapeCommand(shapeToDelete, drawingPane);
        size = drawingPane.getChildren().size();
        index = drawingPane.getChildren().indexOf(shapeToDelete);
        instance.execute();
        instance.undo();
        assertTrue(drawingPane.getChildren().contains(shapeToDelete));
        assertEquals(size, drawingPane.getChildren().size());
        assertEquals(index, drawingPane.getChildren().indexOf(shapeToDelete));
        
        // adding the shape in the center of the children array, after the undo 
        // the shape should be present in the array and also be in the position 
        // it was before the deletion
        shapeToDelete = new Rectangle();
        
        drawingPane.getChildren().add(drawingPane.getChildren().size() / 2, shapeToDelete);
        instance = new DeleteShapeCommand(shapeToDelete, drawingPane);
        size = drawingPane.getChildren().size();
        index = drawingPane.getChildren().indexOf(shapeToDelete);
        instance.execute();
        instance.undo();
        assertTrue(drawingPane.getChildren().contains(shapeToDelete));
        assertEquals(size, drawingPane.getChildren().size());
        assertEquals(index, drawingPane.getChildren().indexOf(shapeToDelete));
        
        // adding the shape at the end of the children array, after the undo 
        // the shape should be present in the array and also be in the position 
        // it was before the deletion
        shapeToDelete = new Ellipse();
        drawingPane.getChildren().add(drawingPane.getChildren().size(), shapeToDelete);
        instance = new DeleteShapeCommand(shapeToDelete, drawingPane);
        size = drawingPane.getChildren().size();
        index = drawingPane.getChildren().indexOf(shapeToDelete);
        instance.execute();
        instance.undo();
        assertTrue(drawingPane.getChildren().contains(shapeToDelete));
        assertEquals(size, drawingPane.getChildren().size());
        assertEquals(index, drawingPane.getChildren().indexOf(shapeToDelete));
        
    }
    
}
