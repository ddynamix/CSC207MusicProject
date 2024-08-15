package entity.user;


/**
 * AudienceUser Class
 */
public class AudienceUser extends User{

    /**
     * Create new AudienceUser instance
     * @param name          String          name of user
     * @param username      String          username of user
     * @param password      String          password of user
     * @param email         String          email of user
     */
    public AudienceUser(String name, String username, String password, String email) {
        super(name, username, password, email);
    }

}

