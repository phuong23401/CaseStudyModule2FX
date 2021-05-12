package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Book;
import model.Manager;
import model.ManagerList;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class MenuManager implements Initializable {
    private ObservableList<Book> bookList;
    private ObservableList<Manager> managerList;

    @FXML
    private TableView<Book> tableView;

    @FXML
    private TableColumn<Book, Integer> idColumn;

    @FXML
    private TableColumn<Book, String> nameColumn;

    @FXML
    private TableColumn<Book, String> kindColumn;

    @FXML
    private TableColumn<Book, Integer> priceColumn;

    @FXML
    private TableColumn<Book, Integer> numColumn;

    @FXML
    private TableColumn<Book, String> statusColumn;

    @FXML
    private TextField inputSearch;

    @FXML
    private TextField inputId;

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputPrice;

    @FXML
    private TextField inputNum;

    @FXML
    private ChoiceBox<String> inputKind;

    @FXML
    private ChoiceBox<String> inputStatus;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button readFileButton;

    @FXML
    private Button writeFileButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button showManagerBtn;

    @FXML
    private ImageView image3;

    public int inputId() {
        return Integer.parseInt(inputId.getText());
    }

    public String inputName() {
        return inputName.getText();
    }

    public String inputKind() {
        return inputKind.getSelectionModel().getSelectedItem();
    }

    private void loadDataKind() {
        ObservableList<String> kindList = FXCollections.observableArrayList();
        String kind1 = "Sách tham khảo";
        String kind2 = "Truyện tranh";
        String kind3 = "Tiểu thuyết";
        String kind4 = "Truyện dài";
        String kind5 = "Truyện ngắn";
        String kind6 = "Tản văn";

        kindList.addAll(kind1, kind2, kind3, kind4, kind5, kind6);
        inputKind.getItems().addAll(kindList);
    }

    public int inputPrice() {
        return Integer.parseInt(inputPrice.getText());
    }

    public int inputNum() {
        return Integer.parseInt(inputNum.getText());
    }

    public String inputStatus() {
        return String.valueOf(inputStatus.getValue());
    }

    private void loadDataStatus() {
        ObservableList<String> statusList = FXCollections.observableArrayList();
        String status1 = "Mới";
        String status2 = "Cũ";

        statusList.addAll(status1, status2);
        inputStatus.getItems().addAll(statusList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("@../../image/img-manager.jpg");
        Image image = new Image(file.toURI().toString());
        image3.setImage(image);

        bookList = FXCollections.observableArrayList(
                new Book(1, "Kiếp Nào Ta Cũng Tìm Thấy Nhau", "Tiểu thuyết", 51900, 10, "Mới"),
                new Book(2, "Nếu Biết Trăm Năm Là Hữu Hạn", "Truyện ngắn", 151000, 10, "Mới"),
                new Book(3, "Nhà", "Truyện ngắn", 85900, 10, "Mới"),
                new Book(4, "Nhật Ký Biết Ơn", "Tản văn", 56700, 10, "Mới"),
                new Book(5, "Có Một Ngày, Bố Mẹ Sẽ Già Đi", "Tản văn", 60500, 10, "Mới"),
                new Book(6, "Đời Có Mấy Tý, Sao Phải Nghĩ", "Truyện ngắn", 63000, 10, "Mới"),
                new Book(7, "Đừng Làm Con Chim Lạc Đàn", "Truyện ngắn", 73800, 10, "Mới"),
                new Book(8, "Cảm Giác Được Yêu", "Truyện ngắn", 60500, 10, "Mới"),
                new Book(9, "Người Không Mặt", "Tản văn", 60500, 10, "Mới"),
                new Book(10, "Danh Tác Việt Nam", "Tản văn", 60100, 10, "Mới"),
                new Book(11, "Trại Hoa Vàng", "Truyện dài", 61300, 10, "Mới"),
                new Book(12, "Tôi Là Bêtô", "Truyện dài", 49100, 10, "Mới"),
                new Book(13, "Còn Chút Gì Để Nhớ", "Truyện dài", 45400, 10, "Mới"),
                new Book(14, "Tiệm Đồ Cổ Á Xá", "Truyện tranh", 57600, 10, "Mới"),
                new Book(15, "Doraemon", "Truyện tranh", 40000, 10, "Mới")
        );
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        kindColumn.setCellValueFactory(new PropertyValueFactory<>("kindOfBook"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        numColumn.setCellValueFactory(new PropertyValueFactory<>("numOfBook"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableView.setItems(bookList);

        loadDataKind();
        loadDataStatus();
    }

    @FXML
    private void search() {
        FilteredList<Book> filteredList = new FilteredList<>(bookList, book -> true);
        inputSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(book -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseName = newValue.toLowerCase();
                if (book.getName().toLowerCase().contains(lowerCaseName)) {
                    return true;
                } else if (book.getKindOfBook().toLowerCase().contains(lowerCaseName)) {
                    return true;
                } else return String.valueOf(book.getPrice()).contains(lowerCaseName);
            });
        });

        SortedList<Book> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
    }

    public void addNew() {
        try {
            int id = inputId();
            String name = inputName();
            int price = inputPrice();
            String kind = inputKind();
            int num = inputNum();
            String status = inputStatus();

            if (!checkId(id)) {
                showAlertError("Trùng id. Mời nhập lại!");
            } else {
                Book book = new Book(id, name, kind, price, num, status);
                bookList.add(book);
                inputId.clear();
                inputName.clear();
                inputPrice.clear();
                inputNum.clear();
                inputKind.getSelectionModel().clearSelection();
                inputStatus.getSelectionModel().clearSelection();
            }
        } catch (Exception e) {
            showAlertError("Mời nhập đầy đủ thông tin sách!");
        }
    }

    public boolean checkId(int id) {
        for (Book book : bookList) {
            if (book.getId() == id) {
                return false;
            }
        }
        return true;
    }

    public void delete() {
        Alert alert = new Alert(AlertType.INFORMATION);
        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
        alert.setTitle("QUẢN LÍ THƯ VIỆN");
        alert.setHeaderText("THÔNG BÁO");
        alert.setContentText("Xóa " + selectedBook.getName() + " ?");

        ButtonType buttonTypeYes = new ButtonType("Xóa", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("Hủy", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes) {
            bookList.remove(selectedBook);
        } else {
            alert.close();
        }
    }

    public void getBookDetails(@NotNull MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() > 1) {
            Book book = tableView.getSelectionModel().getSelectedItem();
            if (book != null) {
                inputId.setText(String.valueOf(book.getId()));
                inputName.setText(book.getName());
                inputKind.setValue(book.getKindOfBook());
                inputPrice.setText(String.valueOf(book.getPrice()));
                inputNum.setText(String.valueOf(book.getNumOfBook()));
                inputStatus.setValue(book.getStatus());
            }
        }
    }

    public void edit() {
        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
        try {
//            selectedBook.setId(inputId());
            selectedBook.setName(inputName());
            selectedBook.setKindOfBook(inputKind());
            selectedBook.setPrice(inputPrice());
            selectedBook.setNumOfBook(inputNum());
            selectedBook.setStatus(inputStatus());

            if (inputId() != selectedBook.getId() && !checkId(inputId())) {
                showAlertError("Trùng id. Mời nhập lại!");
            } else {
                tableView.refresh();
                inputId.clear();
                inputName.clear();
                inputPrice.clear();
                inputNum.clear();
                inputKind.getSelectionModel().clearSelection();
                inputStatus.getSelectionModel().clearSelection();
            }
        } catch (Exception e) {
            showAlertError("Mời nhập đầy đủ thông tin sách!");
        }
    }

    public void readFile() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Library.doc"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            showAlertInfo("Đã đọc file!");
        } catch (IOException e) {
            showAlertError("Không thể đọc file hoặc file không tồn tại!");
        }
    }

    public void writeFile() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Library.doc"));
            for (Book book : bookList) {
                bufferedWriter.write(book.toString());
            }
            bufferedWriter.close();
            showAlertInfo("Đã lưu danh sách vào file!");
        } catch (IOException e) {
            showAlertError("Không thể ghi vào file!");
        }
    }

    private void showAlertInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("QUẢN LÝ THƯ VIỆN");
        alert.setHeaderText("THÔNG BÁO");
        alert.setContentText(message);
        alert.show();
    }

    private void showAlertError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("QUẢN LÝ THƯ VIÊN");
        alert.setHeaderText("LỖI");
        alert.setContentText(message);
        alert.show();
    }

    public void showManager() {
        for (Manager manager : ManagerList.getMANAGER_LIST()) {
            System.out.println(manager);
        }
    }

    public void logout(@NotNull ActionEvent event) {
        try {
            ((Node)(event.getSource())).getScene().getWindow().hide();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
            Stage stage = new Stage();
            stage.setTitle("CHƯƠNG TRÌNH QUẢN LÝ SÁCH");
            stage.setScene(new Scene(root, 600, 500));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

