package shell.commands;

import shell.Command;
import shell.FileStructure;
import shell.Shell;

import java.io.File;
import java.io.IOException;


public class ShowFilesAndDirectories implements Command {
    private Shell shell;

    public ShowFilesAndDirectories(Shell shell) {
        this.shell = shell;
    }

    /**
     * Method responsible for showing up all files and directories in our current working folder
     */
    @Override
    public void executeWithOneParameter(Object param) {
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
