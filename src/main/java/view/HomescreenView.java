package view;

import interface_adapter.homescreen.HomescreenController;
import interface_adapter.homescreen.HomescreenPresenter;
import interface_adapter.homescreen.HomescreenState;
import interface_adapter.homescreen.HomescreenViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomescreenView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "home";
    private final HomescreenViewModel homescreenViewModel;

    private final HomescreenController homescreenController;
    private final HomescreenPresenter homescreenPresenter;

    // Declare as class-level fields
    private String signedInAs = ""; // Initialize with an empty string or a default value
    JLabel welcome_message; // Declare the JLabel

    public HomescreenView(HomescreenViewModel homescreenViewModel, HomescreenController homescreenController, HomescreenPresenter homescreenPresenter) {
        this.homescreenViewModel = homescreenViewModel;
        this.homescreenController = homescreenController;
        this.homescreenPresenter = homescreenPresenter;
        this.homescreenViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(homescreenViewModel.TITLE_LABEL);
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        // Initialize welcome_message using the class-level field
        welcome_message = new JLabel("Signed in as: " + signedInAs);
        welcome_message.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(welcome_message);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        // Implementation as needed
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            HomescreenState state = (HomescreenState) evt.getNewValue();
            this.signedInAs = state.getUsername(); // Use the class-level field
            welcome_message.setText("Signed in as: " + signedInAs); // Update the JLabel directly
        } catch (ClassCastException e) {
            System.out.println("Error: HomescreenView received an unexpected event.");
        }
    }
}