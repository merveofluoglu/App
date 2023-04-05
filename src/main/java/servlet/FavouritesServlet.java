package servlet;

import dao.Favourites.AddFavouriteDao;
import dao.Favourites.RemoveFavouriteDao;
import dao.Post.GetPostsByCustomerFavouritesDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import resource.Favourites;
import utils.ResourceNotFoundException;

import java.io.IOException;
import java.sql.SQLException;

public class FavouritesServlet extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest _req, HttpServletResponse _resp) throws ServletException, IOException {
        // Will be updated!
    }

    @Override
    protected void doPost(HttpServletRequest _req, HttpServletResponse _resp) throws ServletException, IOException {
        // Will be updated!
    }

    private void getCustomerFavourites(HttpServletRequest _req, HttpServletResponse _resp) throws SQLException, IOException, ResourceNotFoundException {

        try {
            long _userId = Long.parseLong(_req.getParameter("user_id"));

            _resp.setContentType("application/json");
            _resp.setStatus(HttpServletResponse.SC_OK);
            JSONObject _result = new JSONObject();
            _result.put("data",
                    new GetPostsByCustomerFavouritesDao(getConnection()).getPostsByCustomerFavourites(_userId));

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
        }

    }

    private void addFavourite(HttpServletRequest _req, HttpServletResponse _resp) {

        Favourites _fav = null;

        try {
            _fav.setUser_id(Long.parseLong(_req.getParameter("user_id")));
            _fav.setPost_id(Long.parseLong(_req.getParameter("post_id")));

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

            _result.put("affectedRow", new RemoveFavouriteDao(getConnection()).removeFavourite(_postId));

            _resp.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }
}
