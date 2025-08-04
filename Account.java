
import BankBackend.*;
class Account{
    Backend db=new Backend();
    String acholder="";
    long acno;
    String actype="";
    String acstatus="INACTIVE";
    float acbal=0;
    Account(String acholder,String actype,float acbal,int freeno){
        if(isValidAccount(acholder,actype)){
        this.acholder=acholder.toUpperCase();
        this.actype=actype.toUpperCase();
        this.acbal=acbal;
        this.acno=generateAcNO(freeno);
        this.acstatus="ACTIVE";
        db.insertValues( this.acno, this.acholder, this.acbal, this.acstatus, this.actype);
        System.out.println("--------------------|Account created |-------------------- \n");
        }
    }
    //validate name-- full_alphabet and acc type(savings,current,fd,rd) I/P->(name,acctype) O/P->T/F
    private static boolean isValidAccount(String name,String actype){
        for(char c:name.toCharArray()){
            if(!Character.isAlphabetic(c) && c!=' '){
                return false;
            }
        }
        String type=actype.toUpperCase();
        if(type.equals("SAVINGS")||type.equals("CURRENT")||type.equals("FD")||type.equals("RD")){
        return true;
        }
        else{
            String msg="Savings,Current,Fd,Rd";
            invalidMsg(msg);
            return false;
        }
    }
    //method should have 4 digit no only
    private static long generateAcNO(int lastno){
        return (832000070000l+lastno);
    }
    public void removeAccount(long ac_no){
            
            if(db.containsAccount(ac_no)){
                db.removeFromDb(ac_no);
        }
        else{
            System.out.print("Invlaid Account Number");
        }
    }
    private static void invalidMsg(String msg){
        System.out.println("Invalid option ! choose ("+msg+")");
    }
    public void demo(){
        System.out.print("Run...");
    }
}