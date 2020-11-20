
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConectaBanco {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String HOST = System.getenv("DATABASE_HOST");
    private static final String PORT = System.getenv("DATABASE_PORT");
    private static final String DATABASENAME = System.getenv("DATABASE_NAME");
    private static final String USERNAME = System.getenv("DATABASE_USER");
    private static final String PASSWORD = System.getenv("DATABASE_PASSWORD");

    private static final String URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASENAME; //+ "?sslmode=require&sslfactory=org.postgresql.ssl.NonValidatingFactory";
    public PreparedStatement prepareStatement;


    public static Connection getConnection() throws SQLException {

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ex) {
            System.out.println("Where is your PostgreSQL JDBC Driver? Include in your pom.xml");
            return null;
        }

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

}