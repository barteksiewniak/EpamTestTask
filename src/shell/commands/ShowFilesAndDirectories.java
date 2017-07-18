package shell.commands;

import shell.Command;
import shell.FileStructure;
import shell.Shell;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Calendar;
import java.util.Date;


public class ShowFilesAndDirectories implements Command {
    private Shell shell;

    public ShowFilesAndDirectories(Shell shell) {
        this.shell = shell;
    }

    /**
     * Method responsible for showing up all files and directories in our current working folder
     */
    @Override
    public void executeWithoutParameters() {
        String directoryPlaceholder;

        if (shell.getDirectory().equals(shell.getHomeDirectory())) {
            directoryPlaceholder = shell.getHomeDirectory();
        } else {
            directoryPlaceholder = shell.getDirectory();
        }

        File currentDirectory = new File(directoryPlaceholder);
        File[] listOfFilesAndFolders = currentDirectory.listFiles();

        try {
            System.out.println("Content of " + currentDirectory.getCanonicalFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (listOfFilesAndFolders != null) {
                for (File element : listOfFilesAndFolders) {
                    BasicFileAttributes basicFileAttributes = Files.readAttributes(element.toPath(),
                            BasicFileAttributes.class);
                    Calendar time = Calendar.getInstance();
                    time.setTimeInMillis(basicFileAttributes.lastAccessTime().toMillis());
                    Date dateTime = time.getTime();
                    System.out.print(dateTime + " ");

                    if (element.isDirectory()) {
                        System.out.print(FileStructure.DIR + "     ");
                    } else {
                        System.out.print(FileStructure.FILE + "    ");
                    }
                    System.out.println(element.getCanonicalFile());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
