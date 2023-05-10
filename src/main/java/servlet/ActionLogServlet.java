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

    @Override
    protected void doPost(HttpServletRequest _req, HttpServletResponse _resp) throws ServletException, IOException {
        String op = _req.getRequestURI().split("/", 5)[3];
        switch (op) {
            // the requested operation is login
            case "add" :
                try {
                    addActionLog(_req,_resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ResourceNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;

            // the requested operation is unknown
            default :
                writeError(_resp, ErrorCode.OPERATION_UNKNOWN);
                break;
        }
    }

    private void addActionLog(HttpServletRequest _req, HttpServletResponse _resp) throws SQLException, IOException, ResourceNotFoundException {

        ActionLog _actionLog = null;

        try {
            _actionLog.setUserAct(Boolean.parseBoolean(_req.getParameter("is_user_act")));
            _actionLog.setSystemAct(Boolean.parseBoolean(_req.getParameter("is_system_act")));
            _actionLog.setDescription(_req.getParameter("description"));
            _actionLog.setActionDate(new Timestamp(System.currentTimeMillis()));
            _actionLog.setUserId(Long.parseLong(_req.getParameter("user_id")));

            JSONObject _result = new JSONObject();

            _result.put("data", new AddActionLogDao(getConnection()).addActionLog(_actionLog));

            _resp.getWriter().write(_result.toString());

        } catch (Exception _e) {
            throw new RuntimeException(_e);
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
