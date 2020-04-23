using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CourseReg
{
    //Annotation to line-up table with data
    [Table("course")]
    public class Course
    {
        // Parameterized constructor sets values for all properties
        public Course(string courseNumber, string courseTitle, int credits, int weeks)
        {
            CourseNumber = courseNumber;
            CourseTitle = courseTitle;
            Credits = credits;
            LengthOfCourse = weeks;
            // Initialize Selected to false
            Selected = false;
        }
        //Entity Framework does not use parameterized constructor
        //Parameterless constructor required!
        public Course() { }

        //CourseNumber maps to the key in the course DB table.
        [Key] public string CourseNumber { get; set; }
        public string CourseTitle { get; set; }
        public int Credits { get; set; }

        //Mapping variance in names
        [Column("LengthInWeeks")]
        public int LengthOfCourse { get; set; }
        
        //Annotation to NOT map the Selected column to the table and do not add a column
        [NotMapped]
        public bool Selected { get; set; }

        //Override of System toString
        public override string ToString()
        {
            return $"{CourseNumber}, {CourseTitle}, {Credits} (credits), {LengthOfCourse} (weeks)";
        }

    }
}
