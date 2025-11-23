package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.model.Event;
import main.model.EventManager;
import main.model.Attendee;

public class EventRegisterController {
    private Attendee currentAttendee;

    @FXML
    private ComboBox<Event> eventComboBox; // Sự kiện để đăng ký
    @FXML
    private Label statusLabel;

    public void setCurrentAttendee(Attendee attendee) {
        this.currentAttendee = attendee;
    }

    @FXML
    public void initialize() {
        eventComboBox.getItems().setAll(EventManager.getAllEvents());
    }

    @FXML
private void handleRegister() {
    Event selectedEvent = eventComboBox.getValue();
    if (selectedEvent == null) {
        statusLabel.setText("Vui lòng chọn sự kiện muốn đăng ký!");
        return;
    }
    if (currentAttendee == null) {
        statusLabel.setText("Không tìm thấy thông tin người dùng!");
        return;
    }

    // Kiểm tra nếu đã đăng ký rồi thì báo luôn:
    if (EventManager.isRegistered(selectedEvent, currentAttendee)) {
        statusLabel.setText("Bạn đã đăng ký sự kiện này!");
        return;
    }

    boolean ok = EventManager.registerForEvent(selectedEvent, currentAttendee);
    if (ok) {
        statusLabel.setText("Đăng ký thành công!");
    } else {
        statusLabel.setText("Có lỗi khi đăng ký!");
    }
}


    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/view/AttendeeDashboardView.fxml"));
            Parent root = loader.load();
            main.controller.AttendeeDashboardController controller = loader.getController();
            controller.setLoggedInAttendee(currentAttendee);
            Stage stage = (Stage) eventComboBox.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Trang người dùng");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
private void handleUnregister() {
    Event selectedEvent = eventComboBox.getValue();
    if (selectedEvent == null) {
        statusLabel.setText("Vui lòng chọn sự kiện để hủy đăng ký!");
        return;
    }
    if (currentAttendee == null) {
        statusLabel.setText("Không tìm thấy thông tin người dùng!");
        return;
    }
    // Kiểm tra nếu chưa đăng ký thì không thể hủy
    if (!EventManager.isRegistered(selectedEvent, currentAttendee)) {
        statusLabel.setText("Bạn chưa đăng ký sự kiện này!");
        return;
    }
    // Thực hiện hủy
    selectedEvent.removeAttendee(currentAttendee.getId());
    statusLabel.setText("Đã hủy đăng ký sự kiện!");
}

}
