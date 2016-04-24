package shell.commands;

import shell.Command;
import shell.Shell;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ShowAllDirectoriesAndSubdirectories implements Command
{
    private Shell shell;

    public ShowAllDirectoriesAndSubdirectories(Shell shell)
    {
        this.shell = shell;
    }

    @Override
    public void execute(Object param)
    {
        showAllDirectoriesAndSubdirectories(shell.getDirectory(), 0);
    }

    /**
     * Method responsible for showing directories and subdirectories in current folder
     *
     * @param directoryName It's the path to the current folder
     * @param depth         It's parameter which are responsible for counting our depth of subdirectories, initial number is 0
     */
    private void showAllDirectoriesAndSubdirectories(String directoryName, int depth)
    {
        File directory = new File(directoryName);
        File[] filesAndDirectoriesHolder = directory.listFiles();
        StringBuilder levelOfDepth = new StringBuilder("");

        try
        {
            if (filesAndDirectoriesHolder != null)
            {
                for (int i = 0; i <= depth; i++)
                {
                    levelOfDepth.append("-");
                }
                Arrays.sort(filesAndDirectoriesHolder);
                for (File element : filesAndDirectoriesHolder)
                {
                    if (element.isDirectory())
                    {
                        System.out.println(levelOfDepth + element.getName());
                        showAllDirectoriesAndSubdirectories(element.getCanonicalPath(), depth + 1);
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
