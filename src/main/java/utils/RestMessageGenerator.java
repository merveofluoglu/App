package utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import java.io.OutputStream;

/**
 * The class is a simple POJO that has a single field, message. The class also has a toJSON method that takes an
 * OutputStream and writes the JSON representation of the object to the stream
 */
public class RestMessageGenerator {
    public String message;
    protected static final JsonFactory JSON_FACTORY ;
    static {
        JSON_FACTORY = new JsonFactory();
        JSON_FACTORY.disable(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT);
        JSON_FACTORY.disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
    }
    public RestMessageGenerator(String message) {
        this.message = message;
    }

    public final void toJSON(OutputStream out) throws IOException {
        final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

        jg.writeStartObject();
        jg.writeFieldName("message");
        jg.writeStartObject();
        jg.writeStringField("message", message);
        jg.writeEndObject();
        jg.writeEndObject();
        jg.flush();
    }
}
