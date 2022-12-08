package command;

import gui.DrawingPane;
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
 
        DrawingPane drawingPane = new DrawingPane();
        File file = new File("load_command_test.txt");

        FileWriter writer = new FileWriter("load_command_test.txt");
        
        Double expLineStartX = 0.0;
        Double expLineStartY = 0.0;
        Double expLineEndX = 10.0;
        Double expLineEndY = 10.0;
        String expLineColorStr = "#ffffff";
        Paint expLineColor = Paint.valueOf(expLineColorStr);
        double expLineScaleX = -1.0;
        double expLineScaleY = 1.0;
        double expLineRotate = -55.0;

        writer.write("line;" + expLineStartX + ";" + expLineStartY + ";" + expLineEndX + ";" + expLineEndY + ";" + expLineColorStr + ";" + expLineScaleX + ";" + expLineScaleY + ";" + expLineRotate + "\n");

        
        
        Double expRectX = 1.1;
        Double expRectY = 2.2;
        Double expRectWidth = 30.0;
        Double expRectHeight = 50.5;
        String expRectFillStr = "#00FF00";
        Paint expRectFill = Paint.valueOf(expRectFillStr);
        String expRectOuterStr = "#0000FF";
        Paint expRectOuter = Paint.valueOf(expRectOuterStr);
        double expRectScaleX = 1.0;
        double expRectScaleY = -1.0;
        double expRectRotate = 25.0;
        
        writer.write("rectangle;" + expRectX + ";" + expRectY + ";" + expRectWidth + ";" + expRectHeight + ";" + expRectOuterStr + ";" + expRectFillStr + ";" + expRectScaleX + ";" + expRectScaleY + ";" + expRectRotate + "\n");

        
        
        Double expEllHPos = 10.0;
        Double expEllVPos = 11.5;
        Double expEllWidth = 70.3;
        Double expEllHeight = 35.4;
        String expEllFillStr = "#FFFF00";
        Paint expEllFill = Paint.valueOf(expEllFillStr);
        String expEllOutStr = "#FF0000";
        Paint expEllOut = Paint.valueOf(expEllOutStr);
        double expEllScaleX = -1.0;
        double expEllScaleY = -1.0;
        double expEllRotate = 0.0;

        writer.write("ellipse;" + expEllHPos + ";" + expEllVPos + ";" + expEllWidth + ";" + expEllHeight + ";" + expEllOutStr + ";" + expEllFillStr + ";" + expEllScaleX + ";" + expEllScaleY + ";" + expEllRotate + "\n");

        
        
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
        double resLineScaleX = resLine.getScaleX();
        double resLineScaleY = resLine.getScaleY();
        double resLineRotate = resLine.getRotate();

        
        System.out.println("line test");

        assertEquals(expLineStartX, resLineStartX, 0);
        assertEquals(expLineStartY, resLineStartY, 0);
        assertEquals(expLineEndX, resLineEndX, 0);
        assertEquals(expLineEndY, resLineEndY, 0);
        assertEquals(expLineColor, resLineColor);
        assertEquals(expLineScaleX, resLineScaleX, 0);
        assertEquals(expLineScaleY, resLineScaleY, 0);
        assertEquals(expLineRotate, resLineRotate, 0);

        
        
        Rectangle resRect = (Rectangle) shapeList.get(1);

        Double resRectX = resRect.getRectangleX();
        Double resRectY = resRect.getRectangleY();
        Double resRectWidth = resRect.getRectangleWidth();
        Double resRectHeight = resRect.getRectangleHeight();
        Paint resRectFill = resRect.getFillColor();
        Paint resRectOuter = resRect.getOutlineColor();
        double resRectScaleX = resRect.getScaleX();
        double resRectScaleY = resRect.getScaleY();
        double resRectRotate = resRect.getRotate();

        
        System.out.println("rectangle test");

        assertEquals(expRectX, resRectX, 0);
        assertEquals(expRectY, resRectY, 0);
        assertEquals(expRectWidth, resRectWidth, 0);
        assertEquals(expRectHeight, resRectHeight, 0);
        assertEquals(expRectFill, resRectFill);
        assertEquals(expRectOuter, resRectOuter);
        assertEquals(expRectScaleX, resRectScaleX, 0);
        assertEquals(expRectScaleY, resRectScaleY, 0);
        assertEquals(expRectRotate, resRectRotate, 0);

        
        
        Ellipse resEll = (Ellipse) shapeList.get(2);

        Double resEllHPos = resEll.getEllipseCenterX();
        Double resEllVPos = resEll.getEllipseCenterY();
        Double resEllWidth = resEll.getEllipseRadiusX();
        Double resEllHeight = resEll.getEllipseRadiusY();
        Paint resEllFill = resEll.getFillColor();
        Paint resEllOut = resEll.getOutlineColor();
        double resEllScaleX = resEll.getScaleX();
        double resEllScaleY = resEll.getScaleY();
        double resEllRotate = resEll.getRotate();

        
        System.out.println("ellipse test");

        assertEquals(expEllHPos, resEllHPos, 0);
        assertEquals(expEllVPos, resEllVPos, 0);
        assertEquals(expEllWidth, resEllWidth, 0);
        assertEquals(expEllHeight, resEllHeight, 0);
        assertEquals(expEllFill, resEllFill);
        assertEquals(expEllOut, resEllOut);
        assertEquals(expEllScaleX, resEllScaleX, 0);
        assertEquals(expEllScaleY, resEllScaleY, 0);
        assertEquals(expEllRotate, resEllRotate, 0);

    }

}
