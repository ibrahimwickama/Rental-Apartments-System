package sample;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static Connection con;

    public static Connection getConnected() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String dbName = "RRMSDataBase.db";
        if (con == null) {
            //if (createDB(dbName) && con == null) {
            Class.forName("org.sqlite.JDBC").newInstance();
            con = DriverManager.getConnection("jdbc:sqlite:" + dbName);

        }
        return con;
    }

    private static boolean createDB(String name) {
        File dbFile = new File(name);
        if (!dbFile.exists()) {
            try {
                return dbFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return true;
        }
    }
}
