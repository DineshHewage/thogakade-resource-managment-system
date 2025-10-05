package controller.orderManagmentController;

import javafx.collections.ObservableList;
import model.Order;

public interface OrderManagmentFormService {
    ObservableList<Order> getAllOrders();
}
