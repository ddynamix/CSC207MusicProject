package dataaccess;
import entity.user.ArtistUser;
import entity.user.AudienceUser;
import entity.user.User;
import entity.user.VenueUser;

public interface UserDataAccessInterface {

    // Data Access Interface - implement CRUD
    boolean userExistsInDatabase(String username);

    void updateUsername(User user, String newUsername);
    void updatePassword(User user, String newPassword, String confirmPassword);
    void updateEmail(User user, String newEmail);

    void createFile();

    void saveAudienceUser(AudienceUser user);
    void saveArtistUser(ArtistUser user);
    void saveVenueUser(VenueUser user);
    void delete(User user);

    String[] getUserData(User user);

    boolean passwordMatches(String username, String password);

    // This is also where we declare any Exceptions that wil be thrown
    // e.g. User not found in the database.

    void Throwable(UserDataAccessObject.UserNotFoundException userNotFoundException);
}
