package shell.commands;

import shell.Command;
import shell.Shell;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by BSiewni on 7/4/2017.
 */
public class Copy implements Command {

    private Shell shell;

    public Copy(Shell shell) {
        this.shell = shell;
    }

    @Override
    public void executeWithoutParameters() {
        System.out.println("Please enter path for source and destination files - cp [source] [destination]");
    }

    @Override
    public void executeWithOneParameter(Object param) {
        System.out.println("Please enter path for destination file.");
    }

    @Override
    public void executeWithTwoParameters(Object param, Object param2) {
        final String SOURCE = (String) param;
        final String DESTINATION = (String) param2;

        Path source = Paths.get(SOURCE);
        Path destination = Paths.get(DESTINATION);

        try {
            if (fileExist(SOURCE)) {
                if (directoryExist(DESTINATION)) {
                    Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                } else {
                    System.out.println("Destination directory doesn't exist.");
                }
            } else {
                System.out.println("Source file doesn't exist.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean fileExist(String path) {
        File file = new File(path);
        return file.exists() && !file.isDirectory();
    }

    private boolean directoryExist(String path) {
        return Files.exists(Paths.get(path.substring(0, path.lastIndexOf("/"))));
    }
}
