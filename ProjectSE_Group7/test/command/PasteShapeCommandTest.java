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

public class PasteShapeCommandTest {
    
    public PasteShapeCommandTest() {
    }
    
    @Before
    public void setUpClass() {
    }

    /**
     * Test of execute method, of class PasteShapeCommand.
     */
    @Test
    public void testExecute() throws Exception {
        DrawingPane drawingPane = new DrawingPane();
        Double expLineStartX = 10.0;
        Double expLineStartY = 11.1;
        Double expLineEndX = 30.1;
        Double expLineEndY = 33.4;
        Paint expLineColor = Paint.valueOf("#fff000");
        Line expLine = new Line(expLineStartX, expLineStartY, expLineEndX, expLineEndY, expLineColor);
        drawingPane.getChildren().add(expLine);
        
        Double expRectX = 30.9;
        Double expRectY = 33.1;
        Double expRectWidth = 40.0;
        Double expRectHeight = 80.5;
        Paint expRectFill = Paint.valueOf("#ff00ff");
        Paint expRectOutline = Paint.valueOf("#f0f0f0");
        Rectangle expRect = new Rectangle(expRectX, expRectY, expRectWidth, expRectHeight, expRectFill, expRectOutline);
        drawingPane.getChildren().add(expRect);
        
        Double expEllHPos = 20.0;
        Double expEllVPos = 23.2;
        Double expEllWidth = 40.0;
        Double expEllHeight = 44.4;
        Paint expEllFill = Paint.valueOf("#110011");
        Paint expEllOut = Paint.valueOf("#222200");
        Ellipse expEll = new Ellipse(expEllHPos, expEllVPos, expEllWidth, expEllHeight, expEllOut, expEllFill);
        drawingPane.getChildren().add(expEll);
        
        PasteShapeCommand instance = new PasteShapeCommand(drawingPane);
        
        System.out.println("execute paste line");
        drawingPane.setCopiedShape(expLine);
        instance.execute();
        
        Line pastedLine = (Line) drawingPane.getChildren().get(3);
        Double resLineStartX = pastedLine.getLineStartingX();
        Double resLineStartY = pastedLine.getLineStartingY();
        Double resLineEndX = pastedLine.getLineEndingX();
        Double resLineEndY = pastedLine.getLineEndingY();
        Paint resLineColor = pastedLine.getLineColor();
        
        assertEquals(expLineStartX, resLineStartX, 0);
        assertEquals(expLineStartY, resLineStartY, 0);
        assertEquals(expLineEndX, resLineEndX, 0);
        assertEquals(expLineEndY, resLineEndY, 0);
        assertEquals(expLineColor, resLineColor);
        
        
        System.out.println("execute paste rectangle");
        drawingPane.setCopiedShape(expRect);
        instance.execute();
        
        Rectangle pastedRect = (Rectangle) drawingPane.getChildren().get(4);
        Double resRectX = pastedRect.getRectangleX();
        Double resRectY = pastedRect.getRectangleY();
        Double resRectWidth = pastedRect.getRectangleWidth();
        Double resRectHeight = pastedRect.getRectangleHeight();
        Paint resRectFill = pastedRect.getFillColor();
        Paint resRectOutline = pastedRect.getOutlineColor();
        
        assertEquals(expRectX, resRectX, 0);
        assertEquals(expRectY, resRectY, 0);
        assertEquals(expRectWidth, resRectWidth, 0);
        assertEquals(expRectHeight, resRectHeight, 0);
        assertEquals(expRectFill, resRectFill);
        assertEquals(expRectOutline, resRectOutline);
        
        
        System.out.println("execute paste ellipse");
        drawingPane.setCopiedShape(expEll);
        instance.execute();
        
        Ellipse pastedEll = (Ellipse) drawingPane.getChildren().get(5);
        Double resEllHPos = pastedEll.getEllipseCenterX();
        Double resEllVPos = pastedEll.getEllipseCenterY();
        Double resEllWidth = pastedEll.getEllipseRadiusX();
        Double resEllHeight = pastedEll.getEllipseRadiusY();
        Paint resEllFill = pastedEll.getFillColor();
        Paint resEllOut = pastedEll.getOutlineColor();
        
        assertEquals(expEllHPos, resEllHPos, 0);
        assertEquals(expEllVPos, resEllVPos, 0);
        assertEquals(expEllWidth, resEllWidth, 0);
        assertEquals(expEllHeight, resEllHeight, 0);
        assertEquals(expEllFill, resEllFill);
        assertEquals(expEllOut, resEllOut);
        
        
    }

    /**
     * Test of undo method, of class PasteShapeCommand.
     */
    @Test
    public void testUndo() throws Exception {
        
        Line line = new Line();
        
        System.out.println("undo");
        DrawingPane drawingPane = new DrawingPane();
        drawingPane.setCopiedShape(line);
        PasteShapeCommand instance = new PasteShapeCommand(drawingPane);
        instance.execute();
        
        instance.undo();
        assertEquals(drawingPane.getChildren().isEmpty(), true);
        
    }
    
}
