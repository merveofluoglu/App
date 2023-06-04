package servlet;

import dao.ActionLog.AddActionLogDao;
import dao.Favourites.AddFavouriteDao;
import dao.Favourites.GetFavouritesByUserIdDao;
import dao.Favourites.RemoveFavouriteDao;
import dao.Favourites.RemoveFavouritesByPostIdDao;
import dao.Post.GetPostByIdDao;
import dao.Post.GetPostsByCustomerFavouritesDao;
import dao.PostFiles.GetPostFileByPostIdDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;
import resource.ActionLog;
import resource.Favourites;
import resource.Post;
import utils.ErrorCode;
import utils.ResourceNotFoundException;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class FavouritesServlet extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest _req, HttpServletResponse _resp) throws ServletException, IOException {
        try {
            getCustomerFavourites(_req, _resp);
        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest _req, HttpServletResponse _resp) throws ServletException, IOException {

        String _op = _req.getRequestURI().split("/", 5)[3];
        System.out.println(_op);
        switch (_op) {
            case "add" :
                addFavourite(_req, _resp);
                break;
            case "remove" :
                removeFavourite(_req, _resp);
                break;
            case "removeByUser" :
                removeFavouriteByUserId(_req, _resp);
                break;
            default :
                writeError(_resp, ErrorCode.OPERATION_UNKNOWN);
        }
    }

    private void getCustomerFavourites(HttpServletRequest _req, HttpServletResponse _resp) throws SQLException, IOException, ResourceNotFoundException {

        try {

            HttpSession _session = _req.getSession();
            long _userId = (long) _session.getAttribute("userId");

            List<Post> _posts = new ArrayList<>();

            _resp.setContentType("application/json");
            _resp.setStatus(HttpServletResponse.SC_OK);
            JSONObject _result = new JSONObject();

            var _favs = new GetFavouritesByUserIdDao(getConnection()).getFavouritesByUserIdDao(_userId);

            for(int i=0;i<_favs.size();i++) {

                var _post = new GetPostByIdDao(getConnection()).getPostById(_favs.get(i).getPostId());

                var _postFile = new GetPostFileByPostIdDao(getConnection()).getPostFileByPostId(_post.getPostId());

                if(_postFile != null) {

                    String encoded = Base64.getEncoder().encodeToString(_postFile.getFile());

                    _post.setBase64(encoded);
                }

                _posts.add(_post);
            }
            _result.put("data", _posts);

            new AddActionLogDao(getConnection()).addActionLog(new ActionLog(true, false, "User favourites fetched!", new Timestamp(System.currentTimeMillis()), (Long) _session.getAttribute("userId")));

            _resp.getWriter().write(_result.toString());

        } catch (NumberFormatException _e) {
            throw new RuntimeException(_e);
        } catch (JSONException _e) {
            throw new RuntimeException(_e);
        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void addFavourite(HttpServletRequest _req, HttpServletResponse _resp) {

        Favourites _fav = new Favourites();

        try {

            HttpSession _session = _req.getSession();

            long _postId = Long.parseLong(_req.getRequestURI().split("/", 5)[4]);
            long _userId = (long) _session.getAttribute("userId");

            _fav.setUserId(_userId);
            _fav.setPostId(_postId);

            JSONObject _result = new JSONObject();

            _result.put("data", new AddFavouriteDao(getConnection()).addFavourite(_fav));

            new AddActionLogDao(getConnection()).addActionLog(new ActionLog(true, false, "Post with "+ _postId + " post id have been added to the favourites!", new Timestamp(System.currentTimeMillis()), (Long) _session.getAttribute("userId")));

            _resp.getWriter().write(_result.toString());

        } catch (NumberFormatException _e) {
            throw new RuntimeException(_e);
        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }

    }

    private void removeFavourite(HttpServletRequest _req, HttpServletResponse _resp) {
        try {
            HttpSession _session = _req.getSession();

            long _postId = Long.parseLong(_req.getRequestURI().split("/", 5)[4]);

            _resp.setContentType("application/json");
            _resp.setStatus(HttpServletResponse.SC_OK);

            JSONObject _result = new JSONObject();

            _result.put("affectedRow", new RemoveFavouritesByPostIdDao(getConnection()).removeFavouritesByPostIdDao(_postId));

            new AddActionLogDao(getConnection()).addActionLog(new ActionLog(true, false, "Post with " + _postId + " has been removed from favourites!", new Timestamp(System.currentTimeMillis()), (Long) _session.getAttribute("userId")));

            _resp.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void removeFavouriteByUserId(HttpServletRequest _req, HttpServletResponse _resp) {
        try {
            HttpSession _session = _req.getSession();

            long _postId = Long.parseLong(_req.getRequestURI().split("/", 5)[4]);

            _resp.setContentType("application/json");
            _resp.setStatus(HttpServletResponse.SC_OK);

            JSONObject _result = new JSONObject();

            _result.put("affectedRow", new RemoveFavouriteDao(getConnection()).removeFavourite(_postId, (Long) _session.getAttribute("userId")));

            new AddActionLogDao(getConnection()).addActionLog(new ActionLog(true, false, "Post with " + _postId + " has been removed from favourites!", new Timestamp(System.currentTimeMillis()), (Long) _session.getAttribute("userId")));

            _resp.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }
}
