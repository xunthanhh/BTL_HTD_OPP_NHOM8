package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.model.Attendee;
import main.model.Event;
import main.model.EventManager;

public class ParticipantViewController {
    @FXML private TextField eventId;
    @FXML private TableView<Attendee> attendeeTable;
    @FXML private TableColumn<Attendee, String> colId;
    @FXML private TableColumn<Attendee, String> colName;
    @FXML private TableColumn<Attendee, String> colEmail;
    @FXML private TableColumn<Attendee, String> colPhone;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getId()));
        colName.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));
        colEmail.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEmail()));
        colPhone.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPhone()));
    }

    @FXML
    private void handleViewAttendees() {
        String eid = eventId.getText().trim();
        Event ev = EventManager.getEventById(eid); // hoặc findEventById
        if (ev != null) {
            ObservableList<Attendee> list = FXCollections.observableArrayList(ev.getAttendeeList());
            attendeeTable.setItems(list);
            if (list.isEmpty()) {
                showAlert("Không có người tham dự", "Sự kiện này chưa có người tham dự.");
            }
        } else {
            attendeeTable.setItems(FXCollections.emptyObservableList());
            showAlert("Mã sự kiện không tồn tại", "Vui lòng nhập lại mã sự kiện hợp lệ!");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
