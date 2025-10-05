package controller.orderManagmentController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Order;

import java.sql.*;

public class OrderManagmentController implements OrderManagmentFormService {
    @Override
    public ObservableList<Order> getAllOrders() {
        String sql = "SELECT * FROM orders";

        ObservableList <Order> orderList = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Order order = new Order(
                        resultSet.getString("OrderID"),
                        resultSet.getDate("OrderDate"),
                        resultSet.getString("CustID")
                );
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderList;
    }

    @Override
    public ResultSet searchOrder(String orderId) {
        String sql = "SELECT * FROM orders WHERE OrderID = ?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
