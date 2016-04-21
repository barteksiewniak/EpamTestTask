package shell;

import java.util.Scanner;

/**
 * This is the core class of "Shell" application. Here we have all methods and operations on the our console.
 *
 * @author Bartosz Siewniak
 */
public class Shell
{
    private static String defaultPromptProperty = "$";
    private static String directory = "";
    private static final String HOME_DIRECTORY = System.getProperty("user.dir");
    private static String userInput;
    private static boolean isRunning = true;

    public static String getDefaultPromptProperty()
    {
        return defaultPromptProperty;
    }

    public static void setDefaultPromptProperty(String defaultPromptProperty)
    {
        Shell.defaultPromptProperty = defaultPromptProperty;
    }

    public static boolean isRunning()
    {
        return isRunning;
    }

    static void setIsRunning(boolean isRunning)
    {
        Shell.isRunning = isRunning;
    }

    public static String getDirectory()
    {
        return directory;
    }

    public static void setDirectory(String directory)
    {
        Shell.directory = directory;
    }

    public static String getHomeDirectory()
    {
        return HOME_DIRECTORY;
    }

    static String getUserInput()
    {
        return userInput;
    }

    /**
     * Method holding main loop in our application, it runs since we change boolean on false.
     */
    void run()
    {
        Scanner input = new Scanner(System.in);
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
            else
            {
                CommandFactory.init().executeCommand(userInput);
            }
        }
        input.close();
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
                    defaultPromptProperty = HOME_DIRECTORY;
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
}