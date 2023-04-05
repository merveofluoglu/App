package servlet;

import dao.Post.CreatePostDao;
import dao.Post.GetPostByIdDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;
import resource.Post;
import utils.ErrorCode;
import utils.ResourceNotFoundException;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import static java.lang.Long.parseLong;

@WebServlet(name = "PostServlet", value = "/PostServlet")
public class PostServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        String _op = _request.getRequestURI().split("/", 4)[3].replace("/", "");

        if (_op.contentEquals("details")) {
            getPostDetailsOp(_request, _response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {

        String _op = _request.getRequestURI().split("/", 4)[3];
        System.out.println(_op);
        switch (_op) {
            case "protected/add" :
                addPost(_request, _response);
                break;
            case "update" :
                updatePost(_request, _response);
                break;
            case "protected/delete" :
                removePost(_request, _response);
                break;
            // the requested operation is unknown
            default :
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);
        }
    }

    private void updatePost(HttpServletRequest _request, HttpServletResponse _response) {
    }

    private void removePost(HttpServletRequest _request, HttpServletResponse _response) {
    }

    private void addPost(HttpServletRequest _request, HttpServletResponse _response) {

        Post _post = null;

        try {
            _post.setName(_request.getParameter("name"));
            _post.setDescription(_request.getParameter("description"));
            _post.setUser_id(Long.parseLong(_request.getParameter("user_id")));
            _post.setCustomer_id(Long.parseLong(_request.getParameter("customer_id")));
            _post.setPrice(Double.parseDouble(_request.getParameter("price")));
            _post.setStatus(_request.getParameter("status"));
            _post.setStart_date(new Timestamp(System.currentTimeMillis()));
            _post.setEnd_date(Timestamp.valueOf(_post.getStart_date().toLocalDateTime().plusDays(15)));
            _post.setIs_deleted(false);
            _post.setIs_sold(false);
            _post.setSold_date(null);
            _post.setUpdate_date(null);
            _post.setCategory_id(Long.parseLong(_request.getParameter("category_id")));
            _post.setSubcategory_id(Long.parseLong(_request.getParameter("subcategory_id")));

            JSONObject _result = new JSONObject();

            _result.put("data", new CreatePostDao(getConnection()).createPost(_post));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void getPostDetailsOp (HttpServletRequest _request, HttpServletResponse _response) {
        try {
            long _id = parseLong(_request.getParameter("post_id"));
            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);

            JSONObject _result = new JSONObject();
            _result.put("data", new GetPostByIdDao(getConnection()).getPostById(_id));

            _response.getWriter().write(_result.toString());

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }
}
