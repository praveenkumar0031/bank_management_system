package BankBackend;

import java.sql.*;

public class Backend {
    static Connection con = null;

    public Backend() {
        try {
            ConnectDB("jdbc:mysql://localhost:3306/pk31", "root", "1234");
            createTable();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void ConnectDB(String url, String user, String pass) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        // System.out.println("Connected to DB successfully!");
    }

    private static void createTable() {
        try {
            Statement st = con.createStatement();
            String create_query = "CREATE TABLE IF NOT EXISTS bank ("
                    + "S_NO INT NOT NULL AUTO_INCREMENT, "
                    + "ACCOUNT_NO BIGINT UNIQUE, "
                    + "ACCOUNT_HOLDER VARCHAR(20), "
                    + "BALANCE FLOAT, "
                    + "ACCOUNT_STATUS VARCHAR(10), "
                    + "ACCOUNT_TYPE VARCHAR(10), "
                    + "PRIMARY KEY (S_NO)"
                    + ");";

            st.execute(create_query);

        } catch (Exception e) {
            System.out.println("Error in createTable(): " + e);
        }
    }

    public void insertValues(long acno, String acholder, float acbal, String ac_status, String actype) {
        try {
            createTable();
            String insert_query = "INSERT INTO bank ( ACCOUNT_NO , ACCOUNT_HOLDER, BALANCE, ACCOUNT_STATUS, ACCOUNT_TYPE) VALUES ( ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(insert_query);

            // pst.setInt(1, s_no);
            pst.setLong(1, acno);
            pst.setString(2, acholder);
            pst.setFloat(3, acbal);
            pst.setString(4, ac_status);
            pst.setString(5, actype);

            int rows = pst.executeUpdate();
            if (rows > 0)
                System.out.println("Account created.");
            else
                System.out.println("Insert failed.");
        } catch (Exception e) {
            System.out.println("Insert error: " + e.getMessage());
        }
    }

    public boolean containsAccount(long ac_no) {
        try {
            String fetch_query = "SELECT * FROM bank WHERE ACCOUNT_NO =?;";
            PreparedStatement pst = con.prepareStatement(fetch_query);
            pst.setLong(1, ac_no);
            ResultSet rs = pst.executeQuery();
            // System.out.println("Records in the database:");
            if (rs.next()) {
                // System.out.println("Ac_holder: "+rs.getString("ACCOUNT_HOLDER"));
                return true;
            }
            return false;

        } catch (Exception e) {
            System.out.print(e);
        }
        return false;
    }

    public void fetchAccountDB(long ac_no) {
        try {
            String fetch_query = "SELECT * FROM bank WHERE ACCOUNT_NO =?;";
            PreparedStatement pst = con.prepareStatement(fetch_query);
            pst.setLong(1, ac_no);
            ResultSet rs = pst.executeQuery();
            // System.out.println("Records in the database:");
            if (rs.next()) {
                System.out.println("Account No: " + rs.getLong("ACCOUNT_NO") + "\nAccount Holder: "
                        + rs.getString("ACCOUNT_HOLDER") + "\nAccount Balance: " + rs.getFloat("BALANCE")
                        + "\nAccount Type: " + rs.getString("ACCOUNT_TYPE") + "\nAccount Status: "
                        + rs.getString("ACCOUNT_STATUS"));
            } else
                System.out.println("No Account found");
            // return new

        } catch (Exception e) {
            System.out.print(e);
            // return "ERROR";
        }
    }

    public void ViewblanceDB(long ac_no) {
        try {
            String fetch_query = "SELECT ACCOUNT_NO,ACCOUNT_HOLDER,BALANCE,ACCOUNT_TYPE FROM bank WHERE ACCOUNT_NO =?;";
            PreparedStatement pst = con.prepareStatement(fetch_query);
            pst.setLong(1, ac_no);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // System.out.println("Account No: "+rs.getLong("ACCOUNT_NO")+"\nAccount Holder:
                // "+rs.getString("ACCOUNT_HOLDER")+"\nAccount Balance:
                // "+rs.getFloat("BALANCE")+"\nAccount Type:
                // "+rs.getString("ACCOUNT_TYPE")+"\nAccount Status:
                // "+rs.getString("ACCOUNT_STATUS"));

                System.out.println("Account Number: " + rs.getLong("ACCOUNT_NO") + "\nAccount Holder: "
                        + rs.getString("ACCOUNT_HOLDER") + "\nAccount Balance: Rs." + rs.getFloat("BALANCE")
                        + "\nAccount Type: " + rs.getString("ACCOUNT_TYPE"));
            } else
                System.out.println("No Account found");
            // return new

        } catch (Exception e) {
            System.out.print(e);
            // return "ERROR";
        }
    }

    public void removeFromDb(long ac_no) {
        try {
            String remove_query = "DELETE FROM bank WHERE ACCOUNT_NO=(?)";
            PreparedStatement pst = con.prepareStatement(remove_query);
            pst.setLong(1, ac_no);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Account deleted successfully.");
            } else {
                System.out.println("No Account found with Account Number: " + ac_no);
            }

            pst.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void changeAccountAmount(long ac_no, float amt, float cr_amt, char ch) {
        try {
            if (fetchAccountStatus(ac_no)) {
                String amt_query = "UPDATE bank SET BALANCE = (?) WHERE ACCOUNT_NO= (?);";
                PreparedStatement pst = con.prepareStatement(amt_query);
                if (ch == 'W') {
                    pst.setFloat(1, (cr_amt - amt));
                } else {
                    pst.setFloat(1, (cr_amt + amt));
                }
                pst.setLong(2, ac_no);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    if (ch == 'W')
                        System.out.println("Withdrawal of Amount Rs." + amt +" from Account number: "+ac_no+ " is successfully.");
                    else
                        System.out.println("Deposit of Amount Rs." + amt + " from Account number: "+ac_no+" is successfully.");
                } else {
                    System.out.println("No Account found with Account Number: " + ac_no);
                }

                pst.close();
            } else {
                System.out.println("Account is Deactivated !");
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public float fetchAmount(long ac_no) {
        try {
            String fetch_query = "SELECT BALANCE FROM bank WHERE ACCOUNT_NO =?;";
            PreparedStatement pst = con.prepareStatement(fetch_query);
            pst.setLong(1, ac_no);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return (rs.getFloat("BALANCE"));
            } else
                System.out.println("No Account found");
            return -1;

        } catch (Exception e) {
            System.out.print(e);
            return -1;
        }
    }

    private static boolean fetchAccountStatus(long ac_no) {
        try {
            String fetch_query = "SELECT ACCOUNT_STATUS FROM bank WHERE ACCOUNT_NO =?;";
            PreparedStatement pst = con.prepareStatement(fetch_query);
            pst.setLong(1, ac_no);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String sts = rs.getString("ACCOUNT_STATUS");
                if (sts.equals("ACTIVE")) {
                    return true;
                } else {
                    return false;
                }
            } else
                System.out.println("No Account found");
            return false;

        } catch (Exception e) {
            System.out.print(e);
            return false;
        }
    }

    public void DeactivateAccountDb(long ac_no) {
        try {
            String amt_query = "UPDATE bank SET ACCOUNT_STATUS = 'INACTIVE' WHERE ACCOUNT_NO= (?);";
            PreparedStatement pst = con.prepareStatement(amt_query);
            pst.setLong(1, ac_no);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Account Number: " + ac_no + " was Deactivated successfully.");
            } else {
                System.out.println("No Account found with Account Number: " + ac_no);
            }

            pst.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    public void fetchDB() {
        try {
            String fetch_query = "SELECT * FROM bank;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(fetch_query);
            System.out.println("Fetching DataBase.......");
            System.out.println("Sno\t Account no\t Type\t \t Status \tName\t\t Amount(RS)");
            int n=1;
            while(rs.next()) {
                // System.out.println("Account No: "+rs.getLong("ACCOUNT_NO")+"\nAccount Holder:
                // "+rs.getString("ACCOUNT_HOLDER")+"\nAccount Balance:
                // "+rs.getFloat("BALANCE")+"\nAccount Type:
                // "+rs.getString("ACCOUNT_TYPE")+"\nAccount Status:
                // "+rs.getString("ACCOUNT_STATUS"));

                System.out.println(n+"\t " + rs.getLong("ACCOUNT_NO") + "\t "
                        + rs.getString("ACCOUNT_TYPE")+"\t \t"+rs.getString("ACCOUNT_STATUS")+"\t"
                        + rs.getString("ACCOUNT_HOLDER") + "\t\t " + rs.getFloat("BALANCE")
                        );
                        n++;
            } 
            // return new

        } catch (Exception e) {
            System.out.print(e);
            // return "ERROR";
        }
    }
    public static void main(String[] args) {

    }
}
