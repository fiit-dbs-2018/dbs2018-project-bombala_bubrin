package project.view.panels;

import javax.swing.*;

public class Event extends JPanel {

    ContentPanel parent;

    public Event(ContentPanel contentPanel){
        parent = contentPanel;
        setSize(650, 630);
        setLayout(null);

        EventContent eventContent = new EventContent(this);
        Filter filter = new Filter(this);
        add(eventContent);
        add(filter);





    }

}
