package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.model.Event;
import main.model.EventManager;

public class EventEditController {
    @FXML private TextField eventId;
    @FXML private TextField eventName;
    @FXML private TextField eventDate;
    @FXML private TextField eventLocation;
    @FXML private TextArea eventDescription;

    @FXML
    private void handleFindEvent() {
        String id = eventId.getText();
        if (id.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Vui lòng nhập mã sự kiện!");
            alert.showAndWait();
            return;
        }

        Event event = EventManager.findEventById(id);
        if (event != null) {
            eventName.setText(event.getName());
            eventDate.setText(event.getDate());
            eventLocation.setText(event.getLocation());
            eventDescription.setText(event.getDescription());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Không tìm thấy sự kiện!");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleSaveChanges() {
        String id = eventId.getText();
        Event event = EventManager.findEventById(id);
        if (event != null) {
            event.setName(eventName.getText());
            event.setDate(eventDate.getText());
            event.setLocation(eventLocation.getText());
            event.setDescription(eventDescription.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Đã cập nhật sự kiện thành công!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Không tìm thấy sự kiện để sửa!");
            alert.showAndWait();
        }
    }
}
