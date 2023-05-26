package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Restaurant
 */
public class Restaurant extends Updater {
    // Name of Restaurant
    private final String name;
    // All dishes that restaurant have
    private final List<Dish> dishes;
    // All current orders
    private final List<Order> orders;
    // All finished orders
    private final List<Order> finishedOrders;

    private final Set<String> customers;

    /**
     * Constructor for Restaurant
     */
    public Restaurant(String name) {
        super();

        this.name = name;

        // Initial all dishes that restaurant have
        dishes = new ArrayList<>();
        dishes.add(new Dish("Vegetable", 4));
        dishes.add(new Dish("Meat", 10));
        dishes.add(new Dish("Spagetti", 15));
        dishes.add(new Pizza(20));
        dishes.add(new Hamburger(30));

        orders = new ArrayList<>();
        finishedOrders = new ArrayList<>();
        customers = new HashSet<>();
    }

    /**
     * Show menu of restaurant
     */
    public String getMenus() {
        StringBuilder str = new StringBuilder();
        str.append("Here are menus:\n");
        for (int i = 0; i < dishes.size(); ++i) {
            str.append(i + 1).append(". ").append(dishes.get(i))
                    .append("\n");
        }
        str.append("\n");
        return str.toString();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Set<String> getCustomers() {
        return customers;
    }

    /**
     * Get dish from restaurant
     *
     * @param index index of dish
     * @return dish in restaurant
     */
    public Dish getDish(int index) {
        if (index <= 0 || index > dishes.size()) {
            // No such dish
            System.out.println("No such dish");
            return null;
        }
        return dishes.get(index - 1);
    }

    /**
     * Add an order
     *
     * @param order new order from customer
     */
    public void addOrder(Order order) {
        orders.add(order);
        customers.add(order.getCustomer());
        updateViews();
    }

    /**
     * A customer leave
     *
     * @param customer customer who leaves
     */
    public void leave(String customer) {
        List<Order> removeLists = new ArrayList<>();
        for (Order order : orders) {
            if (customer.equals(order.getCustomer())) {
                removeLists.add(order);
            }
        }

        if (removeLists.isEmpty()) {
            return;
        }

        for (Order order : removeLists) {
            // Remove from current order
            orders.remove(order);
            // The order finished
            finishedOrders.add(order);
        }

        customers.remove(customer);
        updateViews();
    }

    /**
     * Show current orders of restaurant
     */
    public String getOrders() {
        if (orders.isEmpty()) {
            return "No orders.";
        }

        StringBuilder str = new StringBuilder();
        str.append("Here are orders:\n");

        for (int i = 0; i < orders.size(); ++i) {
            Order order = orders.get(i);
            str.append(i + 1).append(". ").append(order).append("\n");
        }
        str.append("\n");
        return str.toString();
    }

    /**
     * Show the summary of restaurant
     */
    public String getSummary() {
        StringBuilder str = new StringBuilder();
        str.append("Here are summary:\n");

        double sells = 0.0;
        for (int i = 0; i < finishedOrders.size(); ++i) {
            Order order = finishedOrders.get(i);
            str.append(i + 1).append(". ").append(order).append("\n");
            sells += order.totalPrice();
        }

        str.append("\n").append("Total sells: ").append(sells).append("\n");
        return str.toString();
    }

    /**
     * Return the name of restaurant
     */
    @Override
    public String toString() {
        return name;
    }
}
