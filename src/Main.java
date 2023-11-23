import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String,String> logininfo = new HashMap<String,String>();

        logininfo.put("customer1","customer123");
        logininfo.put("customer2","customer234");
        logininfo.put("customer3","customer345");
        logininfo.put("staff","staff123");
        logininfo.put("ceo","ceo123");


        loginLoop(scanner, logininfo);
        scanner.close();
    }

    private static void loginLoop(Scanner scanner, HashMap<String, String> loginInfo) {
        System.out.println("Login Screen\n");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (isValidLogin(username, password, loginInfo)) {
            System.out.println("Login successful");
            switch (username) {
                case "customer1":
                case "customer2":
                case "customer3":
                    shoppingApplication(scanner);
                    break;
                case "staff":
                    staffInterface(scanner);
                    break;
                case "ceo":
                    ceoInterface(scanner);
                    break;
            }
        } else {
            System.out.println("Login failed. Invalid username or password. Please try again.");
            loginLoop(scanner, loginInfo); // Recursively call the login loop
        }
    }

    private static boolean isValidLogin(String username, String password, HashMap<String, String> loginInfo) {
        return loginInfo.containsKey(username) && loginInfo.get(username).equals(password);
    }

    //Shopping Mall
    private static void shoppingApplication(Scanner scanner) {
        boolean exit = false;
        List<Item> shoppingCart = new ArrayList<>();
        List<Item> wishlist = new ArrayList<>();

        Item shirt = new Item(5, true, 50.00, "shirt");
        Item pants = new Item(5, true, 50.00, "pants");
        Item shoes = new Item(5, true, 50.00, "shoes");
        Item hat = new Item(5, true, 50.00, "hat");
        Item jacket = new Item(5, true, 50.00, "jacket");

        Set<String> ItemNameString = new HashSet<String>();
        ItemNameString.add("shirt");
        ItemNameString.add("pants");
        ItemNameString.add("shoes");
        ItemNameString.add("hat");
        ItemNameString.add("jacket");

        while (!exit) {
            System.out.println("\n===== Shopping Application =====");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Add to Wishlist");
            System.out.println("6. View Wishlist");
            System.out.println("7. Remove item");
            System.out.println("8. Move item to wishlist");
            System.out.println("9. Message Staff");
            System.out.println("10. Like/Dislike item");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nDisplaying available products...");
                    shirt.printItemDetails();
                    pants.printItemDetails();
                    shoes.printItemDetails();
                    hat.printItemDetails();
                    jacket.printItemDetails();
                    break;

                case 2:
                    System.out.println("\nAdding product to cart...");
                    String itemname;
                    int quantity;


                    System.out.println("Select item you wish to add to your shopping cart: ");
                    itemname = scanner.nextLine();
                    if (ItemNameString.contains(itemname)){
                        System.out.println("Select quantity: ");
                        quantity = scanner.nextInt();

                        Item addedItem = new Item(quantity, true, 50.00, itemname);
                        boolean addedToCart = addedItem.addToCart(shoppingCart, itemname, quantity);
                        if (!addedToCart) {
                            System.out.println("Item not added to the cart. Check item name or quantity available.");
                        } else {
                            System.out.println("Item added to the cart successfully!");
                        }
                    } else {
                        System.out.print("Item selected does not exist in shopping mall");
                    }
                    break;

                case 3:
                    System.out.println("\nViewing shopping cart...");
                    Item.displayShoppingCart(shoppingCart);
                    break;
                case 4:
                    System.out.println("\nChecking out...");
                    // Implement code for checkout process
                    break;
                case 5:
                    System.out.println("\nAdding to Wishlist...");


                    System.out.println("Select item you wish to add to your shopping cart: ");
                    itemname = scanner.nextLine();
                    if (ItemNameString.contains(itemname)){
                        System.out.println("Select quantity: ");
                        quantity = scanner.nextInt();

                        Item addedItem = new Item(quantity, true, 50.00, itemname);
                        boolean addedToCart = addedItem.addToCart(wishlist, itemname, quantity);
                        if (!addedToCart) {
                            System.out.println("Item not added wishlist. Check item name or quantity available.");
                        } else {
                            System.out.println("Item added to the wishlist successfully!");
                        }
                    } else {
                        System.out.print("Item selected does not exist in shopping mall");
                    }
                    break;
                case 6:
                    System.out.println("\nViewing wishlist...");
                    Item.displaywishlist(wishlist);
                    break;
                case 7:
                    System.out.println("\nRemove item");


                    System.out.print("Enter the name of the item to remove: ");
                    String itemToRemove = scanner.nextLine();

                    if (ItemNameString.contains(itemToRemove)){
                        System.out.println("Where do you want to remove this item from? (Must be \"shopping cart\" or \"wishlist\")");
                        String whichCart=scanner.nextLine();

                        if(whichCart.equals("shopping cart")){
                            Item.removeFromCart(shoppingCart, itemToRemove);
                        } else if (whichCart.equals("wishlist")) {
                            Item.removeFromCart(wishlist, itemToRemove);
                        }else{
                            System.out.println("Location incorrect");
                        }
                    }else {
                        System.out.print("Item selected does not exist in shopping mall");
                    }
                    break;
                case 8:
                    System.out.println("\nMove item to wishlist");
                    System.out.print("Enter the name of the item to move to wishlist: ");
                    String itemToMove = scanner.nextLine();

                    boolean movedToWishlist = Item.moveToWishlist(shoppingCart, wishlist, itemToMove);
                    if (movedToWishlist) {
                        System.out.println("Item moved to wishlist successfully!");
                    } else {
                        System.out.println("Item not moved to wishlist. Check item name or quantity available in the shopping cart.");
                    }
                    break;
                case 9:
                    System.out.println("\nMessage staff...");
                    // Implement code for checkout process
                    break;
                case 10:
                    System.out.println("\nLike an item...");
                    System.out.println("Select item you want to like or unlike: ");
                    String itemNameToLike = scanner.nextLine();

                    // Find the item in the shopping mall
                    Item itemToLike = Item.findItemInShoppingMall(itemNameToLike);

                    if (itemToLike != null) {
                        System.out.println("1. Like");
                        System.out.println("2. Unlike");
                        System.out.print("Enter your choice: ");
                        int likeChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        switch (likeChoice) {
                            case 1:
                                itemToLike.likeItem();
                                System.out.println("Item liked!");
                                break;
                            case 2:
                                itemToLike.unLikeItem();
                                System.out.println("Item unliked!");
                                break;
                            default:
                                System.out.println("Invalid choice. Please enter 1 to like or 2 to unlike.");
                                break;
                        }
                    } else {
                        System.out.println("Item not found in the shopping mall.");
                    }

                    break;
                case 0:
                    System.out.println("\nExiting shopping application. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }

        }
    }



    //Staff Interface
    private static void staffInterface(Scanner scanner) {
        boolean exit = false;


        System.out.println("\n===== Staff Interface =====");
        System.out.println("1. Refill Inventory");
        System.out.println("2. Upload Items");
        System.out.println("3. Remove Items");
        System.out.println("4. Modify Item Info");
        System.out.println("5. Message Customer");
        System.out.println("6. Logout");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                System.out.println("Refilling inventory...");
                // Implement code for refilling inventory
                break;
            case 2:
                System.out.println("Uploading items...");
                // Implement code for uploading/removing items
                break;
            case 3:
                System.out.println("Removing items...");
                // Implement code for writing/modifying item information
                break;
            case 4:
                System.out.println("Modify Item information...");
                // Implement code for accessing customer information
                break;
            case 5:
                System.out.println("Message customer...");
                // Implement code for accessing customer information
                break;
            case 6:
                System.out.println("Logging out. Goodbye!");
                exit = true;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    //CEO Interface
    private static void ceoInterface(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== CEO Interface =====");
            System.out.println("1. Generate Daily Report");
            System.out.println("2. Generate Monthly Report");
            System.out.println("3. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("Generating Daily Report...");
                    Reports.scheduleDailyReport();
                    break;
                case 2:
                    System.out.println("Generating Monthly Report...");
                    Reports.scheduleMonthlyReport();
                    break;
                case 3:
                    System.out.println("Logging out. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


}
