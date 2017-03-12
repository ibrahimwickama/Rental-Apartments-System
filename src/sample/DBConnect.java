package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static Connection con;

    public static Connection getConnected() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        if (con == null) {
            Class.forName("org.sqlite.JDBC").newInstance();
            con = DriverManager.getConnection("jdbc:sqlite:RRMSDataBase.db");

        }
        return con;
    }
}
