package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import main.model.Attendee;
import main.model.AttendeeManager;

public class AttendeeLoginFormController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        Attendee loggedInAttendee = null;
        for (Attendee at : AttendeeManager.getAllAttendees()) {
            if (at.getUsername().equalsIgnoreCase(username) && at.getPassword().equals(password)) {
                loggedInAttendee = at;
                break;
            }
        }
        if (loggedInAttendee != null) {
            statusLabel.setText("Đăng nhập thành công!");
            statusLabel.setStyle("-fx-text-fill: green;");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/view/AttendeeDashboardView.fxml"));
                Parent root = loader.load();
                AttendeeDashboardController controller = loader.getController();
                controller.setLoggedInAttendee(loggedInAttendee);

                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Trang người tham dự");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            statusLabel.setText("Tên đăng nhập hoặc mật khẩu không đúng!");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }

        @FXML
    private void handleExit() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/view/MainView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("DNU Event Hub");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
