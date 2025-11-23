package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.model.Attendee;

public class ChangePasswordController {
    @FXML private PasswordField currentPasswordField, newPasswordField, confirmPasswordField;
    @FXML private Label statusLabel;

    private Attendee loggedInAttendee;

    // Gán khi mở form đổi mật khẩu!
    public void setLoggedInAttendee(Attendee attendee) {
        this.loggedInAttendee = attendee;
    }

    @FXML
    private void handleChangePassword() {
        String currentPass = currentPasswordField.getText();
        String newPass = newPasswordField.getText();
        String confirmPass = confirmPasswordField.getText();

        if (loggedInAttendee == null) {
            statusLabel.setText("Lỗi xác thực người dùng!");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        if (!newPass.equals(confirmPass)) {
            statusLabel.setText("Mật khẩu mới nhập lại không khớp!");
            statusLabel.setStyle("-fx-text-fill: orange;");
            return;
        }
        if (!loggedInAttendee.getPassword().equals(currentPass)) {
            statusLabel.setText("Mật khẩu hiện tại không đúng!");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        loggedInAttendee.setPassword(newPass);
        statusLabel.setText("Đổi mật khẩu thành công!");
        statusLabel.setStyle("-fx-text-fill: green;");
        // Nếu cần lưu DB/file thì thêm xử lý ở đây
    }

    // === NÚT THOÁT (ĐÓNG FORM) ===
    @FXML
private void handleClose() {
    try {
        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/main/view/AttendeeDashboardView.fxml"));
        javafx.scene.Parent root = loader.load();
        main.controller.AttendeeDashboardController controller = loader.getController();
        controller.setLoggedInAttendee(loggedInAttendee);
        javafx.stage.Stage stage = (javafx.stage.Stage) statusLabel.getScene().getWindow();
        stage.setScene(new javafx.scene.Scene(root));
        stage.setTitle("Trang người dùng");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
