package entity.post;

import entity.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostFactoryTest {

    @Test
    void testCreatePost() {
        PostFactory postFactory = new PostFactory();
        String title = "My First Post";
        String text = "This is the content of the post.";
        User author = new User("username", "password", "test@email.com"); // Assuming a simple User constructor
        String attachedMedia = "https://example.com/media";

        Post post = postFactory.createPost(title, text, author, attachedMedia);

        assertNotNull(post, "The post should not be null");
        assertEquals(title, post.getTitle(), "The title should be the same");
        assertEquals(text, post.getText(), "The text should be the same");
        assertEquals(author, post.getAuthor(), "The author should be the same");
        assertEquals(attachedMedia, post.getAttachedMedia(), "The attached media should be the same");
    }
}
