package dataaccess;

import entity.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserLocalCSVDataStorageTest {

    private static final String TEST_CSV_PATH = "test_users.csv";
    private UserLocalCSVDataStorage dataStorage;

    @BeforeEach
    void setUp() throws IOException {
        dataStorage = new UserLocalCSVDataStorage(TEST_CSV_PATH);
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_CSV_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testCreateUser() throws UserDataAccessObject.DuplicateUsernameException {
        User user = new User("name", "username", "password", "email@example.com");
        dataStorage.create(user);

        assertTrue(dataStorage.userExistsInDatabase("username"), "User should exist in the database");
        assertTrue(dataStorage.passwordMatches("username", "password"), "Password should match");
    }

    @Test
    void testUserExistsInDatabase() throws UserDataAccessObject.DuplicateUsernameException {
        User user = new User("name", "username", "password", "email@example.com");
        dataStorage.create(user);

        assertTrue(dataStorage.userExistsInDatabase("username"), "User should exist in the database");
        assertFalse(dataStorage.userExistsInDatabase("nonexistentUser"), "Nonexistent user should not exist");
    }

    @Test
    void testPasswordMatches() throws UserDataAccessObject.DuplicateUsernameException {
        User user = new User("name", "username", "password", "email@example.com");
        dataStorage.create(user);

        assertTrue(dataStorage.passwordMatches("username", "password"), "Password should match");
        assertFalse(dataStorage.passwordMatches("username", "wrongPassword"), "Password should not match");
    }

    @Test
    void testCreateUserWithDuplicateUsername() {
        User user1 = new User("name", "username", "password1", "email1@example.com");
        User user2 = new User("name", "username", "password2", "email2@example.com");

        assertDoesNotThrow(() -> dataStorage.create(user1), "Creating user1 should not throw an exception");

        // Expect DuplicateUsernameException to be thrown
        assertThrows(UserDataAccessObject.DuplicateUsernameException.class, () -> dataStorage.create(user2),
                "Creating user2 with the same username should throw DuplicateUsernameException");
    }
}
