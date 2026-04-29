import java.util.Scanner;

import java.util.List;

public class Payment{
  private static int nextPaymentID = 1;
  private int PaymentID;
  private double TotalPrice;
  
  public Payment(){
    this.PaymentID = this.nextPaymentID;
    nextPaymentID ++;
    this.TotalPrice = 0;
  }
  public int getPaymentID(){
    return this.PaymentID;
  }
  public double getTotalPrice(){
    return this.TotalPrice;
  }
  public void setPaymentID(int PaymentID) {
    this.PaymentID = PaymentID;
  }
  public void calcTotal(Order order){
    List<Item> list = order.getItemList();  //expect getitemList on Order
    double sum = 0;

    for (Item currentItem : list){
        sum += currentItem.getItemPrice();  // count the sum without discount based on list item 
    }
        this.TotalPrice = sum;
    deductVoucher(order);
  }

  public void deductVoucher(Order order){ // fixed 10% discount if have voucher 
    if (order.getIsDiscounted()){
        this.TotalPrice = this.TotalPrice - (this.TotalPrice*0.1);
    }

  }

  public void transferToSeller(int sellerID,int PaymentID){ // what to transferToSeller
    System.out.println("The PaymentID : " + PaymentID);
    System.out.println("The Seller ID : " + sellerID);
    System.out.println("Amount Paid : RM " + this.TotalPrice);
  }
}
