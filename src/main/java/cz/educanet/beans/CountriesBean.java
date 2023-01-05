package cz.educanet.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class CountriesBean {

    private String filterName;
    private String filterContinent;
    private String filterArea;
    private String filterPopulation;
    private String filterGdp;

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getFilterContinent() {
        return filterContinent;
    }

    public void setFilterContinent(String filterContinent) {
        this.filterContinent = filterContinent;
    }

    public String getFilterArea() {
        return filterArea;
    }

    public void setFilterArea(String filterArea) {
        this.filterArea = filterArea;
    }

    public String getFilterPopulation() {
        return filterPopulation;
    }

    public void setFilterPopulation(String filterPopulation) {
        this.filterPopulation = filterPopulation;
    }

    public String getFilterGdp() {
        return filterGdp;
    }

    public void setFilterGdp(String filterGdp) {
        this.filterGdp = filterGdp;
    }

    public List<Country> getCountries() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=");

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT w.name, w.continent, w.area, w.population, w.gdp, w.capital, w.tld" +
                        " FROM world.world AS w" +
                        " WHERE w.name LIKE ?" +
                        " AND w.continent LIKE ?" +
                        " AND w.area LIKE ?" +
                        " AND w.population LIKE ?" +
                        " AND w.gdp LIKE ?"
        );

        preparedStatement.setString(1, '%' + filterName + '%');
        preparedStatement.setString(2, '%' + filterContinent + '%');
        preparedStatement.setString(3, '%' + filterArea + '%');
        preparedStatement.setString(4, '%' + filterPopulation + '%');
        preparedStatement.setString(5, '%' + filterGdp + '%');

        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Country> countries = new ArrayList<>();

        while (resultSet.next()) {
            countries.add(new Country(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getLong(3),
                    resultSet.getLong(4),
                    resultSet.getLong(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return countries;
    }



}

