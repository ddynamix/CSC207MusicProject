package use_case.screen_switcher;

import entity.user.ArtistUser;
import entity.user.User;
import entity.user.VenueUser;

import java.util.ArrayList;

public class ScreenSwitcherEventCrafterData {
    private User signedInAs;
    private ArrayList<ArtistUser> artistUsers;
    private ArrayList<VenueUser> venueUsers;

    public ScreenSwitcherEventCrafterData(User signedInAs, ArrayList<ArtistUser> artistUsers, ArrayList<VenueUser> venueUsers) {
        this.signedInAs = signedInAs;
        this.artistUsers = artistUsers;
        this.venueUsers = venueUsers;
    }

    public User getSignedInAs() {
        return signedInAs;
    }

    public ArrayList<ArtistUser> getArtistUsers() {
        return artistUsers;
    }

    public ArrayList<VenueUser> getVenueUsers() {
        return venueUsers;
    }
}
