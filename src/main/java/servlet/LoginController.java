package servlet;

import Database.DatabaseConnector;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.User;
import utils.IpChecker;

import java.io.IOException;
import java.net.InetAddress;

@WebServlet(name = "LoginController", value = "/LoginController")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
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
        String currentIp = "";

        try {
            currentIp = IpChecker.getIp();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Cookie[] cookies = request.getCookies();
        int attempts = 0;
        boolean check = false;
        if(cookies != null){
            for(Cookie cookie : cookies){
                 if(cookie.getName().equals("ip")){
                    if(cookie.getValue().equals(currentIp)){
                        for(Cookie c : cookies){
                            if(c.getName().equals("attempts")){
                                if(Integer.parseInt(c.getValue()) >= 3){
                                    request.setAttribute("user","");
                                    request.setAttribute("error", "Number of attempts exceeded. Please try again later.");
                                    request.setAttribute("disabled","disabled");
                                    doGet(request, response);
                                }
                                check = true;
                                attempts = Integer.parseInt(c.getValue());
                            }
                        }
                        attempts++;
                    }
                }
            }
        }

        if(!check){
            attempts++;
        }

        //Création d'un cookie
        Cookie login = new Cookie("login",request.getParameter("mail"));
        login.setMaxAge(86400);
        response.addCookie(login);

        Cookie ip = new Cookie("ip", currentIp);
        ip.setMaxAge(86400);
        response.addCookie(ip);

        Cookie attempsCookie = new Cookie("attempts",""+attempts);
        attempsCookie.setMaxAge(300);
        response.addCookie(attempsCookie);

        request.setAttribute("disabled","");

        if(user.getId() == 0){
            request.setAttribute("user","");
            request.setAttribute("error", "No account found for this mail and/or password.");
        } else {
            request.setAttribute("user",user);
        }
        doGet(request, response);
    }
}
