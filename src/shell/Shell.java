package shell;

import java.util.Scanner;

/**
 * This is the core class of "Shell" application. Here we have all methods and operations on the our console.
 *
 * @author Bartosz Siewniak
 */
public class Shell {
    private final String defaultPrompt = "$";
    private String prompt = defaultPrompt;
    private final String HOME_DIRECTORY = System.getProperty("user.dir");
    private String directory = HOME_DIRECTORY;
    private boolean isRunning = true;

    public String getDefaultPrompt() {
        return defaultPrompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getHomeDirectory() {
        return HOME_DIRECTORY;
    }

    public String getPrompt() {
        return prompt;
    }

    /**
     * Method holding main loop in our application, it runs since we change boolean on false.
     */
    void run() {
        CommandFactory commandFactory = new CommandFactory(this);
        Scanner input = new Scanner(System.in);
        welcomeScreen();
        while (isRunning) {
            final String MY_SHELL = "[My Shell]";
            System.out.print(MY_SHELL + " " + prompt + ">");
            String userInput = input.nextLine();

            String[] userInputHolder = userInput.split(" ");
            boolean isExecuted;

            if (userInputHolder.length == 3) {
                isExecuted = commandFactory.executeCommand(userInputHolder[0].toLowerCase(), userInputHolder[1],
                        userInputHolder[2]);
            } else if (userInputHolder.length == 2) {
                isExecuted = commandFactory.executeCommand(userInputHolder[0].toLowerCase(), userInputHolder[1], null);
            } else {
                isExecuted = commandFactory.executeCommand(userInputHolder[0].toLowerCase(), null, null);
            }

            if (!isExecuted) {
                if (!"".equals(userInput)){
                    System.out.println("\'" + userInput.trim() + "\'" + " is not recognized as an internal or external" +
                            " command, operable program or batch file.");
                } else {
                    System.out.println();
                }
            }
        }
        input.close();
    }

    private void welcomeScreen() {
        System.out.println("Windows Shell-like [Version 0.1]");
        System.out.println("Copyright (c) 2017 Bartosz Siewniak. All rights reserved.");
        System.out.println("Type help for get all available commands.");
    }
}