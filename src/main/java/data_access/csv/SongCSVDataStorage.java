package data_access.csv;

import data_access.RelationalSongDataAccessInterface;
import data_access.SongDataAccessInterface;
import data_access.UserDataAccessInterface;
import entity.post.Post;
import entity.song.Song;
import entity.user.User;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SongCSVDataStorage implements SongDataAccessInterface {
    private final File csvFile;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<Integer, Song> songs = new HashMap<>();

    public SongCSVDataStorage(String csvPath) throws IOException {
        csvFile = new File(csvPath);

        headers.put("name", 0);
        headers.put("artist", 1);
        headers.put("album", 2);
        headers.put("releaseDate", 3);
        headers.put("tags", 4);
        headers.put("URL", 5);
        headers.put("id", 6);

        if (headers.size() != 7) {
            throw new ArrayIndexOutOfBoundsException("The headers array is improperly initialized - should be 7 elements");
        } else if (csvFile.length() == 0) {
            createFile();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                // This will ensure the file is correctly formatted
                String header = reader.readLine();
                assert header.equals(headersToString(headers));

                // This will load all songs into memory as Song objects
                String row;

                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");

                    if (col.length != 7) {
                        System.out.println("Length = " + col.length + "\n" + Arrays.toString(col));
                        throw new ArrayIndexOutOfBoundsException("it should have a length of 5" + col);
                    }

                    String name = String.valueOf(col[headers.get("name")]);
                    String artist = String.valueOf(col[headers.get("artist")]);
                    String album = String.valueOf(col[headers.get("album")]);
                    String releaseDate = String.valueOf(col[headers.get("releaseDate")]);
                    String tags = String.valueOf(col[headers.get("tags")]);
                    String URL = String.valueOf(col[headers.get("URL")]);
                    int id = Integer.parseInt(String.valueOf(col[headers.get("id")]));

                    LocalDate releaseDateLocalDateTime = stringToLocalDate(releaseDate);
                    ArrayList<String> tagsList = stringToTags(tags);

                    Song song = new Song(name, artist, album, releaseDateLocalDateTime, tagsList, URL, id);
                    songs.put(id, song);
                }

            }
        }
    }

    private ArrayList<String> stringToTags(String string) {
        return new ArrayList<>(Arrays.asList(string.split(";")));
    }

    private LocalDate stringToLocalDate(String string) {
        return LocalDate.parse(string, formatter);
    }

    private void createFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            writer.println(headersToString(headers));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String headersToString(Map<String, Integer> headers) {
        return String.join(",", headers.keySet());
    }

    private void appendSongToCsv(Song song) {
        try (FileWriter fw = new FileWriter(csvFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(songToString(song));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String songToString(Song song) {
        return song.getName() + ","
                + song.getArtist() + ","
                + song.getAlbum() + ","
                + song.getReleaseDate().format(formatter) + ","
                + tagsToString(song.getTags()) + ","
                + song.getURL() + ","
                + song.getId();
    }

    private String tagsToString(ArrayList<String> tags) {
        return String.join(";", tags);
    }

    @Override
    public void createSong(Song song) {
        appendSongToCsv(song);
        songs.put(song.getId(), song);
    }

    public boolean songExists(int id) {
        return songs.containsKey(id);
    }

    public Song getSongFromId(int id) {
        return songs.get(id);
    }

    @Override
    public int getUniqueId() {
        return songs.size() + 1;
    }
}
