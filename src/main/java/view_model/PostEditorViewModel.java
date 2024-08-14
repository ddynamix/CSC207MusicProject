package view_model;

import app.interface_adapter_tools.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PostEditorViewModel extends ViewModel {
    public final String TITLE_LABEL = "Edit your post:";
    public final String CANCEL_BUTTON_LABEL = "Cancel";
    public final String PUBLISH_BUTTON_LABEL = "Publish";
    public final String EDIT_POST_TITLE_LABEL = "Edit post title";
    public final String EDIT_POST_TEXT_LABEL = "Edit post content";
    public final String EDIT_POST_ATTACHED_MEDIA_LABEL = "Edit post attached media";

    private PostEditorState state = new PostEditorState();

    public PostEditorViewModel() {
        super("post editor");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public PostEditorState getState() {
        return state;
    }

    public void setState(PostEditorState state) {
        this.state = state;
    }
}
