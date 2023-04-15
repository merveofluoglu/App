package servlet;

import dao.Category.*;

import dao.Category.GetAllCategoriesDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;
import resource.Category;
import utils.ErrorCode;
import utils.ResourceNotFoundException;

import java.io.IOException;
import java.sql.SQLException;

import static java.lang.Long.parseLong;

@WebServlet(name = "CategoryServlet", value = "/CategoryServlet")
public class CategoryServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        String _op = _request.getRequestURI().split("/", 4)[3].replace("/", "");

        if (_op.contentEquals("details")) {
            getCategoryDetailsOp(_request, _response);
        }
        else {
            getAllCategories(_request, _response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {

        String _op = _request.getRequestURI().split("/", 4)[3];
        System.out.println(_op);
        switch (_op) {
            case "protected/add":
                addCategory(_request, _response);
                break;
            case "update":
                updateCategory(_request, _response);
                break;
            case "protected/delete":
                removeCategory(_request, _response);
                break;
            default:
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);
        }
    }
    private void getAllCategories (HttpServletRequest _req, HttpServletResponse _resp) {
        try {
            JSONObject _result = new JSONObject();

            _result.put("data", new GetAllCategoriesDao(getConnection()).getAllCategories());

            _resp.getWriter().write(_result.toString());
        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

        private void updateCategory (HttpServletRequest _request, HttpServletResponse _response){

            Category _Category = null;

            long _CategoryId = Long.parseLong(_request.getParameter("Category_id"));

            try {
                _Category.setCategory_name(_request.getParameter("name"));
                _Category.setCategory_id(Long.parseLong(_request.getParameter("category_id")));

                JSONObject _result = new JSONObject();

                _result.put("data", new UpdateCategoryDao(getConnection()).UpdateCategoryByIdDao(_Category, _CategoryId));

                _response.getWriter().write(_result.toString());

                //After jsp files prepared, request dispatcher will be implemented!!

            } catch (SQLException _e) {
                throw new RuntimeException(_e);
            } catch (IOException _e) {
                throw new RuntimeException(_e);
            }

        }

        private void removeCategory (HttpServletRequest _request, HttpServletResponse _response){
            try {
                long _CategoryId = Long.parseLong(_request.getParameter("Category_id"));

                _response.setContentType("application/json");
                _response.setStatus(HttpServletResponse.SC_OK);
                JSONObject _result = new JSONObject();

                _result.put("affectedRow", new DeleteCategoryDao(getConnection()).DeleteCategory(_CategoryId));

                _response.getWriter().write(_result.toString());

                //After jsp files prepared, request dispatcher will be implemented!!

            } catch (SQLException _e) {
                throw new RuntimeException(_e);
            } catch (IOException _e) {
                throw new RuntimeException(_e);
            }
        }

        private void addCategory (HttpServletRequest _request, HttpServletResponse _response){

            Category _Category = null;

            try {
                _Category.setCategory_name(_request.getParameter("name"));
                _Category.setCategory_id(Long.parseLong(_request.getParameter("category_id")));

                JSONObject _result = new JSONObject();

                _result.put("data", new CreateCategoryDao(getConnection()).CreateCategory(_Category));

                _response.getWriter().write(_result.toString());

                //After jsp files prepared, request dispatcher will be implemented!!

            } catch (Exception _e) {
                throw new RuntimeException(_e);
            }

        }
        private void getCategoryDetailsOp(HttpServletRequest _request, HttpServletResponse _response){
            try {
                long _id = parseLong(_request.getParameter("Category_id"));
                _response.setContentType("application/json");
                _response.setStatus(HttpServletResponse.SC_OK);

                JSONObject _result = new JSONObject();
                _result.put("data", new GetCategoryByIdDao(getConnection()).getCategoryById(_id));

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