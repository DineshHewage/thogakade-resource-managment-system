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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Item;
import model.Order;
import java.io.IOException;
import java.net.URL;
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
        txtOrderID.setText ((newValue.getOrderID()));
        txtOrderDate.setText (String.valueOf((newValue.getOrderDate())));
        txtCustID.setText (String.valueOf((newValue.getCustomerID())));
    }

    public void loadOrderDetails(){
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

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
