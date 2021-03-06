package com.company;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;

import static java.nio.file.Paths.*;


public final class Main {

    public static void main(String... args) throws IOException {
        Main parser = new Main("CustomerList.txt");
        parser.parseLineByLine();
    }

    /**
     Constructor
     */
    public Main(String fileName){
        filePath = get(fileName);
    }

    public final void parseLineByLine() throws IOException {
        try (Scanner scanner =  new Scanner(filePath, ENCODING.name())){
            while (scanner.hasNextLine()){
                parseEachLine(scanner.nextLine());
            }
        }
    }


    protected void parseEachLine(String line){
        //second Scanner to parse the content of each line
        try(Scanner scanner = new Scanner(line)){
            scanner.useDelimiter(",");
            if (scanner.hasNext()) {

                //assumes the line has a certain structure
                String latitude = scanner.next ();
                String user_id = scanner.next ();
                String name = scanner.next();
                String longitude = scanner.next();
                latitude = latitude.replaceAll("\\p{Ps}", "");
                longitude = longitude.replaceAll("}$", "");
                if(getDistance(latitude,longitude, 53.339428, -6.257664)<=100.0){
                    System.out.println(name.trim() + " "+  "; " + " "+user_id.trim());

                    //System.out.println(latitude.trim() + " "+  "; " + " "+longitude.trim());
                }

            }
            else {
                log("Empty or invalid line. Unable to process.");
            }
        }
    }
    // get distance of each cordinate from the office location 53.339428, -6.257664
   // public static final double Radius = 6372.8; // In kilometers;
    public double getDistance(String latitude, String longitude, double lat2, double long2) {
        String requiredLatitude = latitude.replaceAll("[a-zA-Z:\"\\s]","");
        String requiredLongitude = longitude.replaceAll("[a-zA-Z:\"\\s]","");
        double lat1 = Double.parseDouble(requiredLatitude);
        double long1 = Double.parseDouble(requiredLongitude);
        double deltaLat = Math.toRadians(lat2 - lat1);
        double deltaLong = Math.toRadians(long2 - long1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(deltaLat / 2),2) + Math.pow(Math.sin(deltaLong / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double distance =  6372.8 * c  ;
       if(distance<=100)
       {System.out.println ("Distance " + distance + " km");}

        return distance;
    }
    // PRIVATE
    private final Path filePath;
    private final static Charset ENCODING = StandardCharsets.UTF_8;

    private static void log(Object object){
        System.out.println(Objects.toString(object));
    }

}