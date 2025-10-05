package controller.orderManagmentController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Order;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderMangmentFormController implements Initializable {

    OrderManagmentFormService orderManagmentFormService = new OrderManagmentController();

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//  ---------- Map each TableColumn to the corresponding property in the Order model class
        colCustID.setCellValueFactory(new PropertyValueFactory<>("customerID"));// Binds customerID column → getCustomerID()
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        loadOrderDetails();



    }

    private void loadOrderDetails(){
        ObservableList<Order> orderList = orderManagmentFormService.getAllOrders();
        tblOrders.setItems(orderList);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
