import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Buyer extends Student{
    private int voucherQty;
    private List <Item> cart;

    public Buyer(){
        super();
        this.voucherQty = 0;
        this.cart = new ArrayList<Item>();
        setStudentType("buyer");
    }

    public Buyer (String name, int matricNumber, int age, String sex, String address, float balance){
        super(name, matricNumber, age, sex, address, balance);
        this.voucherQty = 5;
        this.cart = new ArrayList<Item>();
        setStudentType("buyer");
    }

    public int getVoucherQty(){
        return this.voucherQty;
    }

    public void setVoucherQty(int voucherQty){
        this.voucherQty = voucherQty;
    }

    public List <Item> getCart(){
        return this.cart;
    }

    public void setCart(List <Item> cart){
        this.cart = cart;
    }

    public void addToCart (Scanner input, List <Seller> sellerList){
        input.nextLine();

        boolean found = false;

        System.out.print("Enter item name: ");
        String itemName = input.nextLine();

        for (Seller seller : sellerList){
            for (Item item : seller.getItemList()){
                if (item.getItemName().equalsIgnoreCase(itemName)){
                    cart.add(item);
                    seller.getItemList().remove(item);
                    found = true;
                    System.out.println("Item added to cart!");
                    break;
                }
            }
        }
        
        if (!found){
            System.out.println("Item not found!");
        }
    }

    public void removeToCart (Scanner input, List <Item> cart, List <Seller> sellerList){
        input.nextLine();

        boolean found = false;

        if (this.cart == null){
            System.out.println("Cart is empty!");
            return;
        }

        System.out.print("Enter item name: ");
        String itemName = input.nextLine();

        for (Item item : cart){
            if (item.getItemName().equalsIgnoreCase(itemName)){
                found = true;
                cart.remove(item);
                for(Seller seller : sellerList){
                    if (seller.getName().equalsIgnoreCase(item.getSellerName())){
                        seller.getItemList().add(item);
                    }
                }
            }
        }

        if (!found){
            System.out.println("Item not found!");
        }

        System.out.println("Item removed to cart!");    
    }

    public void checkCart(){ 
        if (!this.cart.isEmpty()){
            System.out.println("Items in cart: ");
            for (Item item : cart){
                System.out.println("Item Name: " + item.getItemName());
                System.out.println("Item Description: " + item.getItemDesc());
                System.out.println("Item Price: RM" + item.getItemPrice());
                System.out.println();
            }
        }else{
            System.out.println("Cart is empty!");
        }
        
    }

    public int giveSellerRating(Scanner input, ArrayList <Seller> sellerList){
        input.nextLine();

        int rating;

        System.out.println("Enter seller name: ");
        String sellerName = input.nextLine();

        for (Seller seller : sellerList){
            if (seller.getName().equalsIgnoreCase(sellerName)){
                return seller.getSellerRating();
            }
        }

        System.out.println("Rating given to seller: ");
        rating = input.nextInt();
        
        while (rating < 1 || rating > 5){
            System.out.println("Rating must be between 1 and 5");
            rating = input.nextInt();
        }
        
        return rating;
    }

    public static void showBuyerMenu(Student buyer, Scanner input){
        System.out.println();
        System.out.printf("Welcome to Buyer Menu %s!\n", buyer.getName());
        System.out.println("1. Add to cart");
        System.out.println("2. Remove from cart");
        System.out.println("3. Check cart");
        System.out.println("4. Pay for cart");
        System.out.println("5. Give seller rating");
        System.out.println("6. Exit");
        System.out.println();
    }
}

// Name: Ariff Izham
// Matric Number: 2516463