package BankBackend;

import java.sql.*;

public class Backend {
    static Connection con = null;
    public Backend(){
        try{
        ConnectDB("jdbc:mysql://localhost:3306/pk31", "root", "1234");
        createTable();
        }catch(Exception e){
            System.out.print(e);
        }
    }
    public static void ConnectDB(String url, String user, String pass) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        System.out.println("Connected to DB successfully!");
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


            boolean b = st.execute(create_query);
            
        } catch (Exception e) {
            System.out.println("Error in createTable(): " + e);
        }
    }

    public  void insertValues(long acno,String acholder,float acbal,String ac_status,String actype){
        try {
        createTable();
        String insert_query = "INSERT INTO bank ( ACCOUNT_NO , ACCOUNT_HOLDER, BALANCE, ACCOUNT_STATUS, ACCOUNT_TYPE) VALUES ( ?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(insert_query);

        //pst.setInt(1, s_no);
        pst.setLong(1, acno);
        pst.setString(2, acholder);
        pst.setFloat(3, acbal);
        pst.setString(4, ac_status);
        pst.setString(5, actype);

        int rows = pst.executeUpdate();
        if (rows > 0)
            System.out.println("Account inserted.");
        else
            System.out.println("Insert failed.");
    } catch (Exception e) {
        System.out.println("Insert error: " + e.getMessage());
    }
    }
    public  boolean containsAccount(long ac_no){
        try{
            String fetch_query="SELECT * FROM bank WHERE ACCOUNT_NO =?;";
            PreparedStatement pst = con.prepareStatement(fetch_query);
            pst.setLong(1, ac_no);
            ResultSet rs = pst.executeQuery();
            System.out.println("Records in the database:");
            if(rs.next()){
                //System.out.println("Ac_holder: "+rs.getString("ACCOUNT_HOLDER"));
                return true;
            }
            
        }catch(Exception e){
            System.out.print(e);
        }
        return false;
    }
    public String fetchAccount(long ac_no){
        try{
            String fetch_query="SELECT * FROM bank WHERE ACCOUNT_NO =?;";
            PreparedStatement pst = con.prepareStatement(fetch_query);
        pst.setLong(1, ac_no);
        ResultSet rs = pst.executeQuery();
            System.out.println("Records in the database:");
            if(rs.next()){
                String acc="Account No: "+rs.getLong("ACCOUNT_NO")+"\tAccount Holder: "+rs.getString("ACCOUNT_HOLDER")+"\tAccount Balance: "+rs.getFloat("BALANCE")+"\tAccount Type: "+rs.getString("ACCOUNT_TYPE")+"\tAccount Status: "+rs.getString("ACCOUNT_STATUS")+"";
                return acc;
            }
            return new String("No Account found");
            
        }catch(Exception e){
            System.out.print(e);
            return "ERROR";
        }
    }
    public void removeFromDb(long ac_no){
        try{
            String remove_query="DELETE FROM bank WHERE ACCOUNT_NO=(?)";
           PreparedStatement pst = con.prepareStatement(remove_query);
        pst.setLong(1, ac_no);
        int rowsAffected = pst.executeUpdate(); 
        if (rowsAffected > 0) {
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("No account found with Account No: " + ac_no);
        }

        pst.close();
        }catch(Exception e){
            System.out.print(e);
        }
    }

    public static void main(String[] args) {
        
    }
}
