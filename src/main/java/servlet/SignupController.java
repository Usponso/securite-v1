package servlet;

import Database.DatabaseConnector;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.User;

import java.io.IOException;

@WebServlet(name = "SignupController", value = "/SignupController")
public class SignupController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Première connexion</title>");
        out.println("</head>");
        out.println("<body>Mauvaise configuration</body>");
        out.println("<html>");
        */

        getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean error = false;
        String errorType = "";
        int id = 0;
        String name = request.getParameter("name");
        String firstname = request.getParameter("firstname");
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        Integer age = 0;
        try{
            age = Integer.parseInt(request.getParameter("age"));
        } catch(Exception e){
            System.out.println("Error : " + e);
        }

        if(age<0 || age>100){
            error = true;
            errorType = "Age must be a number between 0 and 100.";
        }

        if(error){
            request.setAttribute("errorType", errorType);
            this.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        } else {
            User user = new User(id, name,firstname,age,mail,password);
            DatabaseConnector dbc = new DatabaseConnector();
            dbc.createUser(user);
            doGet(request, response);
        }
    }
}
