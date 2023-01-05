package cz.educanet.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class GapminderBean {
    private int filterYear;

    public int getFilterYear() {
        return filterYear;
    }

    public void setFilterYear(int filterYear) {
        this.filterYear = filterYear;
    }

    public List<Integer> getYears() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=");

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT DISTINCT g.year FROM gapminder.gapminder AS g ORDER BY g.year"
        );

        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Integer> years = new ArrayList<>();

        while (resultSet.next()) {
            years.add(
                    resultSet.getInt(1)
            );
        }
        return years;
    }

    public List<Gapminder> getGapminders() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=");

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT g.continent, g.year, g.lifeExp, g.pop, g.gdpPercap, g.iso_alpha, g.iso_num FROM gapminder.gapminder AS g"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Gapminder> gapminders = new ArrayList<>();

        while (resultSet.next()) {
            gapminders.add(new Gapminder(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(1),
                    resultSet.getDouble(4),
                    resultSet.getInt(5),
                    resultSet.getDouble(6),
                    resultSet.getString(7),
                    resultSet.getInt(8)
            ));
        }
        return gapminders;
    }
}
