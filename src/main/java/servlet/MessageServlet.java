package servlet;

import dao.Message.*;
import dao.Post.GetAllPostsDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import resource.Message;
import utils.ErrorCode;
import utils.ResourceNotFoundException;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import static java.lang.Long.parseLong;

@WebServlet(name = "MessageServlet", value = "/MessageServlet")
public class MessageServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws IOException{
        try{
            String _op = _request.getRequestURI().split("/", 5)[3].replace("/", "");
            if (_op.contentEquals("details")) {
                getMessage(_request, _response);
            } else if (_op.contentEquals("usermessages")) {
                getUserMessages(_request,_response);
            } else{
                getAllMessages(_request, _response);
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {

        String _op = _request.getRequestURI().split("/", 4)[3];
        System.out.println(_op);
        switch (_op) {
            case "add" :
                addMessage(_request, _response);
                break;
            case "update" :
                updateMessage(_request, _response);
                break;
            case "delete" :
                removeMessage(_request, _response);
                break;
            case "read" :
                readMessage(_request, _response);
                break;
            default :
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);
        }
    }

    protected void getAllMessages (HttpServletRequest _request, HttpServletResponse _response) {
        try {
            JSONObject _result = new JSONObject();

            _result.put("data",new GetAllMessagesDao(getConnection()).getAllMessages());

            _response.getWriter().write(_result.toString());

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    protected void getMessage (HttpServletRequest _request, HttpServletResponse _response) {
        try {
            long _id = parseLong(_request.getRequestURI().split("/", 5)[4].replace("/", ""));
            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);

            JSONObject _result = new JSONObject();
            _result.put("data", new GetMessageByIdDao(getConnection()).getMessageById(_id));

            _response.getWriter().write(_result.toString());

        } catch (SQLException | IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    protected void getUserMessages (HttpServletRequest _request, HttpServletResponse _response) {
        try {
            HttpSession _session = _request.getSession();
            long _userId = (long) _session.getAttribute("userId");
            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);

            JSONObject _result = new JSONObject();
            _result.put("data", new CheckMessageParentIdDao(getConnection()).getAllMessagesByUserId(_userId));

            _response.getWriter().write(_result.toString());

        } catch (SQLException | IOException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void addMessage(HttpServletRequest _request, HttpServletResponse _response) {

        Message _message = new Message();

        try {
            _message.setCreatorId(Long.parseLong(_request.getParameter("creatorId")));
            _message.setRecipientId(Long.parseLong(_request.getParameter("recipientId")));
            _message.setParentMessageId(Long.parseLong(_request.getParameter("parentMessageId")));
            _message.setSubject(_request.getParameter("subject"));
            _message.setMessageBody(_request.getParameter("messageBody"));
            _message.setRead(false);
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

        long _messageId = Long.parseLong(_request.getParameter("messageId"));

        try {
            _message.setCreatorId(Long.parseLong(_request.getParameter("creatorId")));
            _message.setRecipientId(Long.parseLong(_request.getParameter("recipientId")));
            _message.setParentMessageId(Long.parseLong(_request.getParameter("parentMessageId")));
            _message.setSubject(_request.getParameter("subject"));
            _message.setMessageBody(_request.getParameter("messageBody"));
            _message.setRead(Boolean.parseBoolean(_request.getParameter("isRead")));

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

    private void removeMessage(HttpServletRequest _request, HttpServletResponse _response) {
        try {
            long _messageId = Long.parseLong(_request.getParameter("messageId"));

            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);
            JSONObject _result = new JSONObject();

            _result.put("affectedRow", new DeleteMessageByIdDao(getConnection()).deleteMessageById(_messageId));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void readMessage(HttpServletRequest _request, HttpServletResponse _response) {
        try {
            long _messageId = Long.parseLong(_request.getParameter("messageId"));

            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);
            JSONObject _result = new JSONObject();

            _result.put("affectedRow", new ReadMessageByIdDao(getConnection()).readMessage(_messageId));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

}
