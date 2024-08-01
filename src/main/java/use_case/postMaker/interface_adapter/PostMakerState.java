package use_case.postMaker.interface_adapter;

import entity.user.ArtistUser;
import entity.user.User;
import entity.user.VenueUser;

import java.util.ArrayList;

public class PostMakerState {
    private User signedInAs;
    private ArrayList<ArtistUser> artistUsers;
    private ArrayList<VenueUser> venueUsers;

    public PostMakerState() {
        this.signedInAs = null;
        this.artistUsers = new ArrayList<>();
        this.venueUsers = new ArrayList<>();
    }

    public void setSignedInAs(User signedInAs) {
        this.signedInAs = signedInAs;
    }

    public User getSignedInAs() {
        return signedInAs;
    }

    public ArrayList<ArtistUser> getArtistUsers() {
        return artistUsers;
    }

    public void setArtistUsers(ArrayList<ArtistUser> artistUsers) {
        this.artistUsers = artistUsers;
    }

    public ArrayList<VenueUser> getVenueUsers() {
        return venueUsers;
    }

    public void setVenueUsers(ArrayList<VenueUser> venueUsers) {
        this.venueUsers = venueUsers;
    }
}
