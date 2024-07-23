package interface_adapter.eventcrafter;

import dataaccess.EventDataAccessInterface;
import dataaccess.UserDataAccessInterface;
import entity.user.User;
import usecase.eventcrafter.EventCrafterInputBoundary;
import usecase.eventcrafter.EventCrafterInputData;

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
        User artistUser = userDataAccessInterface.getUserFromUsername(artist);
        EventCrafterInputData eventCrafterInputData = new EventCrafterInputData(
                title,
                description,
                artistUser,
                venue,
                dateAndTimeFormatted,
                tagsFormatted,
                postDateFormatted,
                attachedMedia
        );

        eventCrafterInteractor.attemptPostEvent(eventCrafterInputData);
    }

    private ArrayList<String> stringToArrayList(String tags) {
        return new ArrayList<>(Arrays.asList(tags.split(";")));
    }

}
