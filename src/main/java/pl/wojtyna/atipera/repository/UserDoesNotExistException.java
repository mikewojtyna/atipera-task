package pl.wojtyna.atipera.repository;

public class UserDoesNotExistException extends RepositoriesException {

    private final String user;

    public UserDoesNotExistException(String message, String user) {
        super(message);
        this.user = user;
    }

    public UserDoesNotExistException(String message, Throwable cause, String user) {
        super(message, cause);
        this.user = user;
    }

    public String getUser() {
        return user;
    }
}
