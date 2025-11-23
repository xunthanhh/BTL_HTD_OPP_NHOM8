package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.model.Event;
import main.model.EventManager;
import main.model.Attendee;

import java.util.List;
import java.util.stream.Collectors;

public class RegisteredEventController {
    @FXML private TableView<Event> registeredEventTable;
    @FXML private TableColumn<Event, String> nameCol, timeCol, locationCol, descriptionCol;
    @FXML private Label statusLabel;

    private Attendee currentAttendee;

    public void setCurrentAttendee(Attendee attendee) {
        this.currentAttendee = attendee;
        loadRegisteredEvents();
    }

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        timeCol.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        locationCol.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        descriptionCol.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
    }

    private void loadRegisteredEvents() {
        if (currentAttendee == null) {
            registeredEventTable.setItems(FXCollections.observableArrayList());
            statusLabel.setText("Không tìm thấy thông tin người dùng!");
            return;
        }
        List<Event> allEvents = EventManager.getAllEvents();
        List<Event> registeredEvents = allEvents.stream()
                .filter(ev -> ev.getAttendeeList().stream()
                        .anyMatch(a -> a.getId().equals(currentAttendee.getId())))
                .collect(Collectors.toList());
        ObservableList<Event> observableList = FXCollections.observableArrayList(registeredEvents);
        registeredEventTable.setItems(observableList);
        if (registeredEvents.isEmpty()) {
            statusLabel.setText("Bạn chưa đăng ký sự kiện nào!");
        } else {
            statusLabel.setText("");
        }
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/view/AttendeeDashboardView.fxml"));
            Parent root = loader.load();
            main.controller.AttendeeDashboardController controller = loader.getController();
            controller.setLoggedInAttendee(currentAttendee);
            Stage stage = (Stage) registeredEventTable.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Trang người dùng");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
