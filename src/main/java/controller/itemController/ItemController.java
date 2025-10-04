package controller.itemController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import java.sql.*;
import static controller.itemController.ItemFormController.addItemMessage;
import static controller.itemController.ItemFormController.updateItemMessage;

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

    @Override
    public void addItem(Item newItem) {
        String sql = "INSERT INTO item VALUES (?,?,?,?,?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newItem.getItemCode());
            preparedStatement.setString(2, newItem.getDescription());
            preparedStatement.setString(3, newItem.getPackSize());
            preparedStatement.setDouble(4, newItem.getUnitPrice());
            preparedStatement.setInt(5, newItem.getQtyOnHand());
            int i = preparedStatement.executeUpdate();
            addItemMessage(i);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public String itemCodeAutoGenerate() {
        String lastItemCode = null;
        String sql = "SELECT ItemCode FROM Item ORDER BY ItemCode DESC LIMIT 1";
        try {
            DBConnection instance = DBConnection.getInstance();
            Connection connection = instance.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                lastItemCode = resultSet.getString("ItemCode");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int nextItemCode = 1;
        if (lastItemCode != null) {
            nextItemCode = Integer.parseInt(lastItemCode.substring(1))+1;
        }
        return String.format("P%03d", nextItemCode);
    }

    @Override
    public void updatedItem(Item updatedItem) {
        String sql = "UPDATE item SET Description=?, PackSize=?, UnitPrice=?, QtyOnHand=? WHERE ItemCode=?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, updatedItem.getDescription());
            preparedStatement.setString(2, updatedItem.getPackSize());
            preparedStatement.setDouble(3, updatedItem.getUnitPrice());
            preparedStatement.setInt(4, updatedItem.getQtyOnHand());
            preparedStatement.setString(5, updatedItem.getItemCode());
            int i = preparedStatement.executeUpdate();
            updateItemMessage(i);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
