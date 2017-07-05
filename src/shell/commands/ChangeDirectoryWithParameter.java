package shell.commands;

import shell.Command;
import shell.Shell;

/**
 * Created by kaer on 4/24/16.
 */
public class ChangeDirectoryWithParameter implements Command {
    private Shell shell;

    public ChangeDirectoryWithParameter(Shell shell) {
        this.shell = shell;
    }

    @Override
    public void executeWithOneParameter(Object param) {
        String changeDirectoryParam = (String) param;
        shell.setDirectory(changeDirectoryParam);
        shell.setPrompt(changeDirectoryParam);
    }
}
