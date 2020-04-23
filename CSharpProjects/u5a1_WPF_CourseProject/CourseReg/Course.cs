using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CourseReg
{
    public class Course
    {
        // Parameterized constructor sets values for all properties
        public Course(string courseNumber, string courseTitle, int credits, int weeks)
        {
            CourseNumber = courseNumber;
            CourseTitle = courseTitle;
            Credits = credits;
            LengthOfCourse = weeks;
            // Initialize IsRegistered to false
            Selected = false;
        }

        // These properties are auto-implemented and read only.
        public string CourseNumber { get; }
        public string CourseTitle { get; }
        public int Credits { get; }
        public int LengthOfCourse { get; }
        // IsRegistered is read-write
        public bool Selected { get; set; }

        //Override of System toString
        public override string ToString()
        {
            return $"{CourseNumber}, {CourseTitle}, {Credits} (credits), {LengthOfCourse} (weeks)";
        }

    }
}
