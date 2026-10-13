import java.util.*;

interface PaymentMethod {
    boolean processPayment(double amount);
    String getName();
}

class CreditCardPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return true;
    }

    public String getName() {
        return "Credit Card";
    }
}

class PayPalPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return false;
    }

    public String getName() {
        return "PayPal";
    }
}

class BankTransferPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return true;
    }

    public String getName() {
        return "Bank Transfer";
    }
}

class Product {
    private String name;
    private double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class ShoppingCustomer {
    private String name;

    ShoppingCustomer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class OrderItem {
    private Product product;
    private int quantity;

    OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }
}

class Order {
    private String id;
    private ShoppingCustomer customer;
    private List<OrderItem> items = new ArrayList<>();
    private String status = "Pending";

    Order(String id, ShoppingCustomer customer) {
        this.id = id;
        this.customer = customer;
        System.out.println("Order created for " + customer.getName() + ".");
    }

    public void addProduct(Product product, int quantity) {
        if (status.equals("Paid")) {
            System.out.println("Cannot modify a paid order.");
            return;
        }

        if (quantity <= 0) {
            System.out.println("Quantity must be positive.");
            return;
        }

        items.add(new OrderItem(product, quantity));
    }

    public double getTotal() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getSubtotal();
        }

        return total;
    }

    public void pay(PaymentMethod method) {
        if (items.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }

        if (status.equals("Paid")) {
            System.out.println("Order " + id + " is already Paid.");
            return;
        }

        System.out.println("Payment initiated via " + method.getName()
                + " for Order " + id + ".");

        if (method.processPayment(getTotal())) {
            status = "Paid";
            System.out.println("Payment for Order " + id + " successful.");
        } else {
            System.out.println("Payment for Order " + id + " failed.");
        }

        System.out.println("Order status: " + status + ".");
    }
}

public class ShoppingPaymentProcessing {
    public static void main(String[] args) {
        ShoppingCustomer x = new ShoppingCustomer("Customer X");
        Order orderX = new Order("X", x);
        orderX.addProduct(new Product("Product A", 25), 2);
        orderX.addProduct(new Product("Product B", 40), 1);
        orderX.pay(new CreditCardPayment());

        ShoppingCustomer y = new ShoppingCustomer("Customer Y");
        Order orderY = new Order("Y", y);
        orderY.pay(new CreditCardPayment());

        ShoppingCustomer z = new ShoppingCustomer("Customer Z");
        Order orderZ = new Order("Z", z);
        orderZ.addProduct(new Product("Product C", 60), 1);
        orderZ.pay(new PayPalPayment());
    }
}
