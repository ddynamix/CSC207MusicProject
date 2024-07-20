package usecase.signup.venuesignup;

import usecase.signup.UserSignupInputData;

public class VenueSignupInputData extends UserSignupInputData {

    private final String venueName;
    private final String location;

    public VenueSignupInputData(String username, String password, String repeatPass, String email, String venueName, String location) {
        super(username, password, repeatPass, email);
        this.venueName = venueName;
        this.location = location;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getLocation() {
        return location;
    }
}
