package cz.educanet.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class GapminderBean {
    private int filterYear = 0;

    public int getFilterYear() {
        return filterYear;
    }

    public void setFilterYear(int filterYear) {
        this.filterYear = filterYear;
    }

    public List<Gapminder> getYears() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=");

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT DISTINCT g.year" +
                        " FROM gapminder.gapminder AS g"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Gapminder> gapminders = new ArrayList<>();

        while (resultSet.next()) {
            gapminders.add(new Gapminder(
                    "" ,
                    "" ,
                    resultSet.getInt(1),
                    0,
                    0,
                    0,
                    "" ,
                    0
            ));
        }
        return gapminders;
    }

    public List<Gapminder> getCountries() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=");

        PreparedStatement preparedStatement;

        if (this.filterYear == 0) {
            preparedStatement = connection.prepareStatement(
                    "SELECT g.country, AVG(g.lifeExp)" +
                            " FROM gapminder.gapminder AS g" +
                            " GROUP BY g.country" +
                            " ORDER BY g.country"
            );
        } else preparedStatement = connection.prepareStatement(
                "SELECT g.country, AVG(g.lifeExp)" +
                        " FROM gapminder.gapminder AS g" +
                        " WHERE g.year LIKE ?" +
                        " GROUP BY g.country" +
                        " ORDER BY g.country"
        );

        preparedStatement.setString(1, String.valueOf('%' + filterYear + '%'));

        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Gapminder> gapminders = new ArrayList<>();

        while (resultSet.next()) {
            gapminders.add(new Gapminder(
                    resultSet.getString(1),
                    "" ,
                    0,
                    resultSet.getDouble(2),
                    0,
                    0,
                    "" ,
                    0
            ));
        }
        return gapminders;
    }

    public List<Gapminder> getContinents() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=");


        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT g.continent, AVG(g.lifeExp)" +
                        " FROM gapminder.gapminder AS g" +
                        " GROUP BY g.continent" +
                        " ORDER BY g.continent"
        );

        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Gapminder> gapminders = new ArrayList<>();

        while (resultSet.next()) {
            gapminders.add(new Gapminder(
                    "" ,
                    resultSet.getString(1),
                    0,
                    resultSet.getDouble(2),
                    0,
                    0,
                    "" ,
                    0
            ));
        }
        return gapminders;
    }
}
