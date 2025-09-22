package controller.customerController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    public TableColumn colID;
    public TextField txtCustomerID;
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableView<Customer> tblCustomerDetails;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private ComboBox<String> txtComTitle;

    @FXML
    private DatePicker txtDOB;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPostalCode;

    @FXML
    private TextField txtProvince;

    @FXML
    private TextField txtSalary;

    CustomerFormService customerFormService = new CustomerController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        ------initialize Title Combobox-------
        ObservableList<String> customerTitleType = FXCollections.observableArrayList(
                "Mr",
                "Mrs",
                "Ms",
                "Dr"
        );
        txtComTitle.setItems(customerTitleType);

        colID.setCellValueFactory(new PropertyValueFactory<>("CustID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("CustTitle"));
        colName.setCellValueFactory(new PropertyValueFactory<>("CustName"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("CustAddress"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("City"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("Province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));

        loadCustomerDetails();

        //        -------Show Room Infor as per user clicks.
        tblCustomerDetails.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setSelectedValue(newValue);
            }
        });
    }

    private void setSelectedValue(Customer newValue) {
        txtCustomerID.setText(newValue.getCustID());

        txtComTitle.getSelectionModel().select(newValue.getCustTitle());

        txtName.setText(newValue.getCustName());

        if (newValue.getDOB() != null) {
            txtDOB.setValue(newValue.getDOB());
        }

        txtSalary.setText(String.valueOf(newValue.getSalary()));
        txtAddress.setText(newValue.getCustAddress());
        txtCity.setText(newValue.getCity());
        txtProvince.setText(newValue.getProvince());
        txtPostalCode.setText(newValue.getPostalCode());
    }

    private void loadCustomerDetails() {
        ObservableList<Customer> customerObservableList = customerFormService.getAllCustomer();
        tblCustomerDetails.setItems(customerObservableList);
    }

    private void clearCustomerDetails() {
        txtCustomerID.clear();
        txtComTitle.getSelectionModel().clearSelection();
        txtName.clear();
        txtDOB.setValue(null);
        txtSalary.setText(String.valueOf(0));
        txtAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();
    }

    public void btnAddOnAction(ActionEvent actionEvent) {

        Customer customer = new Customer(
                txtCustomerID.getText(),
                txtComTitle.getSelectionModel().getSelectedItem(),
                txtName.getText(),
                txtDOB.getValue(),
                Double.parseDouble(txtSalary.getText()),
                txtAddress.getText(),
                txtCity.getText(),
                txtProvince.getText(),
                txtPostalCode.getText()
        );

        customerFormService.addCustomer(customer);
        loadCustomerDetails();
        clearCustomerDetails();
    }

    public void btnUpdateOnaction(ActionEvent actionEvent) {
        Customer customer = new Customer(
                txtCustomerID.getText(),
                txtComTitle.getSelectionModel().getSelectedItem(),
                txtName.getText(),
                txtDOB.getValue(),
                Double.parseDouble(txtSalary.getText()),
                txtAddress.getText(),
                txtCity.getText(),
                txtProvince.getText(),
                txtPostalCode.getText()
        );

        customerFormService.updateCustomer(customer);
        loadCustomerDetails();
        clearCustomerDetails();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String customerID = txtCustomerID.getText();
        customerFormService.deleteCustomer(customerID);
        loadCustomerDetails();
        clearCustomerDetails();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearCustomerDetails();
    }

    public static void addCustomerMessage(int i){
        Alert alert;
        if (i > 0) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Customer detail added successfully!");
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to add Customer details.");
        }
        alert.showAndWait();
    }

    public static void updateCustomerMessage(int i){
        Alert alert;
        if (i > 0) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Customer detail updated successfully!");
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to update Customer details.");
        }
        alert.showAndWait();
    }

    public static void deleteCustomerMessage(int i){
        Alert alert;
        if (i > 0) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Customer detail deleted successfully!");
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to delete Customer details.");
        }
        alert.showAndWait();
    }
}

