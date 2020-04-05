package sqlconnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.stream.StreamSupport;

public class SQLiteJDBC {
    private static Connection conn;


    //connects to the tatabase
    public static void connect(String nameDB) {

        try {
            String url = "jdbc:sqlite:" + nameDB;
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
            System.out.println("CONNECTED");

        } catch(ClassNotFoundException c) {
            c.printStackTrace();
        } catch(SQLException s) {
            s.printStackTrace();
        }
    }

    public void CreateRegistrationTable() {
        try {
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

            } catch (SQLException s) {
                s.printStackTrace();
            } finally {
               if(stmt != null)
                    stmt.close();
                if(conn != null)
                    conn.close();
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        }


    //makes a querry request to the database in search for a specified object in a specified table
    //TO DO : make an enhanged version to select single entries
    public String selectStatement(String nameDB,String sql_command) {
        try {
            this.connect(nameDB);
            Statement stmt = null;
            ResultSet rs = null;

            try {


                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql_command);
                String result = rs.getString(1);

                return result;

            } catch (SQLException s) {
                s.printStackTrace();

            } finally {
                if(stmt != null)
                    stmt.close();
                if(rs != null)
                    rs.close();
                if(conn != null)
                    conn.close();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
        }



    //the insertion is hardcoded
    public void insertRegistration(String firstname, String lastname, String email, String username, String password) {
        try {
            this.connect("test.db");
            Statement stmt = null;
            String values = "(" + "'" + firstname + "'" + "," + "'" + lastname + "'" + "," + "'" + email + "'" + "," + "'" + username + "'" + "," + "'" + password + "'" + ")";
            System.out.println(values);

            try {
                stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO REGISTRATION(firstname, lastname,email,username,password)" + "VALUES" + values);
                System.out.println("DATA INSERTED");
            } catch(SQLException s) {
                s.printStackTrace();
            } finally {
                if(stmt != null)
                    stmt.close();
                if(conn != null)
                    conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        }

    }
