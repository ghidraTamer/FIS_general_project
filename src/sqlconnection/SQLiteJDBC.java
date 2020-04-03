package sqlconnection;
import java.sql.*;
import java.util.ArrayList;

public class SQLiteJDBC {
    private static Connection conn;


    //connects to the tatabase
    public static void connect(String nameDB) {
        try {

            String url = "jdbc:sqlite:"+nameDB;
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

            System.out.println("CONNECTED");
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void CreateRegistrationTable() {
        this.connect("test.db");

        Statement stmt = null;
        String sql = "CREATE TABLE IF NOT EXISTS REGISTRATION (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    firstname text NOT NULL,\n"
                + "    lastname text NOT NULL,\n"
                + "    email text NOT NULL,\n"
                + "    username text NOT NULL,\n"
                + "    password text NOT NULL\n"
                + ");";

        try {
            stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("CREATED");

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


    }

//makes a querry request to the database in search for a specified object in a specified table
    //TO DO : make an enhanged version to select single entries
    public void selectStatement(String selectData) {
        this.connect("test.db");
        Statement stmt = null;
        String[] foundQuerry;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectData);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    //the insertion is hardcoded
    public void insertRegistration(String firstname, String lastname, String email, String username, String password) {
        this.connect("test.db");

        Statement stmt = null;
        String values = "(" + "'" +firstname + "'" + "," + "'"+lastname + "'" +"," + "'" + email + "'" + "," + "'" + username + "'" + "," + "'" + password+"'" + ")";
        System.out.println(values);

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO REGISTRATION(firstname, lastname,email,username,password)"+ "VALUES"+values);
            System.out.println("DATA INSERTED");

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            }


}
