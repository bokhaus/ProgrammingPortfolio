using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CourseList
{
    public class Course
    {
        //Get and set attributes
        public String CourseNumber { get; private set; }
        public String CourseTitle { get; private set; }
        public int Credits { get; private set; }
        public int LengthOfCourse { get; private set; }

        //Constructor
        public Course (String courseNumber, String courseTitle, int credits, int lengthOfCourse)
        {
            CourseNumber = courseNumber;
            CourseTitle = courseTitle;
            Credits = credits;
            LengthOfCourse = lengthOfCourse;

        }

        //Override of System toString
        public override string ToString()
        {
            return $"{CourseNumber}. {CourseTitle}. {Credits} credit hours. {LengthOfCourse} weeks.";
        }

    }
}
