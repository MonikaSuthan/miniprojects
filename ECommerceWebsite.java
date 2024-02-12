import java.util.ArrayList;
import java.util.List;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
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

class Users {
    private String username;
    private int password;

    public Users(String username, int password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public int getPassword() {
        return password;
    }
}

class ShoppingCart {
    private List<Product> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(Product product) {
        items.add(product);
    }

    public void removeItem(Product product) {
        items.remove(product);
    }

    public List<Product> getItems() {
        return items;
    }

    public double calculateTotal() {
        double total = 0;
        for (Product item : items) {
            total += item.getPrice();
        }
        return total;
    }
}

class CheckoutProcess {
    public static void processCheckout(User user, ShoppingCart cart) {
        System.out.println("Checkout completed for user: " + user.getName());
        System.out.println("Items purchased:");
        for (Product item : cart.getItems()) {
            System.out.println("- " + item.getName() + " : $" + item.getPrice());
        }
        System.out.println("Total amount: $" + cart.calculateTotal());
        System.out.println("Thank you for shopping with us!");
    }
}

public class ECommerceWebsite {
    public static void main(String[] args) {
        // Simulate product listing
        Product product1 = new Product("Product 1", 20.99);
        Product product2 = new Product("Product 2", 15.49);

        // Simulate user registration and login
        User user = new User("john_doe", "password");

        // Simulate user adding items to cart
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(product1);
        cart.addItem(product2);

        // Simulate user checkout process
        CheckoutProcess.processCheckout(user, cart);
    }
}

