package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import main.model.Attendee;
import main.model.AttendeeManager;

public class RegisterAttendeeFormController {
    @FXML private TextField usernameField, fullNameField, emailField, phoneField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;

    @FXML
    private void handleRegister() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();

        if (username.isEmpty() || password.isEmpty() || fullName.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            statusLabel.setText("Vui lòng nhập đầy đủ mọi trường!");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        boolean exists = false;
        for (Attendee at : AttendeeManager.getAllAttendees()) {
            if(at.getUsername().equalsIgnoreCase(username)) {
                exists = true; break;
            }
        }
        if (exists) {
            statusLabel.setText("Tên đăng nhập đã tồn tại!");
            statusLabel.setStyle("-fx-text-fill: orange;");
            return;
        }
        Attendee attendee = new Attendee(username, fullName, email, phone, password);
        AttendeeManager.addAttendee(attendee);
        statusLabel.setText("Đăng ký thành công!");
        statusLabel.setStyle("-fx-text-fill: green;");
        // Optional: reset form or close window here
    }

    @FXML
private void handleExit() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/view/MainView.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("DNU Event Hub");
        // KHÔNG gọi lại stage.show() ở đây!
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
