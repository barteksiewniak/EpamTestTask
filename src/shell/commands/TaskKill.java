package shell.commands;

import shell.Command;

import java.io.IOException;

/**
 * Created by BSiewni on 7/6/2017.
 */
public class TaskKill implements Command {

    @Override
    public void executeWithOneParameter(Object param) {
        String strParam = (String) param;

        try {
            Runtime.getRuntime().exec("taskkill /F /IM " + strParam);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
