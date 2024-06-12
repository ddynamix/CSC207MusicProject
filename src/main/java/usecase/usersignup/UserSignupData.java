package usecase.usersignup;

/**
 * Based on implementation by Paul Gries
 * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/use_case/SignupInputData.java">...</a>
 */
public class UserSignupData {

    final private String username;
    final private String password;
    final private String repeatPass;
    final private String email;
    final private String firstName;
    final private String lastName;

    public UserSignupData(String username, String password, String repeatPass,
                          String email, String firstName, String lastName) {

        this.username = username;
        this.password = password;
        this.repeatPass = repeatPass;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    String getUsername() {return username;}
    String getPassword() {return password;}
    String getRepeatPass() {return repeatPass;}
    String getEmail() {return email;}
    String getFirstName() {return firstName;}
    String getLastName() {return lastName;}
}
