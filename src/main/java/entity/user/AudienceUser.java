package entity.user;


/**
 * AudienceUser Class
 */
public class AudienceUser extends User{

    /**
     * Create new AudienceUser instance
     * @param username      String          username of user
     * @param password      String          password of user
     * @param email         String          email of user
     * @param firstName     String          first name of user
     * @param lastName      String          last name of user
     */
    public AudienceUser(String name, String username, String password, String email) {
        super(name, username, password, email);
    }

}

