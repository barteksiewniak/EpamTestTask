package shell;


public interface Command {
    default void executeWithOneParameter(Object param) {
        System.out.println("Not implemented");
    }

    default void executeWithTwoParameters(Object param, Object param2) {
        System.out.println("Not implemented");
    }
}
