using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using CourseList;

namespace CourseRegistration_Console_u01a1
{
    class Program
    {
        static void Main(string[] args)
        {
            //Create an Array
            Course[] list = new Course[3];

            //Insert user input to array
            for (int i = 0; i < list.Length; i++)
            {   
                //User Input
                Console.Write("Enter Course ID: ");
                String courseNumber = Console.ReadLine();
                Console.Write("Enter Course Title: ");
                String courseTitle = Console.ReadLine();
                Console.Write("Enter Course Credits: ");
                int credits = int.Parse(Console.ReadLine());
                Console.Write("Enter Course Length: ");
                int lengthOfCourse = int.Parse(Console.ReadLine());
                list[i] = new Course(courseNumber, courseTitle, credits, lengthOfCourse);
            }
            //Create Header for list
            Console.WriteLine("\n Course List ");

            //Lambda expression to Loop through array of Courses
            Array.ForEach<Course>(list, corList => Console.WriteLine(corList));
        }
    }
}
