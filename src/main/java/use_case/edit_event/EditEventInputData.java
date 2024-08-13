package use_case.edit_event;

import entity.event.Event;

/**
 * input data for edit event use case
 */
public class EditEventInputData {
    private final Event eventToAlter;

    private final String title;
    private final String description;
    private final String date;
    private final String tags;
    private final String media;


    /**
     * create instance of input data for edit event use case
     * @param eventToAlter current event
     */
    public EditEventInputData(Event eventToAlter) {
        this.eventToAlter = eventToAlter;

        this.title = "";
        this.description = "";
        this.date = "";
        this.tags = "";
        this.media = "";
    }

    /**
     * update data
     * @param eventToAlter current event
     * @param title of event
     * @param description of event
     * @param date of event
     * @param tags applied to event
     * @param media of event
     */
    public EditEventInputData(Event eventToAlter, String title, String description, String date, String tags, String media) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.tags = tags;
        this.media = media;

        this.eventToAlter = eventToAlter;
    }

    /**
     * access current event
     * @return current event
     */
    public Event getEventToAlter() {
        return eventToAlter;
    }

    /**
     * access title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * access description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * access date
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * access event tags
     * @return tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * access media
     * @return media
     */
    public String getMedia() {
        return media;
    }
}
