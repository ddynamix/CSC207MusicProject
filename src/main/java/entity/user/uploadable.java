package entity.user;

import entity.event.Event;
import entity.post.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface uploadable {
    public Event createNewEvent(String title, User artist, String venue, LocalDateTime dateAndTime, String description, ArrayList<String> tags, LocalDateTime postDate, String attachedMedia, int id);
    public void contactFollowers(Post post);
    public void updateEventTitle(Event event, String newTitle);
    public void updateEventVenue(Event event, String newVenue);
    public void updateEventTime(Event event, LocalDateTime newTime);
    public void updateEventDescription(Event event, String newDescription);
    public void updateEventTags(Event event, ArrayList<String> newTags);
    public void updateEventMedia(Event event, String newMedia);
    public void removeEvent(Event event);
}
