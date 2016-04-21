package shell.commands;

import shell.Command;
import shell.Shell;

import java.io.File;
import java.io.IOException;

public class ChangeDirectoryLevelUp implements Command
{
    /**
     * Method responsible for change directory ( 1 level of depth up - we switching to parent )
     */
    @Override
    public void execute()
    {
        String temporaryDirectory;

        if (Shell.getDirectory().equals(""))
        {
            temporaryDirectory = Shell.getHomeDirectory();
        }
        else
        {
            temporaryDirectory = Shell.getDirectory();
        }

        File file = new File(temporaryDirectory);
        try
        {
            Shell.setDirectory(file.getCanonicalFile().getParent());
            Shell.setDefaultPromptProperty(Shell.getDirectory());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
