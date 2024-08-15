package data_access.csv;

import data_access.RelationalSongDataAccessInterface;
import data_access.SongDataAccessInterface;
import data_access.UserDataAccessInterface;
import entity.song.Song;
import entity.user.User;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * data access for user -> posts
 */
public class RelationalSongCSVDataAccess implements RelationalSongDataAccessInterface {
    private final File csvFile;
    private final UserDataAccessInterface userDataAccess;
    private final SongDataAccessInterface songDataAccess;
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    //  The keys are the user and the values are the song that they favourite.
    private final HashMap<User, Song> relationships = new HashMap<>();

    /**
     * create instance of data access
     * @param csvPath local file path
     * @param userDataAccess user DAO
     * @param songDataAccess song DAO
     */
    public RelationalSongCSVDataAccess(String csvPath, UserDataAccessInterface userDataAccess, SongDataAccessInterface songDataAccess) {
        csvFile = new File(csvPath);
        this.userDataAccess = userDataAccess;
        this.songDataAccess = songDataAccess;

        headers.put("username", 0);
        headers.put("song_id", 1);

        if (csvFile.length() == 0) {
            createFile();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                // This will ensure the file is correctly formatted
                String header = reader.readLine();
                assert header.equals(headersToString(headers));

                // This will load all relationships into the map.
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String userId = String.valueOf(col[headers.get("username")]);
                    int songId = Integer.parseInt(String.valueOf(col[headers.get("song_id")]));

                    User user = userDataAccess.getUserFromUsername(userId);
                    Song song = songDataAccess.getSongFromId(songId);
                    relationships.put(user, song);
                }

                applyFavouritesToUsers();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createFile() {
        if (csvFile.length() == 0) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
                writer.println(headersToString(headers));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String headersToString(Map<String, Integer> headers) {
        return String.join(",", headers.keySet());
    }

    private void applyFavouritesToUsers() {
        for (Map.Entry<User, Song> entry : relationships.entrySet()) {
            User user = entry.getKey();
            Song song = entry.getValue();
            user.setFeaturedSong(song);
        }
    }

    @Override
    public void addFavourite(User user, Song song) {
        if (!relationships.containsKey(user)) {
            System.out.println("Adding favourite song to user.");
            relationships.put(user, song);
            user.setFeaturedSong(song);
            try (FileWriter fw = new FileWriter(csvFile, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(user.getUsername() + "," + song.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("User already has a favourite song. Use replaceFavourite instead.");
            relationships.replace(user, song);
            user.setFeaturedSong(song);
            replaceFavouriteInCsv(user, song);
        }
    }

    @Override
    public void removeFavourite(User user) {
        if (relationships.containsKey(user)) {
            relationships.remove(user);
            user.setFeaturedSong(null);
            try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
                writer.println(headersToString(headers));
                for (Map.Entry<User, Song> entry : relationships.entrySet()) {
                    writer.println(entry.getKey().getUsername() + "," + entry.getValue().getId());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void replaceFavouriteInCsv(User user, Song song) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            StringBuilder newContent = new StringBuilder();
            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                String userId = String.valueOf(col[headers.get("username")]);
                int songId = Integer.parseInt(String.valueOf(col[headers.get("song_id")]));
                if (userId.equals(user.getUsername())) {
                    newContent.append(userId).append(",").append(song.getId()).append("\n");
                } else {
                    newContent.append(row).append("\n");
                }
            }
            try (FileWriter fw = new FileWriter(csvFile);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(newContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
