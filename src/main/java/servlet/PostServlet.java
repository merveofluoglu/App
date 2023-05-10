package servlet;

import dao.Favourites.GetFavouritesByPostIdDao;
import dao.Favourites.RemoveFavouriteDao;
import dao.Post.*;
import dao.PostFiles.DeletePostFilesByPostIdDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;
import resource.Favourites;
import resource.Post;
import utils.ErrorCode;
import utils.ResourceNotFoundException;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

@WebServlet(name = "PostServlet", value = "/PostServlet")
public class PostServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        String _op = _request.getRequestURI().split("/", 4)[3].replace("/", "");

        if (_op.contentEquals("details")) {
            getPostDetailsOp(_request, _response);
        } else if (_op.contentEquals("myorders")) {
            myOrders(_request,_response);
        } else if (_op.contentEquals("myposts")){
            myPosts(_request,_response);
        } else {
            getAllPosts(_request, _response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {

        String _op = _request.getRequestURI().split("/", 5)[3];
        System.out.println(_op);
        switch (_op) {
            case "add" :
                addPost(_request, _response);
                break;
            case "update" :
                updatePost(_request, _response);
                break;
            case "delete" :
                removePost(_request, _response);
                break;
            case "buy" :
                buyPost(_request, _response);
                break;
            default :
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);
        }
    }

    private void getAllPosts (HttpServletRequest _req, HttpServletResponse _resp) {
        try {
            JSONObject _result = new JSONObject();

            _result.put("data",new GetAllPostsDao(getConnection()).getAllPosts());

            _resp.getWriter().write(_result.toString());

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void updatePost(HttpServletRequest _request, HttpServletResponse _response) throws IOException {

        Post _post = new Post();

        HttpSession _session = _request.getSession();

        long _postId = Long.parseLong(_request.getParameter("postId"));

        String _role = (String) _session.getAttribute("role");

        if(_role != "admin" && (long)_session.getAttribute("userId") != Long.parseLong(_request.getParameter("userId"))) {
            writeError(_response, ErrorCode.METHOD_NOT_ALLOWED);
        }

        try {
            _post.setName(_request.getParameter("name"));
            _post.setDescription(_request.getParameter("description"));
            _post.setUserId(Long.parseLong(_request.getParameter("userId")));
            _post.setCustomerId(0);
            _post.setPrice(Double.parseDouble(_request.getParameter("price")));
            _post.setStatus(_request.getParameter("status"));
            _post.setStartDate(new Timestamp(System.currentTimeMillis()));
            _post.setEndDate(Timestamp.valueOf(_post.getStartDate().toLocalDateTime().plusDays(15)));
            _post.setDeleted(false);
            _post.setSold(false);
            _post.setSoldDate(null);
            _post.setUpdateDate(null);
            _post.setCategoryId(0);
            _post.setSubcategoryId(0);

            JSONObject _result = new JSONObject();

            _result.put("data", new UpdatePostByIdDao(getConnection()).updatePostById(_post, _postId));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }

    }

    private void removePost(HttpServletRequest _request, HttpServletResponse _response) {
        try {
            long _postId = Long.parseLong(_request.getRequestURI().split("/", 5)[4]);

            HttpSession _session = _request.getSession();

            long _userId = (long) _session.getAttribute("userId");

            String _role = (String) _session.getAttribute("role");

            Post _post = new GetPostByIdDao(getConnection()).getPostById(_postId);

            if(_role != "admin" && _post.getUserId() != _userId) {
                writeError(_response, ErrorCode.METHOD_NOT_ALLOWED);
            }

            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);
            JSONObject _result = new JSONObject();

            int _deletedFiles = new DeletePostFilesByPostIdDao(getConnection()).deletePostFilesByPostIdDao(_postId);

            List<Favourites> _favs = new ArrayList<>();
            _favs = new GetFavouritesByPostIdDao(getConnection()).getFavouritesByPostIdDao(_postId);
            for(int i=0; i<_favs.size();i++){
                int _deletedFavs = new RemoveFavouriteDao(getConnection()).removeFavourite(_favs.get(i).getFavouriteId());
            }

            _result.put("affectedRow", new DeletePostByIdDao(getConnection()).deletePost(_postId));

            _response.getWriter().write(_result.toString());

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void buyPost(HttpServletRequest _request, HttpServletResponse _response) {
        try {
            HttpSession _session = _request.getSession();

            long _postId = Long.parseLong(_request.getRequestURI().split("/", 5)[4]);
            long _customerId = (long) _session.getAttribute("userId");

            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);
            JSONObject _result = new JSONObject();

            _result.put("affectedRow", new BuyPostByPostIdDao(getConnection()).buyPost(_postId, _customerId));

            _response.getWriter().write(_result.toString());


        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void myOrders(HttpServletRequest _request, HttpServletResponse _response) {
        try {
            HttpSession _session = _request.getSession();

            long _customerId = (long) _session.getAttribute("userId");

            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);
            JSONObject _result = new JSONObject();

            _result.put("data", new GetPostsByCustomerIdDao(getConnection()).getPostsByCustomerId(_customerId));

            _response.getWriter().write(_result.toString());

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void myPosts(HttpServletRequest _request, HttpServletResponse _response) {
        try {
            HttpSession _session = _request.getSession();

            long _customerId = (long) _session.getAttribute("userId");

            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);
            JSONObject _result = new JSONObject();

            _result.put("data", new GetPostsByUserIdDao(getConnection()).getPostsByUserId(_customerId));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void addPost(HttpServletRequest _request, HttpServletResponse _response) {

        Post _post = new Post();
        HttpSession _session = _request.getSession();

        try {
            _post.setName(_request.getParameter("name"));
            _post.setDescription(_request.getParameter("description"));
            _post.setUserId((Long) _session.getAttribute("userId"));
            _post.setCustomerId(0);
            _post.setPrice(Double.parseDouble(_request.getParameter("price")));
            _post.setStatus(_request.getParameter("status"));
            _post.setStartDate(new Timestamp(System.currentTimeMillis()));
            _post.setEndDate(Timestamp.valueOf(_post.getStartDate().toLocalDateTime().plusDays(15)));
            _post.setDeleted(false);
            _post.setSold(false);
            _post.setSoldDate(null);
            _post.setUpdateDate(null);
            _post.setCategoryId(Long.parseLong(_request.getParameter("categoryId")));
            _post.setSubcategoryId(Long.parseLong(_request.getParameter("subcategoryId")));

            JSONObject _result = new JSONObject();

            _result.put("data", new CreatePostDao(getConnection()).createPost(_post));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (Exception _e) {
            throw new RuntimeException(_e);
        }

    }

    private void getPostDetailsOp (HttpServletRequest _request, HttpServletResponse _response) {
        try {
            long _id = parseLong(_request.getParameter("postId"));
            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);

            JSONObject _result = new JSONObject();
            _result.put("data", new GetPostByIdDao(getConnection()).getPostById(_id));

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