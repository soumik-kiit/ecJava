package az2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderController2 {

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
        String result = "init\r\n";
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
                    //System.out.println("OrderID: " + orderId + ", CustomerName: " + customerName);
                    result=result.concat("OrderID: " + orderId + ", CustomerName: " + customerName + "\r\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }
    

    public static class Order {
        private int orderId;
        private String customerName;
        // Add other attributes and their getters/setters
        
        // Constructors, getters, and setters
    }
}