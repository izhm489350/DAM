import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Random;

public class Buyer extends Student{
    private int voucherQty;
    private List <Item> cart;

    public Buyer(){
        super();
        this.voucherQty = 0;
        this.cart = new ArrayList<Item>();
    }

    public Buyer (String name, int matricNumber, int age, String sex, String address, float balance){
        super(name, matricNumber, age, sex, address, balance);
        this.voucherQty = 5;
        this.cart = new ArrayList<Item>();
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

        if (this.cart == null || this.cart.isEmpty()){
            System.out.println("Cart is empty!");
            return;
        }

        System.out.print("Enter item name: ");
        String itemName = input.nextLine();

        // change to iterator so can modify data while in loop
        Iterator<Item> iterator = cart.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                found = true;
                iterator.remove();
                for (Seller seller : sellerList) {
                    if (seller.getName().equalsIgnoreCase(item.getSellerName())) {
                        seller.getItemList().add(item);
                    }
                }
            }
        }

        if (!found){
            System.out.println("Item not found!");
            return;
        }

        System.out.println("Item removed to cart!");    
    }

    public void checkCart(){ 
        if (!this.cart.isEmpty()){
            System.out.println("Items in cart: ");
            for (Item item : cart){
                System.out.println();
                System.out.println("Item Name: " + item.getItemName());
                System.out.println("Item Description: " + item.getItemDesc());
                System.out.printf("Item Price: RM%.2f\n", item.getItemPrice());
                System.out.println("Seller Name: " + item.getSellerName());
            }
        }else{
            System.out.println("Cart is empty!");
        }
        
    }

    public void proceedPaymentAndTransfer(Random random, Scanner input, List <Item> cart, List <Seller> sellerList){

        if (this.cart == null || this.cart.isEmpty()){
            System.out.println("Cart is empty!");
            return;
        }
        
        String ID = this.getName() + random.nextInt(1000);
        Order buyerOrder = new Order (ID);
        buyerOrder.setItemList(this.cart);

        System.out.print("Do you want to apply discount? (Y/N): ");
        char choice = input.next().charAt(0);

        if (choice == 'Y' || choice == 'y'){
            if (getVoucherQty() != 0){
                buyerOrder.setIsDiscounted(true);
                setVoucherQty(getVoucherQty() - 1);   
            }else{
                System.out.println("No voucher available!");
                return;
            }
        }

        Payment buyerPayment = new Payment(buyerOrder);
        setBalance(buyerPayment.pay(getBalance()));

        buyerPayment.transferPaymentToSeller(cart, sellerList);

        this.cart.clear();
    }

    public void giveSellerRating(Scanner input, List <Seller> sellerList){
        input.nextLine();

        boolean found = false;
        int rating;
        Seller ratedSeller = null;

        System.out.print("Enter seller name: ");
        String sellerName = input.nextLine();

        for (Seller seller : sellerList){
            if (seller.getName().equalsIgnoreCase(sellerName)){
                ratedSeller = seller;
                found = true;
                break;
            }
        }

        if (!found){
            System.out.println("Seller not found!");
            return;
        }

        System.out.print("Rating given to seller: ");
        rating = input.nextInt();
        
        while (rating < 1 || rating > 5){
            System.out.print("Rating must be between 1 and 5: ");
            rating = input.nextInt();
        }
        
        ratedSeller.setSellerRating(rating);

        System.out.println("Rating given!");
    }

    public static void showBuyerMenu(Student buyer, Scanner input){
        System.out.println();
        System.out.printf("Welcome to Buyer Menu %s!\n", buyer.getName());
        System.out.printf("Your current balance is RM%.2f\n", buyer.getBalance());
        System.out.println("1. Add to cart");
        System.out.println("2. Remove from cart");
        System.out.println("3. Check cart");
        System.out.println("4. Pay for cart");
        System.out.println("5. Give seller rating");
        System.out.println("6. Show profile");
        System.out.println("7. Exit");
        System.out.println();
    }
}

// Name: Ariff Izham
// Matric Number: 2516463