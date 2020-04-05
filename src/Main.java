import sqlconnection.*;
import GUI.*;

import java.sql.SQLException;

public class Main {
    public static void main(String args[]) {
        SQLiteJDBC sqlitejdbc = new SQLiteJDBC();

        //table name + values is hardcoded
        sqlitejdbc.CreateRegistrationTable();
        sqlitejdbc.insertRegistration("bogdan", "boby", "bogdanATyahoocom", "bbogdanel", "ancd");
        LoginPage lp = new LoginPage();
       lp.startLoginPage();


    }
}
