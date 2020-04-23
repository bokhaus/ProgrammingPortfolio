using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using CourseReg;

namespace u7a1_Winform_EntityFramework_WorkingCopy
{
    public partial class CourseFormLoad : Form
    {
        //Validation Variables
        private int totalCredit = 0;
        private bool display = false;
        private List<Course> courseList = new List<Course>();
        private BindingSource courseBindingSource = new BindingSource();
        //Access CourseContext class and create private attribute
        private CourseContext context;
        public CourseFormLoad()
        {
            InitializeComponent();
        }

        private void CourseFormLoad_Load(object sender, EventArgs e)
        {
            try
            {
                //New CourseContext
                context = new CourseContext();
                //Load data from DB
                context.Course.Load();

                //Using Local copy of Course
                courseBindingSource.DataSource = context.Course.Local.ToBindingList();
                //Assign and display data member
                courseComboBox.DataSource = courseBindingSource.DataSource;
                courseComboBox.DisplayMember = "CourseNumber" + "Credits";

                //set index to negative, allowing first index to be selected.
                courseComboBox.SelectedIndex = -1;

                //populate the courselist with data
                courseList = context.Course.Local.ToList();

                // Set ListView of selected course to List mode
                registeredCourseList.View = View.List;

                //Set display to true 
                display = true;


            }
            catch (SqlException ex)
            {
                MessageBox.Show($"Database error: {ex.Message}");
            }
        }

        private void courseComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (display)
            {
                if (userIDTextBox.Text.Length > 0)
                {

                    //Book object is selected
                    Course chosenCourse = context.Course.Local[courseComboBox.SelectedIndex];
                    int choice = courseComboBox.SelectedIndex;

                    //Validation of Course selection
                    switch (Validator.ValidateRegistration(choice, courseList))
                    {
                        //Validator verifies selection is ok, not duplicate, and not bringing student over 9 hours.

                        // registration OK
                        case 0:
                            statusMessageLabel.Text = "";
                            statusMessageLabel.Font = new Font("Times New Roman", 12);
                            statusMessageLabel.ForeColor = Color.Black;
                            statusMessageLabel.Text = "Please select a course.\n\n*You may not register for more than 9 credit hours.*";
                            context.Course.Local[courseComboBox.SelectedIndex].Selected = true;
                            totalCredit += chosenCourse.Credits;
                            registeredCourseList.Items.Add(chosenCourse.ToString());
                            registeredCourseList.View = View.List;
                            totalCreditValueLabel.Text = totalCredit.ToString();
                            //Creates a selected course and adds to table selected_course
                            SelectedCourse c = new SelectedCourse();
                            c.UserID = userIDTextBox.Text;
                            c.CourseNumber = chosenCourse.CourseNumber;
                            context.SelectedCourses.Local.Add(c);
                            MessageBox.Show($"You have registered for:\n\n{chosenCourse.CourseNumber}- {chosenCourse.CourseTitle}");
                            break;

                        // If duplicate registration  CourseFormLoad
                        case -1:
                            statusMessageLabel.Text = "";
                            statusMessageLabel.Font = new Font("Times New Roman", 12, FontStyle.Bold);
                            statusMessageLabel.ForeColor = Color.Red;
                            statusMessageLabel.Text = "Please make a different selection!";
                            MessageBox.Show($"ERROR:\n\n{chosenCourse.CourseTitle} has already been selected.");
                            break;

                        //If user is registering over 9 hours.
                        case -2:
                            statusMessageLabel.Text = "";
                            MessageBox.Show("ERROR:\n\nYou may not register for more than 9 credit hours.");
                            statusMessageLabel.Text = "Select a different course.\n -OR-\nSelect 'Update Registration'";
                            break;

                    }
                }
                else
                {
                    MessageBox.Show("Enter User Name.");
                    //set display to false to prevent selection event
                    display = false;
                    //Sets selected index to -1 so nothing is selected
                    courseComboBox.SelectedIndex = -1;
                    display = true;
                    //Sets focus to textbox to move cursor to textbox
                    userIDTextBox.Focus();

                }
            }
        }

        private void updateDatabaseButton_Click(object sender, EventArgs e)
        {
            try
            {
                //compares and saves changes to selected_course table
                context.SaveChanges();
                MessageBox.Show($"The Database has been updated.");
            }
            catch (DbUpdateException ex)
            {
                MessageBox.Show($"Error updating database: {ex.InnerException.InnerException}");
            }

        }
    }
}

