package entity.song;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ISong {
    public String getName();
    public String getArtist();
    public String getAlbum();
    public LocalDateTime getReleaseDate();
    public ArrayList<String> getTags(); //list of genres/tags, change to proper object
    public String getURL(); //URL to song on youtube or whatever
}
