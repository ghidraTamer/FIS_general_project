package sqlconnection;
import java.sql.*;
import java.util.ArrayList;

public class SQLiteJDBC {
    private static Connection conn;


    //connects to the tatabase
    public static void connect(String nameDB) throws ClassNotFoundException, SQLException {


            String url = "jdbc:sqlite:" + nameDB;
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

            System.out.println("CONNECTED");

    }

    public void CreateRegistrationTable() throws SQLException, ClassNotFoundException {
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

        stmt = conn.createStatement();
        stmt.execute(sql);
        System.out.println("CREATED");
        stmt.close();
        conn.close();
        }


    //makes a querry request to the database in search for a specified object in a specified table
    //TO DO : make an enhanged version to select single entries
    public void selectStatement(String selectData) throws SQLException, ClassNotFoundException {
        this.connect("test.db");
        Statement stmt = null;
        String[] foundQuerry;

        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(selectData);

        stmt.close();
        conn.close();
        }



    //the insertion is hardcoded
    public void insertRegistration(String firstname, String lastname, String email, String username, String password) throws SQLException, ClassNotFoundException {
        this.connect("test.db");

        Statement stmt = null;
        String values = "(" + "'" + firstname + "'" + "," + "'" + lastname + "'" + "," + "'" + email + "'" + "," + "'" + username + "'" + "," + "'" + password + "'" + ")";
        System.out.println(values);

        stmt = conn.createStatement();
        stmt.executeUpdate("INSERT INTO REGISTRATION(firstname, lastname,email,username,password)" + "VALUES" + values);
        System.out.println("DATA INSERTED");

        stmt.close();
        conn.close();

        }

    }
