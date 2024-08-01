package use_case.edit_event;

public class EditEventOutputData {
    private final String eventTitle;
    private final String eventDescription;
    private final String eventDate;
    private final String eventTags;
    private final String eventMedia;

    public EditEventOutputData(String eventTitle, String eventDescription, String eventDate, String eventTags, String eventMedia) {
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.eventTags = eventTags;
        this.eventMedia = eventMedia;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventTags() {
        return eventTags;
    }

    public String getEventMedia() {
        return eventMedia;
    }
}
