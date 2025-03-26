import java.util.*;
class Account{
    String name;
    String acno;
    int acbal=0;
    Account(String n,String no){
        this.name=n;
        this.acno=no;
        System.out.println("Account created !\n-------------------- \nAccount holder: "+n+"\nAccount number: "+no+"\nAccount balance: "+acbal);
    }

}
public class App{
    static void balance(String an,ArrayList<Account> sb){
        if(sb.isEmpty())
        System.out.println("create an account first!");
        int found=0;
      for(Account b:sb){
        if(an.equals(b.acno)){
            found=1;
            System.out.println("---------------------------------------");
            System.out.println("Account Holder:"+b.name+"\nAccount number: "+b.acno+"\nAccount balance: "+b.acbal);
        }
      }
      if(found==0)
        {
            System.out.println("Account number: "+an+" is invalid ");
        }
    }
    
    static void withdraw(String an,int amt,ArrayList<Account> sb){
        int found=0;
    if(amt>0){
      for(Account b:sb){
        if(an.equals(b.acno)){
            if(b.acbal>=amt){
            System.out.println("Amount withdrawed successfully!");
            found=1;
            b.acbal-=amt;
            System.out.println("---------------------------------------");
            System.out.println("Account Holder:"+b.name+"\nAccount number: "+b.acno+"\nAccount balance: "+b.acbal);
            }
            else{
                System.out.println("Amount withdrawal failed!(insufficient Balance : "+b.acbal+") ");
                found=1;
                }
            }
          }
      if(found==0)
        {
            System.out.println("Amount withdrawal failed! Account number: "+an+" is not exist ");
        }
    }
    else{
        System.out.println("Amount withdrawal failed! "+amt+" is not valid amount");
    }
    }
    static void deposit(String an,int amt,ArrayList<Account> sb){
        int found=0;
    if(amt>0){
      for(Account b:sb){
        if(an.equals(b.acno)){
            
            System.out.println("Amount Deposited successfully!");
            found=1;
            b.acbal+=amt;
            System.out.println("---------------------------------------");
            System.out.println("Account Holder:"+b.name+"\nAccount number: "+b.acno+"\nAccount balance: "+b.acbal);
        }
      }
      if(found==0)
        {
            System.out.println("Amount Deposit failed!");
            System.out.println("Account number: "+an+" is invalid ");
        }
    }
    else{
        System.out.println("Amount withdrawal failed! "+amt+" is not valid amount");
    }
    }
public static void main(String[] args){
Scanner in=new Scanner(System.in);
String an;
int amt;
System.out.println("Bank management system");
System.out.print("___________________________________");
ArrayList<Account> sb=new ArrayList<Account>();
while(true){
    System.out.println("\nOpertaions:\n 1.create Account \n 2.view balance \n 3.withdraw amount \n 4.deposit amount \n 5.exit");
    System.out.print("Enter your choise: ");
    int ch=in.nextInt();
    switch(ch){
      case 1:
        System.out.print("Enter account holder name: ");
        String name=in.next();
        System.out.print("Enter account number: ");
        String ac=in.next();
        Account a=new Account(name,ac);
        sb.add(a);
        
        //System.out.print();
        break;
      case 2:
          System.out.print("Enter account number: ");
       an=in.next();
        balance(an,sb);
          break;
      case 3:
          System.out.print("Enter account number: ");
         an=in.next();
         System.out.print("Enter withdrawalamount: ");
         amt=in.nextInt();
          withdraw(an,amt,sb);
          break;
      case 4:
          System.out.print("Enter account number: ");
         an=in.next();
         System.out.print("Enter depositamount: ");
         amt=in.nextInt();
         deposit(an,amt,sb);
          break;
      case 5:
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
