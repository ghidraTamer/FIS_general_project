package sqlconnection;
import java.sql.*;

public class SQLiteJDBC {

    public static void connect() {
        try {

            String url = "jdbc:sqlite:test.db";
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);

            System.out.println("CONNECTED");
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



}
