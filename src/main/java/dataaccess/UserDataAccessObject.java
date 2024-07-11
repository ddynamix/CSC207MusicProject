package dataaccess;

import dataaccess.mongodb.Connection;
import entity.user.User;
import entity.user.UserFactory;

public class UserDataAccessObject implements UserDataAccessInterface {

    public static class UserNotFoundException extends Exception{

    }

    private Connection connection;
    private UserFactory userFactory;

    public UserDataAccessObject(Connection connection, UserFactory userFactory) {
        this.userFactory = userFactory;
        this.connection = connection;

    }

    @Override
    public boolean userExistsInDatabase(String username) {
        return false;
    }

    @Override
    public void update(User user) {

    }

    private void create() {

    }

    @Override
    public void create(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public String[] getUserData(User user) {
        return new String[0];
    }

    @Override
    public void Throwable(UserNotFoundException userNotFoundException){
        System.out.println("User was not found in the database");
    }
}
