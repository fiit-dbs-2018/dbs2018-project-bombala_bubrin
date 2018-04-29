package project.view.panels;

import project.db_connection.DbConnectionBuilder;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Filter extends JPanel {

    private Event parent;
    private final int minPrice = 5;
    private final int maxPrice = 100;
    private int fromPrice = 5;
    private int toPrice = 100;
    private int filterInt = 0;

    private JTextField eventNameField;
    private JTextField countryField;
    private JTextField cityField;

    public Filter(Event event){
        parent = event;
        this.setBounds(450,0,200,630);
        this.setLayout(null);

        JLabel filter = new JLabel("Filter");
        this.add(filter);
        JLabel eventName = new JLabel("Event Name:");
        this.add(eventName);
        JLabel country = new JLabel("Country:");
        this.add(country);
        JLabel city = new JLabel("City:");
        this.add(city);
        JLabel lowPriceLabel = new JLabel("From:");
        this.add(lowPriceLabel);
        JLabel highPriceLabel = new JLabel("To:");
        this.add(highPriceLabel);

        eventNameField = new JTextField();
        this.add(eventNameField);
        countryField = new JTextField();
        this.add(countryField);
        cityField = new JTextField();
        this.add(cityField);

        JSlider lowPrice = new JSlider(JSlider.HORIZONTAL,
                minPrice,maxPrice,fromPrice);
        lowPrice.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                fromPrice = lowPrice.getValue();
                if(fromPrice>toPrice){
                    fromPrice=toPrice-1;
                    lowPrice.setValue(fromPrice);
                }
            }
        });
        this.add(lowPrice);

        lowPrice.setMajorTickSpacing(25);
        lowPrice.setMinorTickSpacing(5);
        lowPrice.setPaintTicks(true);
        lowPrice.setPaintLabels(true);
        lowPrice.setBorder(
                BorderFactory.createEmptyBorder(0,0,10,0));
        Font font = new Font("Serif", Font.ITALIC, 15);
        lowPrice.setFont(font);

        JSlider highPrice = new JSlider(JSlider.HORIZONTAL, minPrice,maxPrice,toPrice);
        highPrice.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                toPrice = highPrice.getValue();
                if(toPrice<fromPrice){
                    toPrice=fromPrice+1;
                    highPrice.setValue(toPrice);
                }
            }
        });
        this.add(highPrice);

        highPrice.setMajorTickSpacing(25);
        highPrice.setMinorTickSpacing(5);
        highPrice.setPaintTicks(true);
        highPrice.setPaintLabels(true);
        highPrice.setBorder(
                BorderFactory.createEmptyBorder(0,0,10,0));
        highPrice.setFont(font);

        JButton search = new JButton("Search");
        this.add(search);

        filter.setBounds(0,0,190,50);
        eventName.setBounds(0,50,190,30);
        eventNameField.setBounds(0,80,190,30);
        country.setBounds(0,110,190,30);
        countryField.setBounds(0,140,190,30);
        city.setBounds(0,170,190,30);
        cityField.setBounds(0,200,190,30);
        lowPriceLabel.setBounds(0,240,190,30);
        lowPrice.setBounds(0,270,190,55);
        highPriceLabel.setBounds(0,325,190,30);
        highPrice.setBounds(0,355,190,55);
        search.setBounds(0,580,130,40);

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterInt = 1;
                filterEvents(filterInt);
            }
        });



    }

    private void filterEvents(int filterInt) {
        String filterName = eventNameField.getText();
        String filterCountry = countryField.getText();
        String filterCity = cityField.getText();
        parent.filterEvents(filterName, filterCountry, filterCity, fromPrice, toPrice,filterInt,0);
    }

}
