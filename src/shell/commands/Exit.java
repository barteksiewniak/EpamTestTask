package shell.commands;

import shell.Command;
import shell.Shell;

public class Exit implements Command
{
    private Shell shell;
    public Exit(Shell shell)
    {
        this.shell = shell;
    }

    @Override
    public void execute(Object param)
    {
        System.out.println("Bye!");
        shell.setIsRunning(false);
    }
}
