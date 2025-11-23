package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.Event;
import main.model.EventManager;

public class EventReportController {
    @FXML private TableView<Event> eventTable;
    @FXML private TableColumn<Event, String> eventIdCol, nameCol, dateCol, locationCol;
    @FXML private TableColumn<Event, Integer> attendeeCountCol;
    @FXML private Label totalLabel;

    @FXML
    public void initialize() {
        ObservableList<Event> eventList = FXCollections.observableArrayList(EventManager.getAllEvents());
        eventIdCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        locationCol.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        attendeeCountCol.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getAttendeeList().size()).asObject()
        );
        eventTable.setItems(eventList);
        totalLabel.setText("Tổng số sự kiện: " + eventList.size());
    }
}
