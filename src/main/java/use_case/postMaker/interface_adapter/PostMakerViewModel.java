package use_case.postMaker.interface_adapter;

import app.interface_adapter_tools.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PostMakerViewModel extends ViewModel {
    public final String TITLE_LABEL = "Post Maker View";
    public final String POST_TEXT_LABEL = "Enter Post Content";
    public final String POST_MEDIA_LABEL = "Enter post media";
    public final String POST_BUTTON_LABEL = "Post";
    public final String CANCEL_BUTTON_LABEL = "Cancel";

    private PostMakerState state = new PostMakerState();

    public PostMakerViewModel() {
        super("postmaker view");
    }

    public void setState(PostMakerState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public PostMakerState getState() {
        return state;
    }
}
