package servlet;

import dao.PostFiles.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;
import resource.Post;
import resource.PostFiles;
import utils.ErrorCode;
import utils.ResourceNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

@MultipartConfig
@WebServlet(name = "PostFiles", value = "/PostFiles")
public class PostFilesServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        String _op = _request.getRequestURI().split("/", 5)[3];
        System.out.println(_op);
        switch (_op) {
            case "getAllPostFiles" :
                getAllPostFiles(_request, _response);
                break;
            case "getPostFilesOp" :
                getPostFilesOp(_request, _response);
                break;
            case "loadPostFile" :
                loadPostFile(_request, _response);
                break;
            case "postList" :
                try {
                    postList(_request, _response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default :
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);
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
            case "upload" :
                uploadPostFile(_request, _response);
                break;
            case "update" :
                updatePostFile(_request, _response);
                break;
            case "delete" :
                deletePostFile(_request, _response);
                break;
            case "getPostList" :
                try {
                    getPostList(_request, _response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default :
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);
        }
    }

    private void getAllPostFiles (HttpServletRequest _request, HttpServletResponse _response) {

        List<PostFiles> postFiles= new ArrayList<>();
        _request.setAttribute("postFiles", postFiles);

        try {
            JSONObject _result = new JSONObject();

            _result.put("data",new GetPostFilesByIdDao(getConnection()).getPostFilesById());
            List<PostFiles> files = new GetPostFilesByIdDao(getConnection()).getPostFilesById();
            byte[] data = files.get(0).getFile();

            String str = new String(data,StandardCharsets.UTF_8.toString().trim());

            _request.getSession().setAttribute("str",str);
            _request.getRequestDispatcher("/jsp/uploadFile-result.jsp").forward(_request, _response);


        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        }catch( Exception e){
            e.printStackTrace();
        }
    }

    private void updatePostFile(HttpServletRequest _request, HttpServletResponse _response) {

        PostFiles _postFiles = new PostFiles();

        long _fileId = Long.parseLong(_request.getParameter("file_id"));

        try {
            _postFiles.setFile_id(Long.parseLong(_request.getParameter("file_id")));
            _postFiles.setPost_id(Long.parseLong(_request.getParameter("post_id")));
            _postFiles.setFile(_request.getParameter("file").getBytes());
            _postFiles.setIs_deleted(false);
            _postFiles.setFile_media_type(_request.getParameter("file_media_type"));

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
            _postFiles.setFile_media_type(_request.getParameter("file_media_type"));

            JSONObject _result = new JSONObject();

            _result.put("data", new AddFileToPostDao(getConnection()).addFileToPost(_postFiles));

            _response.getWriter().write(_result.toString());

            //_request.getRequestDispatcher("/jsp/upload-file.jsp").forward(_request, _response);

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (Exception _e) {
            throw new RuntimeException(_e);
        }

    }

    private void postList(HttpServletRequest _request, HttpServletResponse _response) throws SQLException {

        PostListDao dao = (PostListDao) new PostListDao(getConnection()).postList();

        try {

            List<Post> listPost = dao.postList();
            _request.setAttribute("listPost", listPost);

            RequestDispatcher dispatcher = _request.getRequestDispatcher("upload-file.jsp");
            dispatcher.forward(_request, _response);

        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void getPostList(HttpServletRequest _request, HttpServletResponse _response) throws SQLException {

        long post_id = Long.parseLong(_request.getParameter("name"));

        _request.setAttribute("selectedPostId", post_id);

        postList(_request, _response);

    }

    private void uploadPostFile(HttpServletRequest _request, HttpServletResponse _response) {

        PostFiles _postFiles = null;

        try {
            _postFiles = parseRequest(_request);
            new AddFileToPostDao(getConnection()).addFileToPost(_postFiles);
        } catch (SQLException | ServletException | IOException e) {
            throw new RuntimeException(e);
        }

        try {
            _request.setAttribute("postFiles", _postFiles);

            _request.getRequestDispatcher("/jsp/uploadFile-result.jsp").forward(_request, _response);

        } catch (Exception _e) {
            throw new RuntimeException(_e);
        }

    }

    private PostFiles parseRequest(HttpServletRequest _request) throws ServletException, IOException {
        long file_id = -1;
        long post_id = -1;
        byte[] file = null;
        boolean is_deleted = false;
        String file_media_type = null;

        for (Part p : _request.getParts()) {
            switch (p.getName()) {
                case "file_id":
                    try (InputStream is = p.getInputStream()) {
                        file_id = Long.parseLong(new String(is.readAllBytes(),
                                StandardCharsets.UTF_8.toString().trim()));
                    }
                    break;
                case "post_id":
                    try (InputStream is = p.getInputStream()) {
                        post_id = Long.parseLong(new String(is.readAllBytes(),
                                StandardCharsets.UTF_8.toString().trim()));
                    }
                    break;
                case "is_deleted":
                    try (InputStream is = p.getInputStream()) {
                        is_deleted = Boolean.parseBoolean(new String(is.readAllBytes(),
                                StandardCharsets.UTF_8.toString().trim()));
                    }
                    break;
                case "file":
                    file_media_type = p.getContentType();

                    switch (file_media_type.toLowerCase().trim()) {

                        case "image/png":
                        case "image/jpeg":
                        case "image/jpg":
                            break;

                        default:
                            LOGGER.error("Unsupported type for file.");
                    }

                    try (InputStream is = p.getInputStream()) {
                        file = is.readAllBytes();
                    }
                    break;
            }
        }
        return new PostFiles(file_id, post_id, file, is_deleted, file_media_type);
    }

    private void loadPostFile (HttpServletRequest _request, HttpServletResponse _response) {

        long file_id = Long.parseLong(_request.getParameter("file_id"));
        PostFiles postFiles = null;

        try {
            file_id = Long.parseLong(_request.getParameter("file_id"));
            postFiles = (PostFiles) new LoadPostFileDao(getConnection(), file_id).access().getOutputParam();

            if(postFiles.hasFile()) {
                _response.setContentType(postFiles.getFile_media_type());
                _response.getOutputStream().write(postFiles.getFile());
                _response.getOutputStream().flush();
            }



        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
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
