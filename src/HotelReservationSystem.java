import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class HotelReservationSystem {
    private static final String url = "jdbc:mysql://localhost:3306/employees";
    private static final String user = "root";
    private static final String password = "2024";

    public static void main(String[] args) throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            while (true) {
                System.out.println();
                System.out.println("Hotel Management System");
                System.out.println("1. Reserve a room");
                System.out.println("2. View reservations");
                System.out.println("3. Get room number");
                System.out.println("4. Update reservations");
                System.out.println("5. Delete reservations");
                System.out.println("6. Exit");
                System.out.println("Choose an option: ");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        reserveRoom(connection, scanner);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                        break;
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    public static void reserveRoom(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter guest name: ");
            String guestName = scanner.next();
            scanner.nextLine();
            System.out.println("Enter room number: ");
            int roomNumber = scanner.nextInt();
            System.out.println("Enter contact number: ");
            String contactNumber = scanner.next();

            String sqlQuery = "INSERT INTO reservations (guest_name, room_number, contact_number) VALUES (" + guestName + ", " + roomNumber + ", " + contactNumber + ")";
            try (Statement statement = connection.createStatement()) {
                int rowsAffected = statement.executeUpdate(sqlQuery);
                if (rowsAffected > 0) {
                    System.out.println("Reservation successful");
                } else {
                    System.err.println("Reservation failed");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
