package servlet;

import dao.Category.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import resource.Category;
import resource.SubCategory;
import utils.ErrorCode;
import utils.ResourceNotFoundException;

import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import static java.lang.Long.parseLong;


@WebServlet(name = "SubCategoryServlet", value = "/SubCategoryServlet")
public class SubCategoryServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        String _op = _request.getRequestURI().split("/", 5)[3].replace("/", "");

        if (_op.contentEquals("details")) {
            getSubCategoriesByCategoryId(_request, _response);
        }
        else {
            getAllSubCategories(_request, _response);
        }

    }


    @Override
    protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {

        String _op = _request.getRequestURI().split("/", 5)[3];
        System.out.println(_op);
        switch (_op) {
            case "add":
                addSubCategory(_request, _response);
                break;
            case "update":
                updateSubCategory(_request, _response);
                break;
            case "delete":
                removeSubCategory(_request, _response);
                break;
            default:
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);
                break;
        }
    }

    private void getAllSubCategories(HttpServletRequest _request, HttpServletResponse _response) {
        try {

            JSONObject _result = new JSONObject();

            _result.put("data", new GetAllSubCategoryDao(getConnection()).getAllSubCategories());

            _response.getWriter().write(_result.toString());

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void updateSubCategory (HttpServletRequest _request, HttpServletResponse _response){

        SubCategory _SubCategory = new SubCategory();

        long _SubCategoryId = parseLong(_request.getParameter("subcategoryId"));

        HttpSession _session = _request.getSession();

        try {

            if(_session.getAttribute("role") != "admin") {
                throw new Exception("You don't have access to this area!");
            }

            _SubCategory.setSubcategoryName(_request.getParameter("subcategoryName"));

            JSONObject _result = new JSONObject();

            _result.put("data", new UpdateSubCategoryDao(getConnection()).updateSubCategory(_SubCategory, _SubCategoryId));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        } catch (Exception _e) {
            throw new RuntimeException(_e.getMessage());
        }

    }

    private void removeSubCategory (HttpServletRequest _request, HttpServletResponse _response){

        HttpSession _session = _request.getSession();

        try {

            if(_session.getAttribute("role") != "admin") {
                throw new Exception("You don't have access to this area!");
            }

            long _SubCategoryId = Long.parseLong(_request.getRequestURI().split("/", 5)[4]);

            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);
            JSONObject _result = new JSONObject();

            _result.put("affectedRow", new DeleteSubCategoryDao(getConnection()).DeleteSubCategory(_SubCategoryId));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        } catch (Exception _e) {
            throw new RuntimeException(_e.getMessage());
        }
    }

    private void addSubCategory (HttpServletRequest _request, HttpServletResponse _response){

        SubCategory _SubCategory = new SubCategory();

        HttpSession _session = _request.getSession();

        try {

            if(_session.getAttribute("role") != "admin") {
                throw new Exception("You don't have access to this area!");
            }

            _SubCategory.setSubcategoryName(_request.getParameter("subcategoryName"));

            var _checkSubCategory = new GetSubCategoryByNameDao(getConnection()).getSubCategoriesByName(_SubCategory.getSubcategoryName());

            if(!_checkSubCategory.isEmpty()) {
                throw new Exception("There is already a subcategory with that name! Please choose another name!");
            }

            _SubCategory.setCategoryId(parseLong(_request.getParameter("categoryId")));

            JSONObject _result = new JSONObject();

            _result.put("data", new CreateSubCategoryDao(getConnection()).CreateSubCategory(_SubCategory));

            _response.getWriter().write(_result.toString());

        } catch (Exception _e) {
            throw new RuntimeException(_e.getMessage());
        }

    }

    private void getSubCategoriesByCategoryId(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        try {
            long _id = Long.parseLong(_request.getRequestURI().split("/", 5)[4]);
            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);

            JSONObject _result = new JSONObject();
            _result.put("data", new GetSubCategoriesByCategoryIdDao(getConnection()).GetSubCategoriesByCategoryId(_id));
            _response.getWriter().write(_result.toString());
        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    /*

    private void getSubCategoriesIdByCategory(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {

        try {
            JSONObject _result = new JSONObject();

            _result.put("data", new GetSubCategoriesByCategoryIdDao(getConnection()).GetSubCategoriesByCategoryId());

            _response.getWriter().write(_result.toString());
        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }

    }

*/


    /*

    private void getSubCategoryDetailsOp(HttpServletRequest _request, HttpServletResponse _response) {
        try {
            long _id = parseLong(_request.getParameter("subcategoryId"));
            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);

            JSONObject _result = new JSONObject();
            _result.put("data", new GetAllSubCategoryDao(getConnection()).getAllSubCategory(_id));

            _response.getWriter().write(_result.toString());

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }*/
}