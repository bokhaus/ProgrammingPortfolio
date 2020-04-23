
package u07a1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author BrianBok
 */

// Add Hibernate annotations for class to map it the course_offering table
@Entity
@Table(name="course_offerings")
public class Course {
    // Add Hibernate @Column and @Id annotations
    @Id
    @Column(name="course_code")
    private String courseCode;
    
    // Add Hibernate @Column annotation
    @Column (name="credit_hours")
    private int creditHours;
    
    public Course() { }
    
    public Course(String courseCode, int creditHours) {
        this.courseCode = courseCode;
        this.creditHours = creditHours;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }
    
    @Override
    public String toString(){
        return courseCode + " (" + creditHours + ")";
    }
    
}
