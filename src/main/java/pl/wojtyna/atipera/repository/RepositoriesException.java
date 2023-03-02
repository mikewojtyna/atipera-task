package pl.wojtyna.atipera.repository;

public class RepositoriesException extends RuntimeException {

    public RepositoriesException(String message) {
        super(message);
    }

    public RepositoriesException(String message, Throwable cause) {
        super(message, cause);
    }
}
