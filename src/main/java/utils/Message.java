package utils;

import org.json.JSONObject;

/**
 * It's a simple class that holds a message and a boolean that indicates whether the message is an error or not
 */
public class Message {
    final boolean error;
    final String message;


    public Message(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() {
        return error;
    }

    public JSONObject toJSON(){
        JSONObject result = new JSONObject();
        result.put("error", this.error);
        result.put("message", this.message);
        return result;
    }
}
