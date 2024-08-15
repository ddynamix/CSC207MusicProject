package use_case.eventcrafter.interface_adapter;

import data_access.UserDataAccessInterface;
import entity.user.ArtistUser;
import entity.user.VenueUser;
import use_case.eventcrafter.EventCrafterInputBoundary;
import use_case.eventcrafter.EventCrafterInputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * controller for event use case
 */
public class EventCrafterController {
    final EventCrafterInputBoundary eventCrafterInteractor;
    final UserDataAccessInterface userDataAccessInterface;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    /**
     * create instance of controller for event use case
     * @param eventCrafterInteractor interactor for event use case
     * @param userDataAccessInterface user DAO
     */
    public EventCrafterController(EventCrafterInputBoundary eventCrafterInteractor, UserDataAccessInterface userDataAccessInterface) {
        this.eventCrafterInteractor = eventCrafterInteractor;
        this.userDataAccessInterface = userDataAccessInterface;
    }

    /**
     * Create event and action
     * @param title of event
     * @param artist of event
     * @param venue of event
     * @param dateAndTime of event
     * @param description of event
     * @param tags applied to event
     * @param postDate of event
     * @param attachedMedia of event
     */
    public void excecute(String title,
                         String artist,
                         String venue,
                         String dateAndTime,
                         String description,
                         String tags,
                         String postDate,
                         String attachedMedia) {

        LocalDateTime dateAndTimeFormatted = LocalDateTime.parse(dateAndTime, formatter);
        LocalDateTime postDateFormatted = LocalDateTime.parse(postDate, formatter);
        ArrayList<String> tagsFormatted = stringToArrayList(tags);

        ArtistUser artistUser = userDataAccessInterface.getUserFromUsername(artist);
        VenueUser venueUser = userDataAccessInterface.getUserFromUsername(venue);

        EventCrafterInputData eventCrafterInputData = new EventCrafterInputData(
                title,
                description,
                artistUser,
                venueUser,
                dateAndTimeFormatted,
                tagsFormatted,
                postDateFormatted,
                attachedMedia
        );

        eventCrafterInteractor.attemptPostEvent(eventCrafterInputData);
    }

    /**
     * convert string to ArrayList
     * @param tags string from data
     * @return ArrayList of tags
     */
    private ArrayList<String> stringToArrayList(String tags) {
        return new ArrayList<>(Arrays.asList(tags.split(";")));
    }
}
