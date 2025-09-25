package controller.itemController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    ItemFormService itemFormService = new ItemController();
    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colDiscription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colPackSize;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private ComboBox<String> comPackSizeUnit;

    @FXML
    private TableView<Item> tblItem;

    @FXML
    private JFXTextField txtDiscription;

    @FXML
    private JFXTextField txtPackSizeNumber;

    @FXML
    private JFXTextField txtQtyOnhand;

    @FXML
    private JFXTextField txtUnitPrice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//  ---- Set items for combo-box -----
        ObservableList <String> packSizeUnit = FXCollections.observableArrayList(
                "Kg",
                "g",
                "L",
                "ml"
        );
        comPackSizeUnit.setItems(packSizeUnit);

//  ---- Set table details ----
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colDiscription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("PackSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("QtyOnHand"));

//  ---- load table details ----
        loadItemTable();

//  ---- Show item details as per user click -----
        tblItem.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setSelectedValue(newValue);
            }
        });


    }

    private void setSelectedValue(Item newValue) {
       txtDiscription.setText ((newValue.getDescription()));
       txtPackSizeNumber.setText ((newValue.getPackSize()));
       txtUnitPrice.setText(String.valueOf((newValue.getUnitPrice())));
       txtQtyOnhand.setText(String.valueOf((newValue.getQtyOnHand())));
    }

    private void loadItemTable(){
        ObservableList<Item> itemObservableList = itemFormService.getAllItems();
        tblItem.setItems(itemObservableList);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
