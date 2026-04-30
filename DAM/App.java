import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        Random random = new Random();

        List <Student> studentList = new ArrayList<Student> ();
        List <Buyer> buyerList = new ArrayList<Buyer> ();
        List <Seller> sellerList = new ArrayList<Seller> ();
        
        studentList.add(new Buyer ("Ahmad", 2516666, 20, "M", "Jalan 1", 100));

        studentList.add(new Seller ("Aqilah", 2516667, 20, "F", "Jalan 2", 100));

        Utility.studentSorter(studentList, buyerList, sellerList);
        
        int mainChoice = 0;

        do{
            Utility.showMenu();

            mainChoice = Utility.choiceValidator(1, 2, input);

            input.nextLine();

            if (mainChoice == 1){
                Student student = Utility.loginUser(input, studentList);
                
                if (student instanceof Buyer){

                    Utility.buyerLogic(student, random, input, sellerList);
                
                }else if (student instanceof Seller){

                    Utility.sellerLogic(student,input);
  
                }

            }else{

                System.out.println("Goodbye!");
            
            }
        }while (mainChoice != 2);
        
    }    
    
}
