package shell;

@FunctionalInterface
public interface Command
{
    void execute(Object param);
}
