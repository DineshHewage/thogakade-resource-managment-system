package controller.itemController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ItemController implements ItemFormService{
    @Override
    public ObservableList<Item> getAllItems() {
        ObservableList<Item> ItemObserverList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM item";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Item item = new Item(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("QtyOnHand")
                );
                ItemObserverList.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ItemObserverList;
    }
}
