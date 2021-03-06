package shell.commands;

import shell.Command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by BSiewni on 7/4/2017.
 */
public class ShowDate implements Command {

    @Override
    public void executeWithOneParameter(Object param) {

        String strParam = (String) param;

        try {
            switch (strParam) {
                case "%c":
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    System.out.println(dtf.format(now));
            }
        } catch (NullPointerException e) {
            System.out.println("Enter the second argument. Current options:");
            System.out.println("%c - current date in format yyyy/MM/dd HH:mm:ss");
        }
    }
}
