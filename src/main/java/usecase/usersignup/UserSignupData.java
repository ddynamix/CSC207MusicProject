package usecase.usersignup;

/**
 * Based on implementation by Paul Gries
 * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/use_case/SignupInputData.java">...</a>
 *
 * Input data from signup view
 */
public class UserSignupData {

    final private String type;
    final private String username;
    final private String password;
    final private String repeatPass;
    final private String email;
    final private String name;

    /**
     * instance of input data
     * @param type  String  type of user desired
     * @param username  String  user identifier
     * @param password  String  password
     * @param repeatPass    String  repeated password entry
     * @param email String  email of user
     * @param name  String  name of user
     */
    public UserSignupData(String type, String username, String password, String repeatPass,
                          String email, String name) {

        this.type = type;
        this.username = username;
        this.password = password;
        this.repeatPass = repeatPass;
        this.email = email;
        this.name = name;
    }

    /**
     * return username
     * @return String username
     */
    String getUsername() {return username;}

    /**
     * Return password
     * @return String password
     */
    String getPassword() {return password;}

    /**
     * Return repeated password entry
     * @return String repeatPass
     */
    String getRepeatPass() {return repeatPass;}

    /**
     * Return email
     * @return String email
     */
    String getEmail() {return email;}

    /**
     * Return name
     * @return String name
     */
    String getName() {return name;}

    /**
     * Return type of user
     * @return String type
     */
    String getType(){return type;}
}
