package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import main.model.Event;
import main.model.EventManager;
import main.model.Feedback;
import main.model.FeedbackManager;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Arrays;

public class ExportReportController {
    @FXML private ComboBox<String> reportTypeCombo;
    @FXML private Label statusLabel;

    private final List<String> reportTypes = Arrays.asList(
            "Danh sách sự kiện",
            "Thống kê người tham dự",
            "Phản hồi người tham dự"
    );

    @FXML
    public void initialize() {
        reportTypeCombo.getItems().setAll(reportTypes);
    }

    @FXML
    private void handleExportCSV() {
        String type = reportTypeCombo.getValue();
        if (type == null) {
            statusLabel.setText("Vui lòng chọn loại báo cáo!");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        FileChooser fc = new FileChooser();
        fc.setTitle("Chọn nơi lưu file CSV");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
        File file = fc.showSaveDialog(null);
        if (file == null) return;
        try (FileWriter fw = new FileWriter(file)) {
            if (type.equals(reportTypes.get(0))) {
                fw.write("Mã sự kiện,Tên sự kiện,Ngày,Địa điểm,Số người tham dự\n");
                for (Event ev : EventManager.getAllEvents()) {
                    fw.write(ev.getId() + "," + ev.getName() + "," + ev.getDate() + "," + ev.getLocation() + "," + ev.getAttendeeList().size() + "\n");
                }
            } else if (type.equals(reportTypes.get(1))) {
                fw.write("Mã sự kiện,Tên sự kiện,Mã người tham dự,Tên người tham dự\n");
                for (Event ev : EventManager.getAllEvents()) {
                    for (var at : ev.getAttendeeList()) {
                        fw.write(ev.getId() + "," + ev.getName() + "," + at.getId() + "," + at.getName() + "\n");
                    }
                }
            } else {
                fw.write("Sự kiện,Người tham dự,Nội dung phản hồi\n");
                for (Feedback fb : FeedbackManager.getAllFeedback()) {
                    fw.write(fb.getEventId() + "," + fb.getAttendeeId() + "," + fb.getContent() + "\n");
                }
            }
            statusLabel.setText("Xuất file CSV thành công: " + file.getAbsolutePath());
            statusLabel.setStyle("-fx-text-fill: green;");
        } catch (Exception e) {
            statusLabel.setText("Lỗi khi xuất file: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }
}
