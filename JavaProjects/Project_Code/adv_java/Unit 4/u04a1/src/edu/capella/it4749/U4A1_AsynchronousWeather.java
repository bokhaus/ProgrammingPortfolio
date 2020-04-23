/*
 * This program makes an asynchronous call to a server, then displays the called 
 * weather data to the console.
 */
package edu.capella.it4749;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
/**
 *
 * @author BrianBok
 */
public class U4A1_AsynchronousWeather {
    
    //Url CONSTANT variables for thread call
    private static final String API_KEY = "aa3c36081cd74393e397d2af2c74394b";
    private static final String OPEN_WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?";
    
    //Creates logger CONSTANT variable. Must be static to be use in Lambda expression  
    static final Logger LOG = Logger.getLogger(U4A1_AsynchronousWeather.class.getName()); 
        
    public static void main(String[] args) {
        
        //Format the logger in both log file and console
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        
        //FileHandler for logger
        try{
            FileHandler fileHandler = new FileHandler("weather.log");
            fileHandler.setFormatter(simpleFormatter);
            LOG.addHandler(fileHandler);
            
            //Logger for log file --> weather.log. Records all levels
            LOG.setLevel(Level.ALL);
            
            //Logger for console --> ONLY records Level.SEVERE
            Logger.getLogger("").getHandlers()[0].setLevel(Level.SEVERE);
            
        //multi-catch block   
        } catch (IOException | SecurityException ex) {
            LOG.log(Level.SEVERE,ex.getMessage());
            System.exit(1);
        }
        LOG.log(Level.INFO, "Creating Logger and handlers.");
        
        //Requests user input of zip code
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the zip code for the location: ");
        String zip = input.nextLine();
        System.out.print("\nYou requested zip code: " + zip + "\n");
        System.out.print("\n-----------------------------------------------\n");
    
        System.out.println("Waiting for server response...");
        LOG.log(Level.INFO, "Create Scanner object and request info from user");
        
        //The asynchronous method call will return a Future object 
        Future<Weather> result = null;
        try {
            System.out.println("Retrieving Weather Data...");
            
            // Make call to anynchronous method
            result = getCurrentWeatherAsync(zip);
                        
            // Loop to simulate other activity. Prints Waiting... message
            while(! result.isDone()) {
                Thread.sleep(100);
                System.out.println("Waiting...");
            }
            System.out.println("Complete.");
        } catch (Exception ex) {
            LOG.log(Level.WARNING, "Call interrupted before result was ready.");
        }
        
        // Variable holds "unpacked" results from Weather object
        try{
            // Get Weather data from result object
            Weather weatherData = result.get();
            LOG.log(Level.INFO,"Weather data object result returned");

            //format the various data types
            DecimalFormat tempFormat = new DecimalFormat("#.##F");
            DecimalFormat baroFormat = new DecimalFormat("##.00\"  hg");
            DecimalFormat windSpeedFormat = new DecimalFormat("## mph");
            DecimalFormat windDirectionFormat = new DecimalFormat("### degrees");

            //Print location information to the console
            System.out.print("\n");
            System.out.println("As of: " + weatherData.getReadingDateTime());
            System.out.println("-----------------------------------------------");
            System.out.println("Weather for: " + weatherData.getLocation());
            System.out.println("Current Temperature: "+ tempFormat.format(weatherData.getCurrentTemperatureF()));
            System.out.println("High Temperature: " + tempFormat.format(weatherData.getHighTemperatureF()));
            System.out.println("Low Temperature: " + tempFormat.format(weatherData.getLowTemperatureF()));
            System.out.println("Current Pressure: " + baroFormat.format(weatherData.getPressureInHg()));
            System.out.println("Current Humidity: " + Integer.toString(weatherData.getHumidity()) + "%");
            System.out.println("Current Wind Speed: " + windSpeedFormat.format(weatherData.getWindSpeed()));
            System.out.println("Current Wind Direction: " + windDirectionFormat.format(weatherData.getWindDirection()));
            System.out.print("-----------------------------------------------\n");
            LOG.log(Level.INFO,"Display Data to console.");

            }catch (InterruptedException ex) {
            LOG.log(Level.SEVERE,ex.getMessage());
            } catch (ExecutionException ex) {
            LOG.log(Level.SEVERE,ex.getMessage());
            }    
    }
    
    //Method for Asynchronous threads.
    public static Future<Weather> getCurrentWeatherAsync(String z){
        
        CompletableFuture<Weather> weatherResult = new CompletableFuture<>();
        
        //Create new thread for async call
        new Thread( () -> {
            
            //Variable holds user input, passed as a parameter
            String zip = z;
            
            //Variables to create weather url
            URL weatherDataUrl = null;
            HttpURLConnection connect = null;
            
            //Variable holds weather data
            Weather w = null;
            
            //Formats and concatenates the url address with correct api call with API_KEY
            try {
                weatherDataUrl = new URL(OPEN_WEATHER_URL + "zip=" + zip + ",us&APPID=" + API_KEY);
                LOG.log(Level.INFO, "Weather URL created: " + weatherDataUrl.toString());
                
            }catch (MalformedURLException ex) {
                LOG.log(Level.SEVERE, "ERROR with URL: " + ex.getClass().getName()+ ex.getMessage());
                
            }catch(Exception ex){
                LOG.log(Level.SEVERE, "Error: " + ex.getClass().getName()+ ex.getMessage());
            }
            //reads url to ensure it is formatted correctly and if so uses try block to test connection code
            if(weatherDataUrl!= null){
                try{
                    connect = (HttpURLConnection)weatherDataUrl.openConnection();
                    int responseCode = connect.getResponseCode();
                    if(responseCode == HttpURLConnection.HTTP_OK){
                        LOG.log(Level.INFO, "Server found, Connection Successful");
                                                
                        //try with resources with a general catch block retrieve URL and parse
                        try(Scanner data = new Scanner(connect.getInputStream())){
                            w = WeatherJSONParser.parseJsonWeatherData(data);
                            LOG.log(Level.INFO,"Weather data retrieved, current as of: " + w.getReadingDateTime());
                        }
                        catch(Exception ex){
                            LOG.log(Level.WARNING,"ERROR creating Weather data object: " + 
                                    ex.getClass().getName()+ ex.getMessage());
                        }
                        if(w != null)
                        // When done, add to Future returned by method
                        weatherResult.complete(w);
                        LOG.log(Level.INFO,"Weather data complete, weatherResult returned.");
                    }
                    else{ //log if the response code does not equal OK
                        LOG.log(Level.WARNING, "INCORRECT RESPONSE CODE.");
                    }
                } catch (IOException ex) {//log if the connection is incorrect
                    LOG.log(Level.SEVERE,"Error connection not valid: " + ex.getMessage() + ex.getClass().getName());
                }
            }
        // start thread   
        }).start();
        LOG.log(Level.INFO, "Method Start...Passing user input to method through parameter.");
        return weatherResult;
        
    }
    
}