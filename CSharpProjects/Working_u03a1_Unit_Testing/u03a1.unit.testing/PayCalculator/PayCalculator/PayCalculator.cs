using System;
using System.Collections.Generic;

namespace PayCalculator
{
    public class PayCalculator
    {
        // list holds hours by week in pay period
        private List<decimal> hours;
        // hourly rate is the same for whole pay period
        private decimal hourlyRate;
        private decimal grossPay = 0;
        private decimal tax = 0;
        private decimal netPay = 0;

        //{ get; private set; }

        // Parameterized constructor. 
        public PayCalculator(List<decimal> hours, decimal hourlyRate)
        {
            this.hours = hours;
            this.hourlyRate = hourlyRate;
        }

        /* Calculates the gross pay for pay period with overtime 
         * paid at time and half for hours over 40 in a week. */
        public decimal GetGrossPay()
        {
            /*========================================
             * Complete code to calculate gross pay
             *========================================*/
            foreach (decimal totalWeekHours in hours)
            {
                if (totalWeekHours <= 40)
                {
                    grossPay = totalWeekHours * hourlyRate;
                }
                else
                {
                    grossPay = (40 * hourlyRate) + ((totalWeekHours - 40) * 30.00m);
                }
            }
            return grossPay;
        }

        /* Calculate tax: 15% on first $800.00, 20% on 
           any amount over $800.00 */
        public decimal GetTax()
        {

            /*========================================
             * Complete code to calculate gross tax
             *========================================*/
            decimal tax1 = .15m;
            decimal tax2 = .20m;

            foreach (decimal totalWeekHours in hours)
            {
                if (grossPay == 0)
                {
                    GetGrossPay();
                }
                if (grossPay <= 800)
                {
                    tax = grossPay * tax1;
                }
                else
                {
                    tax = (800 * tax1) + ((grossPay - 800) * tax2);
                }
            }
            return tax;
        }
        
        public decimal GetNetPay()
        {
            /*========================================
             * Complete code to calculate net pay
             *========================================*/
            foreach (decimal totalWeekHours in hours)
            {
                if (grossPay == 0)
                {
                    GetGrossPay();
                }
                if (tax == 0)
                {
                    GetTax();
                }
                netPay = grossPay - tax;
            }
            return netPay;
        }

        /*
         * ToString() returns pay info in string formatted like:
         * Gross pay: 830.00, Tax: 136.00, Net pay: 694.00
         */ 
        override
        public string ToString()
        {
            foreach (decimal totalWeekHours in hours)
            {
                if (grossPay == 0)
                {
                    GetGrossPay();
                }
                if (tax == 0)
                {
                    GetTax();
                }
                if (tax == 0)
                {
                    GetTax();
                }
                if (netPay == 0)
                {
                    GetNetPay();
                }
            }
            return string.Format("Gross pay: {0:F2}, Tax: {1:F2}, Net pay: {2:F2}",
                this.grossPay, this.tax, this.netPay);
        }
    }
}
