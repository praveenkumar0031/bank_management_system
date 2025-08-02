import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        System.out.println("name:");
        String name=in.nextLine();
        System.out.println("Acc Type:");
        String type=in.nextLine();
        System.out.println("Amt :");
        Float amt=in.nextFloat();
        int freeno=23;
        Account a=new Account(name, type, amt, freeno);
        System.out.print("Name: "+a.acholder+"\nACC NO: "+a.acno+"\nACC sattus: "+a.acstatus+"\nACC BALANCE: "+a.acbal+"\nACC TYPE: "+a.actype);
    }
}
