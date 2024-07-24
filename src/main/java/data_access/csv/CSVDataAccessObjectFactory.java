package data_access.csv;

import data_access.DataAccessFactoryInterface;
import data_access.EventDataAccessInterface;
import data_access.UserDataAccessInterface;

import java.io.IOException;

public class CSVDataAccessObjectFactory implements DataAccessFactoryInterface {
    private final UserDataAccessInterface userDataAccessObject;
    private final EventDataAccessInterface eventDataAccessObject;

    public CSVDataAccessObjectFactory() {
        try {
            userDataAccessObject = new UserLocalCSVDataStorage("./users.csv");
            eventDataAccessObject = new EventLocalCSVDataStorage("./events.csv", userDataAccessObject);
        } catch (IOException e) {
            throw new RuntimeException("Could not create CSV data access objects.");
        }
    }

    @Override
    public EventDataAccessInterface getEventDAO() {
        return eventDataAccessObject;
    }

    @Override
    public UserDataAccessInterface getUserDAO() {
        return userDataAccessObject;
    }
}
