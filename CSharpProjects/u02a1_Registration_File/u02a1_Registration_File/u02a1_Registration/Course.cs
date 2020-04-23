using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace u02a1_Registration_File
{
    public class Course
    {
        //Get and set attributes
        public String CourseNumber { get; private set; }
        public String CourseTitle { get; private set; }
        public int Credits { get; private set; }
        public int LengthOfCourse { get; private set; }
        // IsRegistered is read-write
        public bool IsRegistered { get; set; }


        //Constructor
        public Course (String courseNumber, String courseTitle, int credits, int lengthOfCourse)
        {
            CourseNumber = courseNumber;
            CourseTitle = courseTitle;
            Credits = credits;
            LengthOfCourse = lengthOfCourse;
            //Initialize IsRegistered to false
            IsRegistered = false;
        }

        //Override of System toString
        public override string ToString()
        {
            return $"{CourseNumber} [course], {CourseTitle} [title], {Credits} [credit hours], {LengthOfCourse} [weeks]\n";
        }
        
    }
}

