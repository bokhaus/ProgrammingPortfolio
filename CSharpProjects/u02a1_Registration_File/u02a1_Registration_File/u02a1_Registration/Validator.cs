using System;
using System.Collections.Generic;

namespace u02a1_Registration_File
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

            /* =================================
             * ADD VALIDATION CODE HERE. 
             * ================================= */

            //Lambda expression to calculate total credits of registered courses
            //This is used for validation expression below case -3.
            Action<Course> calcTotalCredits = (c)=>
			{
                if (c.IsRegistered)
                {
                    totalCredit += c.Credits;
                    
                }
            };
			courses.ForEach(calcTotalCredits);

            //Item selected must be greater than 0 or less than item count currently 8
            //Currently 7 courses available
            if (itemNumber > courses.Count || itemNumber < 0)
                return -1;
            //Duplicate registration
            else if (courses[itemNumber - 1].IsRegistered)
                return -2;
            //totalCredit plus selected course credit cannot be greater than 9
            else if ((totalCredit += courses[itemNumber - 1].Credits) > 9)
                return -3;

            //If validation passes return 0
            return validationResult;

        }
    }
}