package command;

/**
 * This is the interface used to implement the command operations.
 * 
 * @author group7
 */
public interface Command {
    
    public void execute() throws Exception;
    public void undo() throws Exception;
    
}
