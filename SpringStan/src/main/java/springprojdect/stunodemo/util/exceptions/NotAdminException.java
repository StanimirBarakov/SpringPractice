package springprojdect.stunodemo.util.exceptions;

public class NotAdminException extends BaseException {
    public NotAdminException() {
        super("You are not admin!");
    }
}
