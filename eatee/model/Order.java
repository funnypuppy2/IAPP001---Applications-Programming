package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Order in restaurant
 *
 */
public class Order {
    /**
     * Dishes of this order
     */
    private final List<Dish> dishes;
    /**
     * Customer who ordered this
     */
    private final String customer;

    public Order(String customer) {
        this.customer = customer;
        dishes = new ArrayList<>();
    }

    /**
     * Add dish to the order
     * 
     * @param dish dish
     */
    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    /**
     * Return the customer who ordered this order
     * 
     * @return customer name
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * Total price of this order
     * 
     * @return total price
     */
    public double totalPrice() {
        double sum = 0;
        for (Dish dish : dishes) {
            // Each dish has its price
            sum += dish.getPrice();
        }

        return sum;
    }

    /**
     * Return string representation of order
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(String.format("Customer %s ordered:\n", customer));
        for (Dish dish : dishes) {
            result.append("\t").append(dish).append("\n");
        }
        return result.toString();
    }
}
