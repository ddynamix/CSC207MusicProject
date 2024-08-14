package view_model;

import entity.user.ArtistUser;
import entity.user.User;
import entity.user.VenueUser;

import java.util.ArrayList;

/**
 * state of event
 */
public class EventCrafterState {
    private User signedInAs;
    private ArrayList<ArtistUser> artistUsers;
    private ArrayList<VenueUser> venueUsers;

    /**
     * create instance of state
     */
    public EventCrafterState() {
        this.signedInAs = null;
        this.artistUsers = new ArrayList<>();
        this.venueUsers = new ArrayList<>();
    }

    /**
     * update logged in user
     * @param signedInAs new login
     */
    public void setSignedInAs(User signedInAs) {
        this.signedInAs = signedInAs;
    }

    /**
     * access current login
     * @return user logged in
     */
    public User getSignedInAs() {
        return signedInAs;
    }

    /**
     * access artist users
     * @return list of artist users
     */
    public ArrayList<ArtistUser> getArtistUsers() {
        return artistUsers;
    }

    /**
     * update artist users
     * @param artistUsers list to be set
     */
    public void setArtistUsers(ArrayList<ArtistUser> artistUsers) {
        this.artistUsers = artistUsers;
    }

    /**
     * access venue users
     * @return list of venue users
     */
    public ArrayList<VenueUser> getVenueUsers() {
        return venueUsers;
    }

    /**
     * update venue users
     * @param venueUsers list of venue users
     */
    public void setVenueUsers(ArrayList<VenueUser> venueUsers) {
        this.venueUsers = venueUsers;
    }
}
