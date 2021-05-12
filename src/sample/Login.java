package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Manager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import model.ManagerList;
import org.jetbrains.annotations.NotNull;

public class Login implements Initializable {
    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label userNameMessage;

    @FXML
    private Label passwordMessage;

    @FXML
    private ImageView image1;

    @FXML
    private Hyperlink switchToSignUp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("@../../image/img-login.jpg");
        Image image = new Image(file.toURI().toString());
        image1.setImage(image);
    }

    @FXML
    void signIn(MouseEvent mouseEvent) {
        for (Manager manager : ManagerList.getMANAGER_LIST()) {
            if (!manager.getUserName().equals(userNameField.getText())) {
                userNameMessage.setText("Tên đăng nhập không đúng!");
            }
            if (!manager.getPassWord().equals(passwordField.getText())) {
                passwordMessage.setText("Mật khẩu không đúng!");
            }
            if (manager.getUserName().equals(userNameField.getText()) && !manager.getPassWord().equals(passwordField.getText())) {
                userNameMessage.setText(" ");
            }
            if (manager.getUserName().equals(userNameField.getText()) && manager.getPassWord().equals(passwordField.getText())){
                try {
                    ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();

                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MenuManager.fxml")));
                    Stage stage = new Stage();
                    stage.setTitle("QUẢN LÝ THƯ VIỆN");
                    stage.setScene(new Scene(root, 800, 550));
                    stage.show();
                    showAlert("XIN CHÀO! " + userNameField.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CHƯƠNG TRÌNH QUẢN LÝ SÁCH");
        alert.setHeaderText("THÔNG BÁO");
        alert.setContentText(message);
        alert.show();
    }

    public void switchToSignUp(@NotNull ActionEvent actionEvent) {
        try {
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SignUp.fxml")));
            Stage stage = new Stage();
            stage.setTitle("CHƯƠNG TRÌNH QUẢN LÝ SÁCH");
            stage.setScene(new Scene(root, 600, 500));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

