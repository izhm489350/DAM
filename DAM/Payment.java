import java.util.List;

public class Payment{
  private String paymentID;
  private Order order;
  private double totalPrice;
    
  public Payment(Order order){
    this.paymentID = order.getOrderID();
    this.order = order;
    this.totalPrice = 0;
  }

  public String getPaymentID(){
    return this.paymentID;
  }

  public double getTotalPrice(){
    return this.totalPrice;
  }

  public Order getOrder(){
    return this.order;
  }

  public void setPaymentID(String paymentID) {
    this.paymentID = paymentID;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public void calcTotal(Order order){
    List<Item> list = order.getItemList();  //expect getitemList on Order
    double sum = 0;

    for (Item currentItem : list){
        sum += currentItem.getItemPrice();  // count the sum without discount based on list item 
    }
        this.totalPrice = sum;
        deductVoucher(order);
  }

  public void deductVoucher(Order order){ // fixed 10% discount if have voucher 
    if (order.getIsDiscounted()){
        this.totalPrice = this.totalPrice * 0.9;
    }
  }

   public double pay(double balance){
      calcTotal(this.order);
      if (balance >= this.totalPrice){
        balance -= this.totalPrice;
        System.out.printf("The total price is RM %.2f\n", this.totalPrice);
        System.out.printf("The balance is RM%.2f\n", balance);
        System.out.println("Payment success!");
        return balance;
      }else{
        System.out.printf("The total price is RM %.2f\n", this.totalPrice);
        System.out.printf("The balance is RM%.2f\n", balance);
        System.out.println("Payment failed!");
      }

      return balance;
    }

    public void transferPaymentToSeller (List <Item> cart, List <Seller> sellerList) {
        for (Item item : cart){
            for (Seller seller : sellerList){
                if (seller.getName().equalsIgnoreCase(item.getSellerName())){
                    seller.setBalance(seller.getBalance() + item.getItemPrice());
                }
            }
        }
    }
}

// Name: Adam Hafiz
// Matric Number: 2516767
