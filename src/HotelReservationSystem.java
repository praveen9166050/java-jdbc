import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
                        viewReservations(connection);
                        break;
                    case 3:
                        getRoomNumber(connection, scanner);
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
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

            String sqlQuery = "INSERT INTO reservations (guest_name, room_number, contact_number) VALUES ('" + guestName + "', " + roomNumber + ", '" + contactNumber + "')";
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

    public static void viewReservations(Connection connection) throws SQLException {
        String sqlQuery = "SELECT reservation_id, guest_name, room_number, contact_number, reservation_date FROM reservations";

        try (
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
        ) {

            System.out.println("Current Reservations:");
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
            System.out.println("| Reservation ID | Guest           | Room Number   | Contact Number      | Reservation Date        |");
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");

            while (resultSet.next()) {
                int reservationId = resultSet.getInt("reservation_id");
                String guestName = resultSet.getString("guest_name");
                int roomNumber = resultSet.getInt("room_number");
                String contactNumber = resultSet.getString("contact_number");
                String reservationDate = resultSet.getTimestamp("reservation_date").toString();

                // Format and display the reservation data in a table-like format
                System.out.printf("| %-14d | %-15s | %-13d | %-20s | %-19s   |\n", reservationId, guestName, roomNumber, contactNumber, reservationDate);
            }
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
        }
    }

    private static void getRoomNumber(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter reservation ID: ");
            int reservationId = scanner.nextInt();
            System.out.print("Enter guest name: ");
            String guestName = scanner.next();

            String sqlQuery = "SELECT room_number FROM reservations WHERE reservation_id = " + reservationId + " AND guest_name = '" + guestName + "'";

            try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);
            ) {

                if (resultSet.next()) {
                    int roomNumber = resultSet.getInt("room_number");
                    System.out.println("Room number for Reservation ID " + reservationId + " and Guest " + guestName + " is: " + roomNumber);
                } else {
                    System.out.println("Reservation not found for the given ID and guest name.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
