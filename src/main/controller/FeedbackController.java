package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.Event;
import main.model.EventManager;
import main.model.Feedback;
import main.model.Attendee;
import main.model.FeedbackManager;
import main.model.AttendeeManager;

import java.util.List;
import java.util.stream.Collectors;

public class FeedbackController {
    @FXML private TabPane tabPaneEvents;

    private Attendee currentAttendee;

    // Truyền Attendee từ Dashboard khi mở view
    public void setCurrentAttendee(Attendee attendee) {
        this.currentAttendee = attendee;
    }

    @FXML
    public void initialize() {
        List<Event> events = EventManager.getAllEvents();
        for (Event ev : events) {
            Tab tab = new Tab(ev.getName() + " (" + ev.getId() + ")");
            TableView<Feedback> table = new TableView<>();
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            // Cột tên người phản hồi (hiển thị tên, hover hiện tooltip chi tiết)
            TableColumn<Feedback, String> colAttendee = new TableColumn<>("Tên người phản hồi");
            colAttendee.setCellValueFactory(data -> {
                Attendee at = AttendeeManager.getAttendeeById(data.getValue().getAttendeeId());
                return new javafx.beans.property.SimpleStringProperty(at != null ? at.getName() : data.getValue().getAttendeeId());
            });
            colAttendee.setCellFactory(tc -> {
                TableCell<Feedback, String> cell = new TableCell<>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setTooltip(null);
                        } else {
                            setText(item);
                            Feedback fb = getTableView().getItems().get(getIndex());
                            Attendee attendee = AttendeeManager.getAttendeeById(fb.getAttendeeId());
                            if (attendee != null) {
                                Tooltip tp = new Tooltip(
                                    "Mã: " + attendee.getId() + "\n"
                                    + "Tên: " + attendee.getName() + "\n"
                                    + "Email: " + attendee.getEmail() + "\n"
                                    + "SĐT: " + attendee.getPhone()
                                );
                                setTooltip(tp);
                            } else {
                                setTooltip(new Tooltip("Không tìm thấy thông tin!"));
                            }
                        }
                    }
                };
                return cell;
            });

            TableColumn<Feedback, String> colContent = new TableColumn<>("Nội dung phản hồi");
            colContent.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getContent()));

            table.getColumns().addAll(colAttendee, colContent);
            table.setPrefHeight(410);

            // Lấy dữ liệu phản hồi THỰC TẾ gửi vào Manager chung
            ObservableList<Feedback> feedbacks = FXCollections.observableArrayList(
                FeedbackManager.getAllFeedback().stream()
                    .filter(fb -> fb.getEventId().equals(ev.getId()))
                    .collect(Collectors.toList())
            );
            table.setItems(feedbacks);

            VBox vbox = new VBox(table);
            tab.setContent(vbox);
            tabPaneEvents.getTabs().add(tab);
        }
    }
}
