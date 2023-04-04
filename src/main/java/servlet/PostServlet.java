package servlet;

import dao.Post.GetPostByIdDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;
import utils.ResourceNotFoundException;

import java.io.IOException;
import java.sql.SQLException;

import static java.lang.Long.parseLong;

@WebServlet(name = "PostServlet", value = "/PostServlet")
public class PostServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        String op = _request.getRequestURI().split("/", 4)[3].replace("/", "");

        if (op == "details") {
            getPostDetailsOp(_request, _response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {

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
