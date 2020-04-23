using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CourseReg
{
    public static class Validator
    {
        /*
         * ValidateRegistration is a static method that takes 2 parameters:
         *    itemNumber is an integer with the menu selection number
         *    courses is a List<Course> collection with all courses
         *    
         * It returns:
         *   -1 if the menu choice is invalid
         *   -2 if the requested item is a duplicate registration
         *   -3 if the requested item would put credits over 9
         *    0 if the requested item passes validation
         */
        public static int ValidateRegistration(int itemNumber, List<Course> courses)
        {
            int totalCredit = 0;
            int validationResult = 0;
            int itemIndex = itemNumber;

            //calculate total credits
            foreach (Course c in courses)
            {
                if (c.Selected)
                {
                    totalCredit += c.Credits;
                }
            }

            //Check duplicate registration, returns -1 if duplicate
            if (courses[itemIndex].Selected)
            {
                validationResult = -1;
            }
            //Check new credit hour total, returns -2 if credits over 9
            else if ((totalCredit + courses[itemIndex].Credits) > 9)
            {
                validationResult = -2;
            }
            
            //Return 0 if all items pass
            return validationResult;
        }
    }
}