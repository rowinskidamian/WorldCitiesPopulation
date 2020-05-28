package controller;

import db.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/show_continents")
public class ShowContinentsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String continentOption = req.getParameter("continentOption");
        String countryOption = req.getParameter("countryOption");

        try {
            Set<String> continentsSet = getContinentsSet();
            session.setAttribute("continentsSet", continentsSet);
            if (continentOption != null) {
                Set<String> countriesSet = getCountriesSet(continentOption);
                session.setAttribute("countriesSet", countriesSet);
            }
            if (countryOption != null) {
                Map<String, Integer> citiesMap = getCitiesMap(countryOption);
                session.setAttribute("citiesMap", citiesMap);
            }

            req.getServletContext().getRequestDispatcher("/show_continents.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Integer> getCitiesMap(String country) throws SQLException {
        final String sqlQuery = "SELECT city.Name, city.Population FROM city JOIN country c on city.CountryCode " +
                "= c.Code WHERE c.Name = '"+country+"'";
        Map<String, Integer> mapOfCitiesPopulation = new TreeMap<>();

        try (Connection con = DbUtil.getInstance().getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                String cityKey = resultSet.getString("Name");
                Integer populationValue = resultSet.getInt("Population");
                mapOfCitiesPopulation.put(cityKey, populationValue);

            }
        }
        return mapOfCitiesPopulation;
    }

    private static Set<String> getCountriesSet(String continent) throws SQLException {
        Map<String, Set<String>> mapOfCountries = new TreeMap();
        final String sqlQuery = "SELECT Name, Continent FROM Country";
        try (Connection conn = DbUtil.getInstance().getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
                String continentKey = resultSet.getString("Continent");
                String nameOfCountry = resultSet.getString("Name");

                if(mapOfCountries.containsKey(continentKey)){
                    Set<String> currentCountires = mapOfCountries.get(continentKey);
                    currentCountires.add(nameOfCountry);
                    mapOfCountries.put(continentKey, currentCountires);
                } else {
                    mapOfCountries.put(continentKey, new TreeSet<>());
                }
            }
        }

        return mapOfCountries.get(continent);
    }

    private static Set<String> getContinentsSet() throws SQLException {
        Set<String> setOfContinents = new TreeSet<>();
        final String sqlQuery = "SELECT Continent FROM Country";

        try (Connection conn = DbUtil.getInstance().getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
                setOfContinents.add(resultSet.getString("Continent"));
            }
        }
        return setOfContinents;
    }


}
