package pl.wojtyna.atipera.repository;

public class UserDoesNotExistException extends RepositoriesException {

    public UserDoesNotExistException(String message) {
        super(message);
    }

    public UserDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
