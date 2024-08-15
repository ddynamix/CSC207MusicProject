package use_case.add_favourite_song.interface_adapter;

import use_case.add_favourite_song.AddFavouriteSongOutputBoundary;
import view_model.ProfileViewModel;

/**
 * Create Presenters for add favourite song use case
 */
public class AddFavouriteSongPresenter implements AddFavouriteSongOutputBoundary {
    private final ProfileViewModel profileViewModel;

    /**
     * create a presenter for add favourite song use case
     * @param profileViewModel view model for profile view
     */
    public AddFavouriteSongPresenter(ProfileViewModel profileViewModel) {
        this.profileViewModel = profileViewModel;
    }

    @Override
    public void updateFavouriteSong() {
        System.out.println("Favourite song added successfully");
        profileViewModel.firePropertyChanged();
    }
}
