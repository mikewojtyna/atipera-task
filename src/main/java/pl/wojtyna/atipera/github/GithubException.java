package pl.wojtyna.atipera.github;

public class GithubException extends RuntimeException {

    private final int status;

    public GithubException(String message, int status) {
        super(message);
        this.status = status;
    }

    public GithubException(String message, Throwable cause, int status) {
        super(message, cause);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
