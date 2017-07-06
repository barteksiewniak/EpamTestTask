package shell.commands;

import shell.Command;
import shell.Shell;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by BSiewni on 7/6/2017.
 */
public class Process implements Command {

    @Override
    public void executeWithoutParameters() {
        try {
            String line;
            java.lang.Process process = Runtime.getRuntime().exec
                    (System.getenv("windir") +"\\system32\\"+"tasklist.exe");
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
