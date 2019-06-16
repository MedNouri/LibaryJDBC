package application.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnexionHandler {

    private static ConnexionHandler instance;
    private Connection connection;
    private static final String DATABASE_URL = "jdbc:mysql://localhost/bibliothèque";

    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private ConnexionHandler() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

        }catch (SQLException e){
            System.out.print(e);
        }finally {
            if (this.connection != null){
                //   this.connection.close();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static ConnexionHandler getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnexionHandler();
        } else if (instance.getConnection().isClosed()) {
            instance = new ConnexionHandler();
        }

        return instance;
    }
}