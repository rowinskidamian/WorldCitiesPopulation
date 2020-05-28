package controller;

import db.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

@WebServlet("/show_continents")
public class ShowContinentsServlet extends HttpServlet {

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//
//        try {
//            Set<String> continentsSet = getContinentsSet();
//            session.setAttribute("continentsSet", continentsSet);
//            request.getServletContext().getRequestDispatcher("/show_continents.jsp").forward(request, response);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        try {
            Set<String> continentsSet = getContinentsSet();
            session.setAttribute("continentsSet", continentsSet);
            req.getServletContext().getRequestDispatcher("/show_continents.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Set<String> getContinentsSet() throws SQLException {
        Set<String> setOfContinents = new TreeSet<>();
        final String sqlQuery ="SELECT Continent FROM Country";

        try(Connection conn = DbUtil.getInstance().getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
                setOfContinents.add(resultSet.getString("Continent"));
            }
        }
        return setOfContinents;
    }




}
