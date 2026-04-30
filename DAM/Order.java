import java.util.List;
import java.util.ArrayList;

public class Order {
    private String orderID;
    private List <Item> itemList;
    private boolean isDiscounted;
    
    public Order(String orderID) {
        this.orderID = orderID;
        this.itemList = new ArrayList<>();
        this.isDiscounted = false;
    }

    public String getOrderID() {
        return orderID;
    }
    
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
    
    public List <Item> getItemList() {
        return itemList;
    }
    
    public void setItemList(List <Item> itemList) {
        this.itemList = itemList;
    }
    
    public boolean getIsDiscounted() {
        return this.isDiscounted;
    }
    
    public void setIsDiscounted(boolean isDiscounted) {
        this.isDiscounted = isDiscounted;
    }
    
    public void addItem(Item item) {
        itemList.add(item);
    }
    
    public int getItemCount() {
        return itemList.size();
    }
}