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
        String _op = _request.getRequestURI().split("/", 5)[3].replace("/", "");

        if (_op.contentEquals("detailsByUserId")) {
            getReviewDetailsByPostId(_request, _response);
        }
        else {
            getReviewDetailsByUserId(_request, _response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {

        String _op = _request.getRequestURI().split("/", 5)[3];
        System.out.println(_op);
        switch (_op) {
            case "add" :
                addReview(_request, _response);
                break;
            case "update" :
                updateReview(_request, _response);
                break;
            case "delete" :
                removeReview(_request, _response);
                break;
            default :
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);
        }
    }
    private void updateReview(HttpServletRequest _request, HttpServletResponse _response) {

        Reviews _review = null;

        long _reviewId = Long.parseLong(_request.getParameter("reviewId"));

        try {
            _review.setDescription(_request.getParameter("description"));
            _review.setSellerId(Long.parseLong(_request.getParameter("sellerId")));
            _review.setPointScale(Double.parseDouble((_request.getParameter("pointScale"))));
            _review.setPostId(Long.parseLong(_request.getParameter("postId")));
            _review.setUserId(Long.parseLong(_request.getParameter("userId")));
            _review.setCreateDate(new Timestamp(System.currentTimeMillis()));
            _review.setDeleted(false);

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
            long _reviewId = Long.parseLong(_request.getParameter("reviewId"));

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

        Reviews _review = new Reviews();

        try {
            _review.setDescription(_request.getParameter("description"));
            _review.setSellerId(Long.parseLong(_request.getParameter("sellerId")));
            _review.setPointScale(Double.parseDouble((_request.getParameter("pointScale"))));
            _review.setPostId(Long.parseLong((_request.getParameter("postId"))));
            _review.setUserId(Long.parseLong((_request.getParameter("userId"))));
            _review.setCreateDate(new Timestamp(System.currentTimeMillis()));
            _review.setDeleted(false);

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
            long _id = parseLong(_request.getParameter("userId"));
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
            HttpSession _session = _request.getSession();

            long _userId = Long.parseLong(_request.getRequestURI().split("/", 5)[4].replace("/", ""));
            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);

            JSONObject _result = new JSONObject();
            _result.put("data", new GetReviewsByUserIdDao(getConnection()).getReviewsByUserId(_userId));

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
