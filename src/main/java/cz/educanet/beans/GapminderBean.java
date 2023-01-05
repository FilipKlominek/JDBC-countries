package cz.educanet.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.sql.*;
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

    public List<Gapminder> getYears() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=");

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT g.year" +
                        "FROM gapminder.gapminder AS g"
        );

        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Gapminder> years = new ArrayList<>();

        while (resultSet.next()) {
            years.add(new Gapminder(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5),
                    resultSet.getDouble(6),
                    resultSet.getString(7),
                    resultSet.getInt(8)
            ));
        }
        return years;
    }
}
