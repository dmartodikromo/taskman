package sr.scom.taskman.common.exceptions;

public class TaskCompletionFailException extends RuntimeException {
    public TaskCompletionFailException(String message) {
        super(message);
    }
}
