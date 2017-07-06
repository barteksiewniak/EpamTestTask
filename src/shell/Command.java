package shell;


public interface Command {
    // maybe varargs could be better/more scalable solution ??
    default void executeWithoutParameters() {
        System.out.println("Not implemented execute without parameters.");
    }
    default void executeWithOneParameter(Object param) {
        System.out.println("Not implemented execute with one parameter.");
    }
    default void executeWithTwoParameters(Object param, Object param2) {
        System.out.println("Not implemented execute with two parameters.");
    }
}
