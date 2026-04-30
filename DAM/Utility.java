import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Utility {
    public static void studentSorter(List <Student> studentList, List <Buyer> buyerList, List <Seller> sellerList){
        
        for (Student student : studentList){
            if (student instanceof Buyer){
                buyerList.add((Buyer) student);
            }else if (student instanceof Seller){
                sellerList.add((Seller) student);
            }
        }
    }

    public static void showMenu() {
        System.out.println();
        System.out.println("Welcome to Digital Academic Marketplace! (DAM)");
        System.out.println();
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.println();
    }

    public static Student loginUser(Scanner input, List <Student> studentList){
        
        boolean found = false;
        Student student = new Student();

        System.out.print("Enter name: ");
        String name = input.nextLine();
        System.out.print("Enter matric number: ");
        int matricNumber = input.nextInt();

        for (Student stud : studentList){
            if (stud.getName().equalsIgnoreCase(name) && stud.getMatricNumber() == matricNumber){
                student = stud;
                found = true;
                break;
            }
        }

        if (!found){
            System.out.println("Invalid credentials!");
        }
        return student;
    }

    public static int choiceValidator(int min, int max, Scanner input){
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();
        while (choice < min || choice > max){
            System.out.print("Invalid input, please try again: ");
            choice = input.nextInt();
        }
        return choice;
    }

    public static void buyerLogic(Student student, Random random, Scanner input, List <Seller> sellerList){
        Buyer buyer = (Buyer) student;
        Buyer.showBuyerMenu(buyer, input);
        int buyerChoice = Utility.choiceValidator(1, 7, input);
        while (buyerChoice != 7){
            switch (buyerChoice){
                case 1:
                    buyer.addToCart(input, sellerList);
                    break;
                case 2:
                    buyer.removeToCart(input, buyer.getCart(), sellerList);
                    break;
                case 3:
                    buyer.checkCart();
                    break;
                case 4:
                    buyer.proceedPaymentAndTransfer(random, input, buyer.getCart(), sellerList);
                    break;
                case 5:
                    buyer.giveSellerRating(input, sellerList);
                    break;
                case 6:
                    System.out.println("\n" + buyer.toString());
                    System.out.println("Voucher Quantity: " + buyer.getVoucherQty());
                    break;
                case 7:
                    break;
            }
            Buyer.showBuyerMenu(buyer, input);
            buyerChoice = Utility.choiceValidator(1, 7, input);
        }
    }

    public static void sellerLogic(Student student, Scanner input){
        Seller seller = (Seller) student;
        Seller.showSellerMenu(seller);
        int sellerChoice = Utility.choiceValidator(1, 6, input);
        while (sellerChoice != 6){
            switch  (sellerChoice){
                case 1:
                    seller.addItem(input);
                    break;
                case 2:
                    seller.removeItem(input);
                    break;
                case 3:
                    seller.checkInventory();
                    break;
                case 4:
                    seller.checkRating();
                    break;
                case 5:
                    System.out.println("\n" + seller.toString());
                    break;
                case 6:
                    break;
            }
            Seller.showSellerMenu(seller);
            sellerChoice = Utility.choiceValidator(1, 6, input);
        }
    }
}
