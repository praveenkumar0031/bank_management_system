import java.util.*;
//import BankBackend.*;

public class App {
  static Scanner in = new Scanner(System.in);

  public static char choiseMsg() {

    System.out.println("\nEnter your choise: ");
    System.out.println(" 1.create Account \n" +
        " 2.View Balance \n" +
        " 3.Withdraw Amount \n" +
        " 4.Deposit Amount \n" +
        " 5.Money Transfer \n" +
        " 6.De-activate\n" +
        " 7.Quit");
    System.out.print(" ");
    char ch = in.next().charAt(0);
    return ch;
  }

  public static long getAccountNo() {
    System.out.print("Enter Account Number (83200007xxxx) :");
    long acno = in.nextLong();
    return acno;
  }

  public static void fundTransfer(Account ac) {
    System.out.print("Enter Sender Account Number (83200007xxxx) : ");
    long s_acno = in.nextLong();
    System.out.print("Enter Receiver Account Number (83200007xxxx) : ");
    long r_acno = in.nextLong();
    System.out.print("\nEnter Transfer Amount: Rs.");
    float amt = in.nextFloat();
    ac.TransferMoney(s_acno, r_acno, amt);

  }
    static boolean iscrtPass(String p){
      if(p.equals("admin01"))
        return true;
      return false;
    }

  public static void main(String[] args) {

    // ac.demo();
    // ac.removeAccount(832000070025L);
    long acno;
    Float amt;
    Account ac = new Account();
    System.out.println("Bank management system");
    System.out.println("___________________________________");
    while (true) {
      char ch = choiseMsg();
      switch (ch) {
        case '1':
          System.out.print("  Enter account holder name: ");
          in.nextLine();
          String name = in.nextLine();
          // System.out.print(" Enter account number: ");
          // Long ac=in.nextLong();

          System.out.println();
          System.out.print("  Enter account Type(SAVINGS/CURRENT/FD/RD): ");
          String type = in.next();
          System.out.print("  Enter Initial Deposit Amount :Rs.");
          amt = in.nextFloat();
          int no = (int) (Math.random() * 9000) + 1000;
          ac.createAccount(name, type, amt, no);
          System.out.print("Account Number: " + ac.acno);

          // System.out.print();
          break;
        case '2':
          acno = getAccountNo();

          ac.Viewbalance(acno);

          break;
        case '3':
          acno = getAccountNo();

          System.out.print("Enter withdrawal Amount: Rs.");
          amt = in.nextFloat();
          ac.withdrawMoney(acno, amt);

          break;
        case '4':
          acno = getAccountNo();
          System.out.print("Enter deposit Amount: Rs.");
          amt = in.nextFloat();
          ac.depositMoney(acno, amt);
          break;
        case '5':
          fundTransfer(ac);
          break;
        case '6':
          System.out.println("Deactivation of Account");
          acno = getAccountNo();
          ac.DeactivateAccount(acno);
          break;
        case '7':
          System.out.println("exiting ....");
          System.out.println("Thanks for using");
          System.exit(0);
          break;
        case '#':
          String admin="[mode:Admin] :";
          System.out.println("\n"+admin);
          System.out.print("Enter admin password: ");
          String pwd=in.next();
          if(iscrtPass(pwd)){
            ac.adminFunc();
          }
          break;
        default:
          System.out.print("Invalid choise");

      }
    }

  }
}
