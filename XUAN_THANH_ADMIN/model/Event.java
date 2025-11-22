package main.model;

import javafx.beans.property.*;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty date;      // Hiển thị date, có thể là time
    private final StringProperty location;
    private final StringProperty description;

    private final List<Attendee> attendeeList = new ArrayList<>();

    public Event(String id, String name, String date, String location, String description) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleStringProperty(date);
        this.location = new SimpleStringProperty(location);
        this.description = new SimpleStringProperty(description);
    }

    // Property getters - dùng cho TableView
    public StringProperty idProperty() { return id; }
    public StringProperty nameProperty() { return name; }
    public StringProperty dateProperty() { return date; }
    public StringProperty locationProperty() { return location; }
    public StringProperty descriptionProperty() { return description; }

    // Value getters/setters
    public String getId() { return id.get(); }
    public String getName() { return name.get(); }
    public String getDate() { return date.get(); }
    public String getLocation() { return location.get(); }
    public String getDescription() { return description.get(); }

    public void setName(String name) { this.name.set(name); }
    public void setDate(String date) { this.date.set(date); }
    public void setLocation(String location) { this.location.set(location); }
    public void setDescription(String description) { this.description.set(description); }

    // Danh sách attendee
    public List<Attendee> getAttendeeList() { return attendeeList; }
    public void addAttendee(Attendee attendee) { attendeeList.add(attendee); }
    public void removeAttendee(String attendeeId) {
        attendeeList.removeIf(a -> a.getId().equals(attendeeId));
    }

    // Cho TableView: xét attendee đã đăng ký chưa
    public BooleanProperty registeredProperty(Attendee attendee) {
        boolean isRegistered = attendeeList.stream()
                .anyMatch(a -> a.getId().equals(attendee.getId()));
        return new SimpleBooleanProperty(isRegistered);
    }
        @Override
public String toString() {
    return getName() + " - " + getDate();
}


}
