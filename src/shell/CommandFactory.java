package shell;

import shell.commands.ChangeDirectoryLevelUp;
import shell.commands.ShowAllDirectoriesAndSubdirectories;
import shell.commands.ShowFilesAndDirectories;

import java.util.HashMap;

final class CommandFactory
{
    private final HashMap<String, Command> commands;

    private CommandFactory()
    {
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
    static CommandFactory init()
    {
        CommandFactory cf = new CommandFactory();

        String input = Shell.getUserInput();

        if (input.equals("dir"))
        {
            cf.addCommand("dir", new ShowFilesAndDirectories());
        }
        else if (input.equals("cd.."))
        {
            cf.addCommand("cd..", new ChangeDirectoryLevelUp());
        }
        else if (input.equals("exit"))
        {
            System.out.println("Bye!");
            Shell.setIsRunning(false);
        }
        else if (input.equals("tree"))
        {
            if (Shell.getDirectory().equals(""))
            {
                cf.addCommand("tree", new ShowAllDirectoriesAndSubdirectories());
            }
            else
            {
                cf.addCommand("tree", new ShowAllDirectoriesAndSubdirectories());
            }
        }
        else
        {
            System.out.println("Wrong command.");
        }
        return cf;
    }
}
