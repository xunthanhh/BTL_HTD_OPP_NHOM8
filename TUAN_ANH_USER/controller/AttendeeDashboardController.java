package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.model.Attendee;

public class AttendeeDashboardController {
    private Attendee loggedInAttendee;

    @FXML
    private Label welcomeLabel;

    // Hàm truyền attendee đăng nhập cho dashboard
    public void setLoggedInAttendee(Attendee attendee) {
        this.loggedInAttendee = attendee;
        if (welcomeLabel != null && attendee != null)
            welcomeLabel.setText("Xin chào, " + attendee.getFullName());
    }

    @FXML
    private void handleViewUpcomingEvents() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/view/EventListView.fxml"));
        Parent root = loader.load();
        EventListController controller = loader.getController();
        controller.setCurrentAttendee(loggedInAttendee);
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Danh sách sự kiện");
    }

    @FXML
    private void handleViewEventDetail() throws Exception {
        // Tương tự, mở EventDetailView nếu bạn có
        // Chỉ truyền dữ liệu nếu nút lấy từ TableView (hoặc popup chọn)
    }

    @FXML
    private void handleRegisterEvent() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/view/EventRegisterView.fxml"));
        Parent root = loader.load();
        EventRegisterController controller = loader.getController();
        controller.setCurrentAttendee(loggedInAttendee);
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Đăng ký sự kiện");
    }

    @FXML
    private void handleViewRegisteredEvents() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/view/RegisteredEventView.fxml"));
        Parent root = loader.load();
        RegisteredEventController controller = loader.getController();
        controller.setCurrentAttendee(loggedInAttendee);
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Sự kiện đã đăng ký");
    }

    @FXML
private void handleFeedback() throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/view/SendFeedbackView.fxml"));
    Parent root = loader.load();
    main.controller.SendFeedbackController controller = loader.getController();
    controller.setCurrentAttendee(loggedInAttendee);
    Stage stage = (Stage) welcomeLabel.getScene().getWindow();
    stage.setScene(new Scene(root));
    stage.setTitle("Gửi phản hồi");
}


    @FXML
    private void handleChangePassword() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/view/ChangePasswordView.fxml"));
        Parent root = loader.load();
        ChangePasswordController controller = loader.getController();
        controller.setLoggedInAttendee(loggedInAttendee);
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Đổi mật khẩu");
    }

    @FXML
    private void handleLogout() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/view/AttendeeLoginForm.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Đăng nhập Người tham dự");
    }
}
