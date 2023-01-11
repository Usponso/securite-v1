package servlet;

import Database.DatabaseConnector;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.User;

import java.io.IOException;
import java.net.InetAddress;

@WebServlet(name = "LoginController", value = "/LoginController")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
//                System.out.println(cookie.getValue());
                if(cookie.getName().equals("login")){
                    String loginCookie = cookie.getValue();
                    request.setAttribute("loginCookie", loginCookie);
                }
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatabaseConnector dbc = new DatabaseConnector();
        User user = dbc.getUserByLogin(request.getParameter("mail"), request.getParameter("password"));
        String currentIp = InetAddress.getLocalHost().getHostAddress();

        //Création d'un cookie
        Cookie login = new Cookie("login",request.getParameter("mail"));
        login.setMaxAge(86400);
        response.addCookie(login);

        Cookie[] cookies = request.getCookies();
        int attempts = 1;
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("attempts")){
                    attempts = Integer.parseInt(cookie.getValue());
                } else if(cookie.getName().equals("ip")){
                    System.out.println("COOKIE IP : " + cookie.getValue());
                    if(cookie.getValue() == currentIp){
                        attempts++;
                    }
                }
            }
        }

        Cookie ip = new Cookie("ip", currentIp);
        ip.setMaxAge(86400);
        response.addCookie(ip);

        Cookie attempsCookie = new Cookie("attempts",""+attempts);
        attempsCookie.setMaxAge(86400);
        response.addCookie(attempsCookie);

        if(user.getId() == 0){
            request.setAttribute("user","");
            request.setAttribute("error", "No account found for this mail and/or password.");
        } else {
            request.setAttribute("user",user);
        }
        doGet(request, response);
    }
}
