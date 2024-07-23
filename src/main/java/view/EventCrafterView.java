package view;

import interface_adapter.eventcrafter.EventCrafterController;
import interface_adapter.eventcrafter.EventCrafterViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EventCrafterView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "eventcrafter view";
    private final EventCrafterViewModel eventCrafterViewModel;
    private final EventCrafterController eventCrafterController;

    public EventCrafterView(EventCrafterViewModel eventCrafterViewModel, EventCrafterController eventCrafterController) {
        this.eventCrafterViewModel = eventCrafterViewModel;
        this.eventCrafterController = eventCrafterController;
        this.eventCrafterViewModel.addPropertyChangeListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
