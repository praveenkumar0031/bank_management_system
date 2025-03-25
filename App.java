import java.util.*;
class Account{
    String name;
    String acno;
    int acbal=0;
    Account(String n,String no){
        this.name=n;
        this.acno=no;
        System.out.println("Account created ! \nAccount holder: "+n+"\nAccount number: "+no+"\nAccount balance: "+acbal);
    }

}
public class App{
  public static void main(String[] args){
Scanner in=new Scanner(System.in);
System.out.println("Welcome to bank management system");
System.out.print("___________________________________");
ArrayList<Account> sb=new ArrayList<Account>();
while(true){
    System.out.print("Operations \n 1.create Account \n 2.view balance \n 3.withdraw amount \n 4.deposit amount \n 5.exit\nEnter your choise:");
    int ch=in.nextInt();
    switch(ch){
      case 1:
      System.out.print("Enter account holder name: ");
      String name=in.next();
      System.out.print("Enter account number: ");
      String ac=in.next();
      Account a=new Account(name,ac);
      sb.add(a);

          break;
      case 2:
          break;
      case 3:
          break;
      case 4:
          break;
      case 5:
          System.out.println("exiting ....");
          System.exit(0);
          break;
      default:
          System.out.print("Invalid choise");
      }
    }
  }
}
