package shell;

import java.util.Scanner;

/**
 * This is the core class of "Shell" application. Here we have all methods and operations on the our console.
 *
 * @author Bartosz Siewniak
 */
public class Shell
{
    private final String defaultPrompt = "$";
    private String prompt = defaultPrompt;
    private final String HOME_DIRECTORY = System.getProperty("user.dir");
    private String directory = HOME_DIRECTORY;
    private boolean isRunning = true;

    public String getDefaultPrompt()
    {
        return defaultPrompt;
    }

    public void setPrompt(String prompt)
    {
        this.prompt = prompt;
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

    public String getPrompt()
    {
        return prompt;
    }

    /**
     * Method holding main loop in our application, it runs since we change boolean on false.
     */
    void run()
    {
        CommandFactory commandFactory = new CommandFactory(this);
        Scanner input = new Scanner(System.in);
        while (isRunning)
        {
            final String MY_SHELL = "[My Shell]";
            System.out.print(MY_SHELL + " " + prompt + ">");
            String userInput = input.nextLine();

            String[] userInputHolder = userInput.split(" ");
            boolean isExecuted;

            if (userInputHolder.length > 1)
            {
                isExecuted = commandFactory.executeCommand(userInputHolder[0], userInputHolder[1]);
            }
            else
            {
                isExecuted = commandFactory.executeCommand(userInputHolder[0], null);
            }

            if(!isExecuted)
            {
                System.out.println("Wrong command.");
            }
        }
        input.close();
    }
}