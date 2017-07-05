package shell;


public interface Command {
    default void execute() {
        System.out.println("Not implemented execute");
    }
    default void executeWithOneParameter(Object param) {
        System.out.println("Not implemented execute with one parameter");
    }

    default void executeWithTwoParameters(Object param, Object param2) {
        System.out.println("Not implemented execute with two parameters");
    }
}
