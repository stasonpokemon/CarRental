package exceptions;

public class NoConnectionJDBCException extends Exception {
    public NoConnectionJDBCException(String message) {
        super(message);
    }
}
