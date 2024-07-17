package dataaccess;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import entity.user.*;
import org.bson.Document;

public class UserDataAccessObject implements UserDataAccessInterface {

    public static class UserNotFoundException extends Exception{

    }

    private MongoClient mongoClient;
    private UserFactory userFactory;
    private MongoDatabase mongoDatabase;
    private MongoCollection mongoCollection;
    private User user;

    public UserDataAccessObject(User user) {
        this.user= user;
        String connectionString = "mongodb+srv://tasnimreza:dbtestpass@cluster0.vlnfmzu.mongodb.net/?appName=Cluster0";

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        this.mongoClient = MongoClients.create("mongodb+srv://tasnimreza:dbtestpass@cluster0.vlnfmzu.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0");
        this.mongoDatabase = mongoClient.getDatabase("userDataBase");

        if (user instanceof ArtistUser) {
            this.mongoCollection = mongoDatabase.getCollection("artistUser");
        } else if (user instanceof AudienceUser) {
            this.mongoCollection = mongoDatabase.getCollection("audienceUser");
        } else if (user instanceof VenueUser) {
            this.mongoCollection = mongoDatabase.getCollection("venueUser");
        }
    }

    @Override
    public boolean userExistsInDatabase(String username) {
        return false;
    }

    @Override
    public void update(User user) {

    }

    private void create(String username, String password, String email, String firstName, String lastName) {
        if (userExistsInDatabase(username)) {
//            throw new UserNotFoundException();
        } else {
            user = userFactory.createUser(username, password, email, firstName, lastName);
            Document document = new Document("username", user.getUsername())
                    .append("password", user.getPassword())
                    .append("email", user.getEmail())
                    .append("firstName", user.getFirstName())
                    .append("lastName", user.getLastName());
            InsertOneResult insertResult = mongoCollection.insertOne(document);
            user.setId(insertResult.getInsertedId().toString());
        }
    }

    @Override
    public void create(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public String[] getUserData(User user) {
        return new String[0];
    }

    @Override
    public void Throwable(UserNotFoundException userNotFoundException){
        System.out.println("User was not found in the database");
    }
}
