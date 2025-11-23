package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;



public class MainViewController {

@FXML
private void handleAdminLogin(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/view/AdminLoginView.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Đăng nhập Quản trị viên");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}




    @FXML
private void handleAttendeeLogin(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("/main/view/AttendeeLoginForm.fxml"));
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Đăng nhập Người tham dự");
        // KHÔNG gọi lại stage.show()!
    } catch (IOException e) {
        e.printStackTrace();
    }
}



    @FXML
    private void handleExit(ActionEvent event) {
        System.exit(0);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    // đăng kí người tham dự
    @FXML
    private void handleRegisterAttendee(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("/main/view/RegisterAttendeeForm.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Đăng ký Người tham dự");
        // KHÔNG gọi lại stage.show() nếu cửa sổ đang hiển thị
    } catch (IOException e) {
        e.printStackTrace();
    }
}



}
