package use_case.eventcrafter.interface_adapter;

import data_access.UserDataAccessInterface;
import entity.user.ArtistUser;
import entity.user.User;
import entity.user.VenueUser;
import use_case.eventcrafter.EventCrafterInputBoundary;
import use_case.eventcrafter.EventCrafterInputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class EventCrafterController {
    final EventCrafterInputBoundary eventCrafterInteractor;
    final UserDataAccessInterface userDataAccessInterface;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public EventCrafterController(EventCrafterInputBoundary eventCrafterInteractor, UserDataAccessInterface userDataAccessInterface) {
        this.eventCrafterInteractor = eventCrafterInteractor;
        this.userDataAccessInterface = userDataAccessInterface;
    }

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

    public void switchToEventScreen() {
        eventCrafterInteractor.switchToEventScreenCancel();
    }

    private ArrayList<String> stringToArrayList(String tags) {
        return new ArrayList<>(Arrays.asList(tags.split(";")));
    }
}
