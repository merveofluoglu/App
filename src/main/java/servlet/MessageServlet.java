package servlet;

import dao.Message.CreateMessageDao;
import dao.Message.GetMessageByIdDao;
import dao.Message.UpdateMessageByIdDao;
import dao.Post.CreatePostDao;
import dao.Post.DeletePostByIdDao;
import dao.Post.UpdatePostByIdDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import resource.Message;
import resource.Post;
import utils.ErrorCode;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import static java.lang.Long.parseLong;

public class MessageServlet extends AbstractServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        try{
            String _op = req.getRequestURI().split("/", 4)[3].replace("/", "");

            if (_op.contentEquals("details")) {
                getMessage(req, res);
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    protected void getMessage (HttpServletRequest _request, HttpServletResponse _response) {
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

    @Override
    protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {

        String _op = _request.getRequestURI().split("/", 4)[3];
        System.out.println(_op);
        switch (_op) {
            case "protected/add" :
                addMessage(_request, _response);
                break;
            case "update" :
                updateMessage(_request, _response);
                break;
            default :
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);
        }
    }

    private void addMessage(HttpServletRequest _request, HttpServletResponse _response) {

        Message _message = null;

        try {
            _message.setCreatorId(Long.parseLong(_request.getParameter("creator_id")));
            _message.setRecipientId(Long.parseLong(_request.getParameter("recipient_id")));
            _message.setParentMessageId(Long.parseLong(_request.getParameter("parent_message_id")));
            _message.setSubject(_request.getParameter("subject"));
            _message.setMessageBody(_request.getParameter("message_body"));
            _message.setIsRead(false);
            _message.setCreationDate(new Timestamp(System.currentTimeMillis()));
            _message.setExpirationDate(Timestamp.valueOf(_message.getCreationDate().toLocalDateTime().plusDays(15)));

            JSONObject _result = new JSONObject();

            _result.put("data", new CreateMessageDao(getConnection()).createMessage(_message));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (Exception _e) {
            throw new RuntimeException(_e);
        }

    }

    private void updateMessage(HttpServletRequest _request, HttpServletResponse _response){
        Message _message = new Message();

        long _messageId = Long.parseLong(_request.getParameter("message_id"));

        try {
            _message.setCreatorId(Long.parseLong(_request.getParameter("creator_id")));
            _message.setRecipientId(Long.parseLong(_request.getParameter("recipient_id")));
            _message.setParentMessageId(Long.parseLong(_request.getParameter("parent_message_id")));
            _message.setSubject(_request.getParameter("subject"));
            _message.setMessageBody(_request.getParameter("message_body"));
            _message.setIsRead(Boolean.parseBoolean(_request.getParameter("is_read")));

            JSONObject _result = new JSONObject();

            _result.put("data", new UpdateMessageByIdDao(getConnection()).updateMessageById(_message, _messageId));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }


}
