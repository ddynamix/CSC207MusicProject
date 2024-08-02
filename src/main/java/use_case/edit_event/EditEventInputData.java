package use_case.edit_event;

import entity.event.Event;

public class EditEventInputData {
    private final Event eventToAlter;

    private final String title;
    private final String description;
    private final String date;
    private final String tags;
    private final String media;


    public EditEventInputData(Event eventToAlter) {
        this.eventToAlter = eventToAlter;

        this.title = "";
        this.description = "";
        this.date = "";
        this.tags = "";
        this.media = "";
    }

    public EditEventInputData(Event eventToAlter, String title, String description, String date, String tags, String media) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.tags = tags;
        this.media = media;

        this.eventToAlter = eventToAlter;
    }

    public Event getEventToAlter() {
        return eventToAlter;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTags() {
        return tags;
    }

    public String getMedia() {
        return media;
    }
}
