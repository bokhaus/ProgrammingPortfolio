using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using CourseReg;


namespace Unit6_Registration_WinForm_SQL
{
    public partial class Form1 : Form
    {

        private int totalCredit = 0;
        //Connection to database
        private String connectionString = "Data Source=localhost;" +
                    "Initial Catalog=registration;" +
                    "User Id=RegistrationUser;Password=P@ssw0rd;";

        //Query to add registration to the course table
        private string courseQuery = @"select CourseNumber, CourseTitle, Credits, LengthInWeeks 
                                        from course
                                        order by CourseNumber";

        private BindingSource courseBindingSource = new BindingSource();
        private SqlDataAdapter courseDataAdapter = new SqlDataAdapter();
        private List<Course> courseList = new List<Course>();
        private bool display = false;
        private SqlConnection connection;

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            try
            {
                // using statement makes sure connection will be closed properly
                using (connection = new SqlConnection(connectionString))
                {
                    courseDataAdapter = new SqlDataAdapter(courseQuery, connection);
                    DataTable courseTable = new DataTable();
                    courseDataAdapter.Fill(courseTable);
                    // Select() provides an iterable list of rows in data table.
                    // Use var to simplify declaration of data type
                    foreach (var bookData in courseTable.Select())
                    {
                        // Access each columan as a Field of appropriate type
                        // Parameters are names of DB table columns
                        string courseNumber = bookData.Field<string>("CourseNumber");
                        string courseTitle = bookData.Field<string>("CourseTitle");
                        int credits = bookData.Field<int>("Credits");
                        int weeks = bookData.Field<int>("LengthInWeeks");
                        courseList.Add(new Course(courseNumber, courseTitle, credits, weeks));
                    }
                    // Put course list in binding source's DataSource property
                    courseBindingSource.DataSource = courseList;
                    // Set ComboBox's DataSource to binding source
                    courseComboBox.DataSource = courseBindingSource;
                    // Use course object's course number as display text in combobox
                    courseComboBox.DisplayMember = "CourseNumber";

                    // Set selected index to -1 so course number could be selected.
                    // Otherwise, user can't select 1st item as 1st choice. 
                    courseComboBox.SelectedIndex = -1;

                    // Set ListView of selected course to List mode
                    registeredCourseList.View = View.List;

                    // Display is now ready
                    display = true;
                }
            }
            catch (SqlException ex)
            {
                MessageBox.Show($"SQL error: ${ex.Message}");
            }

        }

        // Event handler for combobox selection
        private void courseComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            // Only handle event if display setup is complete
            if (display)
            {
                statusMessageLabel.Text = courseComboBox.SelectedIndex.ToString();
                int choice = courseComboBox.SelectedIndex;
                switch (Validator.ValidateRegistration(choice, courseList))
                {
                    //If validator verifies selection is ok, not duplicate, and not brining student over 9 hours.
                    case 0: // registration OK
                        ((Course)courseBindingSource[courseComboBox.SelectedIndex]).Selected = true;

                        statusMessageLabel.Text = $"You have registered for {courseList[choice].CourseNumber}- {courseList[choice].CourseTitle}";
                        totalCredit += courseList[choice].Credits;
                        registeredCourseList.Items.Add(courseList[choice].ToString());
                        registeredCourseList.View = View.List;
                        totalCreditValueLabel.Text = totalCredit.ToString();
                        break;
                    // If duplicate registration
                    case -1:
                        statusMessageLabel.Text = $"You are already registered for {courseList[choice].CourseNumber}.  Please make another selection.";
                        break;
                    //If user is registering over 9 hours.
                    case -2:
                        statusMessageLabel.Text = "You may not register for more than 9 credits.";
                        break;

                }
            }
        }

        private void updateDatabaseButton_Click(object sender, EventArgs e)
        {
            // SQL statement with named parameters
            string sqlInsert = @"insert into selected_course(CourseNumber, userid)
                                    VALUES(@CourseNumber,@userID)";
            // Make sure user ID box isn't empty
            if (userIDTextBox.Text.Length > 0)
            {
                // using statement guarantees connection will be closed correctly
                using (connection = new SqlConnection(connectionString))
                {
                    // Connection must be explicitly opened
                    connection.Open();
                    foreach (Course c in (List<Course>)courseBindingSource.DataSource)
                    {
                        if (c.Selected)
                        {
                            try
                            {
                                // Create new SqlCommand object to insert a course into selected_courses
                                // Leave first argument, command text, empty for now.
                                SqlCommand insertCommand = new SqlCommand(null, connection);
                                // Set command text to SQL statement above
                                insertCommand.CommandText = sqlInsert;
                                // Create parameter objects with param name, SQL data type, and size
                                SqlParameter codeParam = new SqlParameter("@CourseNumber", SqlDbType.VarChar, 80);
                                SqlParameter userIDParam = new SqlParameter("@userID", SqlDbType.VarChar, 80);
                                // Set values of parameters
                                codeParam.Value = c.CourseNumber;
                                userIDParam.Value = userIDTextBox.Text;
                                // Add parameter objects to command
                                insertCommand.Parameters.Add(codeParam);
                                insertCommand.Parameters.Add(userIDParam);
                                // Prepare command and execute as non-query since it's a SQL insert
                                insertCommand.Prepare();
                                insertCommand.ExecuteNonQuery();
                            }
                            catch (SqlException ex)
                            {
                                MessageBox.Show($"SQL insert error: {ex.Message}");
                            }
                        }
                    }
                }
                statusMessageLabel.Text = "Database updated.";
            }
            else // user ID box was empty
            {
                statusMessageLabel.Text = "Please enter User ID.";
                userIDTextBox.Focus();
            }
        }
    }
}



