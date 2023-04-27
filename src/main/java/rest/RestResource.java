package rest;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import java.io.OutputStream;


/**
 * The JSON factory to be used for creating JSON parsers and generators.
 */
public abstract class RestResource {

    public static final JsonFactory JSON_FACTORY ;
    static {
        JSON_FACTORY = new JsonFactory();
        JSON_FACTORY.disable(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT);
        JSON_FACTORY.disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
    }
    /**
     /**
     * Returns a JSON representation of the {@code Resource} into the given {@code OutputStream}.
     *
     * @param out  the stream to which the JSON representation of the {@code Resource} has to be written.
     *
     * @throws IOException if something goes wrong during the parsing.
     */
    public abstract void toJson (final OutputStream out) throws IOException;
}

