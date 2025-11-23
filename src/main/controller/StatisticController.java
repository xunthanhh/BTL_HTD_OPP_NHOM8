package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.chart.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.Event;
import main.model.EventManager;

public class StatisticController {
    @FXML private Label lblTotalEvent;
    @FXML private Label lblTotalAttendee;

    @FXML private TableView<Event> statisticTable;
    @FXML private TableColumn<Event, String> colEventId;
    @FXML private TableColumn<Event, String> colEventName;
    @FXML private TableColumn<Event, Integer> colAttendeeCount;

    @FXML private BarChart<String, Number> chartEvent;
    @FXML private CategoryAxis xAxis;
    @FXML private NumberAxis yAxis;

    @FXML
    public void initialize() {
        var events = EventManager.getAllEvents();
        int totalEvent = events.size();
        int totalAttendee = events.stream().mapToInt(ev -> ev.getAttendeeList().size()).sum();
        lblTotalEvent.setText("Tổng số sự kiện: " + totalEvent);
        lblTotalAttendee.setText("Tổng số người tham gia: " + totalAttendee);

        // Bảng chi tiết
        colEventId.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        colEventName.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        colAttendeeCount.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getAttendeeList().size()).asObject());
        ObservableList<Event> eventList = FXCollections.observableArrayList(events);
        statisticTable.setItems(eventList);

        // BarChart
        xAxis.setLabel("Sự kiện");
        yAxis.setLabel("Số người tham gia");
        chartEvent.setTitle("Số người tham gia theo từng sự kiện");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Tham dự");
        for (Event ev : events) {
            series.getData().add(new XYChart.Data<>(ev.getName(), ev.getAttendeeList().size()));
        }
        chartEvent.getData().clear();
        chartEvent.getData().add(series);
    }
}
