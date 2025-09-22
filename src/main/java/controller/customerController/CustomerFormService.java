package controller.customerController;

import javafx.collections.ObservableList;
import model.Customer;

public interface CustomerFormService {
    ObservableList<Customer> getAllCustomer();

    void addCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(String customerID);
}
