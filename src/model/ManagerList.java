package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManagerList {
    private static final ObservableList<Manager> MANAGER_LIST = FXCollections.observableArrayList(
            new Manager("hoaphuong23401@gmail.com", "0961232104", "phuongdz", "123"),
            new Manager("abc123@gmail.com", "0123456789", "abc", "123"),
            new Manager("abc@gmail.com", "0966666666", "a", "a")
    );

    public static ObservableList<Manager> getMANAGER_LIST() {
        return MANAGER_LIST;
    }
}
