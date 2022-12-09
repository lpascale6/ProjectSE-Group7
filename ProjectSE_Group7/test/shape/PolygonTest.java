package shape;

import exception.InvalidNumberOfPointsException;
import java.util.ArrayList;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author group7
 */
public class PolygonTest {

    private Polygon polygon;

    @Before
    public void setUp() throws InvalidNumberOfPointsException {
        polygon = new Polygon();
        // setting up the polygon by adding 5 vertex 
        polygon.addPoints(1.0, 1.0);
        polygon.addPoints(-2.2, 12.7);
        polygon.addPoints(100, -11.0);
        polygon.addPoints(0.0, 0.0);
        polygon.addPoints(-47.21, -3.2);
    }

    /**
     * Test of moveOf method, of class Polygon.
     */
    @Test
    public void testMoveOf() throws InvalidNumberOfPointsException {
        System.out.println("Testing moveOf method of Polygon class.");

        // testing for a move of 0 for x and 0 for y
        double x = 0.0;
        double y = 0.0;

        ArrayList<Double> originalPoints = new ArrayList<>();
        ArrayList<Double> modifiedPoints = new ArrayList<>();

        for (Double point : polygon.getPolygonPoints()) {
            originalPoints.add(point);
        }

        // moving the polygon by 0 and 0 means that all the points should not move
        polygon.moveOf(x, y);

        for (Double point : polygon.getPolygonPoints()) {
            modifiedPoints.add(point);
        }

        assertEquals(originalPoints, modifiedPoints);

        // testing for a move of negative x and positive y
        x = -123.456;
        y = 456.789;
        // restoring original points values 
        polygon.setPolygonPoints(originalPoints);

        modifiedPoints = new ArrayList<>();

        // moving the polygon by 0 and 0 means that all the points should not move
        polygon.moveOf(x, y);

        for (Double point : polygon.getPolygonPoints()) {
            modifiedPoints.add(point);
        }

        assertEquals(originalPoints.size(), modifiedPoints.size());
        for (int i = 0; i < originalPoints.size(); i += 2) {
            assertEquals(originalPoints.get(i) + x, modifiedPoints.get(i), 0);
            assertEquals(originalPoints.get(i + 1) + y, modifiedPoints.get(i + 1), 0);
        }

        // testing for a move of positive x and negative y
        x = 7428.12;
        y = -124.147;
        // restoring original points values 
        polygon.setPolygonPoints(originalPoints);

        modifiedPoints = new ArrayList<>();

        // moving the polygon by 0 and 0 means that all the points should not move
        polygon.moveOf(x, y);

        for (Double point : polygon.getPolygonPoints()) {
            modifiedPoints.add(point);
        }

        assertEquals(originalPoints.size(), modifiedPoints.size());
        for (int i = 0; i < originalPoints.size(); i += 2) {
            assertEquals(originalPoints.get(i) + x, modifiedPoints.get(i), 0);
            assertEquals(originalPoints.get(i + 1) + y, modifiedPoints.get(i + 1), 0);
        }

    }

    /**
     * Test of getPolygonPoints method, of class Polygon.
     */
    @Test
    public void testGetPolygonPoints() {
        System.out.println("Testing getPolygonPoints method of Polygon class.");

        ObservableList<Double> expectedResult = FXCollections.observableArrayList();
        expectedResult.addAll(1.0, 1.0, -2.2, 12.7, 100.0, -11.0, 0.0, 0.0, -47.21, -3.2);

        ObservableList<Double> result = polygon.getPolygonPoints();
        assertEquals(expectedResult.size(), result.size());
        assertEquals(expectedResult, result);

        // testing with an empty list of points
        polygon = new Polygon();
        // creating the result, that should be empty
        expectedResult = FXCollections.observableArrayList();

        result = polygon.getPolygonPoints();
        assertEquals(expectedResult.size(), result.size());
        assertEquals(expectedResult, result);
    }

    /**
     * Test of setPolygonPoints method, of class Polygon.
     */
    @Test
    public void testSetPolygonPoints() throws InvalidNumberOfPointsException {
        System.out.println("Testing setPolygonPoints method of Polygon class.");

        ArrayList<Double> points = new ArrayList<>();
        points.add(1.1);
        points.add(2.2);
        points.add(3.3);
        points.add(4.4);
        points.add(5.5);
        points.add(6.6);

        Polygon instance = new Polygon();
        instance.setPolygonPoints(points);

        assertEquals(points, instance.getPolygonPoints());
    }

    /**
     * Test of setPolygonPoints method, of class Polygon.
     */
    @Test(expected = InvalidNumberOfPointsException.class)
    public void testSetPolygonPointsWhenThrowsException() throws InvalidNumberOfPointsException {
        System.out.println("Testing setPolygonPoints method of Polygon class when it throws InvalidNumberOfPointsException exception.");

        // trying to add 3 points
        ArrayList<Double> points = new ArrayList<>();
        points.add(1.1);
        points.add(2.2);
        points.add(3.3);

        Polygon instance = new Polygon();
        instance.setPolygonPoints(points);
    }

    /**
     * Test of addPoints method, of class Polygon.
     */
    @Test
    public void testAddPoints() throws InvalidNumberOfPointsException {
        System.out.println("Testing addPoints method of Polygon class.");

        double point1 = 1.0;
        double point2 = -2.3;

        int originalSize = polygon.getPolygonPoints().size();
        ArrayList<Double> originalPoints = new ArrayList<>();
        ArrayList<Double> modifiedPoints = new ArrayList<>();

        for (Double point : polygon.getPolygonPoints()) {
            originalPoints.add(point);
        }

        polygon.addPoints(point1, point2);

        for (Double point : polygon.getPolygonPoints()) {
            modifiedPoints.add(point);
        }

        originalPoints.add(point1);
        originalPoints.add(point2);

        assertEquals(originalSize + 2, modifiedPoints.size());
        assertEquals(originalPoints, modifiedPoints);

    }

    /**
     * Test of addPoints method, of class Polygon.
     */
    @Test(expected = InvalidNumberOfPointsException.class)
    public void testAddPointsWhenThrowsException() throws InvalidNumberOfPointsException {
        System.out.println("Testing addPoints method of Polygon class when it throws InvalidNumberOfPointsException exception.");
        
        double point1 = 1.0;

        polygon.addPoints(point1);
    }

    /**
     * Test of setOutlineColor method, of class Polygon.
     */
    @Test
    public void testSetOutlineColor() {
        System.out.println("Testing setOutlineColor method of Polygon class.");
        
        // testing with null value
        Paint outlineColor = null;
        polygon.setOutlineColor(outlineColor);
        assertEquals(outlineColor, polygon.getOutlineColor());
        
        // testing with other colors
        outlineColor = Color.BLUE;
        polygon.setOutlineColor(outlineColor);
        assertEquals(outlineColor, polygon.getOutlineColor());
        
        outlineColor = Color.RED;
        polygon.setOutlineColor(outlineColor);
        assertEquals(outlineColor, polygon.getOutlineColor());
    }

    /**
     * Test of getOutlineColor method, of class Polygon.
     */
    @Test
    public void testGetOutlineColor() {
        System.out.println("Testing getOutlineColor method of Polygon class.");
        
        // testing with a color
        polygon.setOutlineColor(Color.CYAN);
        Paint polygonColor = polygon.getOutlineColor();
        
        assertEquals(Color.CYAN, polygonColor);
        
        // testing with null value
        polygon.setOutlineColor(null);
        polygonColor = polygon.getOutlineColor();
        assertEquals(null, polygonColor);
    }

    /**
     * Test of setFillColor method, of class Polygon.
     */
    @Test
    public void testSetFillColor() {
        System.out.println("Testing setFillColor method of Polygon class.");
        
        // testing with null value
        Paint fillColor = null;
        polygon.setFillColor(fillColor);
        assertEquals(fillColor, polygon.getFillColor());
        
        // testing with other colors
        fillColor = Color.BLUE;
        polygon.setFillColor(fillColor);
        assertEquals(fillColor, polygon.getFillColor());
        
        fillColor = Color.RED;
        polygon.setFillColor(fillColor);
        assertEquals(fillColor, polygon.getFillColor());
    }

    /**
     * Test of getFillColor method, of class Polygon.
     */
    @Test
    public void testGetFillColor() {
        System.out.println("Testing getFillColor method of Polygon class.");
        
        // testing with a color
        polygon.setFillColor(Color.CYAN);
        Paint polygonColor = polygon.getFillColor();
        
        assertEquals(Color.CYAN, polygonColor);
        
        // testing with null value
        polygon.setFillColor(null);
        polygonColor = polygon.getFillColor();
        assertEquals(null, polygonColor);
    }

    /**
     * Test of toString method, of class Polygon.
     */
    @Test
    public void testToString() {
        System.out.println("Testing toString method of Polygon class.");
        
        String name = "polygon";
        String points = "1.0 1.0 -2.2 12.7 100.0 -11.0 0.0 0.0 -47.21 -3.2";
        String outlineColor = "#000000";
        String fillColor = "#ffffff";
        String scaleX = "1.0";
        String scaleY = "1.0";
        String rotate = "0.0";
        
        String expString = String.join(";", name, points, outlineColor, fillColor, scaleX, scaleY, rotate);
        
        assertEquals(expString, polygon.toString());
                }

}
