import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Seller extends Student
{
private int sellerRating;
private List <Item> itemList = new ArrayList<>();
    
    public Seller()
    {
        super();
        this.sellerRating = 0;
    }

    public Seller(String name, int matricNumber, int age, String sex, String address, float balance)
    {
        super(name, matricNumber, age, sex, address, balance);
        this.sellerRating = 0;
    }
    
    public int getSellerRating()
    {
        return this.sellerRating;
    }
    
    public void setSellerRating(int rating)
    {
        this.sellerRating += rating;
    }

    public List <Item> getItemList()
    {
        return this.itemList;
    }

    public void setItemList(List <Item> itemList)
    {
        this.itemList = itemList;
    }
    
    public void addItem(Scanner input)
    {
        input.nextLine();

        System.out.print("Enter item name: ");
        String itemName = input.nextLine();
        System.out.print("Enter item description: ");
        String itemDesc = input.nextLine();
        System.out.print("Enter item price: ");
        double itemPrice = input.nextDouble();
        
        this.itemList.add(new Item(itemName, itemDesc, itemPrice, this.getName()));

        System.out.println("Item added successfully!");
    }
    
    public void removeItem(Scanner input)
    {
        input.nextLine();

        System.out.print("Enter item name: ");
        String itemName = input.nextLine();
        
        for(Item item :this.itemList)
        {
            if(item.getItemName().equalsIgnoreCase(itemName))
            {
                this.itemList.remove(item);
                System.out.println("Item removed successfully!");
                return;
            }else{
                System.out.println("Item not found!");
            }
        }
    }

    public void checkInventory(){
        if (!this.itemList.isEmpty()){
            for (Item item : itemList){
                System.out.println();
                System.out.println("Item Name: " + item.getItemName());
                System.out.println("Item Description: " + item.getItemDesc());
                System.out.printf("Item Price: RM%.2f\n", item.getItemPrice());
            }
        }else{
            System.out.println("Inventory is empty!");
        }
    }

    public void checkRating(){
        System.out.println("Your recent rating is: " + this.sellerRating + " stars!");
    }

    public static void showSellerMenu(Seller seller){
        System.out.println();
        System.out.printf("Welcome to Seller Menu %s!\n", seller.getName());
        System.out.printf("Your current balance is RM%.2f\n", seller.getBalance());
        System.out.println("1. Add to inventory");
        System.out.println("2. Remove from inventory");
        System.out.println("3. Check inventory");
        System.out.println("4. Check your rating");
        System.out.println("5. Exit");
        System.out.println();
    }
}

