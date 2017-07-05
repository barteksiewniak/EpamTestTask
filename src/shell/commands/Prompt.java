package shell.commands;

import shell.Command;
import shell.Shell;

public class Prompt implements Command {
    private Shell shell;

    public Prompt(Shell shell) {
        this.shell = shell;
    }

    /**
     * This method is responsible for take actions (change or reset atm) while user type 'prompt' in shell.
     *
     * @param param it takes our string array from shell, which we previously created from taking input from user
     *              and split it by spaces and cast it on String type
     */
    @Override
    public void executeWithOneParameter(Object param) {
        String promptParameter = (String) param;

        try {
            switch (promptParameter) {
                case "$cwd":
                    shell.setPrompt(shell.getDirectory());
                    break;
                case "reset":
                    if (shell.getPrompt().equals(shell.getDefaultPrompt())) {
                        System.out.println("Default prompt is actually in use.");
                    } else {
                        shell.setPrompt(shell.getDefaultPrompt());
                    }
                    break;
                default:
                    shell.setPrompt(promptParameter);
                    break;
            }
        } catch (NullPointerException e) {
            System.out.println("Use prompt with correct parameter.\n " +
                    "prompt [name] - for change default\n " +
                    "prompt $cwd - for directory\n " +
                    "prompt reset - for default prompt name");
        }
    }
}

