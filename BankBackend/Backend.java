package BankBackend;
import java.sql.*;

public class Backend {
    Connection con = null;

    public void ConnectDB(String url, String user, String pass) throws Exception {
            con = DriverManager.getConnection(url, user, pass);// "jdbc:mysql://localhost:3306/sample","root","1234");
            }
            
    

    void createTable() {
        try {
            Statement st = con.createStatement();
            String create_query = "create table if not exists Stud1(id int,name varchar(20))";
            boolean b = st.execute(create_query);
            if (!b)
                System.out.println("table created/ present");
            else {
                System.out.println("not created");
            }
        } catch (Exception E) {
            System.out.println(E);
        }
    }
    public static void main(String[] args) {
        try{
            Backend obj=new Backend();
            obj.ConnectDB("jdbc:mysql://localhost:3306/sample","root","1234");
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
