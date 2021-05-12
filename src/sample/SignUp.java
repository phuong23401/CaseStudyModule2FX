package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Manager;
import model.ManagerList;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp implements Initializable {
    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField password1Field;

    @FXML
    private PasswordField password2Field;

    @FXML
    private Button registrationBtn;

    @FXML
    private Label emailMessage;

    @FXML
    private Label phoneMessage;

    @FXML
    private Label userNameMessage;

    @FXML
    private Label password1Message;

    @FXML
    private Label password2Message;

    @FXML
    private ImageView image2;

    @FXML
    private Hyperlink switchToLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("@../../image/img-login.jpg");
        Image image = new Image(file.toURI().toString());
        image2.setImage(image);
    }

    @FXML
    void signUp() {
        String email = emailField.getText();
        String phone = phoneField.getText();
        String userName = userNameField.getText();
        String passWord1 = password1Field.getText();
        String passWord2 = password2Field.getText();

        if (!validateEmail(email)) {
            emailMessage.setText("Email không đúng định dạng!");
        }
        if (!validatePhone(phone)) {
            phoneMessage.setText("Số điện thoại không đúng định dạng!");
        }
        if (!passWord2.equals(passWord1)) {
            password2Message.setText("Vui lòng nhập đúng mật khẩu!");
        }
        for (Manager manager : ManagerList.getMANAGER_LIST()) {
            if (email.equals(manager.getEmail())) {
                emailMessage.setText("Email đã được sử dụng!");
            }
            if (phone.equals(manager.getPhone())) {
                phoneMessage.setText("Số điện thoại đã được sử dụng!");
            }
            if (userName.equals(manager.getUserName())) {
                userNameMessage.setText("Tên đăng nhập đã tồn tại!");
            }
            if (validateEmail(email) && validatePhone(phone)
                    && !email.equals(manager.getEmail()) && !phone.equals(manager.getPhone())
                    && !userName.equals(manager.getUserName()) && passWord1.equals(passWord2)){
                Manager manager1 = new Manager(email, phone, userName, passWord1);
                ManagerList.getMANAGER_LIST().add(manager1);
                emailField.clear();
                phoneField.clear();
                userNameField.clear();
                password1Field.clear();
                password2Field.clear();
                emailMessage.setText("");
                phoneMessage.setText("");
                userNameMessage.setText("");
                password1Message.setText("");
                password2Message.setText("");

                showAlert("ĐĂNG KÍ THÀNH VIÊN THÀNH CÔNG!");
                break;
            }
        }
    }

    @FXML
    void switchToLogin(@NotNull ActionEvent event) {
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

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CHƯƠNG TRÌNH QUẢN LÝ SÁCH");
        alert.setHeaderText("THÔNG BÁO");
        alert.setContentText(message);
        alert.show();
    }

    boolean validateEmail(String regex) {
        String email_regex = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z]+(\\.[A-Za-z]+)$";
        Pattern pattern = Pattern.compile(email_regex);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }

    boolean validatePhone(String regex) {
        String phone_regex = "^(84|0[3|5|7|8|9])+([0-9]{8})$";
        Pattern pattern = Pattern.compile(phone_regex);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
