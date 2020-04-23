using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using CourseReg;

namespace u5a1_WPF_CourseProject
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private CourseViewModel courseList;
        public MainWindow()
        {
            InitializeComponent();
            try
            {
                courseList = new CourseViewModel(@"Data\course.data.txt");
            }
            catch(IOException ex)
            {
                statusMessage.Content = $"Unable to read file: {ex.Message}";
            }
            catch (IndexOutOfRangeException ex)
            {
                statusMessage.Content = $"Error Parsing data: {ex.Message}";
            }
            catch(FormatException ex)
            {
                statusMessage.Content = $"Erroe parsing numeric data: {ex.Message}";
            }
        }
        //Method to load Combo Box 
        private void CourseWindow_Loaded(object sender, RoutedEventArgs e)
        {
            statusMessage.Content = $"({courseList.Course.Count}) Courses Available.\n" + 
                "Please select a course from the list.";
            courseComboBox.ItemsSource = courseList.Course;
        }

        private void CourseCombox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            //Variable for selected item from ComboBox
            Course chosenCourse = courseList.Course[courseComboBox.SelectedIndex];
            if (!chosenCourse.Selected)
            {
                //Validation for maximum credits allowed
                if ((courseList.TotalCredits + chosenCourse.Credits) > 9)
                {
                    statusMessage.Foreground = Brushes.Red;
                    statusMessage.Content = "You may not register for more than 9 credits.";
                }
                else
                {
                    //Displys status message as black when not duplicate
                    statusMessage.Foreground = Brushes.Black;
                    //Sets selected item to true
                    courseList.Course[courseComboBox.SelectedIndex].Selected = true;
                    //Displays selected item to status message label
                    statusMessage.Content = $"{chosenCourse.CourseNumber} slected.";
                    //Displays selected courses to ListView
                    registeredCourseList.ItemsSource = courseList.SelectedCourses;
                    //Displays total credits to credit hour label
                    totalCreditHoursLabel.Content = courseList.TotalCredits;
                    //Refreshes ComboBox list
                    courseComboBox.Items.Refresh();
                }
            }
            else
            {
                //Displays status message in red when duplicate selection has been made. 
                statusMessage.Foreground = Brushes.Red;
                //Displays status message with chosen title specified.
                statusMessage.Content = $"{chosenCourse.CourseTitle} ** already selected **";
            }

        }

        private void RegisterBtn_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                //Print list of registered courses to file
                foreach (Course c in courseList.Course)
                {
                    if (c.Selected)
                    {
                        File.AppendAllText(@"Data\registered.courses.txt", c.ToString() + $"\nRegistered on: {DateTime.Now}" + Environment.NewLine);
                        statusMessage.Content = "Your registration has been processed.";
                    }
                }
            }
            catch (IOException ex)
            {
                statusMessage.Content = $"ERROR - Unable to write registration to file: {ex.Message}";
            }

        }

    }
}
