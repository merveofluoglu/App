package utils;

/**
 * This class is a custom exception that is thrown when a resource is not found.
 */
public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
