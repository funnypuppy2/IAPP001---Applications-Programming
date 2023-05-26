package model;

/**
 * Dish of the restaurant
 * Each dish has name and price
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Dish {
    private final String name;
    private final double price;

    /**
     * Constructor of Dish
     */
    public Dish(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Name of dish
     * 
     * @return dish name
     */
    public String getName() {
        return name;
    }

    /**
     * Price of dish
     * 
     * @return dish
     */
    public double getPrice() {
        return price;
    }

    /**
     * Dish representation
     */
    @Override
    public String toString() {
        return String.format("%s($%.2f)", name, price);
    }
}
