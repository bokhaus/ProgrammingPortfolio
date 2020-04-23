/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.capella.it4749;

/**
 *
 * @author User
 */
public class Weather {
    private String location;
    private double currentTemp; //in Kelvin
    private double highTemp; //in Kelvin
    private double lowTemp; //in Kelvin
    private double pressure; //in Millibars
    private int humidity;
    private double windSpeed;
    private int windDirection; //in compass degrees
    
    public Weather(String location, double currentTemp, double highTemp, double lowTemp, double pressure, int humidity, double windSpeed, int windDirection){
        
        //setters
        this.location = location;
        this.currentTemp = currentTemp;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
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

}
    