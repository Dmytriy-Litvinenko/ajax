package testtask.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/initial_test";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String ENCODING_PARAMETERS="useUnicode=true&characterEncoding=utf8";

    public static Connection getConnection() {

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR: can't load mysql driver");
        }

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL+"?user="+USER+"&password="+PASSWORD+"&"+ENCODING_PARAMETERS);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed");
        }
        return conn;
    }

}
