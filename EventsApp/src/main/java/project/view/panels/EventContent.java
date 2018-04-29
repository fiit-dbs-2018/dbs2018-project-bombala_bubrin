package project.view.panels;

import project.db_connection.DbConnectionBuilder;
import project.model.EventObj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EventContent extends JPanel {

    private Event parent;
    private JPanel pagination;
    private JPanel eventsPanel;
    private int actualPosition = 0;
    private int filterInt = 0;
    private String filterName;
    private String filterCountry;
    private String filterCity;
    private int fromPrice;
    private int toPrice;

    public EventContent(Event event) {
        parent = event;
        setSize(440, 630);
        setLayout(null);

        eventsPanel = new JPanel();
        eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));
        eventsPanel.setSize(440, 600);
        eventsPanel.setBackground(Color.LIGHT_GRAY);//
        this.add(eventsPanel);


        pagination = new JPanel();
        pagination.setBounds(0, 600, 440, 30);
        pagination.setLayout(null);
        pagination.setBackground(Color.LIGHT_GRAY);
        add(pagination);
        pagination.setVisible(true);

        JButton next = new JButton("Next");
        pagination.add(next);
        next.setBounds(330, 0, 100, 20);

        JButton previous = new JButton("Previous");
        pagination.add(previous);
        previous.setBounds(10, 0, 100, 20);


        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (filterInt == 1) {
                    actualPosition += 1;
                    filterEvents(filterName, filterCountry, filterCity, fromPrice, toPrice, filterInt, actualPosition);
                }
            }
        });

        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (filterInt == 1) {
                    actualPosition -= 1;
                    filterEvents(filterName, filterCountry, filterCity, fromPrice, toPrice, filterInt, actualPosition);
                }
            }
        });


    }

    private void drawEvent(EventObj event) {
        EventView postPanel = new EventView(event);
        eventsPanel.add(postPanel);
        eventsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        setVisible(true);
    }

    public void filterEvents(String filterName, String filterCountry, String filterCity, int fromPrice, int toPrice, int filterInt, int actualPosition) {

        if (actualPosition == 0) {
            this.actualPosition = 0;
        }
        this.filterInt = filterInt;
        this.filterName = filterName;
        this.filterCountry = filterCountry;
        this.filterCity = filterCity;
        this.fromPrice = fromPrice;
        this.toPrice = toPrice;

        if (actualPosition < 0) {
            actualPosition = 0;
            this.actualPosition = 0;
        }
        clearView();
        List<EventObj> events = DbConnectionBuilder.getInstance().filterEvents(filterName, filterCountry, filterCity, fromPrice, toPrice, actualPosition);
        for (EventObj eventObj : events) {
            drawEvent(eventObj);
        }
    }

    private void clearView() {
        eventsPanel.removeAll();
        eventsPanel.repaint();
        eventsPanel.revalidate();
    }

}
