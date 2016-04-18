package shell;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * This is the core class of "Shell" application. Here we have all methods and operations on the our console.
 *
 * @author Bartosz Siewniak
 */
class Shell
{
    private String defaultPromptProperty = "$";
    private boolean isRunning = true;
    private String directory = "";
    private final String CURRENT_DIRECTORY = System.getProperty("user.dir");

    /**
     * Method holding main loop in our application, it runs since we change boolean on false.
     */
    void run()
    {
        Scanner input = new Scanner(System.in);
        String userInput;

        while (isRunning)
        {
            final String MY_SHELL = "[My Shell]";
            System.out.print(MY_SHELL + " " + defaultPromptProperty + ">");
            userInput = input.nextLine();

            String[] userInputHolder = userInput.split(" ");

            if (userInputHolder[0].equals("prompt"))
            {
                prompt(userInputHolder);
            }
            if (userInput.equals("exit"))
            {
                System.out.println("Have a nice day!");
                isRunning = false;
                input.close();
            }
            if (userInput.equals("dir"))
            {
                showFilesAndDirectories();
            }
            if (userInput.equals("tree"))
            {
                showAllDirectoriesAndSubdirectories(CURRENT_DIRECTORY, 0);
            }
            if (userInput.equals("cd.."))
            {
                changeDirectory();
            }

        }
    }

    /**
     * This method is responsible for take actions (change or reset atm) while user type 'prompt' in shell.
     *
     * @param userInput it takes our string array, which we previously created from taking input from user
     *                  and split it by spaces
     */
    private void prompt(String[] userInput)
    {
        try
        {
            switch (userInput[1])
            {
                case "$cwd":
                    defaultPromptProperty = CURRENT_DIRECTORY;
                    break;
                case "reset":
                    if (defaultPromptProperty.equals("$"))
                    {
                        System.out.println("Default prompt is actually in use.");
                    }
                    else
                    {
                        defaultPromptProperty = "$";
                    }
                    break;
                default:
                    defaultPromptProperty = userInput[1];
                    break;
            }

        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Use prompt with correct parameter.\n " +
                    "prompt [name] - for change default\n " +
                    "prompt $cwd - for directory\n " +
                    "prompt reset - for default prompt name");
        }
    }

    /**
     * Method responsible for showing up all files and directories in our current working folder
     */
    private void showFilesAndDirectories()
    {
        File currentDirectory = new File(CURRENT_DIRECTORY);
        File[] filesAndDirectoriesHolder = currentDirectory.listFiles();

        try
        {
            System.out.println("Content of " + currentDirectory.getCanonicalFile());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            if (filesAndDirectoriesHolder != null)
            {
                for (File element : filesAndDirectoriesHolder)
                {
                    if (element.isDirectory())
                    {
                        System.out.print(FileStructure.DIR + "     ");
                    }
                    else
                    {
                        System.out.print(FileStructure.FILE + "    ");
                    }
                    System.out.println(element.getCanonicalFile());
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // TODO: 4/15/16 consider how to sort

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

        for (int i = 0; i <= depth; i++)
        {
            levelOfDepth.append("-");
        }

        if (filesAndDirectoriesHolder != null)
        {
            try
            {
                for (File element : filesAndDirectoriesHolder)
                {
                    if (element.isDirectory())
                    {
                        System.out.println(levelOfDepth + element.getName());
                        showAllDirectoriesAndSubdirectories(element.getCanonicalPath(), depth + 1);
                    }
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method responsible for change directory ( 1 level of depth up - we switching to parent )
     */
    private void changeDirectory()
    {
        String temporaryDirectory;

        if (directory.equals(""))
        {
            temporaryDirectory = CURRENT_DIRECTORY;
        }
        else
        {
            temporaryDirectory = directory;
        }

        File file = new File(temporaryDirectory);
        try
        {
            directory = (file.getCanonicalFile().getParent());
            System.out.println(directory);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}