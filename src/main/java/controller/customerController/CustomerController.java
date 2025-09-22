package controller.customerController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static controller.customerController.CustomerFormController.*;

public class CustomerController implements CustomerFormService{

    @Override
    public ObservableList<Customer> getAllCustomer(){
        ObservableList<Customer> observableList = FXCollections.observableArrayList();
        try {
            String sql = "select * from customer";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getString("CustID"),
                        resultSet.getString("CustTitle"),
                        resultSet.getString("CustName"),
                        resultSet.getDate("DOB").toLocalDate(),
                        resultSet.getDouble("salary"),
                        resultSet.getString("CustAddress"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode")
                );
                observableList.add(customer);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return observableList;
    }

    @Override
    public void addCustomer(Customer customer) {
        try {
            String sql = "INSERT INTO customer (CustID, CustTitle, CustName, DOB, salary, CustAddress, City, Province, PostalCode) " +
                    "VALUES (?,?,?,?,?,?,?,?,?)";

            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, customer.getCustID());
            preparedStatement.setString(2, customer.getCustTitle());
            preparedStatement.setString(3, customer.getCustName());
            preparedStatement.setDate(4, java.sql.Date.valueOf(customer.getDOB())); // <-- correct handling for LocalDate
            preparedStatement.setDouble(5, customer.getSalary());
            preparedStatement.setString(6, customer.getCustAddress());
            preparedStatement.setString(7, customer.getCity());
            preparedStatement.setString(8, customer.getProvince());
            preparedStatement.setString(9, customer.getPostalCode());

            int i = preparedStatement.executeUpdate();
            addCustomerMessage(i);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET CustTitle=?, CustName=?, DOB=?, salary=?, CustAddress=?, City=?, Province=?, PostalCode=? WHERE CustID=?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, customer.getCustTitle());
            preparedStatement.setString(2, customer.getCustName());
            preparedStatement.setDate(3, java.sql.Date.valueOf(customer.getDOB())); // <-- correct handling for LocalDate
            preparedStatement.setDouble(4, customer.getSalary());
            preparedStatement.setString(5, customer.getCustAddress());
            preparedStatement.setString(6, customer.getCity());
            preparedStatement.setString(7, customer.getProvince());
            preparedStatement.setString(8, customer.getPostalCode());
            preparedStatement.setString(9, customer.getCustID());

            int i = preparedStatement.executeUpdate();
            updateCustomerMessage(i);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCustomer(String customerID) {
        String sql = "DELETE FROM customer WHERE CustID = ?;";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customerID);
            int i = preparedStatement.executeUpdate();
            deleteCustomerMessage(i);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
