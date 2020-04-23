using CourseReg;
using System.Collections.Generic;
using System.Data.Entity;
using System.Web.Mvc;

namespace u8a1_MVCCoursesApplication.Models
{

    public class RegistrationModel : DbContext
    {
        // Your context has been configured to use a 'RegistrationModel' connection string from your application's 
        // configuration file (App.config or Web.config). By default, this connection string targets the 
        // 'u8a1_MVCCoursesApplication.Models.RegistrationModel' database on your LocalDb instance. 
        // 
        // If you wish to target a different database and/or database provider, modify the 'RegistrationModel' 
        // connection string in the application configuration file.
        public RegistrationModel()
            : base("name=CourseContextConnString") {}

        //Make available properties, set to public.
        public DbSet<Course> Course { get; set; }
        public DbSet<SelectedCourse> SelectedCourses { get; set; }

        // View model with data for dropdown
        public List<SelectListItem> CourseDropdownList { get; set; }
        // Track index of selected book in dropdown
        public int SelectedCourseID { get; set; }
        // Track string with user ID
        public string UserIDText { get; set; }
        // Text for message to user
        public string UserMessage { get; set; }
        // Total price of selected books
        public int TotalCredits { get; set; }



    }

    
}