
package u09a1_weather;

/**
 * 
 * @author <Your Name Here>
 * 
 * This class will be created by the learner as a Library class in a separate project and added to this project's Libraries. 
 * Class needs to handle data for:
 * Location name (city).
 * Current temperature.
 * High & low temperature.
 * Barometric pressure.
 * Humidity.
 * Wind speed & direction.
 * 
 */

public class Weather {
    private final String location;
    private final double currentTemperature;
    private final double highTemperature;
    private final double lowTemperature;
    private final double pressure;
    private final int humidity; 
    private final double windSpeed;
    private final double windDirection;

    public Weather(String location, double currentTemperature, double highTemperature, double lowTemperature, 
            double pressure, int humidity, double windSpeed, double windDirection) {
        this.location = location;
        this.currentTemperature = currentTemperature;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    public String getLocation() { //pass through location data
        return location;
    }
    
    public double getCurrentTemperatureF() {//Convert Kelvan to Fahrenheit
        return (currentTemperature - 273.15) * 9/5 + 32;
    }

    public double getHighTemperatureF() {//Convert Kelvan to Fahrenheit
        double convertedHTemp = 1.8*(highTemperature - 273.15) + 32;
        return convertedHTemp;
    }
    
    public double getLowTemperatureF() {//Convert Kelvan to Fahrenheit
        double convertedLTemp = 1.8*(lowTemperature - 273.15) + 32;
        return convertedLTemp;
    }

    public double getPressureInHg() {//Convert given pressure to inches of Mercury
        double convertedPressure = .0295300 * pressure;
        return convertedPressure;
    }
    
    public int getHumidity() {//Pass through humdity
        return humidity;
    }
    
    public double getWindSpeed() {//pass through wind speed
        return windSpeed;
    }
    
    public double getWindDirection() {//pass through wind direction
        return windDirection;
    }

}
