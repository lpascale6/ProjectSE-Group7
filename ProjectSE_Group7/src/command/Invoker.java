package command;

import java.util.Stack;

/**
 *
 * @author group7
 */
public class Invoker {
    
    private Stack<Command> commands;
    private static Invoker instance = null;
    
    /**
     * Constructor method to create a new invoker.
     */
    private Invoker() {
        this.commands = new Stack<>();
    }
    
    /**
     * Method to make this a singleton, returns the instance if it already exists, or creates it and then returns it.
     * @return 
     */
    public static Invoker getInstance() {
        if(instance == null) {
            instance = new Invoker();
        }
        return instance;
    }
    
    /**
     * Takes a command and calls its execute method, then insert the command in the stack.
     * @param command
     * @throws Exception 
     */
    public void execute(Command command) throws Exception {
      command.execute();
      this.commands.push(command);
    }
    
    /**
     * Calls the undo method of the last command in the stack.
     * @throws Exception 
     */
    public void undo() throws Exception{
        Command command = this.commands.pop();
        command.undo();
    }
    
    /**
     * Clears the stack.
     */
    public void clearStack() {
        this.commands.clear();
    }
    
    /**
     * Returns the stack.
     * @return the commands stack.
     */
    public Stack<Command> getStack(){
        return this.commands;
    }
}
