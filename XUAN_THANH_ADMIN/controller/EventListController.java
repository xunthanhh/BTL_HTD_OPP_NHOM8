package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import main.model.Event;
import main.model.EventManager;
import main.model.Attendee;

public class EventListController {
    @FXML private TableView<Event> eventTable;
    @FXML private TableColumn<Event, String> nameCol, timeCol, locationCol, registeredCol;
    @FXML private Label statusLabel;

    private Attendee currentAttendee;

    public void setCurrentAttendee(Attendee attendee) {
        this.currentAttendee = attendee;
    }

    @FXML
    private void initialize() {
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        timeCol.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        locationCol.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        registeredCol.setCellValueFactory(cellData ->
            new SimpleStringProperty(
                currentAttendee != null && EventManager.isRegistered(cellData.getValue(), currentAttendee) ? "Có" : "Chưa"
            )
        );
        ObservableList<Event> eventList = FXCollections.observableArrayList(EventManager.getAllEvents());
        eventTable.setItems(eventList);
    }

    @FXML
private void handleViewDetail() {
    Event selected = eventTable.getSelectionModel().getSelectedItem();
    if (selected == null) {
        statusLabel.setText("Vui lòng chọn một sự kiện!");
        return;
    }
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/view/EventDetailView.fxml"));
        Parent root = loader.load();

        // Truyền dữ liệu sự kiện và attendee sang màn chi tiết
        EventDetailController controller = loader.getController();
        controller.setEventDetail(selected, currentAttendee);

        Stage stage = (Stage) eventTable.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Chi tiết sự kiện");
    } catch (Exception e) {
        e.printStackTrace();
        // Cực kỳ nên có Alert để phát hiện lỗi FXML/logic!
        Alert alert = new Alert(Alert.AlertType.ERROR, "Lỗi mở màn chi tiết: " + e.getMessage());
        alert.showAndWait();
    }
}



    @FXML
    private void handleExit() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/view/AttendeeDashboardView.fxml"));
            Parent root = loader.load();
            main.controller.AttendeeDashboardController controller = loader.getController();
            controller.setLoggedInAttendee(currentAttendee);
            Stage stage = (Stage) eventTable.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Trang người dùng");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
