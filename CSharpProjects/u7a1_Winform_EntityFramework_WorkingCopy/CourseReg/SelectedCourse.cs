using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CourseReg
{
    //Must use Table annotation to map to table in DB
    //Table in the DB is called selected_course
    [Table("selected_course")]
    public class SelectedCourse
    {
        //Parameterless Constructor used here
        //Defines Key for selected_course table and states the value is auto-generated
        //Entries used when working with selected_course table
        [Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public long SelectedID { get; set; } 
        public string CourseNumber { get; set; }
        public string UserID { get; set; }
    }
}
