package usecase.artistsignup;

import usecase.UserSignupInputData;

public class ArtistSignupInputData extends UserSignupInputData {

    private final String stageName;

    public ArtistSignupInputData(String username, String password, String repeatPass, String email, String stageName) {
        super(username, password, repeatPass, email);
        this.stageName = stageName;
    }

    public String getStageName() {
        return stageName;
    }
}
