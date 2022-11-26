package command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
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
public class SaveCommandTest {
    
    private SaveCommand instance;
    
    public SaveCommandTest() {
    }
    
    /**
     * Set up of the file and shapeList
     * @throws IOException 
     */
    @Before
    public void setUp() throws IOException {
        
        //Creating a file "test.txt"
        File file = new File("test.txt");
        file.createNewFile();
        
        ArrayList<Shape> shapeList = new ArrayList();
        
        Line line = new Line(1.2, 3.4, 5.0, 7.3, Color.BLUE);
        Rectangle rectangle = new Rectangle(3.6, 2.5, 11.2, 9.7, Color.GREEN, Color.YELLOW);
        Ellipse ellipse = new Ellipse(2.4, 7.5, 4.2,5.0, Color.BLACK, Color.WHITE);
        
        //Insert some figures in the Array list
        shapeList.add(line);
        shapeList.add(rectangle);
        shapeList.add(ellipse);
        
        instance = new SaveCommand(file, shapeList);
    }


    /**
     * Test of execute method, of class SaveCommand.
     */
    @Test
    public void testExecute() throws Exception {
        System.out.println("execute");
        instance.execute();
        
        String result = "";
        BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
        
        //Reading di fileb test.txt
        String line = reader.readLine();
        while(line != null){
            result += line + "\n";
            line = reader.readLine();
        }
        
        
        String expRes = "line;1.2;3.4;5.0;7.3;#0000ff\n"
                + "rectangle;3.6;2.5;11.2;9.7;#ffff00;#008000\n"
                + "ellipse;2.4;7.5;4.2;5.0;#000000;#ffffff\n";
        
        assertEquals(expRes, result);
        
        reader.close();
        
    }
    
}
