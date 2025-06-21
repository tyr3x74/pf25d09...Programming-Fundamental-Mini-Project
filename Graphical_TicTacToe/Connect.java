import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class Connect {
    private static String host;
    private static String port;
    private static String databaseName;
    private static String userName;
    private static String password;

    /**
     * Sets the database connection parameters. This must be called before
     * attempting to get a connection.
     */
    public static void setConnectionParams(String h, String p, String dbName, String uName, String pwd) {
        host = h;
        port = p;
        databaseName = dbName;
        userName = uName;
        password = pwd;
    }

    /**
     * Establishes and returns a new database connection.
     * @return A valid database Connection object.
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the JDBC driver cannot be found.
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (host == null || port == null || databaseName == null) {
            throw new SQLException("Database connection parameters are not set. Call Connect.setConnectionParams() first.");
        }
        // Load the MySQL JDBC driver class
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?sslmode=require"; // Use sslmode for Aiven
        return DriverManager.getConnection(url, userName, password);
    }

    // This main method can be used for testing the connection independently.
    public static void main(String[] args) throws ClassNotFoundException {
        String testHost, testPort, testDatabaseName, testUserName, testPassword;
        testHost = testPort = testDatabaseName = testUserName = testPassword = null;
        for (int i = 0; i < args.length - 1; i++) {
            switch (args[i].toLowerCase(Locale.ROOT)) {
                case "-host": testHost = args[++i]; break;
                case "-username": testUserName = args[++i]; break;
                case "-password": testPassword = args[++i]; break;
                case "-database": testDatabaseName = args[++i]; break;
                case "-port": testPort = args[++i]; break;
            }
        }

        if (testHost == null || testPort == null || testDatabaseName == null) {
            System.out.println("Host, port, database information is required as command line arguments for testing.");
            return;
        }

        setConnectionParams(testHost, testPort, testDatabaseName, testUserName, testPassword);

        try (final Connection connection = getConnection();
             final Statement statement = connection.createStatement();
             final ResultSet resultSet = statement.executeQuery("SELECT version() AS version")) {

            while (resultSet.next()) {
                System.out.println("Database Version: " + resultSet.getString("version"));
            }
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }
}