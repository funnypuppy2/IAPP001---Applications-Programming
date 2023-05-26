package model;

/**
 * Pizza dish
 *
 */
public class Pizza extends Dish {

    // Discount of pizza
    private double discount;

    /**
     *
     * Constructor for Pizza
     * 
     * @param price    price of Pizza
     * @param discount discount of pizza
     */
    public Pizza(double price) {
        super("Pizza", price);
        // Pizza is selled at 80% discount by default
        this.discount = 0.8;
    }

    /**
     *
     * Constructor for Pizza
     * 
     * @param price    price of Pizza
     * @param discount discount of pizza
     */
    public Pizza(double price, double discount) {
        super("Pizza", price);
        // Pizza is always selled at 80% discount
        this.discount = discount;
    }

    /**
     * Get price of pizza, it always has a discount
     */
    @Override
    public double getPrice() {
        return super.getPrice() * discount;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("(Discount %.2f)", discount);
    }

}
