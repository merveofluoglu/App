package servlet;

import dao.ActionLog.GetActionLogDao;
import dao.Post.GetPostsByCustomerFavouritesDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import utils.ResourceNotFoundException;

import java.io.IOException;
import java.sql.SQLException;

public class ActionLogServlet extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest _req, HttpServletResponse _resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest _req, HttpServletResponse _resp) throws ServletException, IOException {
    }

    private void getCustomerFavourites(HttpServletRequest _req, HttpServletResponse _resp) throws SQLException, IOException, ResourceNotFoundException {

        try {
            _resp.setContentType("application/json");
            _resp.setStatus(HttpServletResponse.SC_OK);
            JSONObject _result = new JSONObject();
            _result.put("data",
                    new GetActionLogDao(getConnection()).getActionLog());

            _resp.getWriter().write(_result.toString());

        } catch (NumberFormatException _e) {
            throw new RuntimeException(_e);
        } catch (JSONException _e) {
            throw new RuntimeException(_e);
        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        }  catch (IOException _e) {
            throw new RuntimeException(_e);
        }

    }
}
