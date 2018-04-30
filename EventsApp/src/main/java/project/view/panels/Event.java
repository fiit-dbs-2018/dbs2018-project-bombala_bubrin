package project.view.panels;

import javax.swing.*;

public class Event extends JPanel {

    ContentPanel parent;

    private EventContent eventContent;
    private  Filter filter;

    public Event(ContentPanel contentPanel){
        parent = contentPanel;
        setSize(650, 630);
        setLayout(null);

        eventContent = new EventContent(this);
        filter = new Filter(this);
        add(eventContent);
        add(filter);

    }


    public void filterEvents(String filterName, String filterCountry, String filterCity, int fromPrice, int toPrice, int filterInt,int pos) {
        eventContent.filterEvents(filterName,filterCountry,filterCity,fromPrice,toPrice,filterInt,pos);
    }

    public void removeEvents() {
        eventContent.removeEvents();
        filter.setAllInit();
    }
}
