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
    private String defaultPrompt = "$";
    private boolean isRunning = true;

    /**
     * Method holding main loop in our application, it runs since we change boolean on false.
     */
    void run()
    {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (isRunning)
        {
            final String MY_SHELL = "[My Shell]";
            System.out.print(MY_SHELL + " " + defaultPrompt + ">");
            userInput = scanner.nextLine();

            String[] arr = userInput.split(" ");

            if (arr[0].equals("prompt"))
            {
                prompt(arr);
            }

            if (userInput.equals("exit"))
            {
                System.out.println("Have a nice day!");
                isRunning = false;
                scanner.close();
            }

            if (userInput.equals("dir"))
            {
                showFilesAndDirectories();
            }

            if (userInput.equals("list"))
            {
                final int DIRECTORIES_INITIAL_DEPTH = 0;
                showAllDirectoriesAndSubdirectories(".", DIRECTORIES_INITIAL_DEPTH);
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
            // TODO: 4/13/16 : consider to change if/else on switch statement
            if (userInput[1].equals("$cwd"))
            {
                defaultPrompt = System.getProperty("user.dir");
            }
            else if (userInput[1].equals("reset"))
            {
                if (defaultPrompt.equals("$"))
                {
                    System.out.println("Default prompt is actually in use.");
                }
                else
                {
                    defaultPrompt = "$";
                }
            }
            else
            {
                defaultPrompt = userInput[1];
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
        File file = new File(".");
        File[] files = file.listFiles();

        try
        {
            if (files != null)
            {
                for (File element : files)
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

    // TODO: 4/14/16 change the displaying number of subdirectories depth from integers to "-"
    /**
     * Method responsible for showing directories and subdirectories in current folder
     * @param directoryName
     *        It's the path to the current folder
     * @param depth
     *        It's parameter which are responsible for counting our depth of subdirectories, initial number is 0
     */
    private void showAllDirectoriesAndSubdirectories(String directoryName, int depth)
    {
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();

        if (fList != null)
        {
            try
            {
                for (File file : fList)
                {
                    if (file.isDirectory())
                    {
                        System.out.println(depth + " " + file.getName());
                    }
                    if (file.isDirectory())
                    {
                        showAllDirectoriesAndSubdirectories(file.getCanonicalPath(), depth + 1);
                    }
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}