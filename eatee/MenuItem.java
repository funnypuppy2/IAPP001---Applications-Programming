enum MenuItem {
    ORDER_DISHES("Order Dishes"),

    LEAVE("Leave"),
    SHOW_ORDERS("Orders"),
    SUMMARY("Summary"),
    QUIT("QUIT"),
    INVALID("Invalid");

    final String menu;

    MenuItem(String menu) { this.menu = menu; }

    String getMenu() { return menu; }
}