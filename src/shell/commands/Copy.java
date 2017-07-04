package shell.commands;

import shell.Command;
import shell.Shell;

/**
 * Created by BSiewni on 7/4/2017.
 */
public class Copy implements Command {

    private Shell shell;

    public Copy(Shell shell) {
        this.shell = shell;
    }

    @Override
    public void execute(Object param) {
        System.out.println(param);
    }
}
