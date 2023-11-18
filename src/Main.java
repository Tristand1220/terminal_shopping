import java.util.HashMap;
import java.util.Scanner;

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

        while (!exit) {
            System.out.println("\n===== Shopping Application =====");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Wishlist");
            System.out.println("6. Message Staff");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Displaying available products...");

                    break;
                case 2:
                    System.out.println("Adding product to cart...");
                    // Implement code to add product to the cart
                    break;
                case 3:
                    System.out.println("Viewing shopping cart...");
                    // Implement code to view the shopping cart
                    break;
                case 4:
                    System.out.println("Checking out...");
                    // Implement code for checkout process
                    break;
                case 5:
                    System.out.println("Wishlist...");
                    // Implement code for checkout process
                    break;
                case 6:
                    System.out.println("Message staff...");
                    // Implement code for checkout process
                    break;
                case 7:
                    System.out.println("Exiting shopping application. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    //Staff Interface
    private static void staffInterface(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
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
                    // Implement code for generating daily report
                    break;
                case 2:
                    System.out.println("Generating Monthly Report...");
                    // Implement code for generating monthly report
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
