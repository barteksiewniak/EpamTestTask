package shell;

/**
 * This is the main class of "Shell" application. It's used for run the java console (main method).
 * At the moment this class job is to instantiate other objects and let them run.
 *
 * @author Bartosz Siewniak
 */
public class Application
{
    public static void main(String[] args)
    {
        Shell shell = new Shell();
        shell.run();
    }
}
