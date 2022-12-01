package command;

/**
 * This is the interface used to implement the command operations.
 * 
 * @author group7
 */
public interface Command {
    
    /**
     * Generic execute method to be implemented in the implementing subclasses.
     * @throws Exception 
     */
    public void execute() throws Exception;
    
    /**
     * Generic undo method to be implemented in the implementing subclasses.
     * @throws Exception 
     */
    public void undo() throws Exception;
    
}
