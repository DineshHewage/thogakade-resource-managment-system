package controller.orderManagmentController.PlaceOrderController;

import model.Order;

public interface PlaceOrderFormService {
    String orderIDAutoGenerate();

    boolean isOrderIdExists(String enteredOrderId);

    boolean isCustomerExists(String enteredCustomerId);

    void placeOrder(Order order);
}
