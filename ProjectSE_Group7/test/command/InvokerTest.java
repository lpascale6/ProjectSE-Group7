/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package command;

import gui.DrawingPane;
import java.util.Stack;
import javafx.scene.shape.Shape;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shape.Ellipse;
import shape.Line;
import shape.Rectangle;

/**
 *
 * @author luigi
 */
public class InvokerTest {
    Invoker invoker;
    Shape shape1;
    Shape shape2;
    Shape shape3;
    DrawingPane drawingPane;
    //commands to pass to the invoker
    Command command1;
    Command command2;
    Command command3;
    //expected stack
    Stack<Command> expectedCommands;
            
    public InvokerTest() {
    }
    
    @Before
    public void setUp() {
         invoker = Invoker.getInstance();
         //variables to pass to the commands
        shape1 = new Rectangle();
        shape2 = new Line();
        shape3 = new Ellipse();
        drawingPane = new DrawingPane();
        //commands to pass to the invoker
        command1 = new AddShapeCommand(drawingPane, shape1);
        command2 = new AddShapeCommand(drawingPane, shape2);
        command3 = new AddShapeCommand(drawingPane, shape3);
        //expected stack
        expectedCommands = new Stack<Command>();
    }

    /**
     * Test of execute method, of class Invoker.
     */
    @Test
    public void testExecute() throws Exception {
        //the expected commands stack construction
        expectedCommands.push(command1);
        //invoker execute
        invoker.execute(command1);
        //control if the expected stack matches the actual stack
        assertEquals(expectedCommands.size(), invoker.getStack().size());
        for(int i=0; i<invoker.getStack().size(); i++){
            assertEquals(expectedCommands.get(i),invoker.getStack().get(i));
        }
    }

    /**
     * Test of undo method, of class Invoker.
     */
    @Test
    public void testUndo() throws Exception {
        //the expected commands stack construction
        expectedCommands.push(command1);
        expectedCommands.push(command2);
        expectedCommands.pop();
        expectedCommands.push(command3);
        //invoker execute and undo
        invoker.execute(command1);
        invoker.execute(command2);
        invoker.undo();
        invoker.execute(command3);
        //control if the expected stack matches the actual stack
        assertEquals(expectedCommands.size(), invoker.getStack().size());
        for(int i=0; i<invoker.getStack().size(); i++){
            assertEquals(expectedCommands.get(i),invoker.getStack().get(i));
        }
    }

    /**
     * Test of clearStack method, of class Invoker.
     */
    @Test
    public void testClearStack() throws Exception {
        //invoker execute to fill the stack
        invoker.execute(command1);
        invoker.execute(command2);
        invoker.undo();
        invoker.execute(command3);
        //invoker clear
        invoker.clearStack();
        //control if the expected stack matches the actual stack
        assertEquals(0, invoker.getStack().size());
    }
    
}
