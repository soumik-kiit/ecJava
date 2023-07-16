package az2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderController {

    @GetMapping("/orders")
    public String getOrders() {
        // Your code to connect to Azure SQL and execute the query
        // Return the response as per your API design

        String serverName = "relailer-order-demo.database.windows.net";
        String databaseName = "Retail_orders";
        String username = "retailer_admin@relailer-order-demo";
        String password = "asdzxc1234#";
        
        String connectionUrl = "jdbc:sqlserver://" + serverName + ":1433;databaseName=" + databaseName +
                ";user=" + username + ";password=" + password + ";encrypt=true;trustServerCertificate=false;";

        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            // Connection established, you can perform database operations here
            String query = "SELECT * FROM OrdersDemo";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                // Process the query results
                while (resultSet.next()) {
                    // Retrieve data from the result set
                    String orderId = resultSet.getString("order_id");
                    String customerName = resultSet.getString("name");
                    // ... Retrieve other columns as needed

                    // Process the data
                    System.out.println("OrderID: " + orderId + ", CustomerName: " + customerName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Orders API endpoint";
    }
}
