package controller.orderManagmentController;

import javafx.collections.ObservableList;
import model.Order;

import java.sql.ResultSet;

public interface OrderManagmentFormService {
    ObservableList<Order> getAllOrders();

    ResultSet searchOrder(String orderId);
}
