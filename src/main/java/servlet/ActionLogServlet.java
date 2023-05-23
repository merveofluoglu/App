package servlet;

import dao.ActionLog.AddActionLogDao;
import dao.ActionLog.GetActionLogDao;
import dao.ActionLog.GetSystemLogDao;
import dao.ActionLog.GetUserActionLogDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import resource.ActionLog;
import utils.ErrorCode;
import utils.ResourceNotFoundException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ActionLogServlet extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest _req, HttpServletResponse _resp) throws ServletException, IOException {
        String operation = _req.getRequestURI().split("/", 4)[3].replace("/", "");
        if (operation.contentEquals("getLogs")) {
            try {
                getActionLog(_req, _resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if (operation.contentEquals("getSystemLog")) {
            getSystemLog(_req, _resp);
        }
        else if (operation.contentEquals("getUserLog")) {
            getUserActionLog(_req, _resp);
        }

    }

    private void getActionLog(HttpServletRequest _req, HttpServletResponse _resp) throws SQLException, IOException {
        try {
            _resp.setContentType("application/json");
            _resp.setStatus(HttpServletResponse.SC_OK);

            JSONObject _result = new JSONObject();
            _result.put("data", new GetActionLogDao(getConnection()).getActionLog());

            _resp.getWriter().write(_result.toString());
        } catch (JSONException _e) {
            throw new RuntimeException(_e);
        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void getSystemLog(HttpServletRequest _req, HttpServletResponse _resp) {

        try {
            _resp.setContentType("application/json");
            _resp.setStatus(HttpServletResponse.SC_OK);

            JSONObject _result = new JSONObject();

            _result.put("data", new GetSystemLogDao(getConnection()).getSystemLog());

            _resp.getWriter().write(_result.toString());
        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void getUserActionLog(HttpServletRequest _req, HttpServletResponse _resp) {

        try {
            _resp.setContentType("application/json");
            _resp.setStatus(HttpServletResponse.SC_OK);

            JSONObject _result = new JSONObject();

            _result.put("data", new GetUserActionLogDao(getConnection()).getUserLog());

            _resp.getWriter().write(_result.toString());
        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

}
