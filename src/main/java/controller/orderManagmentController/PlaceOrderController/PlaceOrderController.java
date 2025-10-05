package controller.orderManagmentController.PlaceOrderController;

import db.DBConnection;
import model.Order;

import java.sql.*;

public class PlaceOrderController implements PlaceOrderFormService{
    @Override
    public String orderIDAutoGenerate() {
        String lastOrderID = null;
        String sql = "SELECT OrderID FROM orders ORDER BY OrderID DESC LIMIT 1";
        try {
            DBConnection instance = DBConnection.getInstance();
            Connection connection = instance.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                lastOrderID = resultSet.getString("OrderID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int nextItemCode = 1;
        if (lastOrderID != null) {
            nextItemCode = Integer.parseInt(lastOrderID.substring(1))+1;
        }
        return String.format("D%03d", nextItemCode);
    }

    @Override
    public boolean isOrderIdExists(String enteredOrderId) {
        String sql = "SELECT OrderID FROM orders WHERE OrderID = ?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, enteredOrderId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();// returns true if record exists
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isCustomerExists(String enteredCustomerId) {
        String sql = "SELECT CustID FROM Customer WHERE CustID = ?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, enteredCustomerId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // true if record found
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void placeOrder(Order order) {
        String sql = "INSERT INTO orders VALUES (?,?,?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, order.getOrderID());
            statement.setDate(2, Date.valueOf(String.valueOf(order.getOrderDate())));
            statement.setString(3, order.getCustomerID());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
