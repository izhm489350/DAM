import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);

        List <Student> studentList = new ArrayList<Student> ();
        List <Buyer> buyerList = new ArrayList<Buyer> ();
        List <Seller> sellerList = new ArrayList<Seller> ();
        
        studentList.add(new Buyer ("Ahmad", 2516666, 5, "M", "Jalan 1", 100));

        studentList.add(new Seller ("Aqilah", 2516667, 5, "F", "Jalan 2", 100));

        studentSorter(studentList, buyerList, sellerList);

        int mainChoice = 0;

        do{
            showMenu();

            mainChoice = choiceValidator(1, 2, input);

            input.nextLine();

            if (mainChoice == 1){
                Student student = loginUser(input, studentList);
                if (student.getStudentType().equalsIgnoreCase("buyer")){
                    Buyer buyer = (Buyer) student;
                    Buyer.showBuyerMenu(buyer, input);
                    int buyerChoice = choiceValidator(1, 6, input);
                    while (buyerChoice != 6){
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
                                // pay for cart
                                break;
                            case 5:
                                // seller rating
                                break;
                            case 6:
                                //exit
                                break;
                        }
                        Buyer.showBuyerMenu(buyer, input);
                        buyerChoice = choiceValidator(1, 6, input);
                    }
                }else if (student.getStudentType().equalsIgnoreCase("seller")){
                    Seller seller = (Seller) student;
                    Seller.showSellerMenu(seller);
                    int sellerChoice = choiceValidator(1, 5, input);
                    while (sellerChoice != 5){
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
                                System.out.println("Your rating is: " + seller.getSellerRating() + " stars!");
                                break;
                            case 5:
                                //exit
                                break;
                        }
                        Seller.showSellerMenu(seller);
                        sellerChoice = choiceValidator(1, 5, input);
                    }
                }else{
                }    
            }else{
                System.out.println("Goodbye!");
            }
        }while (mainChoice != 2);
        
    }    

    public static void showMenu() {
        System.out.println("Welcome to Digital Academic Marketplace! (DAM)");
        System.out.println();
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.println();
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

    public static Student loginUser(Scanner input, List <Student> studentList){
        
        Student student = new Student();

        System.out.print("Enter name: ");
        String name = input.nextLine();
        System.out.print("Enter matric number: ");
        int matricNumber = input.nextInt();

        for (Student stud : studentList){
            if (stud.getName().equalsIgnoreCase(name) && stud.getMatricNumber() == matricNumber){
                student = stud;
                break;
            }
        }

        System.out.println("Invalid credentials!\n");
    
        return student;
    }

    public static void studentSorter(List <Student> studentList, List <Buyer> buyerList, List <Seller> sellerList){
        for (Student student : studentList){
            if (student.getStudentType().equalsIgnoreCase("buyer")){
                buyerList.add((Buyer) student);
            }else if (student.getStudentType().equalsIgnoreCase("seller")){
                sellerList.add((Seller) student);
            }
        }
    }
}
