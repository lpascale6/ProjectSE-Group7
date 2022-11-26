package command;

import java.io.File;
import java.io.FileNotFoundException;
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

    public SaveCommand(File file, ArrayList<Shape> shapeList) {
        this.file = file;
        this.shapeList = shapeList;
    }

    /**
     * Function to save shapes in a file.
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
    
}
