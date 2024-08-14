/*
 *  Spotify Java API wrapper created by: Jonas Theleman https://github.com/spotify-web-api-java/spotify-web-api-java/tree/master
 */
package data_access.spotify;

import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;

import java.io.IOException;
import java.util.ArrayList;

public class SpotifyService implements SpotifyServiceInterface {
    private final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(System.getenv("SPOTIFY_CLIENT_ID"))
            .setClientSecret(System.getenv("SPOTIFY_TOKEN"))
            .build();

    private final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();

    public SpotifyService() {
        var env = System.getenv();
        System.out.println("Client ID: " + System.getenv("SPOTIFY_CLIENT_ID"));
        System.out.println("Client Secret: " + System.getenv("SPOTIFY_TOKEN"));
        setClientCredentials();
    }

    private void setClientCredentials() {
        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            // Set access token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());

            System.out.println("Expires in: " + clientCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String getSongName(String songName) {
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(songName).market(CountryCode.NA).build();
        try {
            Track track = searchTracksRequest.execute().getItems()[0];
            return track.getName();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getSongArtist(String songName) {
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(songName).market(CountryCode.NA).build();
        try {
            Track track = searchTracksRequest.execute().getItems()[0];
            return track.getArtists()[0].getName();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getSongAlbum(String songName) {
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(songName).market(CountryCode.NA).build();
        try {
            Track track = searchTracksRequest.execute().getItems()[0];
            return track.getAlbum().getName();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getSongReleaseDate(String songName) {
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(songName).market(CountryCode.NA).build();
        try {
            Track track = searchTracksRequest.execute().getItems()[0];
            return track.getAlbum().getReleaseDate();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getSongTags(String songName) {
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

    @Override
    public String getPreviewUrl(String songName) {
        System.out.println("Searching for song: " + songName);
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(songName).market(CountryCode.NA).build();
        try {
            Track track = searchTracksRequest.execute().getItems()[0];
            System.out.println(track.getName() + " " + track.getPreviewUrl());
            return track.getPreviewUrl();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Artist getFullArtist(String artistId) {
        GetArtistRequest getArtistRequest = spotifyApi.getArtist(artistId).build();
        try {
            return getArtistRequest.execute();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
