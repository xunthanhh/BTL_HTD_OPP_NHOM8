package main.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Attendee {
    private final StringProperty username;
    private final StringProperty fullName;
    private final StringProperty email;
    private final StringProperty phone;
    private final StringProperty password;

    public Attendee(String username, String fullName, String email, String phone, String password) {
        this.username = new SimpleStringProperty(username);
        this.fullName = new SimpleStringProperty(fullName);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.password = new SimpleStringProperty(password);
    }

    public String getUsername() { return username.get(); }
    public StringProperty usernameProperty() { return username; }

    public String getFullName() { return fullName.get(); }
    public StringProperty fullNameProperty() { return fullName; }

    public String getEmail() { return email.get(); }
    public StringProperty emailProperty() { return email; }

    public String getPhone() { return phone.get(); }
    public StringProperty phoneProperty() { return phone; }

    public String getPassword() { return password.get(); }
    public StringProperty passwordProperty() { return password; }

    public void setPassword(String password) { this.password.set(password); }

    // Để tương thích với phương thức trong Event: getId()
    public String getId() { return getUsername(); }
    public String getName() { return getFullName(); }
}
