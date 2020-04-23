using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using CourseReg;

namespace u5a1_WPF_CourseProject
{
    public class CourseViewModel
    {
        //Create list of courses
        public List<Course> Course { get; set; }

        //Create View Model
        //Separates data in text file
        public CourseViewModel(String filePath)
        {
            Course = new List<Course>();
            foreach (string line in File.ReadLines(filePath))
            {
                // Parse data and add Course objects to courses List as they are created
                string[] data = line.Split(',');
                // Need to convert credits and weeks to integers
                Course.Add(new Course(data[0], data[1], Convert.ToInt16(data[2]),
                  Convert.ToInt16(data[3])));
            }
            //Sort List in combo box
            Course.Sort((a, b) => a.CourseNumber.CompareTo(b.CourseNumber));
        }

        //Returns selected items from combo box
        //Read Only
        public List<Course> SelectedCourses
        {
            get
            {
                return Course.Where((b) => b.Selected).ToList();
            }
        }
        //Return Total Credits of selected items from combo box
        //Read Only
        public int TotalCredits
        {
            get
            {
                int totalCredits = 0;

                foreach(Course c in Course)
                {
                    if (c.Selected)
                    {
                        totalCredits += c.Credits;
                    }
                }
                return totalCredits;
            }

        }
    }
}
