using System;
using System.Collections.Generic;

namespace PayCalculator
{
    class Program
    {
        /* The Main() is not tested and is separate from the 
         * unit tests. Technically, Main() is not needed for 
         * unit tests. */
        static void Main(string[] args)
        {
            const int WeeksInPayPeriod = 4;
            List<decimal> hours = new List<decimal>();
            decimal hourlyRate;

            // Prompt and read hourly rate of pay (same for all weeks)
            Console.Write("Hourly Rate: ");
            hourlyRate = Convert.ToDecimal(Console.ReadLine());

            // Loop to prompt for and read hours for each week
            for(int i = 0; i < WeeksInPayPeriod; i++)
            {
                Console.Write("Enter hours for week {0}: ", i + 1);
                decimal h = Convert.ToDecimal(Console.ReadLine());
                hours.Add(h);
            }
            // Create Pay object using parameterized constructor
            PayCalculator totalPay = new PayCalculator(hours, hourlyRate);
            // WriteLine() causes object's ToString() to be called
            Console.WriteLine(totalPay);
        }
    }
}
