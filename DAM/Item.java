public class Item {
    
    private String itemName;
    private String itemDesc;
    private double itemPrice;
    private String sellerName;

    // Default Constructor
    public Item() {
    }

    // Parameter Constructor
    public Item(String itemName, String itemDesc, double itemPrice, String sellerName) {
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.itemPrice = itemPrice;
        this.sellerName = sellerName;
    }

    // Getter 
    public String getItemName() {
        return itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public String getSellerName() {
        return sellerName;
    }

    // Setter
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}