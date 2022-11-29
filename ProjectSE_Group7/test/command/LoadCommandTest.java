package command;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

import org.junit.*;
import static org.junit.Assert.*;
import shape.Ellipse;
import shape.Line;
import shape.Rectangle;

/**
 *
 * @author group7
 */
public class LoadCommandTest {

    public LoadCommandTest() {
    }

    @Before
    public void setUpClass() {
    }

    /**
     * Test of execute method, of class LoadCommand.
     */
    @Test
    public void testExecute() throws Exception {

        Pane drawingPane = new Pane();
        File file = new File("load_command_test.txt");

        FileWriter writer = new FileWriter("load_command_test.txt");

        
        Double expLineStartX = 0.0;
        Double expLineStartY = 0.0;
        Double expLineEndX = 10.0;
        Double expLineEndY = 10.0;
        String expLineColorStr = "#ffffff";
        Paint expLineColor = Paint.valueOf(expLineColorStr);

        writer.write("line;" + expLineStartX + ";" + expLineStartY + ";" + expLineEndX + ";" + expLineEndY + ";" + expLineColorStr + "\n");

        
        
        Double expRectX = 1.1;
        Double expRectY = 2.2;
        Double expRectWidth = 30.0;
        Double expRectHeight = 50.5;
        String expRectFillStr = "#00FF00";
        Paint expRectFill = Paint.valueOf(expRectFillStr);
        String expRectOuterStr = "#0000FF";
        Paint expRectOuter = Paint.valueOf(expRectOuterStr);

        writer.write("rectangle;" + expRectX + ";" + expRectY + ";" + expRectWidth + ";" + expRectHeight + ";" + expRectOuterStr + ";" + expRectFillStr + "\n");

        
        
        Double expEllHPos = 10.0;
        Double expEllVPos = 11.5;
        Double expEllWidth = 70.3;
        Double expEllHeight = 35.4;
        String expEllFillStr = "#FFFF00";
        Paint expEllFill = Paint.valueOf(expEllFillStr);
        String expEllOutStr = "#FF0000";
        Paint expEllOut = Paint.valueOf(expEllOutStr);

        writer.write("ellipse;" + expEllHPos + ";" + expEllVPos + ";" + expEllWidth + ";" + expEllHeight + ";" + expEllOutStr + ";" + expEllFillStr + "\n");

        
        
        writer.close();

        System.out.println("execute line rectangle and ellipse");

        LoadCommand instance = new LoadCommand(file, drawingPane);
        instance.execute();

        ArrayList<Object> shapeList = new ArrayList<Object>(instance.drawingPane.getChildren());

        
        
        Line resLine = (Line) shapeList.get(0);

        Double resLineStartX = resLine.getLineStartingX();
        Double resLineStartY = resLine.getLineStartingY();
        Double resLineEndX = resLine.getLineEndingX();
        Double resLineEndY = resLine.getLineEndingY();
        Paint resLineColor = resLine.getLineColor();

        
        System.out.println("line test");

        assertEquals(expLineStartX, resLineStartX, 0);
        assertEquals(expLineStartY, resLineStartY, 0);
        assertEquals(expLineEndX, resLineEndX, 0);
        assertEquals(expLineEndY, resLineEndY, 0);
        assertEquals(expLineColor, resLineColor);

        
        
        Rectangle resRect = (Rectangle) shapeList.get(1);

        Double resRectX = resRect.getRectangleX();
        Double resRectY = resRect.getRectangleY();
        Double resRectWidth = resRect.getRectangleWidth();
        Double resRectHeight = resRect.getRectangleHeight();
        Paint resRectFill = resRect.getFillColor();
        Paint resRectOuter = resRect.getOutlineColor();

        
        System.out.println("rectangle test");

        assertEquals(expRectX, resRectX, 0);
        assertEquals(expRectY, resRectY, 0);
        assertEquals(expRectWidth, resRectWidth, 0);
        assertEquals(expRectHeight, resRectHeight, 0);
        assertEquals(expRectFill, resRectFill);
        assertEquals(expRectOuter, resRectOuter);

        
        
        Ellipse resEll = (Ellipse) shapeList.get(2);

        Double resEllHPos = resEll.getEllipseCenterX();
        Double resEllVPos = resEll.getEllipseCenterY();
        Double resEllWidth = resEll.getEllipseRadiusX();
        Double resEllHeight = resEll.getEllipseRadiusY();
        Paint resEllFill = resEll.getFillColor();
        Paint resEllOut = resEll.getOutlineColor();

        
        System.out.println("ellipse test");

        assertEquals(expEllHPos, resEllHPos, 0);
        assertEquals(expEllVPos, resEllVPos, 0);
        assertEquals(expEllWidth, resEllWidth, 0);
        assertEquals(expEllHeight, resEllHeight, 0);
        assertEquals(expEllFill, resEllFill);
        assertEquals(expEllOut, resEllOut);

    }

}
