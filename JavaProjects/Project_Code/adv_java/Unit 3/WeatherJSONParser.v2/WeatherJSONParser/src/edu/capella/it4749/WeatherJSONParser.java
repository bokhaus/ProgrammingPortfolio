package edu.capella.it4749;

import java.io.StringReader;
import java.util.Scanner;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 * @author bberkland
 */
public class WeatherJSONParser {
    private Weather weather;
    
         Weather getWeather() {
             return weather;
         }
         
         public static Weather parseJsonWeatherData(Scanner weatherDataInput) {
        /**
             *  Scanner will read JSON data file contents into a string. 
             *  Then use StringReader to pass JSON to JsonReader.
             *   JsonReader will get complete JsonObject with weather data.
             */
            String jsonString = weatherDataInput.nextLine();
            StringReader stringData = new StringReader(jsonString);
            JsonObject jsonWeatherData;
            // try with resources guarantees reader will be closed 
            try (JsonReader jsonReader = Json.createReader(stringData)) {
                // Get top-level JSON object with complete weather data
                jsonWeatherData = jsonReader.readObject();
            }
            
            // Top-level item called name in JSON object is location
            String location = jsonWeatherData.getString("name");
            // Get main JSON object with main body of weather data
            JsonObject jsonMainData = jsonWeatherData.getJsonObject("main");
            // Read temperature data and convert JsonNumber to double
            double currentTemp = jsonMainData.getJsonNumber("temp").doubleValue();
            double highTemp = jsonMainData.getJsonNumber("temp_max").doubleValue();
            double lowTemp = jsonMainData.getJsonNumber("temp_min").doubleValue();
            double pressure = jsonMainData.getJsonNumber("pressure").doubleValue();
            int humidity = jsonMainData.getJsonNumber("humidity").intValue();
            JsonObject jsonWindData = jsonWeatherData.getJsonObject("wind");
            double windSpeed = jsonWindData.getJsonNumber("speed").doubleValue();
            int windDir = jsonWindData.getJsonNumber("deg").intValue();
            long dtSeconds = jsonWeatherData.getJsonNumber("dt").longValue();
            
            //Instantiate a Weather object to hold weather information.
            Weather currentConditions = new Weather(location, currentTemp, highTemp, lowTemp,pressure, humidity, windSpeed, windDir, dtSeconds);
            
            return currentConditions;
         }    
}
