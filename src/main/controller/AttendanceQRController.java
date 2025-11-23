package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.model.EventManager;
import main.model.Event;

public class AttendanceQRController {
    @FXML private TextField txtQRInput;
    @FXML private Label lblStatus;

    @FXML
    private void handleCheckIn() {
        String qr = txtQRInput.getText().trim();
        // Ví dụ mã QR là dạng: EVENTID-ATTENDEEID (hoặc chỉ ATTENDEEID)
        if (qr.isEmpty()) {
            lblStatus.setText("Vui lòng nhập hoặc quét mã QR.");
            lblStatus.setStyle("-fx-text-fill: red;");
            return;
        }
        // Giả sử bạn mã hóa kiểu: EVENTID-ATTENDEEID 
        String[] parts = qr.split("-");
        if(parts.length != 2){
            lblStatus.setText("Mã QR không hợp lệ!");
            lblStatus.setStyle("-fx-text-fill: red;");
            return;
        }
        String eventId = parts[0];
        String attendeeId = parts[1];
        Event event = EventManager.getEventById(eventId);
        if (event != null && event.getAttendeeList().stream().anyMatch(a -> a.getId().equals(attendeeId))) {
            lblStatus.setText("Điểm danh thành công cho " + attendeeId + "!");
            lblStatus.setStyle("-fx-text-fill: green;");
            // Có thể cập nhật trạng thái check-in thực tế ở attendee (nếu phát triển thêm)
        } else {
            lblStatus.setText("Không tìm thấy thông tin!");
            lblStatus.setStyle("-fx-text-fill: red;");
        }
    }
}
