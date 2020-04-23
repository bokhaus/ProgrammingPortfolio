/*
 * This is the weather jar file which includes a constructor and methods for conversion of data 
 */
package edu.capella.it4749;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 *
 * @author User
 */
public class Weather {
    private final String location;
    private final double currentTemp; //in Kelvin
    private final double highTemp; //in Kelvin
    private final double lowTemp; //in Kelvin
    private final double pressure; //in Millibars
    private final int humidity;
    private final double windSpeed;
    private final int windDirection; //in compass degrees
    private final long dtSeconds;
    
    public Weather(String location, double currentTemp, double highTemp, 
            double lowTemp, double pressure, int humidity, double windSpeed, 
            int windDirection,long dtSeconds){
        
        //setters
        this.location = location;
        this.currentTemp = currentTemp;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.dtSeconds = dtSeconds;
    }
    //Getter methods
    public String getLocation(){
        return location;
    }

    public double getCurrentTemperatureF(){ //Convert Kelvan to Fahrenheit
        double convertedCTemp = 1.8*(currentTemp - 273.15) + 32;
        return convertedCTemp;
    }
    public double getHighTemperatureF(){ //Convert Kelvan to Fahrenheit
        double convertedHTemp = 1.8*(highTemp - 273.15) + 32;
        return convertedHTemp;
    }
    public double getLowTemperatureF(){ //Convert Kelvan to Fahrenheit
        double convertedLTemp = 1.8*(lowTemp - 273.15) + 32;
        return convertedLTemp;
    }
    public double getPressureInHg(){//getPressureInHg
        double convertedPressure = .0295300 * pressure;
        return convertedPressure;
        
    }
    public int getHumidity(){
        
        return humidity;
    }    
    public double getWindSpeed(){
        
        return windSpeed;
    }
    public int getWindDirection(){
        
        return windDirection;
    }
    //Convert Epoch to current date
    public ZonedDateTime getReadingDateTime(){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochSecond(dtSeconds),zoneId);
        return zdt;
        
    }

}
    