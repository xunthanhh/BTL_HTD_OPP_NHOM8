package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.model.Event;
import main.model.EventManager;
import main.model.Feedback;
import main.model.Attendee;
import main.model.FeedbackManager;

import java.util.List;
import java.util.stream.Collectors;

public class SendFeedbackController {
    @FXML private ComboBox<Event> eventComboBox;
    @FXML private TextArea feedbackContent;
    @FXML private Label statusLabel;

    private Attendee currentAttendee;

    public void setCurrentAttendee(Attendee attendee) {
        this.currentAttendee = attendee;
        loadRegisteredEvents();
    }

    // Hiện ra chỉ các sự kiện đã đăng ký mới được gửi phản hồi
    private void loadRegisteredEvents() {
        if (currentAttendee == null) {
            eventComboBox.getItems().clear();
            return;
        }
        List<Event> events = EventManager.getAllEvents().stream()
            .filter(ev -> ev.getAttendeeList().stream()
                .anyMatch(a -> a.getId().equals(currentAttendee.getId())))
            .collect(Collectors.toList());
        eventComboBox.getItems().setAll(events);
    }

    @FXML
    public void initialize() {
        statusLabel.setText("");
    }

    @FXML
    private void handleSendFeedback() {
        Event selectedEvent = eventComboBox.getValue();
        String content = feedbackContent.getText().trim();
        if (selectedEvent == null) {
            statusLabel.setText("Bạn cần chọn sự kiện đã tham gia!");
            statusLabel.setStyle("-fx-text-fill:red;");
            return;
        }
        if (content.isEmpty()) {
            statusLabel.setText("Phản hồi không được để trống!");
            statusLabel.setStyle("-fx-text-fill:red;");
            return;
        }
        Feedback newFeedback = new Feedback(
            currentAttendee.getId(),
            selectedEvent.getId(),
            content
        );
        // ======= FIX Ở ĐÂY: DÙNG MANAGER CHUNG =======
        FeedbackManager.addFeedback(newFeedback);

        statusLabel.setText("Gửi phản hồi thành công!");
        statusLabel.setStyle("-fx-text-fill:green;");
        feedbackContent.clear();
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/view/AttendeeDashboardView.fxml"));
            Parent root = loader.load();
            main.controller.AttendeeDashboardController controller = loader.getController();
            controller.setLoggedInAttendee(currentAttendee);
            Stage stage = (Stage) statusLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Trang người dùng");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
