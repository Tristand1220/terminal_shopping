import java.util.List;

public class Items {
    private int quantity;
    private boolean isInStock;
    private double price;

    private String itemname;

    // Constructor
    public Items(int quantity, boolean isInStock, double price, String itemname) {
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

    // Method to add the item
    public boolean addToCart(List<Items> cart) {
        if (isInStock) {
            cart.add(this);
            return true; // Item added successfully
        }
        return false; // Item not added because it is not in stock
    }

    // Function to remove the item from a shopping cart or wishlist
    public void removeFromCart(List<Items> cart) {
        cart.remove(this);
    }

    //Method to Like the Item
    /*public void likeitem(List<Items> cart) {
        cart.likeitem(this);
    }*/

    public void printItemDetails() {
        System.out.println("Item: " + itemname + " Price: " + price);
    }

    public void Isinstock (int quantity){
        this.quantity = quantity;
        if (quantity==0){
            setInStock(false);
        }
    }
}
