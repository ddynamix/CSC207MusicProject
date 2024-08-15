package use_case.screen_switcher;

import entity.user.ArtistUser;
import entity.user.User;
import entity.user.VenueUser;

import java.util.ArrayList;

/**
 * data for switcher
 */
public class ScreenSwitcherEventCrafterData {
    private User signedInAs;
    private ArrayList<ArtistUser> artistUsers;
    private ArrayList<VenueUser> venueUsers;

    /**
     * create instance of switcher data
     * @param signedInAs current login
     * @param artistUsers list of all artist
     * @param venueUsers list of all venues
     */
    public ScreenSwitcherEventCrafterData(User signedInAs, ArrayList<ArtistUser> artistUsers, ArrayList<VenueUser> venueUsers) {
        this.signedInAs = signedInAs;
        this.artistUsers = artistUsers;
        this.venueUsers = venueUsers;
    }

    /**
     * access current login
     * @return current login
     */
    public User getSignedInAs() {
        return signedInAs;
    }

    /**
     * access artists
     * @return artists
     */
    public ArrayList<ArtistUser> getArtistUsers() {
        return artistUsers;
    }

    /**
     * access venues
     * @return venues
     */
    public ArrayList<VenueUser> getVenueUsers() {
        return venueUsers;
    }
}
