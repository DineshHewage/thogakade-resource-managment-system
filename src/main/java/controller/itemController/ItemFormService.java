package controller.itemController;

import javafx.collections.ObservableList;
import model.Item;

public interface ItemFormService {
    ObservableList<Item> getAllItems();

    void addItem(Item newItem);

    String itemCodeAutoGenerate();

    void updatedItem(Item updatedItem);

    void deleteItem(Item deteleItem);
}
