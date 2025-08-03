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

    public static void createTable() {
        try {
            Statement st = con.createStatement();
            String create_query = "CREATE TABLE IF NOT EXISTS bank (S_NO INT, ACCOUNT_NO BIGINT,ACCOUNT_HOLDER VARCHAR(20),BALANCE FLOAT,ACCOUNT_STATUS VARCHAR(10),ACCOUNT_TYPE VARCHAR(10));";

            boolean b = st.execute(create_query);
            if (!b)
                System.out.println("Table created or already exists.");
            else
                System.out.println("Table creation returned true (unexpected).");
        } catch (Exception e) {
            System.out.println("Error in createTable(): " + e);
        }
    }

    public  void insertValues(int s_no,long acno,String acholder,float acbal,String ac_status,String actype){
        try {
        String insert_query = "INSERT INTO bank (S_NO, ACCOUNT_NO, ACCOUNT_HOLDER, BALANCE, ACCOUNT_STATUS, ACCOUNT_TYPE) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(insert_query);

        pst.setInt(1, s_no);
        pst.setLong(2, acno);
        pst.setString(3, acholder);
        pst.setFloat(4, acbal);
        pst.setString(5, ac_status);
        pst.setString(6, actype);

        int rows = pst.executeUpdate();
        if (rows > 0)
            System.out.println("Account inserted.");
        else
            System.out.println("Insert failed.");
    } catch (Exception e) {
        System.out.println("Insert error: " + e.getMessage());
    }
    }


    public static void main(String[] args) {
        
    }
}
