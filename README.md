# BTL thực hành OOP - Nhóm 8
**Đồ án Java: Hệ thống quản lý sự kiện DNU Event Hub**

---

## Thành viên nhóm 8:

| STT | Họ tên | MSSV | GitHub Username | Vai trò |
|-----|--------|------|-----------------|---------|
| 1 | Quách Xuân Thành | [1871020545] | [@xunthanhh](https://github.com/xunthanhh) | Leader, Admin Module |
| 2 | Vũ Tuấn Anh | [1871020033] | [@tunxd492-byte](https://github.com/tunxd492-byte) | User Module |
| 3 | [Văn Linh] | [1871020356] | [@username3] https://github.com/beulinh/2025_FIT4007_H-T_NGUYENVANLINH | System Module |

---

## Mô tả dự án:

**DNU Event Hub** là hệ thống quản lý sự kiện dành cho trường Đại học, được xây dựng bằng Java với giao diện JavaFX theo mô hình MVC.

**Mục tiêu:**
- Quản trị viên quản lý sự kiện, người tham dự, xem báo cáo thống kê
- Người dùng đăng ký, tham gia sự kiện, gửi feedback, điểm danh QR

---

## Công nghệ sử dụng:

- **Ngôn ngữ:** Java
- **Framework UI:** JavaFX
- **Kiến trúc:** MVC (Model-View-Controller)
- **Design Pattern:** Singleton, Manager Pattern
- **Version Control:** Git, GitHub

---

## Cấu trúc dự án:
```BTL_HTD_OPP_NHOM8/
├── src/
│   ├── main/
│   │   ├── controller/
│   │   │   ├── AdminDashboardController.java
│   │   │   ├── AdminLoginController.java
│   │   │   ├── AttendanceQRController.java
│   │   │   ├── AttendeeDashboardController.java
│   │   │   ├── AttendeeLoginFormController.java
│   │   │   ├── ChangePasswordController.java
│   │   │   ├── EventCreateController.java
│   │   │   ├── EventDeleteController.java
│   │   │   ├── EventDetailController.java
│   │   │   ├── EventEditController.java
│   │   │   ├── EventListController.java
│   │   │   ├── EventRegisterController.java
│   │   │   ├── EventReportController.java
│   │   │   ├── ExportReportController.java
│   │   │   ├── FeedbackController.java
│   │   │   ├── MainViewController.java
│   │   │   ├── ParticipantViewController.java
│   │   │   ├── RegisterAttendeeFormController.java
│   │   │   ├── RegisteredEventController.java
│   │   │   ├── SendFeedbackController.java
│   │   │   ├── StatisticController.java
│   │   ├── model/
│   │   │   ├── Attendee.java
│   │   │   ├── Event.java
│   │   │   ├── EventManager.java
│   │   │   ├── Feedback.java
│   │   │   ├── FeedbackManager.java
│   │   │   ├── AttendeeManager.java
│   │   ├── view/
│   │   │   ├── AdminDashboardView.fxml
│   │   │   ├── AdminLoginView.fxml
│   │   │   ├── AttendanceQRView.fxml
│   │   │   ├── AttendeeDashboardView.fxml
│   │   │   ├── AttendeeLoginForm.fxml
│   │   │   ├── ChangePasswordView.fxml
│   │   │   ├── EventCreateView.fxml
│   │   │   ├── EventDeleteView.fxml
│   │   │   ├── EventDetailView.fxml
│   │   │   ├── EventEditView.fxml
│   │   │   ├── EventListView.fxml
│   │   │   ├── EventRegisterView.fxml
│   │   │   ├── EventReportView.fxml
│   │   │   ├── FeedbackExportView.fxml
│   │   │   ├── FeedbackView.fxml
│   │   │   ├── MainView.fxml
│   │   │   ├── ParticipantView.fxml
│   │   │   ├── RegisterAttendeeForm.fxml
│   │   │   ├── RegisteredEventView.fxml
│   │   │   ├── SendFeedbackView.fxml
│   │   │   ├── StatisticView.fxml
│   │   ├── DNUEventHubApp.java          # Main Application (Entry Point)
│   │   ├── style.css                   # CSS styling
├── README.md                           # File mô tả dự án
├── UML.png                             # Sơ đồ UML
```




## Sơ đồ UML:

### **1. Model/Entity Layer:**

+---------------------------------+
|           Event                 | <--------+
+---------------------------------+          |
| - id: String                    |          |
| - name: String                  |          |
| - date: String                  |          |   0..*     +-----------------------+
| - location: String              |          +----------> |    Attendee           |
| - description: String           |                     +-----------------------+
+---------------------------------+                     | - id: String          |
                                                        | - name: String        |
                                                        | - email: String       |
                                                        | - phone: String       |
                                                        | ...                   |
                                                        +-----------------------+
       ^
       |   0..*
       |
+------------------------------+
|      EventManager            |
+------------------------------+
| - events: List<Event>        |
| + addEvent(), editEvent()    |
| + deleteEvent()              |
| + getEventById(id)           |
+------------------------------+

+------------------------------+
|   AttendeeManager            |
+------------------------------+
| - attendees: List<Attendee>  |
| + addAttendee(),             |
| + registerForEvent(), ...    |
+------------------------------+

+-----------------------------+
|   FeedbackManager           |
+-----------------------------+
| - feedbacks: List<Feedback> |
| + addFeedback(), ...        |
+-----------------------------+ 
         |
         | 0..*
         V
+-----------------------------+
|         Feedback            |
+-----------------------------+
| - eventId: String           |
| - attendeeId: String        |
| - content: String           |
+-----------------------------+

// --------------------- Controller Layer ---------------------

[ADMIN]
+----------------------------------------+
| AdminLoginController                   |
| AdminDashboardController               |
| EventCreateController/EventEditController/EventDeleteController
| EventReportController                  |
| ExportReportController                 |
| StatisticController                    |
| ParticipantViewController              |
+----------------------------------------+

[USER/ATTENDEE]
+----------------------------------------+
| AttendeeLoginFormController            |
| AttendeeDashboardController            |
| RegisterAttendeeFormController         |
| RegisteredEventController              |
| SendFeedbackController                 |
| ChangePasswordController               |
+----------------------------------------+

[CHUNG/UI]
+----------------------------------------+
| MainViewController                     |
| DNUEventHubApp                         |
| FeedbackController                     |
| style.css                              |
| FeedbackExportView.fxml                |
| MainView.fxml                          |
+----------------------------------------+


bảng UML controller, chức năng
             +-------------------------+                +-----------------------+
             |      Event              |<-------------- | AdminDashboardController
             +-------------------------+                +-----------------------+
             | - id, name, ...         | <---create---  | EventCreateController
             +-------------------------+ <---edit-----  | EventEditController
                 ^ 0..*                 <---delete----  | EventDeleteController
                 |                      <---report----  | EventReportController
                 |                      <---list------  | EventListController
                 |                      <---detail----  | EventDetailController
                 |                      <---export----  | ExportReportController
                 |                      <---statistic-  | StatisticController
                 |                      <---participant-| ParticipantViewController
                 |                                      +
            +---------------------------+               +
            | Attendee                  | <---login---- | AttendeeLoginFormController
            +---------------------------+ <---dashboard | AttendeeDashboardController
            | - id, name, ...           | <---changePwd | ChangePasswordController
            +---------------------------+ <---register--| RegisterAttendeeFormController
                 ^ 0..*
                 |
                 | <---registerEvent--  | EventRegisterController
                 | <---registeredEvent- | RegisteredEventController
                 | <---feedback-------- | SendFeedbackController
                 | <---feedbackView---- | FeedbackController
                 | <---QRCheckIn------- | AttendanceQRController

            +---------------------------+
            | Feedback                  | <----system---| FeedbackManager
            +---------------------------+               | FeedbackController (system)
                                                        | FeedbackExportView.fxml
            +---------------------------+
            | Main Application          | <-----init----| MainViewController
            +---------------------------+ <----runMain--| DNUEventHubApp.java
                                                        | MainView.fxml
                                                        | style.css
XUÂN THÀNH 1
TUẤN ANH 2
VĂN LINH 3
(trong báo cáo có phân công rõ ràng và cụ thể thầy nhé !!!)
| Controller                     | Phân công |
| ------------------------------ | --------- |
| AdminDashboardController       | Người 1   |
| AdminLoginController           | Người 1   |
| EventCreateController          | Người 1   |
| EventEditController            | Người 1   |
| EventDeleteController          | Người 1   |
| EventReportController          | Người 1   |
| ExportReportController         | Người 1   |
| StatisticController            | Người 1   |
| ParticipantViewController      | Người 1   |
| AttendeeDashboardController    | Người 2   |
| AttendeeLoginFormController    | Người 2   |
| ChangePasswordController       | Người 2   |
| RegisterAttendeeFormController | Người 2   |
| RegisteredEventController      | Người 2   |
| EventRegisterController        | Người 2   |
| SendFeedbackController         | Người 2   |
| FeedbackController             | Người 2/3 |
| AttendanceQRController         | Người 2   |
| MainViewController             | Người 3   |
| DNUEventHubApp.java            | Người 3   |
| FeedbackManager.java           | Người 3   |

### **2. Controller Layer:**

**Admin Controllers Xuân Thành (Người 1):**
- AdminLoginController
- AdminDashboardController
- EventCreateController
- EventEditController
- EventDeleteController
- EventReportController
- ExportReportController
- StatisticController
- ParticipantViewController

**User Controllersontrollers Tuấn Anh (Người 2):**
- AttendeeLoginFormController
- AttendeeDashboardController
- RegisterAttendeeFormController
- EventRegisterController
- RegisteredEventController
- SendFeedbackController
- FeedbackController (Người 2/3)
- ChangePasswordController
- AttendanceQRController

### **Người 3 - Văn Linh (System Module):**
- **MainViewController** - Controller màn hình chính
- **DNUEventHubApp.java** - Main Application (Entry Point)
- **FeedbackController (system)** - Quản lý feedback hệ thống
- **AttendanceQRController** - Điểm danh QR
- **Models (toàn hệ thống):**
  - Event.java, EventManager.java
  - Attendee.java, AttendeeManager.java
  - Feedback.java, FeedbackManager.java
- **Views:** MainView.fxml, FeedbackExportView.fxml, style.css
- Thiết lập cấu trúc package `main`


---

## Các chức năng chính:

### **Phần Admin:**
1. Đăng nhập quản trị
2. Tạo/Sửa/Xóa sự kiện
3. Xem danh sách người tham dự
4. Xem báo cáo sự kiện
5. Xuất báo cáo
6. Xem thống kê

### **Phần User:**
1. Đăng ký tài khoản
2. Đăng nhập
3. Xem danh sách sự kiện
4. Đăng ký tham gia sự kiện
5. Xem sự kiện đã đăng ký
6. Điểm danh QR code
7. Gửi feedback
8. Xem feedback
9. Đổi mật khẩu

### **Phần System:**
1. Quản lý luồng ứng dụng
2. Quản lý feedback hệ thống
3. Styling và UI chung

---

## Git Branches:

- **`main`**: Nhánh chính, code hoàn chỉnh
- **`xuanthanh-admin`**: Nhánh phát triển Admin Module (Người 1)
- **`TUANHANH-USER`**: Nhánh phát triển User Module (Người 2)
- **`VANLINHsystem-module`**: Nhánh phát triển System Module (Người 3)

---

## Ghi chú:

- Dự án tuân thủ nguyên tắc OOP và Design Pattern
- Phân công rõ ràng theo module: Admin, User, System
- Mỗi thành viên làm việc trên nhánh riêng
- Code được merge vào `main` sau khi hoàn thành
- **Trong báo cáo có phân công rõ ràng và có thể thấy như trên!!!**

---

## Thông tin liên hệ:

- **Nhóm:** Nhóm 8
- **Lớp:** [ CNTT 18-07 ]
- **Giảng viên hướng dẫn:** [ GV TẠ CHÍ HIẾU ]
- **Học kỳ:** [HK1 - NĂM 2025 - 2026 ]
- SDT : 0837520206
- EMAIL: quachxuanthanh206@gmail.com
---

**Ngày hoàn thành:** 20/11/2025  
**GitHub Repository:** https://github.com/xunthanhh/BTL_HTD_OPP_NHOM8

---

© 2025 DNU Event Hub - Nhóm 8
