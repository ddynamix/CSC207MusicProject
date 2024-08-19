package entity.song;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * interface for Song Object
 */
public interface ISong {
    String getName();
    String getArtist();
    String getAlbum();
    LocalDate getReleaseDate();
    ArrayList<String> getTags(); //list of genres/tags, change to proper object
    String getURL(); //URL to song on YouTube or whatever
}
