package servlet;

import dao.Favourites.AddFavouriteDao;
import dao.Favourites.RemoveFavouriteDao;
import dao.Favourites.RemoveFavouritesByPostIdDao;
import dao.Post.GetPostsByCustomerFavouritesDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;
import resource.Favourites;
import utils.ErrorCode;
import utils.ResourceNotFoundException;

import java.io.IOException;
import java.sql.SQLException;

public class FavouritesServlet extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest _req, HttpServletResponse _resp) throws ServletException, IOException {
        try {
            getCustomerFavourites(_req, _resp);
        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest _req, HttpServletResponse _resp) throws ServletException, IOException {
        // Will be updated!

        String _op = _req.getRequestURI().split("/", 5)[3];
        System.out.println(_op);
        switch (_op) {
            case "add" :
                addFavourite(_req, _resp);
                break;
            case "remove" :
                removeFavourite(_req, _resp);
                break;
            default :
                writeError(_resp, ErrorCode.OPERATION_UNKNOWN);
        }
    }

    private void getCustomerFavourites(HttpServletRequest _req, HttpServletResponse _resp) throws SQLException, IOException, ResourceNotFoundException {

        try {

            HttpSession _session = _req.getSession();
            long _userId = (long) _session.getAttribute("user_id");

            _resp.setContentType("application/json");
            _resp.setStatus(HttpServletResponse.SC_OK);
            JSONObject _result = new JSONObject();

            var data = new GetPostsByCustomerFavouritesDao(getConnection()).getPostsByCustomerFavourites(_userId);
            _result.put("data", data);

            _req.setAttribute("favourites", data);

            _req.getServletContext().getRequestDispatcher("/jsp/get_favourites.jsp").forward(_req,_resp);

            _resp.getWriter().write(_result.toString());

        } catch (NumberFormatException _e) {
            throw new RuntimeException(_e);
        } catch (JSONException _e) {
            throw new RuntimeException(_e);
        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        } catch (ServletException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void addFavourite(HttpServletRequest _req, HttpServletResponse _resp) {

        Favourites _fav = new Favourites();

        try {

            HttpSession _session = _req.getSession();

            long _postId = Long.parseLong(_req.getRequestURI().split("/", 5)[4]);
            long _userId = (long) _session.getAttribute("user_id");

            _fav.setUser_id(_userId);
            _fav.setPost_id(_postId);

            JSONObject _result = new JSONObject();

            _result.put("data", new AddFavouriteDao(getConnection()).addFavourite(_fav));

            _resp.getWriter().write(_result.toString());

        } catch (NumberFormatException _e) {
            throw new RuntimeException(_e);
        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }

    }

    private void removeFavourite(HttpServletRequest _req, HttpServletResponse _resp) {
        try {
            long _postId = Long.parseLong(_req.getParameter("post_id"));

            _resp.setContentType("application/json");
            _resp.setStatus(HttpServletResponse.SC_OK);
            JSONObject _result = new JSONObject();

            _result.put("affectedRow", new RemoveFavouritesByPostIdDao(getConnection()).removeFavouritesByPostIdDao(_postId));

            _resp.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }
}
