/*
Letter	Date or Time Component	Examples
G	Era designator              AD
y	Year	2018
M	Month in year           July or Jul or 07
w	Week in year                27
W	Week in month               2
D	Day in year                 189
d	Day in month                10
F	Day of week in month        2
E	Day name in week	Tuesday or Tue
u	Day number of week      (1 = Monday, ..., 7 = Sunday)	1
a	Am/pm marker	PM
H	Hour in day (0-23)           0
k	Hour in day (1-24)           24
K	Hour in am/pm (0-11)         0
h	Hour in am/pm (1-12)	    12
m	Minute in hour	30
s	Second in minute	    55
S	Millisecond                 978
z	Time zone               Pacific Standard Time; PST; GMT-08:00
Z	Time zone                   -0800
X	Time zone                   -08 or -0800 or -08:00
*/
package dateandtime1;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author User
 */
public class DateAndTime1 {
    
 public static void main(String args[]) {
     
     System.out.println("*******example 1*******"); 
      Date objDate = new Date(); // Current System Date and time is assigned to objDate
      System.out.println(objDate);
      String strDateFormat = "hh:mm:ss a dd-MMM-yyyy"; //Date format is Specified
      SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); //Date format string is passed as an argument to the Date format object
      System.out.println(objSDF.format(objDate)); //Date formatting is applied to the current date

      System.out.println("*******example 2*******"); 
      Date objDate2 = new Date(); // Current System Date and time is assigned to objDate
      //System.out.println(objDate2);
      String strDateFormat2 = "dd"; //Date format is Specified
      SimpleDateFormat objSDF2 = new SimpleDateFormat(strDateFormat2); //Date format string is passed as an argument to the Date format object
      System.out.println(objSDF2.format(objDate2)); //Date formatting is applied to the current date

      System.out.println("*******example 3*******"); 
      Date objDate3 = new Date(); // Current System Date and time is assigned to objDate
      //System.out.println(objDate3);
      String strDateFormat3 = "E"; //Date format is Specified
      SimpleDateFormat objSDF3 = new SimpleDateFormat(strDateFormat3); //Date format string is passed as an argument to the Date format object
      System.out.println(objSDF3.format(objDate3)); //Date formatting is applied to the current date

      System.out.println("*******example 4*******"); 
      Date objDate4 = new Date(); // Current System Date and time is assigned to objDate
      //System.out.println(objDate3);
      String strDateFormat4 = "E d h:mm a"; //Date format is Specified
      SimpleDateFormat objSDF4 = new SimpleDateFormat(strDateFormat4); //Date format string is passed as an argument to the Date format object
      System.out.println(objSDF4.format(objDate4)); //Date formatting is applied to the current date

      System.out.println("*******example 5*******"); 
      Date objDate5 = new Date(); // Current System Date and time is assigned to objDate
      //System.out.println(objDate3);
      String strDateFormat5 = "E dd h:mm D"; //Date format is Specified
      SimpleDateFormat objSDF5 = new SimpleDateFormat(strDateFormat5); //Date format string is passed as an argument to the Date format object
      System.out.println(objSDF5.format(objDate5)); //Date formatting is applied to the current date
            
    }
}
