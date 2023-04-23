package servlet;

import dao.PostFiles.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;
import resource.PostFiles;
import utils.ErrorCode;
import utils.ResourceNotFoundException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

@WebServlet(name = "PostFilesServlet", value = "/PostFilesServlet")
public class PostFilesServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        String _op = _request.getRequestURI().split("/", 4)[3].replace("/", "");

        if (_op.contentEquals("postFiles")) {
            getPostFilesOp(_request, _response);
        }
        else {
            getAllPostFiles(_request, _response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        String _op = _request.getRequestURI().split("/", 5)[3];
        System.out.println(_op);
        switch (_op) {
            case "add" :
                addPostFile(_request, _response);
                break;
            case "update" :
                updatePostFile(_request, _response);
                break;
            case "delete" :
                deletePostFile(_request, _response);
                break;
            default :
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);
        }
    }

    private void getAllPostFiles (HttpServletRequest _request, HttpServletResponse _response) {

        List<PostFiles> postFiles = new ArrayList<>();
        _request.setAttribute("postFiles", postFiles);

        try {
            JSONObject _result = new JSONObject();

            _result.put("data",new GetPostFilesByIdDao(getConnection()).getPostFilesById());

            _response.getWriter().write(_result.toString());

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void updatePostFile(HttpServletRequest _request, HttpServletResponse _response) {

        PostFiles _postFiles = new PostFiles();

        long _fileId = Long.parseLong(_request.getParameter("file_id"));

        try {
            _postFiles.setPost_id(Long.parseLong(_request.getParameter("post_id")));
            _postFiles.setFile(_request.getParameter("file").getBytes());
            _postFiles.setIs_deleted(false);

            JSONObject _result = new JSONObject();

            _result.put("data", new UpdateFilesByPostIdDao(getConnection()).updateFilesByPostId(_postFiles, _fileId));

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
            long _fileId = Long.parseLong(_request.getRequestURI().split("/", 5)[4]);

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

        PostFiles _postFiles = new PostFiles();

        try {
            _postFiles.setPost_id(Long.parseLong(_request.getParameter("post_id")));
            _postFiles.setFile(_request.getParameter("file").getBytes());
            _postFiles.setIs_deleted(false);

            JSONObject _result = new JSONObject();

            _result.put("data", new AddFileToPostDao(getConnection()).addFileToPost(_postFiles));

            _response.getWriter().write(_result.toString());

            _request.getRequestDispatcher("/jsp/upload-file.jsp").forward(_request, _response);

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (Exception _e) {
            throw new RuntimeException(_e);
        }

    }

    private void getPostFilesOp (HttpServletRequest _request, HttpServletResponse _response) {

        try {
            long _id = parseLong(_request.getParameter("file_id"));
            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);

            JSONObject _result = new JSONObject();
            _result.put("data", new GetPostFileByPostIdDao(getConnection()).getPostFileByPostId(_id));

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
