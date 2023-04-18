package servlet;

import dao.Message.GetMessageByIdDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import java.io.IOException;
import java.sql.SQLException;
import static java.lang.Long.parseLong;

public class MessageServlet extends AbstractServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        try{
            String _op = req.getRequestURI().split("/", 4)[3].replace("/", "");

            if (_op.contentEquals("details")) {
                getMessage(req, res);
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private void getMessage (HttpServletRequest _request, HttpServletResponse _response) {
        try {
            long _id = parseLong(_request.getParameter("message_id"));
            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);

            JSONObject _result = new JSONObject();
            _result.put("data", new GetMessageByIdDao(getConnection()).getMessageById(_id));

            _response.getWriter().write(_result.toString());

        } catch (SQLException | IOException _e) {
            throw new RuntimeException(_e);
        }
    }
}
