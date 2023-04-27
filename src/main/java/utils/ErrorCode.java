package utils;

import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
//Enumerated class for the definition of errors that derives from the operations or features within tbe overall system.
//These error codes are classified according to the possibilities of the system.
//These error codes can be returned from any API within the system.
public enum ErrorCode {
    OK(0, HttpServletResponse.SC_OK,"OK RESPONSE"),
    INVALID_INPUT_TYPES(-99, HttpServletResponse.SC_BAD_REQUEST, "One or more input fields are invalid."),
    INVALID_USER_ID(-100, HttpServletResponse.SC_BAD_REQUEST,"ID is invalid for the resource"),
    NO_SUCH_RESOURCE_FOUND(-101, HttpServletResponse.SC_NOT_FOUND, "Resource not found"),
    INVALID_REVIEW_RANGE(-102, HttpServletResponse.SC_BAD_REQUEST, "Invalid review range"),
    EMAIL_MISSING(-103, HttpServletResponse.SC_BAD_REQUEST, "Email credential is missing"),
    PASSWORD_MISSING(-104, HttpServletResponse.SC_BAD_REQUEST, "Password credential is missing"),
    WRONG_CREDENTIALS(-105, HttpServletResponse.SC_BAD_REQUEST, "Submitted credentials are not in correct format"),
    INVALID_REVIEW_RATING(-106, HttpServletResponse.SC_BAD_REQUEST, "Rating range of review is invalid"),
    INVALID_USER_INFO(-107, HttpServletResponse.SC_BAD_REQUEST, "Invalid user credentials"),
    ADMIN_ONLY_ACTION(-108, HttpServletResponse.SC_CONFLICT, "Authorized action for only admin"),
    INVALID_EMAIL_ADDRESS(-110, HttpServletResponse.SC_BAD_REQUEST, "Invalid email address credential"),
    INVALID_PASSWORD(-111, HttpServletResponse.SC_BAD_REQUEST, "Invalid password credential"),
    USER_NOT_FOUND(-117, HttpServletResponse.SC_NOT_FOUND, "User not found"),
    BADLY_FORMATTED_JSON(-120,  HttpServletResponse.SC_BAD_REQUEST, "JSON format is invalid"),
    OPERATION_UNKNOWN(-200, HttpServletResponse.SC_BAD_REQUEST, "Operation unknown"),
    METHOD_NOT_ALLOWED(-500, HttpServletResponse.SC_METHOD_NOT_ALLOWED, "There is no such authorized method"),
    INTERNAL_ERROR(-999, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Error");

    private final int errorCode;
    private final int httpCode;
    private final String errorMessage;

    ErrorCode(int errorCode, int httpCode, String errorMessage) {
        this.errorCode = errorCode;
        this.httpCode = httpCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getHTTPCode() {
        return httpCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    public JSONObject toJSON() {
        JSONObject data = new JSONObject();
        data.put("code", errorCode);
        data.put("message", errorMessage);
        JSONObject info = new JSONObject();
        info.put("error", data);
        return info;
    }
}