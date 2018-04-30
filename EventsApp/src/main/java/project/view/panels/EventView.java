package project.view.panels;

import project.db_connection.DbConnectionBuilder;
import project.model.EventObj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class EventView extends JPanel {

    private EventObj event;


    public EventView(EventObj event) {
        this.event = event;
        setLayout(null);
        setSize(440,200);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JTextArea eventName = new JTextArea(event.getName());
        eventName.setBounds(0, 0, 440, 40);
        eventName.setFont(new Font("Calibri", Font.BOLD, 19));
        eventName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(eventName);

        JLabel cityLabel = new JLabel("City:                  "+event.getCity());
        cityLabel.setBounds(0,40,440,20);
        cityLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(cityLabel);

        JLabel countryLabel = new JLabel("Country:           "+event.getCountry());
        countryLabel.setBounds(0,60,440,20);
        countryLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(countryLabel);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy' 'HH:00");
        JLabel timeOfStartLabel = new JLabel("Starting:          "+simpleDateFormat.format(event.getTimeOfStart()));
        timeOfStartLabel.setBounds(0,80,440,20);
        timeOfStartLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(timeOfStartLabel);

        JLabel ticketPriceLabel = new JLabel("Ticket price:   "+event.getTicketPrice()+" $");
        ticketPriceLabel.setBounds(0,100,440,20);
        ticketPriceLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(ticketPriceLabel);

        JLabel addressLabel = new JLabel("Address:          "+event.getAddress());
        addressLabel.setBounds(0,120,440,20);
        addressLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(addressLabel);

        JButton follow = new JButton("Follow");
        follow.setBounds(10,150,150,30);
        this.add(follow);
        follow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                follow.setFont(follow.getFont().deriveFont(Font.BOLD));
                follow.setEnabled(false);
                followEvent();

            }
        });

    }

    private void followEvent() {
        DbConnectionBuilder.getInstance().followEvent(event.getId());
    }
}
