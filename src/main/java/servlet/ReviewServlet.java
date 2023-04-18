package servlet;


import dao.Review.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;
import resource.Reviews;
import utils.ErrorCode;
import utils.ResourceNotFoundException;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import static java.lang.Long.parseLong;

@WebServlet(name = "ReviewServlet", value = "/ReviewServlet")
public class ReviewServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        String _op = _request.getRequestURI().split("/", 4)[3].replace("/", "");

        if (_op.contentEquals("details_by_post_id")) {
            getReviewDetailsByPostId(_request, _response);
        }
        else {
            getReviewDetailsByUserId(_request, _response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {

        String _op = _request.getRequestURI().split("/", 4)[3];
        System.out.println(_op);
        switch (_op) {
            case "protected/add" :
                addReview(_request, _response);
                break;
            case "update" :
                updateReview(_request, _response);
                break;
            case "protected/delete" :
                removeReview(_request, _response);
                break;
            default :
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);
        }
    }
    private void updateReview(HttpServletRequest _request, HttpServletResponse _response) {

        Reviews _review = null;

        long _reviewId = Long.parseLong(_request.getParameter("review_id"));

        try {
            _review.setDescription(_request.getParameter("description"));
            _review.setSeller_id(Long.parseLong(_request.getParameter("seller_id")));
            _review.setPoint_scale(Double.parseDouble((_request.getParameter("point_scale"))));
            _review.setPost_id(Long.parseLong(_request.getParameter("post_id")));
            _review.setUser_id(Long.parseLong(_request.getParameter("user_id")));
            _review.setCreate_date(new Timestamp(System.currentTimeMillis()));
            _review.setIs_deleted(false);

            JSONObject _result = new JSONObject();

            _result.put("data", new UpdateReviewDao(getConnection()).updateReview(_review, _reviewId));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }

    }
    private void removeReview(HttpServletRequest _request, HttpServletResponse _response) {
        try {
            long _reviewId = Long.parseLong(_request.getParameter("review_id"));

            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);
            JSONObject _result = new JSONObject();

            _result.put("affectedRow", new DeleteReviewDao(getConnection()).deleteReview(_reviewId));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }
    private void addReview(HttpServletRequest _request, HttpServletResponse _response) {

        Reviews _review = null;

        try {
            _review.setDescription(_request.getParameter("description"));
            _review.setSeller_id(Long.parseLong(_request.getParameter("seller_id")));
            _review.setPoint_scale(Double.parseDouble((_request.getParameter("point_scale"))));
            _review.setPost_id(Long.parseLong((_request.getParameter("post_id"))));
            _review.setUser_id(Long.parseLong((_request.getParameter("user_id"))));
            _review.setCreate_date(new Timestamp(System.currentTimeMillis()));
            _review.setIs_deleted(false);

            JSONObject _result = new JSONObject();

            _result.put("data", new AddReviewDao(getConnection()).addReview(_review));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (Exception _e) {
            throw new RuntimeException(_e);
        }
    }

    private void getReviewDetailsByPostId (HttpServletRequest _request, HttpServletResponse _response) {
        try {
            long _id = parseLong(_request.getParameter("post_id"));
            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);

            JSONObject _result = new JSONObject();
            _result.put("data", new GetReviewsByPostIdDao(getConnection()).getReviewsByPostId(_id));

            _response.getWriter().write(_result.toString());

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void getReviewDetailsByUserId (HttpServletRequest _request, HttpServletResponse _response) {
        try {
            long _id = parseLong(_request.getParameter("user_id"));
            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);

            JSONObject _result = new JSONObject();
            _result.put("data", new GetReviewsByUserIdDao(getConnection()).getReviewsByUserId(_id));

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
