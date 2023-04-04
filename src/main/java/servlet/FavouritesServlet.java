package servlet;

import dao.Post.GetPostsByCustomerFavouritesDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import utils.ResourceNotFoundException;

import java.io.IOException;
import java.sql.SQLException;

public class FavouritesServlet extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest _req, HttpServletResponse _resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest _req, HttpServletResponse _resp) throws ServletException, IOException {

    }

    private void getCustomerFavourites(HttpServletRequest _req, HttpServletResponse _resp) throws SQLException, IOException, ResourceNotFoundException {

        try {
            long user_id = Long.parseLong(_req.getParameter("user_id"));

            _resp.setContentType("application/json");
            _resp.setStatus(HttpServletResponse.SC_OK);
            JSONObject _result = new JSONObject();
            _result.put("data",
                    new GetPostsByCustomerFavouritesDao(getConnection()).getPostsByCustomerFavourites(user_id));

            _resp.getWriter().write(_result.toString());

        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
