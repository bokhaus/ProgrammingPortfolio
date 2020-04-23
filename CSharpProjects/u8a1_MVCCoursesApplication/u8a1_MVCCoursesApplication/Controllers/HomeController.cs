using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using u8a1_MVCCoursesApplication.Models; //added
using CourseReg; //added

namespace u8a1_MVCCoursesApplication.Controllers
{
    public class HomeController : Controller
    {
        //Course Model 
        private RegistrationModel viewModel = new RegistrationModel();
        
        //Method called from code within the class set to private.
        // Source of items for selected list
        private void generateCourseList()
        {
            //new drop down list 
            viewModel.CourseDropdownList = new List<SelectListItem>();
            int index = 0;

            //Iterate through the list providing an Iterable list
            foreach(var c in viewModel.Course.ToList())
            {
                //Provide items needed for the list
                SelectListItem listEntry = new SelectListItem();
                //Set text 
                listEntry.Text = c.CourseNumber;
                //List entry value and increment index value
                //Markup is a String value
                listEntry.Value = (index++).ToString();
                viewModel.CourseDropdownList.Add(listEntry);
            }
            viewModel.TotalCredits = totalCredits();
        }// end generateCourseList()

        private int totalCredits()
        {
            //Create collections of Course Credits
            var selectedCourseCredits = viewModel.SelectedCourses
                .Join(viewModel.Course, sc => sc.CourseNumber, c => c.CourseNumber,
                (sc, c) => c.Credits);
            int creditCount = selectedCourseCredits.Count();
            if (creditCount > 0)
            {
                return selectedCourseCredits.Sum();
            }
            else
            {
                return 0;
            }
        }// end totalCredits()

        // GET: Home - Runs on first visit to page
        public ActionResult Index()
        {
            viewModel.UserMessage = $"{viewModel.Course.Count()} Courses available.";
            generateCourseList();

            return View(viewModel);
        } // end Index()

        // Postback that passes in existing RegistrationModel
        [HttpPost]
        public ActionResult Index(RegistrationModel cModel)
        {

            if (cModel.UserIDText != null)
            {
                //Create drop down list
                viewModel.CourseDropdownList = new List<SelectListItem>();

                //Checks there is a selected course before adding it to the view model
                if (cModel.SelectedCourseID > 0)
                {
                    // Copy selected course ID and user ID to new view model
                    //Refreshes the page
                    viewModel.SelectedCourseID = cModel.SelectedCourseID;
                    viewModel.UserIDText = cModel.UserIDText;

                    // Get object for selected course from viewModel.Course
                    // Need to subtract 1 to correct index
                    Course chosenCourse = viewModel.Course.ToList()[viewModel.SelectedCourseID - 1];

                    //Check for duplicate course
                    if (viewModel.SelectedCourses.Select(c => c.CourseNumber)
                        .Contains(chosenCourse.CourseNumber))
                    {
                        viewModel.UserMessage = $"{chosenCourse.CourseNumber} already selected.";
                    }


                    /*This portion of the Validation should work.
                     * It uses the variable created from the totalCredits() to calculate the total number of 
                     * credits currently registered for from DB.
                     */
                    //Check Greater than 9 credits registered
                    else if (totalCredits() + chosenCourse.Credits > 9)
                    {
                        viewModel.UserMessage = "You may not register for more than 9 credits.";
                    }
                    else
                    {
                        //Add Course to registration
                        SelectedCourse coureSelectedVM = new SelectedCourse();
                        coureSelectedVM.CourseNumber = chosenCourse.CourseNumber;
                        coureSelectedVM.UserID = viewModel.UserIDText;
                        viewModel.SelectedCourses.Add(coureSelectedVM);
                        viewModel.SaveChanges();
                        viewModel.UserMessage = $"{chosenCourse.CourseNumber} selected.";
                    }
                }
                else
                {
                    viewModel.UserMessage = "Please select a book.";
                }
                generateCourseList();
            } // end if (cModel.UserIDText != null)
            else
            {
                viewModel.UserMessage = "Please enter a user ID.";
                generateCourseList();
                return View(viewModel);
            } // end of UserIDText is null
            return View(viewModel);
        }
        public ActionResult DeleteAll()
        {
            viewModel.Database.ExecuteSqlCommand("truncate table selected_course");
            // Return to index view, no need for a delete view
            return RedirectToAction("Index");
        }
    }
}
 
 