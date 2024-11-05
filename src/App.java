import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

            createAnEmployee(connection);
            getAllEmployees(connection);

            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    public static void getAllEmployees(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM employees";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("==================================================");
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Role: " + resultSet.getString("role"));
                System.out.println("Salary: " + resultSet.getDouble("salary"));
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    public static void createAnEmployee(Connection connection) {
        try {
            // String insertQuery = "INSERT INTO employees (id, name, role, salary) VALUE (?, ?, ?, ?)";

            // PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            // preparedStatement.setInt(1, 4);
            // preparedStatement.setString(2, "Rohit");
            // preparedStatement.setString(3, "Frontend");
            // preparedStatement.setDouble(4, 70000.0);

            // int rowsAffected = preparedStatement.executeUpdate(insertQuery);

            Statement statement = connection.createStatement();
            String insertQuery = "INSERT INTO employees (id, name, role, salary) VALUE (4, 'Rohit', 'Frontend', 70000)";
            int rowsAffected = statement.executeUpdate(insertQuery);
            if (rowsAffected > 0) {
                System.out.println("Employee created sucessfully");
            } else {
                System.err.println("Insertion failed");
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }
}
