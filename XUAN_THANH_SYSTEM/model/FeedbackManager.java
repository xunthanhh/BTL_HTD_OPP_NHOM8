package main.model;
import java.util.List;
import java.util.ArrayList;

public class FeedbackManager {
    private static final List<Feedback> feedbackList = new ArrayList<>();
    public static List<Feedback> getAllFeedback() {
        return feedbackList;
    }
    public static void addFeedback(Feedback fb) {
        feedbackList.add(fb);
    }
}

