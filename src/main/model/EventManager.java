package main.model;

import java.util.*;

public class EventManager {
    private static final Map<String, Event> eventMap = new HashMap<>();

    public static void addEvent(Event event) {
        eventMap.put(event.getId(), event);
    }

    public static void removeEvent(String eventId) {
        eventMap.remove(eventId);
    }

    public static Event getEventById(String eventId) {
        return eventMap.get(eventId);
    }

    public static List<Event> getAllEvents() {
        return new ArrayList<>(eventMap.values());
    }

    public static boolean removeEventById(String id) {
        return eventMap.remove(id) != null;
    }

    public static Event findEventById(String id) {
        return eventMap.get(id);
    }

    // Nghiệp vụ: đăng ký sự kiện cho attendee
    public static boolean registerForEvent(Event event, Attendee attendee) {
        if (!event.getAttendeeList().stream().anyMatch(a -> a.getId().equals(attendee.getId()))) {
            event.addAttendee(attendee);
            return true;
        }
        return false;
    }

    // Kiểm tra attendee đã đăng ký event chưa (dùng cho cột Registered TableView)
    public static boolean isRegistered(Event event, Attendee attendee) {
        return event.getAttendeeList().stream().anyMatch(a -> a.getId().equals(attendee.getId()));
    }
}
