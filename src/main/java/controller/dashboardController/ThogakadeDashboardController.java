package controller.dashboardController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class ThogakadeDashboardController {

    public ImageView imgLogo;
    @FXML
    private Button btnCustomerMangment;

    @FXML
    private Button btnItemManagment;

    @FXML
    private Button btnOrderDetailMangment;

    @FXML
    private Button btnOrdermangment;

    Stage customerManagement = new Stage();

    @FXML
    void btnCustomerMangmentOnAction(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"));
            Scene scene = new Scene(root);
            customerManagement.setScene(scene);
            customerManagement.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnItemManagmentOnAction(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/ItemForm.fxml"));
            Scene scene = new Scene(root);
            customerManagement.setScene(scene);
            customerManagement.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
     }

    @FXML
    void btnOrderDetailMangmentOnAction(ActionEvent event) {

    }

    @FXML
    void btnOrdermangmentOnAction(ActionEvent event) {

    }
}
