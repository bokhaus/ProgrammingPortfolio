package u09a1_weather;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author <YourNameHere>
 */
public class WeatherTest {
    // Class Weather object for tests. 
    Weather instance;
    
    public WeatherTest() {   }
    
    @Before
    public void setUp() {
        /* Construct a Weather object with test data
            1: Location
            2: Current temp. in Kelvin
            3: High temp. in Kelvin
            4: Low temp. in Kelvin
            5: Pressure in Hectopascals 
            6: % relative humidity
            7: Wind speed in meters per second
            8: Wind direction in compass degrees
        */
        instance = new Weather("Minneapolis", 291.72, 293.15, 290.15, 1016, 93, 3.1, 170);
    }
    
    @Test//provided test
    public void testGetLocation() {
        System.out.println("getLocation");
        String expResult = "Minneapolis";
        String result = instance.getLocation();
        assertEquals(expResult, result);
    }

    @Test//provided test
    public void testGetCurrentTemperatureF() {
        System.out.println("getCurrentTemperatureF");
        double expResult = 65.42;
        double result = instance.getCurrentTemperatureF();
        assertEquals(expResult, result, 0.01);
    }
    @Test//Assignment Test
    public void testGetHighTemperatureF() {
        System.out.println("getHighTemperatureF");
        double expResult = 68.001;
        double result = instance.getHighTemperatureF();
        assertEquals(expResult, result, 0.01);
    }
    @Test//Assignment Test
    public void testGetLowTemperatureF() {
        System.out.println("getLowTemperatureF");
        double expResult = 62.601;
        double result = instance.getLowTemperatureF();
        assertEquals(expResult, result, 0.01);
    }
    @Test//Assignment Test
    public void testGetPressureInHg() {
        System.out.println("getPressureInHg");
        double expResult = 30.00;
        double result = instance.getPressureInHg();
        assertEquals(expResult, result, 0.01);
    }
    @Test//Assignment Test
    public void testGetHumidity() {
        System.out.println("getHumidity");
        double expResult = 93.000;
        double result = instance.getHumidity();
        assertEquals(expResult, result, 0.01);
    }
    @Test//Assignment Test
    public void testGetWindSpeed() {
        System.out.println("getWindSpeed");
        double expResult = 3.100;
        double result = instance.getWindSpeed();
        assertEquals(expResult, result, 0.01);
    }
    @Test//Assignment Test
    public void testGetWindDirection() {
        System.out.println("getWindDirection");
        double expResult = 170.000;
        double result = instance.getWindDirection();
        assertEquals(expResult, result, 0.01);
    }    
}
