package servlet;

import dao.Post.GetPostByIdDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;
import utils.ErrorCode;
import utils.ResourceNotFoundException;

import java.io.IOException;
import java.sql.SQLException;

import static java.lang.Long.parseLong;

@WebServlet(name = "PostServlet", value = "/PostServlet")
public class PostServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        String _op = _request.getRequestURI().split("/", 4)[3].replace("/", "");

        if (_op == "details") {
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
