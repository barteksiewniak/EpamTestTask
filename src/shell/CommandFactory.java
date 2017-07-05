package shell;

import shell.commands.*;

import java.util.HashMap;

final class CommandFactory {
    private final HashMap<String, Command> commands;
    private Shell shell;

    CommandFactory(Shell shell) {
        this.shell = shell;
        commands = new HashMap<>();
        fillCommands();
    }

    private void addCommand(String name, Command command) {
        commands.put(name, command);
    }

    boolean executeCommandWithOneParameter(String name, Object param) {
        if (commands.containsKey(name)) {
            commands.get(name).executeWithOneParameter(param);
            return true;
        }
        return false;
    }

    boolean executeCommandWithTwoParameters(String name, Object param, Object param2) {
        if (commands.containsKey(name)) {
            commands.get(name).executeWithTwoParameters(param, param2);
            return true;
        }
        return false;
    }

    private void fillCommands() {
        addCommand("dir", new ShowFilesAndDirectories(shell));
        addCommand("cd..", new ChangeDirectoryLevelUp(shell));
        addCommand("exit", new Exit(shell));
        addCommand("tree", new ShowAllDirectoriesAndSubdirectories(shell));
        addCommand("prompt", new Prompt(shell));
        addCommand("cd", new ChangeDirectoryWithParameter(shell));
        addCommand("help", new Help(shell));
        addCommand("date", new ShowDate(shell));
        addCommand("cp", new Copy(shell));
    }
}

