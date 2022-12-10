/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state;

import command.AddShapeCommand;
import command.Invoker;
import gui.DrawingPane;
import javafx.scene.paint.Color;
import shape.Text;

/**
 *
 * @author Leonardo
 */
public class DrawTextState implements DrawState {
 
    private DrawingPane drawingPane;
    private Invoker invoker;
    private Text creatingText;
    private double xStartPoint;
    private double yStartPoint;
    
    public DrawTextState(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
        invoker = Invoker.getInstance();
    }
    

    @Override
    public void draw(double x, double y) {
       
    }

    @Override
    public void startDrawing(double xStartPoint, double yStartPoint, Color outlineColor, Color fillColor, String textString, int textSize) {
        this.xStartPoint = xStartPoint;
        this.yStartPoint = yStartPoint;

        this.creatingText = new Text(xStartPoint, yStartPoint, textString, textSize);
        
        this.creatingText.setStroke(outlineColor);
        this.creatingText.setFill(fillColor);
        
        AddShapeCommand addShapeCommand = new AddShapeCommand(this.drawingPane, this.creatingText);
        try {
            invoker.execute(addShapeCommand);
        } catch (Exception ex) {
        }
    }
   
   
    
}
