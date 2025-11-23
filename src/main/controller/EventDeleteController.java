package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.model.EventManager;

public class EventDeleteController {
    @FXML private TextField eventId;

    @FXML
    private void handleDeleteEvent() {
        String id = eventId.getText();
        if (id.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Vui lòng nhập mã sự kiện cần hủy!").showAndWait();
            return;
        }
        boolean deleted = EventManager.removeEventById(id);
        if (deleted) {
            new Alert(Alert.AlertType.INFORMATION, "Đã hủy (xóa) sự kiện!").showAndWait();
            eventId.clear();
        } else {
            new Alert(Alert.AlertType.ERROR, "Không tìm thấy sự kiện cần hủy!").showAndWait();
        }
    }
}
