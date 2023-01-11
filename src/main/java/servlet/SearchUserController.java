package servlet;

import Database.DatabaseConnector;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.User;

import java.io.IOException;

@WebServlet(name = "SearchUserController", value = "/SearchUserController")
public class SearchUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/searchUser.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
        String id = request.getParameter("id");
        DatabaseConnector dbc = new DatabaseConnector();
        User user = dbc.getUserById(id);
        request.setAttribute("user", user);
        doGet(request,response);
    }
}
