package main.model;

public class Feedback {
    private String attendeeId;
    private String eventId;
    private String content;

    public Feedback(String attendeeId, String eventId, String content) {
        this.attendeeId = attendeeId;
        this.eventId = eventId;
        this.content = content;
    }

    public String getAttendeeId() { return attendeeId; }
    public String getEventId() { return eventId; }
    public String getContent() { return content; }
}

