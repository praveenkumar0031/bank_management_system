
import BankBackend.*;

class Account {
    Backend db = new Backend();
    String acholder = "";
    long acno;
    String actype = "";
    String acstatus = "INACTIVE";
    float acbal = 0;

    public void createAccount(String acholder, String actype, float acbal, int freeno) {
        if (isValidAccount(acholder, actype)) {
            this.acholder = acholder.toUpperCase();
            this.actype = actype.toUpperCase();
            this.acbal = acbal;
            this.acno = generateAcNO(freeno);
            this.acstatus = "ACTIVE";
            db.insertValues(this.acno, this.acholder, this.acbal, this.acstatus, this.actype);
            System.out.println("--------------------|Account created |-------------------- \n");
        }
    }

    // validate name-- full_alphabet and acc type(savings,current,fd,rd)
    // I/P->(name,acctype) O/P->T/F
    private static boolean isValidAccount(String name, String actype) {
        for (char c : name.toCharArray()) {
            if (!Character.isAlphabetic(c) && c != ' ') {
                return false;
            }
        }
        String type = actype.toUpperCase();
        if (type.equals("SAVINGS") || type.equals("CURRENT") || type.equals("FD") || type.equals("RD")) {
            return true;
        } else {
            String msg = "Savings,Current,Fd,Rd";
            invalidMsg(msg);
            return false;
        }
    }

    // method should have 4 digit no only
    private static long generateAcNO(int lastno) {
        return (832000070000l + lastno);
    }

    public void removeAccount(long ac_no) {

        if (db.containsAccount(ac_no)) {
            db.removeFromDb(ac_no);
        } else {
            System.out.print("Invlaid Account Number");
        }
    }

    private static void invalidMsg(String msg) {
        System.out.println("Invalid option ! choose (" + msg + ")");
    }

    // balance
    public void Viewbalance(long ac_no) {
        if (db.containsAccount(ac_no)) {
            System.out.println("-----Account Balance-----");
            db.ViewblanceDB(ac_no);
        } else {
            System.out.println("Account not available!");
        }
    }

    public void withdrawMoney(long ac_no, float amt) {
        if (db.containsAccount(ac_no)) {
            float cr_amt = db.fetchAmount(ac_no);
            if (cr_amt > amt) {
                db.changeAccountAmount(ac_no, amt, cr_amt, 'W');
            } else {
                System.out.println("Insufficient balance Rs." + cr_amt);
            }
        } else {
            System.out.println("Account not available!");
        }
    }

    public void depositMoney(long ac_no, float amt) {
        if (db.containsAccount(ac_no)) {
            float cr_amt = db.fetchAmount(ac_no);

            db.changeAccountAmount(ac_no, amt, cr_amt, 'D');

        } else {
            System.out.println("Account not available!");
        }
    }

    public void DeactivateAccount(long ac_no) {
        if (db.containsAccount(ac_no)) {
            db.DeactivateAccountDb(ac_no);
        } else {
            System.out.println("Account not available!");
        }
    }

    public void TransferMoney(long sac_no, long rac_no, float amt) {
        if (db.containsAccount(sac_no)) {
            System.out.println("Sender Account Verified...");
            float cr_amt = db.fetchAmount(sac_no);
            if (cr_amt > amt) {
                db.changeAccountAmount(sac_no, amt, cr_amt, 'W');
            } else {
                System.out.println("Insufficient balance in Sender's Acount Rs." + cr_amt);
            }

            if (db.containsAccount(rac_no)) {
                System.out.println("Reciver Account Verified...");
                float r_cr_amt = db.fetchAmount(rac_no);

                db.changeAccountAmount(rac_no, amt, r_cr_amt, 'D');

                
            }
        }

    }
}