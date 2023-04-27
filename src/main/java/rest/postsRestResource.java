package rest;

import com.fasterxml.jackson.core.JsonGenerator;
import resource.Post;

import java.io.IOException;
import java.io.OutputStream;

import static rest.RestResource.JSON_FACTORY;

public class postsRestResource {
    public final Iterable<Post> _postList;

    public postsRestResource(final Iterable<Post> _postList) {
        this._postList = _postList;
    }

    public void toJson(OutputStream out) throws IOException {

        final JsonGenerator jg = JSON_FACTORY.createGenerator(out);
        jg.writeStartObject();
        jg.writeFieldName("books-list");
        jg.writeStartArray();
        jg.flush();
        boolean firstElement = true;
        for (final Post post : _postList) {
            // very bad work-around to add commas between resources
            if (firstElement) {
                jg.writeRaw(post.toString());
                jg.flush();
                firstElement = false;
            } else {
                jg.writeRaw(',');
                jg.flush();
                jg.writeRaw(post.toString());
                jg.flush();
            }
        }
        jg.writeEndArray();
        jg.writeEndObject();
        jg.flush();
        jg.close();
    }
}
