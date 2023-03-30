package utils;

import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

// This is an enum class that defines all the possible error codes that can be returned by the API.
public enum ErrorCode {
    OK(0, HttpServletResponse.SC_OK,"OK."),
    INVALID_INPUT_TYPES(-99, HttpServletResponse.SC_BAD_REQUEST, "One or more input fields are invalid."),
    INVALID_GUID(-100, HttpServletResponse.SC_BAD_REQUEST,"Invalid resource ID"),
    NO_SUCH_RESOURCE(-101, HttpServletResponse.SC_NOT_FOUND, "Resource not found"),
    INVALID_REVIEW_TITLE(-102, HttpServletResponse.SC_BAD_REQUEST, "Invalid review title"),
    EMAIL_MISSING(-103, HttpServletResponse.SC_BAD_REQUEST, "Email missing"),
    PASSWORD_MISSING(-104, HttpServletResponse.SC_BAD_REQUEST, "Password missing"),
    WRONG_CREDENTIALS(-105, HttpServletResponse.SC_BAD_REQUEST, "Submitted credentials are wrong"),
    INVALID_REVIEW_RATING(-106, HttpServletResponse.SC_BAD_REQUEST, "Invalid review rating"),
    INVALID_USER_INFO(-107, HttpServletResponse.SC_BAD_REQUEST, "Invalid user information"),
    ADMIN_ONLY_ACTION(-108, HttpServletResponse.SC_CONFLICT, "Only admins can perform this action"),
    INVALID_SHELVE_NAME(-109, HttpServletResponse.SC_BAD_REQUEST, "Invalid shelve name"),
    INVALID_EMAIL_ADDRESS(-110, HttpServletResponse.SC_BAD_REQUEST, "Invalid email address"),
    INVALID_PASSWORD(-111, HttpServletResponse.SC_BAD_REQUEST, "Invalid password"),
    INVALID_BOOK_STORE_NAME(-112, HttpServletResponse.SC_BAD_REQUEST, "Invalid book store name"),
    INVALID_CARD_NUMBER(-113, HttpServletResponse.SC_BAD_REQUEST, "Invalid Card Number"),
    INVALID_CVV(-114, HttpServletResponse.SC_BAD_REQUEST, "Invalid cvv"),
    INVALID_CARD_HOLDER_NAME(-115, HttpServletResponse.SC_BAD_REQUEST, "Invalid Card Holder Name."),
    USER_NOT_FOUND(-117, HttpServletResponse.SC_NOT_FOUND, "User not found."),
    BADLY_FORMATTED_JSON(-120,  HttpServletResponse.SC_BAD_REQUEST, "The input json is in the wrong format."),
    OPERATION_UNKNOWN(-200, HttpServletResponse.SC_BAD_REQUEST, "Operation unknown."),
    METHOD_NOT_ALLOWED(-500, HttpServletResponse.SC_METHOD_NOT_ALLOWED, "The method is not allowed"),
    TOKEN_TAMPERED(-750, HttpServletResponse.SC_UNAUTHORIZED, "The token has been tampered!!!!"),
    TOKEN_EXPIRED(-751, HttpServletResponse.SC_UNAUTHORIZED, "The token has expired."),
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