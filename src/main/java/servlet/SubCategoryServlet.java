package servlet;

import dao.Category.CreateSubCategoryDao;
import dao.Category.DeleteSubCategoryDao;
import dao.Category.GetAllSubCategoryDao;
import dao.Category.UpdateSubCategoryDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;
import resource.SubCategory;
import utils.ErrorCode;
import utils.ResourceNotFoundException;

import java.io.IOException;
import java.sql.SQLException;

import static java.lang.Long.parseLong;


@WebServlet(name = "SubCategoryServlet", value = "/SubCategoryServlet")
public class SubCategoryServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        String _op = _request.getRequestURI().split("/", 4)[3].replace("/", "");

       /* if (_op.contentEquals("details")) {
            getSubCategoryDetailsOp(_request, _response);
        }*/
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
                updateSubCategory(_request, _response);
                break;
            case "protected/delete":
                removeSubCategory(_request, _response);
                break;
            default:
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);
        }
    }

    private void updateSubCategory (HttpServletRequest _request, HttpServletResponse _response){

        SubCategory _SubCategory = null;

        long _SubCategoryId = parseLong(_request.getParameter("subcategory_id"));

        try {
            _SubCategory.setSubCategory_name(_request.getParameter("subcategory_name"));
            _SubCategory.setSubCategory_id(parseLong(_request.getParameter("subcategory_id")));

            JSONObject _result = new JSONObject();

            _result.put("data", new UpdateSubCategoryDao(getConnection()).updateSubCategory(_SubCategory, _SubCategoryId));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }

    }

    private void removeSubCategory (HttpServletRequest _request, HttpServletResponse _response){
        try {
            long _SubCategoryId = parseLong(_request.getParameter("subcategory_id"));

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
        }
    }

    private void addCategory (HttpServletRequest _request, HttpServletResponse _response){

        SubCategory _SubCategory = null;

        try {
            _SubCategory.setSubCategory_name(_request.getParameter("subcategory_name"));
            _SubCategory.setSubCategory_id(parseLong(_request.getParameter("subcategory_id")));

            JSONObject _result = new JSONObject();

            _result.put("data", new CreateSubCategoryDao(getConnection()).CreateSubCategory(_SubCategory));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (Exception _e) {
            throw new RuntimeException(_e);
        }

    }/*
    private void getSubCategoryDetailsOp(HttpServletRequest _request, HttpServletResponse _response) {
        try {
            long _id = parseLong(_request.getParameter("subcategory_id"));
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