package shell;

import shell.commands.*;

import java.util.HashMap;

final class CommandFactory
{
    private final HashMap<String, Command> commands;
    private Shell shell;

    CommandFactory(Shell shell)
    {
        this.shell = shell;
        commands = new HashMap<>();
        fillCommands();
    }

    private void addCommand(String name, Command command)
    {
        commands.put(name, command);
    }

    boolean executeCommand(String name, Object param)
    {
        if (commands.containsKey(name))
        {
            commands.get(name).execute(param);
            return true;
        }
        return false;
    }

    private void fillCommands()
    {
        addCommand("dir", new ShowFilesAndDirectories(shell));
        addCommand("cd..", new ChangeDirectoryLevelUp(shell));
        addCommand("exit", new Exit(shell));
        addCommand("tree", new ShowAllDirectoriesAndSubdirectories(shell));
        addCommand("prompt", new Prompt(shell));
        addCommand("cd", new ChangeDirectoryWithParameter(shell));
    }
}

