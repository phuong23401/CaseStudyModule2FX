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
        String kind1 = "S??ch tham kh???o";
        String kind2 = "Truy???n tranh";
        String kind3 = "Ti???u thuy???t";
        String kind4 = "Truy???n d??i";
        String kind5 = "Truy???n ng???n";
        String kind6 = "T???n v??n";

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
        String status1 = "M???i";
        String status2 = "C??";

        statusList.addAll(status1, status2);
        inputStatus.getItems().addAll(statusList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("@../../image/img-manager.jpg");
        Image image = new Image(file.toURI().toString());
        image3.setImage(image);

        bookList = FXCollections.observableArrayList(
                new Book(1, "Ki???p N??o Ta C??ng T??m Th???y Nhau", "Ti???u thuy???t", 51900, 10, "M???i"),
                new Book(2, "N???u Bi???t Tr??m N??m L?? H???u H???n", "Truy???n ng???n", 151000, 10, "M???i"),
                new Book(3, "Nh??", "Truy???n ng???n", 85900, 10, "M???i"),
                new Book(4, "Nh???t K?? Bi???t ??n", "T???n v??n", 56700, 10, "M???i"),
                new Book(5, "C?? M???t Ng??y, B??? M??? S??? Gi?? ??i", "T???n v??n", 60500, 10, "M???i"),
                new Book(6, "?????i C?? M???y T??, Sao Ph???i Ngh??", "Truy???n ng???n", 63000, 10, "M???i"),
                new Book(7, "?????ng L??m Con Chim L???c ????n", "Truy???n ng???n", 73800, 10, "M???i"),
                new Book(8, "C???m Gi??c ???????c Y??u", "Truy???n ng???n", 60500, 10, "M???i"),
                new Book(9, "Ng?????i Kh??ng M???t", "T???n v??n", 60500, 10, "M???i"),
                new Book(10, "Danh T??c Vi???t Nam", "T???n v??n", 60100, 10, "M???i"),
                new Book(11, "Tr???i Hoa V??ng", "Truy???n d??i", 61300, 10, "M???i"),
                new Book(12, "T??i L?? B??t??", "Truy???n d??i", 49100, 10, "M???i"),
                new Book(13, "C??n Ch??t G?? ????? Nh???", "Truy???n d??i", 45400, 10, "M???i"),
                new Book(14, "Ti???m ????? C??? ?? X??", "Truy???n tranh", 57600, 10, "M???i"),
                new Book(15, "Doraemon", "Truy???n tranh", 40000, 10, "M???i")
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
                showAlertError("Tr??ng id. M???i nh???p l???i!");
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
            showAlertError("M???i nh???p ?????y ????? th??ng tin s??ch!");
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
        alert.setTitle("QU???N L?? TH?? VI???N");
        alert.setHeaderText("TH??NG B??O");
        alert.setContentText("X??a " + selectedBook.getName() + " ?");

        ButtonType buttonTypeYes = new ButtonType("X??a", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("H???y", ButtonBar.ButtonData.NO);
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
                showAlertError("Tr??ng id. M???i nh???p l???i!");
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
            showAlertError("M???i nh???p ?????y ????? th??ng tin s??ch!");
        }
    }

    public void readFile() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Library.doc"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            showAlertInfo("???? ?????c file!");
        } catch (IOException e) {
            showAlertError("Kh??ng th??? ?????c file ho???c file kh??ng t???n t???i!");
        }
    }

    public void writeFile() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Library.doc"));
            for (Book book : bookList) {
                bufferedWriter.write(book.toString());
            }
            bufferedWriter.close();
            showAlertInfo("???? l??u danh s??ch v??o file!");
        } catch (IOException e) {
            showAlertError("Kh??ng th??? ghi v??o file!");
        }
    }

    private void showAlertInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("QU???N L?? TH?? VI???N");
        alert.setHeaderText("TH??NG B??O");
        alert.setContentText(message);
        alert.show();
    }

    private void showAlertError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("QU???N L?? TH?? VI??N");
        alert.setHeaderText("L???I");
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
            stage.setTitle("CH????NG TR??NH QU???N L?? S??CH");
            stage.setScene(new Scene(root, 600, 500));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

