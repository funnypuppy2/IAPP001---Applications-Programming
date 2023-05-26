package model;

/**
 * Class represents Hamburger
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Hamburger extends Dish {

    /**
     * Constructor for Hamburger
     * 
     * @param price price of hamburger
     */
    public Hamburger(double price) {
        super("Hamburger", price);
    }

    @Override
    public String toString() {
        return super.toString() + " (Best Selling!!)";
    }
}
