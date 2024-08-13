package use_case.postMaker.interface_adapter;

import data_access.PostDataAccessInterface;
import entity.user.AudienceUser;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.postMaker.PostMakerInputBoundary;
import use_case.postMaker.PostMakerInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PostMakerControllerTest {

    private PostMakerController postMakerController;
    private PostMakerInputBoundary postMakerInteractor;
    private PostDataAccessInterface postDataAccessInterface;

    @BeforeEach
    public void setUp() {
        postMakerInteractor = mock(PostMakerInputBoundary.class);
        postDataAccessInterface = mock(PostDataAccessInterface.class);
        postMakerController = new PostMakerController(postMakerInteractor, postDataAccessInterface);
    }

    @Test
    public void testExecute() {
        String title = "Test Title";
        String text = "Test Text";
        AudienceUser author = new AudienceUser("testUser", "Test User", "testPass", "testMail");
        String attachedMedia = "testMedia";

        postMakerController.execute(title, text, author, attachedMedia);

        ArgumentCaptor<PostMakerInputData> inputDataCaptor = ArgumentCaptor.forClass(PostMakerInputData.class);
        verify(postMakerInteractor, times(1)).attemptPost(inputDataCaptor.capture());

        PostMakerInputData capturedInputData = inputDataCaptor.getValue();
        assertEquals(title, capturedInputData.getTitle());
        assertEquals(text, capturedInputData.getText());
        assertEquals(author, capturedInputData.getAuthor());
        assertEquals(attachedMedia, capturedInputData.getAttachedMedia());
    }
}