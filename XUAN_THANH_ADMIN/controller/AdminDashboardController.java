package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import main.model.Attendee;

public class AdminDashboardController {

    @FXML private AnchorPane contentArea;

    private Attendee currentAttendee; // nếu có logic session

    // Load view FXML vào khu vực contentArea
    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent view = loader.load();

            // Nếu load EventListView thì truyền attendee!
            if (fxmlPath.contains("EventListView.fxml")) {
                EventListController controller = loader.getController();
                controller.setCurrentAttendee(currentAttendee);
            }

            contentArea.getChildren().setAll(view);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Không thể mở giao diện!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML private void handleCreateEvent()     { loadView("/main/view/EventCreateView.fxml"); }
    @FXML private void handleEditEvent()       { loadView("/main/view/EventEditView.fxml"); }
    @FXML private void handleCancelEvent()     { loadView("/main/view/EventDeleteView.fxml"); }
    @FXML private void handleViewParticipants(){ loadView("/main/view/ParticipantView.fxml"); }
    @FXML private void handleViewReports()     { loadView("/main/view/EventReportView.fxml"); }
    @FXML private void handleViewStatistics()  { loadView("/main/view/StatisticView.fxml"); }
    @FXML private void handleAttendanceQR()    { loadView("/main/view/AttendanceQRView.fxml"); }
    @FXML private void handleFeedback()        { loadView("/main/view/FeedbackView.fxml"); }
    @FXML private void handleExportReport()    { loadView("/main/view/FeedbackExportView.fxml"); }
    @FXML private void handleViewEventList()   { loadView("/main/view/EventListView.fxml"); } // THÊM nút cho xem DS sự kiện

    @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/view/AdminLoginView.fxml"));
            Parent root = loader.load();
            javafx.stage.Stage stage = (javafx.stage.Stage) contentArea.getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
            stage.setTitle("Đăng nhập Admin");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
