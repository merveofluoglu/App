package utils;

import org.json.JSONObject;

/**
 * It's a simple class that holds a message and a boolean that indicates whether the message is an error or not
 */
public class Message {
    final boolean _isError;
    final String _message;


    public Message(boolean _isError, String _message) {
        this._isError = _isError;
        this._message = _message;
    }

    public String getMessage() {
        return _message;
    }

    public boolean isError() {
        return _isError;
    }

    public JSONObject toJSON(){
        JSONObject result = new JSONObject();
        result.put("error", this._isError);
        result.put("message", this._message);
        return result;
    }
}
