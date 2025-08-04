import java.util.*;
//import BankBackend.*;

public class App{
    static Scanner in = new Scanner(System.in);
public static int  choiseMsg(){
    
    System.out.println("\nEnter your choise: ");
    System.out.println(" 1.create Account \n" + 
                " 2.View Balance \n" + 
                " 3.Withdraw Amount \n" + 
                " 4.Deposit Amount \n" + 
                " 5.Money Transfer \n"+
                " 6.Quit");
    System.out.print(" ");
    int ch=in.nextInt();
    return ch;
}
public static void main(String[] args){

    //ac.demo();
    //ac.removeAccount(832000070025L);
String an;
Float amt;
System.out.println("Bank management system");
System.out.println("___________________________________");
while(true){
    int ch=choiseMsg();
    switch(ch){
      case 1:
        System.out.print("  Enter account holder name: ");
        in.nextLine();
        String name=in.nextLine();
        //System.out.print("  Enter account number: ");
        //Long ac=in.nextLong();
        
        System.out.println();
        System.out.print("  Enter account Type(SAVINGS/CURRENT/FD/RD): ");
        String type=in.next();
        System.out.print("  Enter Initial Deposit amount(rs): ");
        amt=in.nextFloat();
        int no=(int)(Math.random() * 9000) + 1000;
        Account ac=new Account(name,type,amt,no);
        System.out.print("Account Number: "+ac.acno);
        
        //System.out.print();
        break;
      /*case 2:
          System.out.print("Enter account number: ");
       an=in.next();
       if(!sb.containsKey(an)){
        System.out.println("Account not available!");
        }
        else{
       Account b=sb.get(an);
       balance(b);
        }
        break;
      case 3:
          System.out.print("Enter account number: ");
         an=in.next();
         System.out.print("Enter withdrawalamount: ");
         amt=in.nextInt();
         
         if(!sb.containsKey(an)){
            System.out.println("Account not available!");
          }
         else{
         Account wa=sb.get(an);
          withdraw(wa,amt);
         }
          break;
      case 4:
          System.out.print("Enter account number: ");
         an=in.next();
         System.out.print("Enter depositamount: ");
         amt=in.nextInt();
         if(!sb.containsKey(an)){
            System.out.println("Account not available!");
          }
         else{
            Account da=sb.get(an);
            deposit(da,amt);
            
         }
          break;
        */
      case 6:
          System.out.println("exiting ....");
          System.out.println("Thanks for using");
          System.exit(0);
          break;
      default:
          System.out.print("Invalid choise");

      }
    }
    
    
  }
}
