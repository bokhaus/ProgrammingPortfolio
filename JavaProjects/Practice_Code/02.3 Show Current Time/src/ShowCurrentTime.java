/*
 * Show Current Time (GMT)
 */
public class ShowCurrentTime {

    
    public static void main(String[] args) {
        // Obtain the total milliseconds since midnight, Jan 1 1970
        long totalMilliseconds =System.currentTimeMillis();
        
        //total seconds
        long totalSeconds = totalMilliseconds / 1000;
        
        long currentSecond = (int) (totalSeconds % 60);
        
        //obtain total minutes
        long totalMinutes = totalSeconds / 60;
        
        //obtain current minute
        long currentMinute = (int) (totalMinutes % 60);
        
        long totalHours = totalMinutes / 60;
        
        long currentHour = (int) (totalHours % 24);
        
        //corrected for time zone. I put this line in to the program
        
        long actualHour = currentHour + 7;
        
        System.out.println("Current time is " + actualHour + ":"
        + currentMinute + ":" + currentSecond+ " GMT");
    }
    
}
