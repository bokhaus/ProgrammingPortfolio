/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.capella.it4749;



import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author BrianBok
 */
public class U03A1 {
    
    //URL = http://api.openweathermap.org/data/2.5/weather?
    private static final String API_KEY = "aa3c36081cd74393e397d2af2c74394b";
    private static final String OPEN_WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?";
    
    public static void main(String[] args) {
        
        URL url = null;
        HttpURLConnection connect = null;
        Weather w = null;
        
        //Creates logger and file handler and formats logging file to remove extraneous symbols  
        Logger log = Logger.getLogger(U03A1.class.getName());
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        try{
            FileHandler fileHandler = new FileHandler("weather.log");
            fileHandler.setFormatter(simpleFormatter);
            log.addHandler(fileHandler);
            
            //Logger for log file --> weather.log. Records all levels
            log.setLevel(Level.ALL);
            
            //Logger for console --> ONLY records Level.SEVERE
            Logger.getLogger("").getHandlers()[0].setLevel(Level.SEVERE);
            
        } catch (IOException ex) {
            log.log(Level.SEVERE,ex.getMessage());
            System.exit(1);
        
        } catch (SecurityException ex) {
            log.log(Level.SEVERE,ex.getMessage());
            System.exit(1);
        }
        //Requests user input of zip code
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the zip code for the location: ");
        String zip = input.nextLine();
        System.out.print("\nYou requested zip code: " + zip + "\n");
        System.out.print("\n-----------------------------------------------\n");
        
    //Formats and concatenates the url address with correct api call with API_KEY    
    try {
            url = new URL(OPEN_WEATHER_URL + "zip=" + zip + ",us&APPID=" + API_KEY);
            log.log(Level.INFO, "URL: " + url.toString());
            
        } catch (MalformedURLException ex) {
            log.log(Level.SEVERE, "ERROR with URL: " + ex.getMessage());
        } catch(Exception ex){
            log.log(Level.SEVERE, "Error: " + ex.getClass().getName()+ ex.getMessage());
        }  
    
        //reads url to ensure it is formatted correctly and if so uses try block to test connection code
        if(url!= null){
            try{
                connect = (HttpURLConnection)url.openConnection();
                int responseCode = connect.getResponseCode();
                if(responseCode == HttpURLConnection.HTTP_OK){
                    log.log(Level.INFO, "Connection Successful");
                    
                    //try with resources with a general catch block
                    try(Scanner data = new Scanner(connect.getInputStream())){
                        w = WeatherJSONParser.parseJsonWeatherData(data);
                        log.log(Level.INFO,"Weather data retrieved, current as of: " + w.getReadingDateTime()); 
                    }
                    catch(Exception ex){
                        log.log(Level.WARNING,"ERROR creating Weather data object: " +
                                ex.getMessage());
                    }
                    if(w != null){//test Weather variable is not null and has data
                        
                        //format the various data types
                        DecimalFormat tempFormat = new DecimalFormat("#.##F");
                        DecimalFormat baroFormat = new DecimalFormat("##.00\"  hg");
                        DecimalFormat windSpeedFormat = new DecimalFormat("## mph");
                        DecimalFormat windDirectionFormat = new DecimalFormat("### degrees");
                        
                        //Print location information to the console
                        System.out.println("As of: " + w.getReadingDateTime() + "\n");
                        System.out.println("Weather for: " + w.getLocation());
                        System.out.println("Current Temperature: "+ tempFormat.format(w.getCurrentTemperatureF()));
                        System.out.println("High Temperature: " + tempFormat.format(w.getHighTemperatureF()));
                        System.out.println("Low Temperature: " + tempFormat.format(w.getLowTemperatureF()));
                        System.out.println("Current Pressure: " + baroFormat.format(w.getPressureInHg()));
                        System.out.println("Current Humidity: " + Integer.toString(w.getHumidity()) + "%");
                        System.out.println("Current Wind Speed: " + windSpeedFormat.format(w.getWindSpeed()));
                        System.out.println("Current Wind Direction: " + windDirectionFormat.format(w.getWindDirection()));
                        System.out.print("-----------------------------------------------\n");
                        log.log(Level.INFO,"Display Data to console using Weather.jar");
                    }
                }
                else{ //log if the response code does not equal OK
                    log.log(Level.WARNING, "INCORRECT RESPONSE CODE.");
                }
            
            
            } catch (IOException ex) {//log if the connection is incorrect
                log.log(Level.SEVERE,"Error connection not valid: " + ex.getMessage() + ex.getClass().getName());
                
            }
        }
        
        
    }

}

