package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.model.Event;
import main.model.EventManager;

public class EventCreateController {
    @FXML private TextField eventId;
    @FXML private TextField eventName;
    @FXML private TextField eventDate;
    @FXML private TextField eventLocation;
    @FXML private TextArea eventDescription;

    @FXML
    private void handleCreateEvent() {
        String id = eventId.getText();
        String name = eventName.getText();
        String date = eventDate.getText();
        String location = eventLocation.getText();
        String desc = eventDescription.getText();

        if (id.isEmpty() || name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Vui lòng nhập đầy đủ thông tin!");
            alert.showAndWait();
            return;
        }

        Event event = new Event(id, name, date, location, desc);
        EventManager.addEvent(event);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Tạo sự kiện mới thành công!");
        alert.showAndWait();

        // Optionally clear input hoặc close form
    }
}
