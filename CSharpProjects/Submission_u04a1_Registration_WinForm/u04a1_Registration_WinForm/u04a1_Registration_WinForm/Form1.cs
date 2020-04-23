using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using CourseReg;


namespace u04a1_Registration_WinForm
{
    public partial class Form1 : Form
    {
        private List<Course> courseList = new List<Course>();

        //Variable to handle false events which are triggered.
        private bool display = false;
        private int totalCredits = 0;
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            /* 
             *  When form loads, this code will read the course data from the file
             *  and populate the combobox.
             */
            try
            {
                // Using using statement makes sure file is closed when done 
                using (StreamReader sr = new StreamReader(@"Data\course.data.txt"))
                {
                    string line;
                    while ((line = sr.ReadLine()) != null)
                    {
                        // Parse data and add Course objects to courses List as they are created
                        string[] data = line.Split(',');
                        // Need to convert credits and weeks to integers
                        courseList.Add(new Course(data[0], data[1], Convert.ToInt16(data[2]),
                            Convert.ToInt16(data[3])));
                    }
                }
                // Sort Course list based on CourseNumber using Lambda expression
                courseList.Sort((a, b) => a.CourseNumber.CompareTo(b.CourseNumber));
                // Map displayed value in courses combobox to CourseNumber
                courseComboBox.DataSource = courseList;
                //courseComboBox.DisplayMember = "CourseNumber";
                courseComboBox.ValueMember = "CourseNumber";
                // Set display mode for ListView control
                registeredCourseList.View = View.List;

                // Handle events
                display = true;

            }
            catch (Exception ex)
            {
                statusMessageText.Text = "Error reading data: " + ex.Message;
            }
        }

        // Event handler for combobox selection
        private void courseComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            /*
             * Add code here to handle course selection in combobox.
             * Be sure to validate registration request as in previous assignment.
             * Need to update listing of registered coureses.
             * Update credit total.
             * Display an appropriate message in the statusMessageText. 
             */

            //Turns on the display
            if (display)
            {
                statusMessageText.Text = courseComboBox.SelectedIndex.ToString();
                int choice = courseComboBox.SelectedIndex;
                switch (Validator.ValidateRegistration(choice, courseList))
                {
                    //Registation is valid
                    case 0:
                        courseList[courseComboBox.SelectedIndex].Selected = true;

                        statusMessageText.Text = $"You have registered for {courseList[choice].CourseNumber}  {courseList[choice].CourseTitle}";
                        totalCredits += courseList[choice].Credits;
                        registeredCourseList.Items.Add(courseList[choice].ToString());
                        registeredCourseList.View = View.List;
                        totalCreditValueLabel.Text = totalCredits.ToString();
                        break;
                    // If duplicate registration
                    case -1:
                        statusMessageText.Text = $"You have already registered for {courseList[choice].CourseNumber}  {courseList[choice].CourseTitle}";
                        break;
                    //If registration is greater than 9 hours.
                    case -2:
                        statusMessageText.Text = "You are attempting to register for more than 9 credits.";
                        break;
                }
            }
        }


        private void completeRegistrationButton_Click(object sender, EventArgs e)
        {
            /*
             * When the user clicks the Complete Registration button, the program
             * should write the registration information to a registered.courses.txt
             * file in the Data directory. 
             */
            //Iterate over course array and writes the registered courses to fileName 
            //Uses multi-line Lambda
            Action<Course> writeCourse = (c) =>
            {
                //If course validates, write regisration to file
                if (c.Selected)
                {
                    try
                    {
                        //writes registered courses to fileName. Enviroment.Newline is similar to \n
                        File.AppendAllText(@"Data\registered.courses.txt", c.ToString() + $"\nRegistered on: {DateTime.Now}" + Environment.NewLine);
                        statusMessageText.Text = "Your registration is being saved.";
                    }
                    //Exception handling
                    catch (IOException)
                    {
                        //Print error message for IO Exception
                        statusMessageText.Text = "ERROR - Unable to write registration to file. ";
                    }
                }
            };
            courseList.ForEach(writeCourse);

        }
    }
}
