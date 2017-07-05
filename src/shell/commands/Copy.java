package shell.commands;

import shell.Command;
import shell.Shell;

import java.io.*;

/**
 * Created by BSiewni on 7/4/2017.
 */
public class Copy implements Command {

    private Shell shell;

    public Copy(Shell shell) {
        this.shell = shell;
    }

    @Override
    public void executeWithTwoParameters(Object param, Object param2) {
        System.out.println(checkThatFileExist("C:/Projects/test.txt"));
    }

    private boolean checkThatFileExist(String path) {
        File file = new File(path);
        return file.exists() && !file.isDirectory();
    }
}
