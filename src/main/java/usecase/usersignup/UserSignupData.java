package usecase.usersignup;

/**
 * Based on implementation by Paul Gries
 * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/use_case/SignupInputData.java">...</a>
 */
public class UserSignupData {

    final private String type;
    final private String username;
    final private String password;
    final private String repeatPass;
    final private String email;
    final private String name;

    public UserSignupData(String type, String username, String password, String repeatPass,
                          String email, String name) {

        this.type = type;
        this.username = username;
        this.password = password;
        this.repeatPass = repeatPass;
        this.email = email;
        this.name = name;
    }

    String getUsername() {return username;}
    String getPassword() {return password;}
    String getRepeatPass() {return repeatPass;}
    String getEmail() {return email;}
    String getName() {return name;}
    String getType(){return type;}
}
