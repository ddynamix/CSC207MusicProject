package usecase.audiencesignup;

import usecase.UserSignupInputData;

/**
 * Based on implementation by Paul Gries
 * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/use_case/SignupInputData.java">...</a>
 */
public class AudienceSignupInputData extends UserSignupInputData {

    private final String firstName;
    private final String lastName;

    public AudienceSignupInputData(String username, String password, String repeatPass,
                                   String email, String firstName, String lastName) {
        super(username, password, repeatPass, email);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
}
