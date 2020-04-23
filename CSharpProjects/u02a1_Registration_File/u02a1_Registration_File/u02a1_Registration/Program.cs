using System;
using System.Collections.Generic;
using System.Text;
using System.IO;

namespace u02a1_Registration_File
{
    class Program
    {
        static void Main(string[] args)
        {
            /* Text file with course data is in Data folder in Visual Studio
             * project. This file's Copy to Output Directory property is 
             * set to "Copy If Newer" 
             */
            List<Course> courses = ReadDataFromFile(@"Data\course.data.txt");
            
            // Loop to keep prompting for registrations until user is done
            bool continueRegistration = true;  // true ensures that loop will run.
            while(continueRegistration)
            {
                ListAvailableCourses(courses);
                Console.Out.Write("Enter menu item # for course: ");
                // Read user input & convert to integer
                //***Believe folowing line creates issues when selecting out-of-bounds on first selection***
                //After initial selection within range, validation works.
                //Unable to find fix at this time
                int choice = Convert.ToInt16(Console.ReadLine());
                /* Call Validator.ValidateRegistration() & take action
                 * based on result. 
                 */
                switch(Validator.ValidateRegistration(choice, courses))
                {
                    case 0: // registration OK
                        courses[choice-1].IsRegistered = true;
                        Console.WriteLine("\n*** You are now registered for {0}. ***", courses[choice-1].CourseNumber);
                        break;
                    case -1: 
                        Console.WriteLine("\n*** Unrecognized menu item #: {0}. ***", choice);
                        break;
                    case -2:
                        Console.WriteLine("\n*** You are already registered for {0}. ***", courses[choice-1].CourseNumber);
                        break;
                    case -3:
                        Console.WriteLine("\n*** You may not register for more than 9 credits. ***");
                        break;
                }
                // Display listing of current registration
                ListCurrentRegistration(courses);

                // Loop to prompt user about continuing. Repeat if invalid response.
                bool validResponse = false;
                while (!validResponse)
                {
                    Console.Write("\nDo you wish to continue? (Y/N): ");
                    // Capitalize input and store as char
                    char response = Convert.ToChar(Console.ReadLine().ToUpper());
                    if(response == 'Y')
                    {
                        break;
                    }
                    else if(response == 'N')
                    {
                        //Formatting when printing to console
                        Console.WriteLine("\n**********************************************************");
                        Console.WriteLine("\nThank you for registering. Writing registration to file...\n");
                        continueRegistration = false;
                        break;
                    }
                    else
                    {
                        Console.WriteLine("Unrecognized response: {0}.", response);
                    }
                }
            }
            /* When finished, write registration to file in Data folder.
             * Note that this file will be in the solution's bin/Debug/Data 
             * directory, not in the source code tree. */
            WriteRegistrationToFile(@"Data\registered.courses.txt", courses);
        }

        /* This method reads data from the comma-delimited text file 
         * containing course information in the Data folder in the solution.
         * Input parameter: name of file as a string
         * Returns: List of Course objects based on file data. 
         */ 
        static List<Course> ReadDataFromFile(string fileName)
        {
            // Use this List to hold data about courses
            List<Course> courses = new List<Course>();

            /*======================================================
             * Add code here to read data file and fill the 
             * List<Course> courses collection with corresponding
             * Course objects. 
             *====================================================== */
               
            try
            {
                //use parameter fileName in place of file path
                //Created  by List<Course> courses = ReadDataFromFile(@"Data\course.data.txt");
                //line 16
                foreach (string line in File.ReadAllLines(fileName))
                {
                    //separates string array into indexes pertainig to each piece of data
                    //Split string at comma 
                    string[] courseData = line.Split(',');
                    Course course = new Course(courseData[0], courseData[1],
                        int.Parse(courseData[2]), int.Parse(courseData[3]));
                    courses.Add(course);
                }
            }
            //Exception handling
            catch (IOException ex)
            {
                //Print error message
                Console.WriteLine(ex.Message);
            }
            //Exception handling
            catch (IndexOutOfRangeException ex)
            {
                //Print error message
                Console.WriteLine(ex.Message);
            }
            //Exception handling
            catch (FormatException ex)
            {
                //Print error message
                Console.WriteLine(ex.Message);
            }

            return courses;
        }

        static void ListAvailableCourses(List<Course> courses)
        {
            // Menu selection numbers start with 1
            int itemNumber = 1;
            foreach(Course c in courses)
            {
                Console.Out.WriteLine("[{0}] {1} {2} ({3})", itemNumber++, c.CourseNumber,
                    c.CourseTitle, c.Credits);
            }
            // Add blank line
            Console.WriteLine(" ");
        }

        // List current registrations and credit total
        static void ListCurrentRegistration(List<Course> courses)
        {
            int totalCredits = 0;
            // Use StringBuilder to assemble list of registered courses
            StringBuilder courseListBuilder = new StringBuilder();
            foreach(Course c in courses)
            {
                if (c.IsRegistered)
                {
                    totalCredits += c.Credits;
                    courseListBuilder.Append(c.CourseNumber);
                    courseListBuilder.Append(", ");
                }
            }
            Console.Write("Regisered Courses: {");
            // Remove last 2 chacters to get rid of trailing comma and space
            courseListBuilder.Remove(courseListBuilder.Length - 2, 2);
            Console.WriteLine(courseListBuilder.ToString() + "}");
            Console.WriteLine("Total credits: {0}", totalCredits);
        }

        /* static method to go through collection of courses and write registered 
         * courses to file in format 
         *  <course number>, <course title>, <credits>
         * Parameters:
         *  fileName is string with name of output file
         *  courses is List<Course> collection of all courses */
        static void WriteRegistrationToFile(string fileName, List<Course> courses)

        {
            //Iterate over course array and writes the registered courses to fileName 
            //Uses multi-line Lambda
            Action<Course> writeCourse = (c) =>
            {
                //If course validates, write regisration to file
                if (c.IsRegistered)
                {
                    Console.Write(c);
                    try
                    {
                        //writes registered courses to fileName. Enviroment.Newline is similar to \n
                        File.AppendAllText(fileName, c.ToString() + $"Registered on: {DateTime.Now}" + Environment.NewLine);
                    }
                    //Exception handling
                    catch (IOException ex)
                    {
                        //Print error message for IO Exception
                        Console.WriteLine($"Error writing to file: {ex.Message}");
                    }
                }
            };
            courses.ForEach(writeCourse);

            //Formatting when printing to console
            Console.WriteLine("\n**********************************************************\n");
        }
    }
}
