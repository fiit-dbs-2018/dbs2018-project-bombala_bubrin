package data_filler.filling_from_file;

import data_filler.model.CityCountryPair;

import java.io.File;
import java.io.*;
//import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Filling {

    public static ArrayList readFirstnames() {
        Scanner s = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            File firstNamesFile = new File("data\\firstname.txt");
            if (firstNamesFile.exists()) {
                s = new Scanner(firstNamesFile);
                while (s.hasNext()) {
                    String name = s.nextLine().replace("'", "''");
                    list.add(name);
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e.getMessage());
//            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }

        return list;
    }


    public static ArrayList readLastnames() {


        Scanner s = null;

        {
            try {
                s = new Scanner(new File("data\\lastname.txt"));
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                e.printStackTrace();
            }
        }

        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()) {
            String lastname = s.nextLine();
            list.add(lastname);

        }
        s.close();
        return list;
    }


    public static ArrayList readStyle() {

        ArrayList<String> list = new ArrayList<String>();
        Scanner s = null;

        try {
            File file = new File("data\\genress.txt");
            if (file.exists()) {
                s = new Scanner(file);
                while (s.hasNext()) {
                    String style = s.nextLine();
                    style = style.replace("'", "''");
                    list.add(style);
//            System.out.println(style);
                }
                s.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        return list;
    }

    public static ArrayList<CityCountryPair> readCityCountry() {

        ArrayList<CityCountryPair> list = new ArrayList<CityCountryPair>();
        Scanner s = null;

        try {
            s = new Scanner(new File("data\\citiescountries.txt"));
            while (s.hasNext()) {
                try {
                    CityCountryPair cc = new CityCountryPair(s.nextLine().replace("'", "''"));
                    list.add(cc);
                } catch (Exception ignored) {
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }


        return list;
    }

}