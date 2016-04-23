package shell;

import java.util.Scanner;

/**
 * This is the core class of "Shell" application. Here we have all methods and operations on the our console.
 *
 * @author Bartosz Siewniak
 */
public class Shell
{
    private String defaultPromptProperty = "$";
    private String directory = "";
    private final String HOME_DIRECTORY = System.getProperty("user.dir");
    private String userInput;
    private boolean isRunning = true;

    public void setDefaultPromptProperty(String defaultPromptProperty)
    {
        this.defaultPromptProperty = defaultPromptProperty;
    }

    public void setIsRunning(boolean isRunning)
    {
        this.isRunning = isRunning;
    }

    public String getDirectory()
    {
        return directory;
    }

    public void setDirectory(String directory)
    {
        this.directory = directory;
    }

    public String getHomeDirectory()
    {
        return HOME_DIRECTORY;
    }

    String getUserInput()
    {
        return userInput;
    }

    /**
     * Method holding main loop in our application, it runs since we change boolean on false.
     */
    void run()
    {
        CommandFactory cf = new CommandFactory(this);
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
                cf.init().executeCommand(userInput);
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