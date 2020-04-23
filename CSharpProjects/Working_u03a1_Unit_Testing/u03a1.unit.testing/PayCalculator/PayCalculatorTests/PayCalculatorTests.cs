using Microsoft.VisualStudio.TestTools.UnitTesting;
using PayCalculator;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;



namespace PayCalculator.Tests
{
    [TestClass()]
    public class PayCalculatorTests
    {
        // Private class variable to make PayCalculator available to tests
        private PayCalculator calc;

        // Method to run before the start of each unit test
        //Private class variable to initialize test methods
        //private PayCalculator testPayCalc;
        [TestInitialize()]
        public void SetHoursAndRate()
        {
            List<decimal> hours = new List<decimal>();
            //hours.Add(40);
            //hours.Add(44);
            //hours.Add(43);
            hours.Add(45);
            calc = new PayCalculator(hours, 20.00M);
        }

        [TestMethod()]
        public void GetGrossPayTest()
        {
            Assert.AreEqual(950, calc.GetGrossPay());
        }

        [TestMethod()]
        public void GetTaxTest()
        {
            Assert.AreEqual(150, calc.GetTax());
        }

        [TestMethod()]
        public void GetNetPayTest()
        {
            Assert.AreEqual(800, calc.GetNetPay());
        }

        [TestMethod()]
        public void ToStringTest()
        {
            Assert.AreEqual("Gross pay: 950.00, Tax: 150.00, Net pay: 800.00", calc.ToString());
        }
    }
}