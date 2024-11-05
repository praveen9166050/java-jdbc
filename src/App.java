import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/employees";
        String user = "root";
        String password = "2024";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful");

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM EMPLOYEES";

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("==================================================");
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Role: " + resultSet.getString("role"));
                System.out.println("Salary: " + resultSet.getDouble("salary"));
            }
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
