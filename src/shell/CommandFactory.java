package shell;

import shell.commands.*;
import shell.commands.Process;

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

    boolean executeCommand(String name, Object param, Object param2) {
        if (commands.containsKey(name)) {
            if (param2 == null & param == null) {
                commands.get(name).executeWithoutParameters();
                return true;
            } else if (param2 == null & param != null) {
                commands.get(name).executeWithOneParameter(param);
                return true;
            } else if (param != null) {
                commands.get(name).executeWithTwoParameters(param, param2);
                return true;
            }
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
        addCommand("help", new Help());
        addCommand("date", new ShowDate());
        addCommand("cp", new Copy());
        addCommand("ps", new Process());
        addCommand("taskkill", new TaskKill());
    }
}

