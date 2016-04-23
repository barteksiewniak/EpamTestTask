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
    void init()
    {
        String input = shell.getUserInput();

        switch (input)
        {
            case "dir":
                addCommand("dir", new ShowFilesAndDirectories(shell));
                break;
            case "cd..":
                addCommand("cd..", new ChangeDirectoryLevelUp(shell));
                break;
            case "exit":
                System.out.println("Bye!");
                shell.setIsRunning(false);
                break;
            case "tree":
                addCommand("tree", new ShowAllDirectoriesAndSubdirectories(shell));
                break;
            default:
                System.out.println("Wrong command.");
                break;
        }
    }
}
