package controller.orderManagmentController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controller.orderManagmentController.PlaceOrderController.PlaceOrderFormController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Item;
import model.Order;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrderMangmentFormController implements Initializable {

    OrderManagmentFormService orderManagmentFormService = new OrderManagmentController();

    Stage newStage = new Stage();

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private ComboBox<?> cmbCustID;

    @FXML
    private TableColumn<?, ?> colCustID;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderID;

    @FXML
    private TableView<Order> tblOrders;

    @FXML
    private JFXTextField txtOrderDate;

    @FXML
    private JFXTextField txtOrderID;


    @FXML
    private JFXTextField txtCustID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//  ---------- Map each TableColumn to the corresponding property in the Order model class
        colCustID.setCellValueFactory(new PropertyValueFactory<>("customerID"));// Binds customerID column → getCustomerID()
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        loadOrderDetails();

        //  ---- Show item details as per user click -----
        tblOrders.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setSelectedValue(newValue);
            }
        });

    }

    private void setSelectedValue(Order newValue) {
        txtOrderID.setText((newValue.getOrderID()));
        txtOrderDate.setText(String.valueOf((newValue.getOrderDate())));
        txtCustID.setText(String.valueOf((newValue.getCustomerID())));
    }

    public void loadOrderDetails() {
        ObservableList<Order> orderList = orderManagmentFormService.getAllOrders();
        tblOrders.setItems(orderList);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtOrderID.clear();
        txtOrderDate.clear();
        txtCustID.clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ItemUI/PlaceOrderForm.fxml"));
            Parent root = loader.load();

            // Get the controller and pass a reference to refresh method
            PlaceOrderFormController controller = loader.getController();
            controller.setOnOrderPlaced(() -> loadOrderDetails()); // Pass callback

            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String orderId = txtOrderID.getText().trim();
        ResultSet resultSet = orderManagmentFormService.searchOrder(orderId);

        if (orderId.isEmpty()) {
            showAlert("Please enter an Order ID to search");
            return;
        }

        try {
            if (resultSet.next()) {
                // Extract data from ResultSet and populate text fields
                String orderID = resultSet.getString("orderID");
                Date orderDate = resultSet.getDate("OrderDate");
                String customerID = resultSet.getString("CustID");

                // Set values to text fields
                txtOrderID.setText(orderID);
                txtOrderDate.setText(String.valueOf(orderDate));
                txtCustID.setText(customerID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }
}
