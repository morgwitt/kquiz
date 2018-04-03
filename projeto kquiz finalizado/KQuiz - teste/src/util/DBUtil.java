package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    public static String urlConnection = "localhost:3307/kquiz";
    public static String driver = "com.mysql.jdbc.Driver";
    public static String user = "root";
    public static String password = "85129487";

    public static Connection getConnetion() {
        Connection connection = null;
        try {
            Class.forName(driver);
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:mysql://" + urlConnection, user, password);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Falha na conexão com banco de dados.");
        }

        return connection;
    }

    public static boolean tryConnection() {
        Connection connection = null;
        boolean flag = true;
        try {
            Class.forName(driver);
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:mysql:" + urlConnection, user, password);
            }
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

}
