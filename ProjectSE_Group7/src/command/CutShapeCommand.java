/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command;

import gui.DrawingPane;
import javafx.scene.shape.Shape;

/**
 *
 * @author luigi
 */
public class CutShapeCommand implements Command {

    CopyShapeCommand copyShapeCommand;
    DeleteShapeCommand deleteShapeCommand;
    DrawingPane drawingPane;

    /**
     * Constructor method of class CutShapeCommand.
     *
     * @param selectedShape the shape to cut
     * @param drawingPane the drawing Pane of the selected shape and where to
     * put the copied shape
     */
    public CutShapeCommand(Shape selectedShape, DrawingPane drawingPane) {
        this.copyShapeCommand = new CopyShapeCommand(selectedShape, drawingPane);
        this.deleteShapeCommand = new DeleteShapeCommand(selectedShape, drawingPane);
        this.drawingPane = drawingPane;
    }

    /**
     * method that cuts a shape (saves it in copiedShape of drawingPane and
     * deletes it from the drawingPane).
     *
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        this.copyShapeCommand.execute();
        this.deleteShapeCommand.execute();
        this.drawingPane.deselectShape();
    }

    /**
     * method that undoes the cut of a shape.
     *
     * @throws Exception
     */
    @Override
    public void undo() throws Exception {
        this.deleteShapeCommand.undo();
        this.drawingPane.setIsShapeCopied(false);
    }

}
