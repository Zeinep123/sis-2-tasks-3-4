import java.sql.*;

public class MyDatabase {
    public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/EmployeeDB";
        String user = "root";
        String password = "";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stm = conn.createStatement();

            String insertSQL = "INSERT INTO Employees (first, last, age) VALUES ('Aibek', 'Sarah', 30)";
            stm.executeUpdate(insertSQL);
            System.out.println("Record inserted successfully.");
            

            String selectSQL = "SELECT id, first, last, age FROM Employees";
            ResultSet rs = stm.executeQuery(selectSQL);
            while (rs.next()) {
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", Age: " + rs.getInt("age"));
                System.out.print(", First: " + rs.getString("first"));
                System.out.println(", Last: " + rs.getString("last"));
            }

            String updateSQL = "UPDATE Employees SET age = 31 WHERE first = 'Aibek' AND last = 'Sarah'";
            stm.executeUpdate(updateSQL);
            System.out.println("Record updated successfully.");
            

            String deleteSQL = "DELETE FROM Employees WHERE first = 'Aibek' AND last = 'Sarah'";
            stm.executeUpdate(deleteSQL);
            System.out.println("Record deleted successfully.");
           

            conn.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}   //SIS 2301 Azatkyzy Zeinep
