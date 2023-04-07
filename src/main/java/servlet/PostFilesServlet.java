package servlet;

import dao.PostFiles.AddFileToPostDao;
import dao.PostFiles.UpdateFilesByPostIdDao;
import dao.PostFiles.DeleteFileFromPostDao;
import dao.PostFiles.GetPostFilesByPostIdDao;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;
import resource.PostFiles;
import utils.ErrorCode;
import utils.ResourceNotFoundException;

import java.io.IOException;
import java.sql.SQLException;

import static java.lang.Long.parseLong;

@WebServlet(name = "PostFilesServlet", value = "/PostFilesServlet")
public class PostFilesServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        String _op = _request.getRequestURI().split("/", 4)[3].replace("/", "");

        if (_op.contentEquals("postFiles")) {
            getPostFilesOp(_request, _response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        String _op = _request.getRequestURI().split("/", 4)[3];
        System.out.println(_op);
        switch (_op) {
            case "protected/add" :
                addPostFile(_request, _response);
                break;
            case "update" :
                updatePostFile(_request, _response);
                break;
            case "protected/delete" :
                deletePostFile(_request, _response);
                break;
            default :
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);
        }
    }

    private void updatePostFile(HttpServletRequest _request, HttpServletResponse _response) {

        PostFiles _postFiles = null;

        long _postId = Long.parseLong(_request.getParameter("post_id"));

        try {
            _postFiles.setFile_id(Long.parseLong(_request.getParameter("file_id")));
            _postFiles.setFile_type(_request.getParameter("file_type"));
            _postFiles.setFile_size(Double.parseDouble(_request.getParameter("file_size")));
            _postFiles.setFile_path(_request.getParameter("file_path"));

            JSONObject _result = new JSONObject();

            _result.put("data", new UpdateFilesByPostIdDao(getConnection()).updateFilesByPostId(_postFiles, _postId));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }

    }

    private void deletePostFile(HttpServletRequest _request, HttpServletResponse _response) {
        try {
            long _fileId = Long.parseLong(_request.getParameter("file_id"));

            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);
            JSONObject _result = new JSONObject();

            _result.put("affectedRow", new DeleteFileFromPostDao(getConnection()).deleteFileFromPost(_fileId));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void addPostFile(HttpServletRequest _request, HttpServletResponse _response) {

        PostFiles _postFiles = null;

        try {
            _postFiles.setPost_id(Long.parseLong(_request.getParameter("post_id")));
            _postFiles.setFile_type(_request.getParameter("file_type"));
            _postFiles.setFile_size(Double.parseDouble(_request.getParameter("file_size")));
            _postFiles.setFile_path(_request.getParameter("file_path"));

            JSONObject _result = new JSONObject();

            _result.put("data", new AddFileToPostDao(getConnection()).addFileToPost(_postFiles));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (Exception _e) {
            throw new RuntimeException(_e);
        }

    }

    private void getPostFilesOp (HttpServletRequest _request, HttpServletResponse _response) {

        try {
            Long _id = parseLong(_request.getParameter("post_id"));
            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);

            JSONObject _result = new JSONObject();
            _result.put("data", new GetPostFilesByPostIdDao(getConnection()).getPostFilesByPostId(_id));

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
