package view_model;

public class EventEditorState {
    private String eventTitle;
    private String eventDescription;
    private String eventDate;
    private String eventTags;
    private String eventMedia;

    public EventEditorState() {
        this.eventTitle = null;
        this.eventDescription = null;
        this.eventDate = null;
        this.eventTags = null;
        this.eventMedia = null;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTags() {
        return eventTags;
    }

    public void setEventTags(String eventTags) {
        this.eventTags = eventTags;
    }

    public String getEventMedia() {
        return eventMedia;
    }

    public void setEventMedia(String eventMedia) {
        this.eventMedia = eventMedia;
    }
}
