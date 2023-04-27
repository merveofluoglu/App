package servlet;

import dao.Category.GetAllCategoriesDao;
import dao.Favourites.GetFavouritesByPostIdDao;
import dao.Favourites.RemoveFavouriteDao;
import dao.Post.*;
import dao.PostFiles.DeleteFileFromPostDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;
import resource.Favourites;
import resource.Post;
import utils.ErrorCode;
import utils.ResourceNotFoundException;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        long _postId = Long.parseLong(_request.getParameter("post_id"));

        String _role = (String) _session.getAttribute("role");

        if(_role != "admin" && (long)_session.getAttribute("user_id") != Long.parseLong(_request.getParameter("user_id"))) {
            writeError(_response, ErrorCode.METHOD_NOT_ALLOWED);
        }

        try {
            _post.setName(_request.getParameter("name"));
            _post.setDescription(_request.getParameter("description"));
            _post.setUser_id(Long.parseLong(_request.getParameter("user_id")));
            _post.setCustomer_id(0);
            _post.setPrice(Double.parseDouble(_request.getParameter("price")));
            _post.setStatus(_request.getParameter("status"));
            _post.setStart_date(new Timestamp(System.currentTimeMillis()));
            _post.setEnd_date(Timestamp.valueOf(_post.getStart_date().toLocalDateTime().plusDays(15)));
            _post.setIs_deleted(false);
            _post.setIs_sold(false);
            _post.setSold_date(null);
            _post.setUpdate_date(null);
            _post.setCategory_id(0);
            _post.setSubcategory_id(0);

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

            long _userId = (long) _session.getAttribute("user_id");

            String _role = (String) _session.getAttribute("role");

            Post _post = new GetPostByIdDao(getConnection()).getPostById(_postId);

            if(_role != "admin" && _post.getUser_id() != _userId) {
                writeError(_response, ErrorCode.METHOD_NOT_ALLOWED);
            }

            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);
            JSONObject _result = new JSONObject();

            _result.put("affectedRow", new DeletePostByIdDao(getConnection()).deletePost(_postId));

            int _deletedFiles = new DeleteFileFromPostDao(getConnection()).deleteFileFromPost(_postId);

            List<Favourites> _favs = new ArrayList<>();
            _favs = new GetFavouritesByPostIdDao(getConnection()).getFavouritesByPostIdDao(_postId);
            for(int i=0; i<_favs.size();i++){
                int _deletedFavs = new RemoveFavouriteDao(getConnection()).removeFavourite(_favs.get(i).getFavourite_id());
            }

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
            long _customerId = (long) _session.getAttribute("user_id");

            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);
            JSONObject _result = new JSONObject();

            _result.put("affectedRow", new BuyPostByPostIdDao(getConnection()).buyPost(_postId, _customerId));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void myOrders(HttpServletRequest _request, HttpServletResponse _response) {
        try {
            HttpSession _session = _request.getSession();

            long _customerId = (long) _session.getAttribute("user_id");

            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);
            JSONObject _result = new JSONObject();

            _result.put("data", new GetPostsByCustomerIdDao(getConnection()).getPostsByCustomerId(_customerId));

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

    private void myPosts(HttpServletRequest _request, HttpServletResponse _response) {
        try {
            HttpSession _session = _request.getSession();

            long _customerId = (long) _session.getAttribute("user_id");

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
            _post.setUser_id((Long) _session.getAttribute("user_id"));
            _post.setCustomer_id(0);
            _post.setPrice(Double.parseDouble(_request.getParameter("price")));
            _post.setStatus(_request.getParameter("status"));
            _post.setStart_date(new Timestamp(System.currentTimeMillis()));
            _post.setEnd_date(Timestamp.valueOf(_post.getStart_date().toLocalDateTime().plusDays(15)));
            _post.setIs_deleted(false);
            _post.setIs_sold(false);
            _post.setSold_date(null);
            _post.setUpdate_date(null);
            _post.setCategory_id(Long.parseLong(_request.getParameter("category_id")));
            _post.setSubcategory_id(Long.parseLong(_request.getParameter("subcategory_id")));

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
            long _id = parseLong(_request.getParameter("post_id"));
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