package shell.commands;

import shell.Command;
import shell.Shell;

import java.io.File;
import java.io.IOException;

public class ChangeDirectoryLevelUp implements Command
{
    private Shell shell;

    public ChangeDirectoryLevelUp(Shell shell)
    {
        this.shell = shell;
    }
    /**
     * Method responsible for change directory ( 1 level of depth up - we switching to parent )
     */
    @Override
    public void execute(Object param)
    {
        String temporaryDirectory;

        if (shell.getDirectory().equals(""))
        {
            temporaryDirectory = shell.getHomeDirectory();
        }
        else
        {
            temporaryDirectory = shell.getDirectory();
        }

        File file = new File(temporaryDirectory);
        try
        {
            shell.setDirectory(file.getCanonicalFile().getParent());
            shell.setPrompt(shell.getDirectory());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
