package servlet;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import dao.Post.GetPostsByNameDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import resource.Post;
import rest.postsRestResource;
import utils.ErrorCode;
import utils.RestMessageGenerator;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;

import static rest.RestResource.JSON_FACTORY;

public class RestServlet extends AbstractServlet{
    private static final String JSON_UTF_8_MEDIA_TYPE = "application/json";

    /**
     * If the request is a GET request, and the request URL is /books, then return a list of books
     *
     * @param req The request object
     * @param res The response object.
     */
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {

        res.setContentType(JSON_UTF_8_MEDIA_TYPE);
        final OutputStream out = res.getOutputStream();

        try {
            if (PostProcessing(req, res)) {
                return;
            }
            final RestMessageGenerator m = new RestMessageGenerator("Resource is unknown!");
            m.toJSON(out);

        } finally {
            out.flush();
            out.close();
        }
    }

    /**
     * If the request is a GET request, then call the PostSearching function
     *
     * @param _request The request object.
     * @param _result The response object.
     */
    private boolean PostProcessing(HttpServletRequest _request, HttpServletResponse _result) throws IOException {
        final String _method = _request.getMethod();
        final OutputStream _output_stream = _result.getOutputStream();
        String _uri_path = _request.getRequestURI();
        RestMessageGenerator _message = null;
        System.out.println(_uri_path);

        if (_uri_path.lastIndexOf("rest/posts") <= 0) {
            return false;
        }
        try {
            _uri_path = _uri_path.substring(_uri_path.lastIndexOf("posts") + 6);
            System.out.println(_method);
            if (_uri_path.contains("search")) {
                if (Objects.equals(_method, "GET") || Objects.equals(_method, "-G") || Objects.equals(_method, "-g"))
                {
                    PostSearching(_request, _result);
                } else {

                    _message = new RestMessageGenerator("Unsupported operation for URI /posts/search."
                            + String.format("Requested operation %s.", _method));
                    _result.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    _message.toJSON(_result.getOutputStream());
                }
            }
        } catch (Exception e) {
            writeError(_result, ErrorCode.INTERNAL_ERROR);
        }
        return true;
    }

    /**
     * There exist a keyword within the request body that can be obtainable from the function. These keywrod will be used
     * for searching the post within the database.
     * If there are no posts exists with that keyword, function will return an indication about non-existence of the posts.
     * If there are posts that can be found with the keyword, then the function returns the posts that match with keyword
     *
     * @param _request The HttpServletRequest object.
     * @param _response The HttpServletResponse object.
     */
    public void PostSearching(HttpServletRequest _request, HttpServletResponse _response) throws IOException {

        String keyword = null;
        try {
            final JsonParser jp = JSON_FACTORY.createParser(_request.getInputStream());
            while (jp.nextToken() != JsonToken.END_OBJECT) {
                System.out.println(jp.getCurrentToken());
                if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {
                    System.out.println(jp.getCurrentName());
                    if (Objects.equals(jp.getCurrentName(), "keyword")) {
                        jp.nextToken();
                        keyword = jp.getText();
                    }
                }
            }
            List<Post> posts = new GetPostsByNameDao(getConnection()).getPostsByName(keyword);
            if (posts.size() < 1){
                RestMessageGenerator _message = new RestMessageGenerator("There is no existed post with this keyword");
                _response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                _message.toJSON(_response.getOutputStream());
                writeError(_response, ErrorCode.NO_SUCH_RESOURCE_FOUND);
            }
            postsRestResource _postRestResourceObject = new postsRestResource(posts);
            _postRestResourceObject.toJson(_response.getOutputStream());
        } catch (Throwable threw) {
            RestMessageGenerator _message = new RestMessageGenerator(threw.toString());
            _response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            _message.toJSON(_response.getOutputStream());
            writeError(_response, ErrorCode.INTERNAL_ERROR);
        }
    }
}
