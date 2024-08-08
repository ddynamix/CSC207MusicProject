/*
 *  Spotify Java API wrapper created by: Jonas Theleman https://github.com/spotify-web-api-java/spotify-web-api-java/tree/master
 */
package data_access.spotify;

import com.neovisionaries.i18n.CountryCode;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class SpotifyService {
    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(System.getenv("spotifyClientID"))
            .setClientSecret(System.getenv("spotifyClientSecret"))
            .build();

    private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();

    public SpotifyService() {
        setClientCredentials();
    }

    private static void setClientCredentials() {
        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            // Set access token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());

            System.out.println("Expires in: " + clientCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public String getPreviewUrl(String songName) {
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(songName).market(CountryCode.NA).build();
        try {
            Track track = searchTracksRequest.execute().getItems()[0];
            return track.getPreviewUrl();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
