import java.sql.DriverManager;
import java.sql.SQLException;

public class Conneection123 {

    public java.sql.Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        java.sql.Connection conl = DriverManager.getConnection("jdbc:mysql:/localhost/inventory", "root", "");
        return conl;
    }
}
