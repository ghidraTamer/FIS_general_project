import sqlconnection.*;

import java.sql.SQLException;

public class Main {
    public static void main(String args[]) {
        SQLiteJDBC sqlitejdbc = new SQLiteJDBC();

        try {
            //table name + values is hardcoded
            sqlitejdbc.CreateRegistrationTable();
            sqlitejdbc.insertRegistration("bogdan", "boby", "bogdanATyahoocom", "bbogdanel", "ancd");
            sqlitejdbc.selectStatement("SELECT * FROM REGISTRATION");


        } catch(SQLException e) {}
          catch(ClassNotFoundException e) {}
    }
}
