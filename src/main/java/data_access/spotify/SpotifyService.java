/*
 *  Spotify Java API wrapper created by: Jonas Theleman https://github.com/spotify-web-api-java/spotify-web-api-java/tree/master
 */
package data_access.spotify;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.*;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.model_objects.AbstractModelObject;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.artists.GetArtistRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.ArrayList;

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

    public static String getSongName(String songName) {
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(songName).market(CountryCode.NA).build();
        try {
            Track track = searchTracksRequest.execute().getItems()[0];
            return track.getName();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSongArtist(String songName) {
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(songName).market(CountryCode.NA).build();
        try {
            Track track = searchTracksRequest.execute().getItems()[0];
            return track.getArtists()[0].getName();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSongAlbum(String songName) {
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(songName).market(CountryCode.NA).build();
        try {
            Track track = searchTracksRequest.execute().getItems()[0];
            return track.getAlbum().getName();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSongReleaseDate(String songName) {
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(songName).market(CountryCode.NA).build();
        try {
            Track track = searchTracksRequest.execute().getItems()[0];
            return track.getAlbum().getReleaseDate();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSongTags(String songName) {
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(songName).market(CountryCode.NA).build();
        try {
            Track track = searchTracksRequest.execute().getItems()[0];
            Artist artist = getFullArtist(track.getArtists()[0].getId());
            return artist.getGenres()[0];
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getPreviewUrl(String songName) {
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(songName).market(CountryCode.NA).build();
        try {
            Track track = searchTracksRequest.execute().getItems()[0];
            return track.getPreviewUrl();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Artist getFullArtist(String artistId) {
        GetArtistRequest getArtistRequest = spotifyApi.getArtist(artistId).build();
        try {
            return getArtistRequest.execute();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
