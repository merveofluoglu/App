package utils;

/**
 * This class is a custom exception that is thrown when a user tries to perform an action that is not allowed
 */
public class RestrictedActionException extends Exception{
    public RestrictedActionException(String _message) {
        super(_message);
    }
}
