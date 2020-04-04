import sqlconnection.*;
import GUI.*;

import java.sql.SQLException;

public class Main {
    public static void main(String args[]) {
        SQLiteJDBC sqlitejdbc = new SQLiteJDBC();

        try {
            //table name + values is hardcoded
            sqlitejdbc.CreateRegistrationTable();
            sqlitejdbc.insertRegistration("bogdan", "boby", "bogdanATyahoocom", "bbogdanel", "ancd");
            LoginPage lp = new LoginPage();
            lp.startLoginPage();
          // System.out.println(sqlitejdbc.selectStatement("test.db","SELECT username FROM REGISTRATION WHERE username =       'bbogdanel' "));

        } catch(SQLException e) {}
          catch(ClassNotFoundException e) {}
    }
}
