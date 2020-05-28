package controller;

import model.progs.CheckLogin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

//        HttpSession session = request.getSession();
//        session.setAttribute("username", username);
//        session.setAttribute("password", password);
//
//        getServletContext().getRequestDispatcher("/checkLogin").forward(request, response);

        if (CheckLogin.check(username, password)) {
            getServletContext().getRequestDispatcher("/show_continents").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/login_error.jsp").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
}
