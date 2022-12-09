package cz.educanet.beans;

public class Country {

    private final String name;
    private final String continent;
    private final long area;
    private final long population;
    private final long gdp;
    private final String capital;
    private final String tld;

    public Country(String name, String continent, long area, long population, long gdp, String capital, String tld) {
        this.name = name;
        this.continent = continent;
        this.area = area;
        this.population = population;
        this.gdp = gdp;
        this.capital = capital;
        this.tld = tld;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public long getArea() {
        return area;
    }

    public long getPopulation() {
        return population;
    }

    public long getGdp() {
        return gdp;
    }

    public String getCapital() {
        return capital;
    }

    public String getTld() {
        return tld;
    }
}
