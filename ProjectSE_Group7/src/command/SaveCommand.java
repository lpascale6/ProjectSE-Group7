package command;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import javafx.scene.shape.Shape;


/**
 *
 * @author group7
 */
public class SaveCommand implements Command{
    private File file;
    private ArrayList<Shape> shapeList;

    /**
     * method that creates a save command.
     *
     * @param file File from which to get the data to reconstruct the drawing.
     * @param shapeList List of shapes that have to be saved.
     */
    public SaveCommand(File file, ArrayList<Shape> shapeList) {
        this.file = file;
        this.shapeList = shapeList;
    }

    /**
     * Method that saves shapes in a file.
     * @throws Exception 
     */
    @Override
    public void execute() throws Exception {
        
            FileWriter fw = new FileWriter(this.file.getAbsolutePath());
            
            for(int i = 0; i < this.shapeList.size(); i++){
                fw.write(this.shapeList.get(i).toString() + "\n");
            }
            
            fw.close();
    }

    /**
     * Save command cannot be undone.
     * @throws Exception 
     */
    @Override
    public void undo() throws Exception {
        return;
    }
    
}
