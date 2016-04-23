package shell;

import shell.commands.ChangeDirectoryLevelUp;
import shell.commands.ShowAllDirectoriesAndSubdirectories;
import shell.commands.ShowFilesAndDirectories;

import java.util.HashMap;

final class CommandFactory
{
    private final HashMap<String, Command> commands;
    private Shell shell;

    public CommandFactory(Shell shell)
    {
        this.shell = shell;
        commands = new HashMap<>();
    }

    private void addCommand(String name, Command command)
    {
        commands.put(name, command);
    }

    void executeCommand(String name)
    {
        if (commands.containsKey(name))
        {
            commands.get(name).execute();
        }
    }

    // factory pattern
    CommandFactory init()
    {
        CommandFactory cf = new CommandFactory(shell);

        String input = shell.getUserInput();

        if (input.equals("dir"))
        {
            cf.addCommand("dir", new ShowFilesAndDirectories(shell));
        }
        else if (input.equals("cd.."))
        {
            cf.addCommand("cd..", new ChangeDirectoryLevelUp(shell));
        }
        else if (input.equals("exit"))
        {
            System.out.println("Bye!");
            shell.setIsRunning(false);
        }
        else if (input.equals("tree"))
        {
            cf.addCommand("tree", new ShowAllDirectoriesAndSubdirectories(shell));
        }
        else
        {
            System.out.println("Wrong command.");
        }
        return cf;
    }
}
