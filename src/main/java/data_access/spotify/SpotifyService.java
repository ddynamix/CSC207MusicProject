package data_access.spotify;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class SpotifyService {
    private SpotifyApi spotifyApi;

    public SpotifyService() {
        this.spotifyApi = new SpotifyApi.Builder()
                .setClientId(System.getenv("spotifyClientID"))
                .setClientSecret("spotifyClientSecret")
                .build();

        try {
            String accessToken = spotifyApi.clientCredentials().build().execute().getAccessToken();
            spotifyApi.setAccessToken(accessToken);
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace();
        }
    }

    public String getPreviewUrl(String songName) {
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(songName).build();
        try {
            Track track = searchTracksRequest.execute().getItems()[0];
            return track.getPreviewUrl();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
