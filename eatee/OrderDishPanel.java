import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.MyObserver;
import model.Order;
import model.Restaurant;

public class OrderDishPanel extends JPanel implements MyObserver {

    private Restaurant restaurant;

    public OrderDishPanel(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void update() {
        removeAll();
        List<Dish> dishes = restaurant.getDishes();
        final DefaultListModel<String> dishList = new DefaultListModel<>();
        for (Dish dish : dishes) {
            dishList.addElement(dish.toString());
        }
        JTextField customerTextField = new JTextField(20);
        JList<String> list = new JList<>(dishList);
        JScrollPane scrollPane = new JScrollPane(list);
        JButton orderButton = new JButton("Order");

        orderButton.addActionListener((event) -> {
            if (list.getSelectedIndex() != -1 && customerTextField.getText() != null
                    && !customerTextField.getText().equals("")) {
                Order order = new Order(customerTextField.getText());
                for (String dishString : list.getSelectedValuesList()) {
                    for (Dish dish : dishes) {
                        if (dishString.equals(dish.toString())) {
                            order.addDish(dish);
                        }
                    }
                }
                restaurant.addOrder(order);
            }
        });
        add(scrollPane);
        add(customerTextField);
        add(orderButton);
    }
}
