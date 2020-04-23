using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CourseReg
{
    //CourseContext class inherits from DbContext
    public class CourseContext : DbContext
    {
        //Provides constructor which calls base constructor and calls the CourseContextConnString
        //from app.config file.
        public CourseContext() : base("name=CourseContextConnString") { }
        
        //Make available properties, set to public.
        public DbSet<Course> Course { get; set; }
        public DbSet<SelectedCourse> SelectedCourses { get; set; }

        
    }
}
