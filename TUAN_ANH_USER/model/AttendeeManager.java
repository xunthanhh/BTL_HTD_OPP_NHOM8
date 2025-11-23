package main.model;

import java.util.ArrayList;
import java.util.List;

public class AttendeeManager {
    private static final List<Attendee> attendeeList = new ArrayList<>();

    public static List<Attendee> getAllAttendees() {
        return attendeeList;
    }

    public static void addAttendee(Attendee attendee) {
        attendeeList.add(attendee);
    }

    public static Attendee getAttendeeById(String id) {
    for (Attendee at : getAllAttendees()) {
        if (at.getId().equals(id)) return at;
    }
    return null;
}

}
