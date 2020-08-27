package com.company;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

import static java.nio.file.Paths.*;


public final class Main {

    public static void main(String[] args) throws IOException {
        Main parser = new Main("/Users/manasipalkar/Desktop/Assignment/Intercom/Destination/src/CustomerList.txt");
        parser.parseLineByLine();
        log("Records parsed");
     //   log(System.getProperty ( "user.dir" ));
    }
      ArrayList<Customer> list = new ArrayList<Customer> ();
    /**
     Constructor
     */
    public Main(String fileName){
        filePath = get(fileName);
    }

    public final void parseLineByLine()  {
        try (Scanner scanner =  new Scanner(filePath, ENCODING.name())){
            while (scanner.hasNextLine()){
             parseEachLine(scanner.nextLine());
            }
        }
        catch (IOException e){
            System.out.println (e.getCause ());
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
//
               if(getDistance(latitude,longitude, 53.339428, -6.043701)) //returns true print corresponding user_id and user_name)
                {
               // check for user_id
                System.out.println(name.trim() + " "+  "; " + " "+user_id.trim());
                }
//             //System.out.println(name.trim() + " "+  "; " + " "+user_id.trim() + " "+  "; " + " "+latitude.trim() + " "+  "; " + " "+longitude.trim());
             //  System.out.println(latitude.trim()+ " "+  "; " + " " + longitude.trim());

                //Store the contents in an  output file

            }

            else {
                log("Empty or invalid line. Unable to process.");
            }
            scanner.close ();
        }
    }
        // get distance of each cordinate from the office location 53.339428, -6.257664
    public static final double Radius; // In kilometers;

    static {
        Radius = 6372.8;
    }

    public static boolean getDistance(String latitude, String longitude, double lat2, double long2) {
//      System.out.println (double.parseDouble( String.valueOf ( latitude));
     // double lat1=Double.parseDouble( String.valueOf ( latitude ).getClass () .getName ());
        try{
            double lat1 = Double.parseDouble(latitude);
            System.out.println (lat1);
        } catch(NumberFormatException e) {
            System.out.println ("Invalid format");
        }

   //  double long1=double.parseDouble( String.valueOf ( longitude ) );
//        double deltaLat = Math.toRadians(lat2 - lat1);
//        double deltaLong = Math.toRadians(long2 - long1);
//        lat1 = Math.toRadians(lat1);
//        lat2 = Math.toRadians(lat2);
//
//        double a = Math.pow(Math.sin(deltaLat / 2),2) + Math.pow(Math.sin(deltaLong / 2),2) * Math.cos(lat1) * Math.cos(lat2);
//        double c = 2 * Math.asin(Math.sqrt(a));
//        double distance =  Radius * c  ;
//      System.out.println ("Distance of the customer from the office in km "+ " " + distance);
//        return distance<=100;
        return true;

    }
    // private objectsgit
    private final Path filePath;
    private final static Charset ENCODING = StandardCharsets.UTF_8;

    private static void log(Object object){
        System.out.println(Objects.toString(object));
    }

}