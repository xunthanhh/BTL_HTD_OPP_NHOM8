package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.model.Event;
import main.model.Attendee;

public class EventDetailController {
    @FXML private Label nameLabel, timeLabel, locationLabel, descriptionLabel;

    private Event event;
    private Attendee currentAttendee;

    // Gọi từ EventListController
    public void setEventDetail(Event event, Attendee attendee) {
        this.event = event;
        this.currentAttendee = attendee;
        if (event != null) {
            nameLabel.setText("Tên sự kiện: " + event.getName());
            timeLabel.setText("Thời gian: " + event.getDate());
            locationLabel.setText("Địa điểm: " + event.getLocation());
            descriptionLabel.setText("Mô tả: " + event.getDescription());
        }
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/view/EventListView.fxml"));
            Parent root = loader.load();
            main.controller.EventListController listController = loader.getController();
            listController.setCurrentAttendee(currentAttendee);
            Stage stage = (Stage) nameLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Danh sách sự kiện");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
