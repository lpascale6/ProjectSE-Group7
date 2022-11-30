package command;

import java.util.Stack;

/**
 *
 * @author group7
 */
public class Invoker {
    private Stack<Command> commands;
    private static Invoker instance = null;
    
    private Invoker() {
        this.commands = new Stack<>();
    }
    
    public static Invoker getInstance() {
        if(instance == null) {
            instance = new Invoker();
        }
        return instance;
    }
    
    public void execute(Command command) throws Exception {
      command.execute();
      this.commands.push(command);
    }
    
    public void undo() throws Exception{
        Command command = this.commands.pop();
        command.undo();
    }
    
    public void clearStack() {
        this.commands.clear();
    }
}
