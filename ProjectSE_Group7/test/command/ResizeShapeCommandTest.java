package command;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import shape.Border;
import shape.Ellipse;
import shape.Line;
import shape.Rectangle;

/**
 *
 * @author paolo
 */
public class ResizeShapeCommandTest {
    
    //Variables for shapes to resize
    private Line line;
    private Rectangle rectangle;
    private Ellipse ellipse;
    
    //Variables for borders of the shapes
    private Border lineBorder;
    private Border rectangleBorder;
    private Border ellipseBorder;
    
    //Variables for resize commands
    private ResizeShapeCommand instanceLine;
    private ResizeShapeCommand instanceRectangle;
    private ResizeShapeCommand instanceEllipse;
    
    //Variables for initial Line size
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    
    //Variables for initial Rectangle, Ellipse and Border size
    private double x;
    private double y;
    private double width;
    private double height;
    
    public ResizeShapeCommandTest() {
    }
    
    @Before
    public void setUp() {
        this.x = this.startX = 2.0;
        this.y = this.startY =  4.0;
        this.width = this.endX =  14;
        this.height = this.endY = 16;
        
        Line line = new Line(this.startX, this.startY, this.endX, this.endY);
        Rectangle rectangle = new Rectangle(this.x, this.y, this.width, this.height);
        Ellipse ellipse = new Ellipse(this.x, this.y, this.width, this.height);
        
        Border lineBorder = new Border(this.startX - 3, this.startY - 3, this.endX, this.endY);
        Border rectangleBorder = new Border(this.x - 3, this.y - 3, this.width + 5, this.height + 6);
        Border ellipseBorder = new Border(this.x - 3, this.y - 3, this.width + 5, this.height + 6);
        
        instanceLine = new ResizeShapeCommand(line, lineBorder);
        instanceRectangle = new ResizeShapeCommand(rectangle, rectangleBorder);
        instanceEllipse = new ResizeShapeCommand(ellipse, ellipseBorder);
        
        this.line = line;
        this.rectangle = rectangle;
        this.ellipse = ellipse;
        
        this.lineBorder = lineBorder;
        this.rectangleBorder = rectangleBorder;
        this.ellipseBorder = ellipseBorder;
    }
    

    /**
     * Test of execute method, of class ResizeShapeCommand.
     */
    @Test
    public void testExecute() throws Exception {
        System.out.println("execute");
        
        //Execute for line
        // starting coordinate is the topleft corner of the border
        instanceLine.execute();
        double expResultStartX = this.lineBorder.getRectangleX();
        double expResultStartY = this.lineBorder.getRectangleY();
        double expResultEndX = this.lineBorder.getRectangleX() + this.lineBorder.getRectangleWidth();
        double expResultEndY = this.lineBorder.getRectangleY() + this.lineBorder.getRectangleHeight();
        
        assertEquals(expResultStartX, this.line.getLineStartingX(), 0);
        assertEquals(expResultStartY, this.line.getLineStartingY(), 0);
        assertEquals(expResultEndX, this.line.getLineEndingX(), 0);
        assertEquals(expResultEndY, this.line.getLineEndingY(), 0);
        
        // starting coordinate is the bottomleft corner of the border
        this.line.setLineStartingX(this.line.getLineEndingX() - 5);
        this.line.setLineStartingY(this.line.getLineEndingY() + 6);
        
        instanceLine.execute();
        
        expResultStartX = this.lineBorder.getRectangleX();
        expResultStartY = this.lineBorder.getRectangleY() + this.lineBorder.getRectangleHeight();
        expResultEndX = this.lineBorder.getRectangleX() + this.lineBorder.getRectangleWidth();
        expResultEndY = this.lineBorder.getRectangleY();
        
        assertEquals(expResultStartX, this.line.getLineStartingX(), 0);
        assertEquals(expResultStartY, this.line.getLineStartingY(), 0);
        assertEquals(expResultEndX, this.line.getLineEndingX(), 0);
        assertEquals(expResultEndY, this.line.getLineEndingY(), 0);
        
        // starting coordinate is the topright corner of the border
        this.line.setLineStartingX(this.line.getLineEndingX() + 5);
        this.line.setLineStartingY(this.line.getLineEndingY() - 6);
        
        instanceLine.execute();
        
        expResultStartX = this.lineBorder.getRectangleX() + this.lineBorder.getRectangleWidth();
        expResultStartY = this.lineBorder.getRectangleY();
        expResultEndX = this.lineBorder.getRectangleX();
        expResultEndY = this.lineBorder.getRectangleY() + this.lineBorder.getRectangleHeight();
        
        assertEquals(expResultStartX, this.line.getLineStartingX(), 0);
        assertEquals(expResultStartY, this.line.getLineStartingY(), 0);
        assertEquals(expResultEndX, this.line.getLineEndingX(), 0);
        assertEquals(expResultEndY, this.line.getLineEndingY(), 0);
        
        // starting coordinate is the bottomright corner of the border
        this.line.setLineStartingX(this.line.getLineEndingX() + 5);
        this.line.setLineStartingY(this.line.getLineEndingY() + 6);
        
        instanceLine.execute();
        
        expResultStartX = this.lineBorder.getRectangleX() + this.lineBorder.getRectangleWidth();
        expResultStartY = this.lineBorder.getRectangleY() + this.lineBorder.getRectangleHeight();
        expResultEndX = this.lineBorder.getRectangleX();
        expResultEndY = this.lineBorder.getRectangleY();
        
        assertEquals(expResultStartX, this.line.getLineStartingX(), 0);
        assertEquals(expResultStartY, this.line.getLineStartingY(), 0);
        assertEquals(expResultEndX, this.line.getLineEndingX(), 0);
        assertEquals(expResultEndY, this.line.getLineEndingY(), 0);
        
        
        
        
        //Execute for the rectangle
        instanceRectangle.execute();
        double expResultX = this.x - 3;
        double expResultY = this.y - 3;
        double expResultWidth = this.width + 5;
        double expResultHeight = this.height + 6;
        
        assertEquals(expResultX, this.rectangle.getRectangleX(), 0);
        assertEquals(expResultY, this.rectangle.getRectangleY(), 0);
        assertEquals(expResultWidth, this.rectangle.getRectangleWidth(), 0);
        assertEquals(expResultHeight, this.rectangle.getRectangleHeight(), 0);
        
        //Test resizing the rectangle to a smaller one
        this.rectangleBorder.setRectangleX(this.x + 3);
        this.rectangleBorder.setRectangleY(this.y + 3);
        this.rectangleBorder.setWidth(this.width - 5);
        this.rectangleBorder.setHeight(this.height - 6);
        
        expResultX = this.rectangleBorder.getRectangleX();
        expResultY = this.rectangleBorder.getRectangleY();
        expResultWidth = this.rectangleBorder.getRectangleWidth();
        expResultHeight = this.rectangleBorder.getRectangleHeight();
        
        instanceRectangle.execute();
        
        assertEquals(expResultX, this.rectangle.getRectangleX(), 0);
        assertEquals(expResultY, this.rectangle.getRectangleY(), 0);
        assertEquals(expResultWidth, this.rectangle.getRectangleWidth(), 0);
        assertEquals(expResultHeight, this.rectangle.getRectangleHeight(), 0);
        

 
        //Execute for the ellipse
        instanceEllipse.execute();
        
        double expResultCenterX = this.ellipseBorder.getRectangleX() + (this.ellipseBorder.getRectangleWidth() / 2);
        double expResultCenterY = this.ellipseBorder.getRectangleY() + (this.ellipseBorder.getRectangleHeight() / 2);
        double expResultRadiusX = (this.ellipseBorder.getRectangleWidth()) / 2;
        double expResultRadiusY = (this.ellipseBorder.getRectangleHeight()) / 2;
        
        assertEquals(expResultCenterX, this.ellipse.getEllipseCenterX(), 0);
        assertEquals(expResultCenterY, this.ellipse.getEllipseCenterY(), 0);
        assertEquals(expResultRadiusX, this.ellipse.getEllipseRadiusX(), 0);
        assertEquals(expResultRadiusY, this.ellipse.getEllipseRadiusY(), 0);
        
        //Test resizing the ellipse to a smaller one
        ellipseBorder.setRectangleX(this.x - 3);
        ellipseBorder.setRectangleY(this.y - 3);
        ellipseBorder.setRectangleWidth(this.width - 5);
        ellipseBorder.setRectangleHeight(this.height - 6);
        
        instanceEllipse.execute();
        
        expResultCenterX = this.ellipseBorder.getRectangleX() + (this.ellipseBorder.getRectangleWidth() / 2);
        expResultCenterY = this.ellipseBorder.getRectangleY() + (this.ellipseBorder.getRectangleHeight() / 2);
        expResultRadiusX = (this.ellipseBorder.getRectangleWidth()) / 2;
        expResultRadiusY = (this.ellipseBorder.getRectangleHeight()) / 2;
        
        assertEquals(expResultCenterX, this.ellipse.getEllipseCenterX(), 0);
        assertEquals(expResultCenterY, this.ellipse.getEllipseCenterY(), 0);
        assertEquals(expResultRadiusX, this.ellipse.getEllipseRadiusX(), 0);
        assertEquals(expResultRadiusY, this.ellipse.getEllipseRadiusY(), 0);
        
        
    }

    /**
     * Test of undo method, of class ResizeShapeCommand.
     */
    @Test
    public void testUndo() throws Exception {
        System.out.println("undo");
        
        //Undo for Line
        // starting coordinate is the topleft corner of the border
        instanceLine.execute();
        instanceLine.undo();
        
        assertEquals(this.startX, this.line.getLineStartingX(), 0);
        assertEquals(this.startY, this.line.getLineStartingY(), 0);
        assertEquals(this.endX, this.line.getLineEndingX(), 0);
        assertEquals(this.endY, this.line.getLineEndingY(), 0);
        
        // starting coordinate is the bottomleft corner of the border
        this.line.setLineStartingX(this.line.getLineEndingX() - 5);
        this.line.setLineStartingY(this.line.getLineEndingY() + 6);
        
        instanceLine.execute();
        instanceLine.undo();
        
        assertEquals(this.startX, this.line.getLineStartingX(), 0);
        assertEquals(this.startY, this.line.getLineStartingY(), 0);
        assertEquals(this.endX, this.line.getLineEndingX(), 0);
        assertEquals(this.endY, this.line.getLineEndingY(), 0);
        
        // starting coordinate is the topright corner of the border
        this.line.setLineStartingX(this.line.getLineEndingX() + 5);
        this.line.setLineStartingY(this.line.getLineEndingY() - 6);
        
        instanceLine.execute();
        instanceLine.undo();
        
        assertEquals(this.startX, this.line.getLineStartingX(), 0);
        assertEquals(this.startY, this.line.getLineStartingY(), 0);
        assertEquals(this.endX, this.line.getLineEndingX(), 0);
        assertEquals(this.endY, this.line.getLineEndingY(), 0);
        
        // starting coordinate is the bottomright corner of the border
        this.line.setLineStartingX(this.line.getLineEndingX() + 5);
        this.line.setLineStartingY(this.line.getLineEndingY() + 6);
        
        instanceLine.execute();
        instanceLine.undo();
        
        assertEquals(this.startX, this.line.getLineStartingX(), 0);
        assertEquals(this.startY, this.line.getLineStartingY(), 0);
        assertEquals(this.endX, this.line.getLineEndingX(), 0);
        assertEquals(this.endY, this.line.getLineEndingY(), 0);
        
        
        //Undo for the rectangle
        instanceRectangle.execute();
        instanceRectangle.undo();
        
        assertEquals(this.x, this.rectangle.getRectangleX(), 0);
        assertEquals(this.y, this.rectangle.getRectangleY(), 0);
        assertEquals(this.width, this.rectangle.getRectangleWidth(), 0);
        assertEquals(this.height, this.rectangle.getRectangleHeight(), 0);
        
        //Test resizing the rectangle to a smaller one
        this.rectangleBorder.setRectangleX(this.x + 3);
        this.rectangleBorder.setRectangleY(this.y + 3);
        this.rectangleBorder.setWidth(this.width - 5);
        this.rectangleBorder.setHeight(this.height - 6);
        
        instanceRectangle.execute();
        instanceRectangle.undo();

        assertEquals(this.x, this.rectangle.getRectangleX(), 0);
        assertEquals(this.y, this.rectangle.getRectangleY(), 0);
        assertEquals(this.width, this.rectangle.getRectangleWidth(), 0);
        assertEquals(this.height, this.rectangle.getRectangleHeight(), 0);
        

        //Undo for the ellipse
        this.instanceEllipse.execute();
        this.instanceEllipse.undo();
        
        
        assertEquals(this.x, this.ellipse.getEllipseCenterX(), 0);
        assertEquals(this.y, this.ellipse.getEllipseCenterY(), 0);
        assertEquals(this.width, this.ellipse.getEllipseRadiusX(), 0);
        assertEquals(this.height, this.ellipse.getEllipseRadiusY(), 0);
        
        //Test resizing the ellipse to a smaller one
        ellipseBorder.setRectangleX(this.x - 3);
        ellipseBorder.setRectangleY(this.y - 3);
        ellipseBorder.setRectangleWidth(this.width - 5);
        ellipseBorder.setRectangleHeight(this.height - 6);
        
        this.instanceEllipse.execute();
        this.instanceEllipse.undo();
        
        assertEquals(this.x, this.ellipse.getEllipseCenterX(), 0);
        assertEquals(this.y, this.ellipse.getEllipseCenterY(), 0);
        assertEquals(this.width, this.ellipse.getEllipseRadiusX(), 0);
        assertEquals(this.height, this.ellipse.getEllipseRadiusY(), 0);
    }
    
}
