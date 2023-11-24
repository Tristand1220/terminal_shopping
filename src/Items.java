import java.util.Arrays;
import java.util.List;

public class Item {
    private int quantity;
    private boolean isInStock;
    private double price;
    private String itemname;

    private int likes;

    // Constructor
    public Item(int quantity, boolean isInStock, double price, String itemname) {
        this.quantity = quantity;
        this.isInStock = isInStock;
        this.price = price;
        this.itemname = itemname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getter and setter methods for isInStock
    public boolean getStock() {
        return isInStock;
    }

    public void setInStock(boolean isInStock) {
        this.isInStock = isInStock;
    }

    // Getter and setter methods for price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Getter and setter methods for item name
    public String getItemname() {
        return itemname;
    }

    public void setItemName(String itemname) {
        this.itemname = itemname;
    }

    public void Isinstock (int quantity){
        this.quantity = quantity;
        if (quantity==0){
            setInStock(false);
        }
    }

    // Method to add the item
    public boolean addToCart(List<Item> cart, String itemname, int quantity) {
        Isinstock(quantity);
        if (isInStock) {
            cart.add(this);
            return true; // Item added successfully
        }
        return false; // Item not added because it is not in stock
    }

    // Function to remove the item from a shopping cart or wishlist
    public static void removeFromCart(List<Item> cart, String itemname) {
        cart.removeIf(item -> item.getItemname().equals(itemname));
    }

    //Method to Like the Item
    /*public void likeitem(List<Items> cart) {
        cart.likeitem(this);
    }*/

    public void printItemDetails() {
        System.out.println("Item: " + itemname + " Price: " + price);
    }

    public void printItemDetailsCart() {
        System.out.println("Item: " + itemname + " Price: " + price + "Quantity: " + quantity);
    }

    public static void displayShoppingCart(List<Item> shoppingCart) {
        System.out.println("\n===== Shopping Cart =====");
        if (shoppingCart.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
        } else {
            for (Item item : shoppingCart) {
                item.printItemDetailsCart();
            }
        }
    }

    public static void displaywishlist(List<Item> shoppingCart) {
        System.out.println("\n===== Wishlist =====");
        if (shoppingCart.isEmpty()) {
            System.out.println("Your wishlist is empty.");
        } else {
            for (Item item : shoppingCart) {
                item.printItemDetailsCart();
            }
        }
    }

    public static boolean moveToWishlist(List<Item> shoppingCart, List<Item> wishlist, String itemName) {
        // Find the item in the shopping cart
        Item itemInCart = findItem(shoppingCart, itemName);

        if (itemInCart != null) {
            // Create a new item with the specified quantity and add it to the wishlist
            Item itemInWishlist = new Item(itemInCart.getQuantity(), true, itemInCart.getPrice(), itemName);
            wishlist.add(itemInWishlist);
            return true; // Item moved to wishlist successfully
        }
        return false; // Item not moved to wishlist
    }

    // Helper method to find an item in a list by name
    static Item findItem(List<Item> itemList, String itemName) {
        for (Item item : itemList) {
            if (item.getItemname().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public static Item findItemInShoppingMall(String itemName) {
        List<Item> shoppingMall = Arrays.asList(
                new Item(5, true, 50.00, "shirt"),
                new Item(5, true, 50.00, "pants"),
                new Item(5, true, 50.00, "shoes"),
                new Item(5, true, 50.00, "hat"),
                new Item(5, true, 50.00, "jacket")
        );

        for (Item item : shoppingMall) {
            if (item.getItemname().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void likeItem(){
        likes++;
    }
    public void unLikeItem(){
        if (likes > 0){
            likes--;
        }
    }

    public int getLikes(){
        return likes;
    }

    static boolean purchaseItems(List<Item> shoppingCart) {
        // Check if the shopping cart is empty
        if (shoppingCart.isEmpty()) {
            System.out.println("Shopping cart is empty. No items to purchase.");
            return false;
        }

        double totalPrice = 0.0;

        // Display an itemized list of each item in the shopping cart
        System.out.println("\nItemized List:");
        for (Item item : shoppingCart) {
            item.printItemDetailsCart();

            // Calculate the total price for each item and add it to the overall total
            double itemTotalPrice = item.getPrice() * item.getQuantity();
            totalPrice += itemTotalPrice;
        }

        // Display the total price to the customer
        System.out.println("\nTotal Price: $" + totalPrice);

        // Purchase completed successfully
        return true;
    }

    // Method to display a receipt for the purchased items
    static void displayReceipt(List<Item> shoppingCart) {
        // Check if the shopping cart is empty
        if (shoppingCart.isEmpty()) {
            System.out.println("Shopping cart is empty. No receipt available.");
            return;
        }

        // Display a detailed receipt for each item
        double totalPrice = 0.0;
        System.out.println("\n---Receipt:---");
        for (Item item : shoppingCart) {
            int quantity = item.getQuantity();
            double individualPrice = item.getPrice();
            double itemTotalPrice = quantity * individualPrice;

            totalPrice += itemTotalPrice;

            System.out.println("Item: " + item.getItemname() +
                    " | Quantity: " + quantity +
                    " | Individual Price: $" + individualPrice);
            System.out.println("\nTotal Price: $" + totalPrice);
        }
    }

}


