package entity.event;

import entity.user.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EventFactoryTest {

    @Test
    void testCreateEvent() {
        EventFactory eventFactory = new EventFactory();
        String title = "Music Concert";
        User artist = new User("artistUsername", "password", "test@email.com"); // Assuming a simple User constructor
        String venue = "Madison Square Garden";
        LocalDateTime dateAndTime = LocalDateTime.of(2024, 8, 15, 20, 0);
        String description = "A live music concert featuring famous artists.";
        ArrayList<String> tags = new ArrayList<>();
        tags.add("music");
        tags.add("live");
        String attachedMedia = "https://example.com/media";

        Event event = eventFactory.createEvent(title, artist, venue, dateAndTime, description, tags, attachedMedia);

        assertNotNull(event, "The event should not be null");
        assertEquals(title, event.getTitle(), "The title should be the same");
        assertEquals(artist, event.getArtist(), "The artist should be the same");
        assertEquals(venue, event.getVenue(), "The venue should be the same");
        assertEquals(dateAndTime, event.getDateAndTime(), "The date and time should be the same");
        assertEquals(description, event.getDescription(), "The description should be the same");
        assertEquals(tags, event.getTags(), "The tags should be the same");
        assertEquals(attachedMedia, event.getAttachedMedia(), "The attached media should be the same");

        // Check that postDate is set to close to the current time
        LocalDateTime now = LocalDateTime.now();
        assertTrue(event.getPostDate().isAfter(now.minusMinutes(1)) && event.getPostDate().isBefore(now.plusMinutes(1)),
                "The postDate should be close to the current time");
    }
}
