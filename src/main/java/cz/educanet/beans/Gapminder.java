package cz.educanet.beans;

public class Gapminder {
    private final String country;
    private final String continent;
    private final int year;
    private final double lifeExp;
    private final int pop;
    private final double gdpPercap;
    private final String iso_alpha;
    private final int iso_num;

    public Gapminder(String country, String continent, int year, double lifeExp, int pop, double gdpPercap, String iso_alpha, int iso_num) {
        this.country = country;
        this.continent = continent;
        this.year = year;
        this.lifeExp = lifeExp;
        this.pop = pop;
        this.gdpPercap = gdpPercap;
        this.iso_alpha = iso_alpha;
        this.iso_num = iso_num;
    }

    public String getCountry() {
        return country;
    }

    public String getContinent() {
        return continent;
    }

    public int getYear() {
        return year;
    }

    public double getLifeExp() {
        return lifeExp;
    }

    public int getPop() {
        return pop;
    }

    public double getGdpPercap() {
        return gdpPercap;
    }

    public String getIso_alpha() {
        return iso_alpha;
    }

    public int getIso_num() {
        return iso_num;
    }
}
