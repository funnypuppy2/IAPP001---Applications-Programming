import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import model.*;

public class Eatee implements GuiComponent {

    private final List<MenuItem> menus;
    private final List<JPanel> panels;
    private final JTabbedPane jTabbedPane;

    private final Restaurant restaurant;

    public Eatee() {
        this.restaurant = new Restaurant("Eatee");
        this.menus = Arrays.stream(MenuItem.values())
                .filter(menuItemEnum -> menuItemEnum != MenuItem.INVALID)
                .collect(Collectors.toList());
        this.panels = new ArrayList<>();
        this.restaurant.registerObserver(this);
        // Panels
        for (int i = 0; i < this.menus.size(); ++i) {
            this.panels.add(new JPanel());
        }
        jTabbedPane = new JTabbedPane();
        for (int i = 0; i < menus.size(); ++i) {
            MenuItem name = menus.get(i);
            JPanel panel = panels.get(i);
            jTabbedPane.addTab(name.getMenu(), panel);
            updatePanels(name, panel);
        }
    }

    private void createUIComponents() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Eatee");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(jTabbedPane);
        frame.setVisible(true);
    }

    public void updatePanels(MenuItem name, JPanel panel) {
        switch (name) {
            case ORDER_DISHES -> updateOrderDishes(panel);
            case SHOW_ORDERS -> updateShowOrders(panel);
            case LEAVE -> updateLeave(panel);
            case SUMMARY -> updateSummary(panel);
            case QUIT -> updateQuit(panel);
            default -> {
            }
        }
    }

    public void update() {
        for (int i = 0; i < menus.size(); ++i) {
            MenuItem name = menus.get(i);
            JPanel panel = panels.get(i);
            updatePanels(name, panel);
        }

        jTabbedPane.updateUI();
        jTabbedPane.repaint();
    }

    private void updateOrderDishes(JPanel panel) {
        panel.removeAll();
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
        panel.add(scrollPane);
        panel.add(customerTextField);
        panel.add(orderButton);
    }

    private void updateLeave(JPanel panel) {
        panel.removeAll();
        Set<String> customers = restaurant.getCustomers();
        final DefaultListModel<String> customerList = new DefaultListModel<>();
        for (String customer : customers) {
            customerList.addElement(customer);
        }
        JList<String> list = new JList<>(customerList);
        JScrollPane scrollPane = new JScrollPane(list);
        JButton orderButton = new JButton("Leave");
        orderButton.addActionListener((event) -> {
            if (list.getSelectedIndex() != -1) {
                for (String customer : list.getSelectedValuesList()) {
                    restaurant.leave(customer);
                }
            }
        });
        panel.add(scrollPane);
        panel.add(orderButton);
    }

    public void updateShowOrders(JPanel panel) {
        panel.removeAll();
        TextArea textArea = new TextArea(restaurant.getOrders(), 20, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        textArea.setEditable(false);
        panel.add(textArea);
    }

    public void updateSummary(JPanel panel) {
        panel.removeAll();
        TextArea textArea = new TextArea(restaurant.getSummary(), 20, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        textArea.setEditable(false);
        panel.add(textArea);
    }

    public void updateQuit(JPanel panel) {
        panel.removeAll();
        JButton button = new JButton("Quit");
        button.addActionListener((event) -> System.exit(0));
        panel.add(button);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Eatee()::createUIComponents);
    }
}
