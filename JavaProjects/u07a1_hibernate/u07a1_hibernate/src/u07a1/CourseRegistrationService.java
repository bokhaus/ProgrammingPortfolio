package u07a1;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * @author BrianBok
 */
public class CourseRegistrationService {
    protected EntityManager em;
    
    public CourseRegistrationService(EntityManager em) {
        this.em = em;
    }
    
    public Course createCourse(String courseCode, int creditHours) {
        Course course = new Course(courseCode, creditHours);
        em.persist(course);
        return course;
    }
    
    public List<Course> getAllCourses() {
        // Query is written in Hibernate Query Language (HQL) not SQL
        String hql = "SELECT crs FROM Course crs ORDER BY courseCode";
        TypedQuery<Course> query = em.createQuery(hql, Course.class);
        U07a1.LOG.log(Level.INFO, "getAllCourses method call.");
        return query.getResultList();
    }
    
    public CourseRegistration createCourseRegistration(String learnerID, String courseCode) {
        CourseRegistration registration = new CourseRegistration(learnerID, courseCode);
        em.getTransaction().begin();
        em.persist(registration);
        em.flush();
        em.getTransaction().commit();
        U07a1.LOG.log(Level.INFO, "createCourseRegistration method call.");
        return registration;

    }
    
    public List<CourseRegistration> getAllCourseRegistrations(String id) {
        // Query is written in Hibernate Query Language (HQL) not SQL
        String hql = "SELECT reg FROM CourseRegistration reg WHERE learnerID = :id";
        TypedQuery<CourseRegistration> query = em.createQuery(hql,
        CourseRegistration.class).setParameter("id", id);
        U07a1.LOG.log(Level.INFO, "getAllCourseRegistrations method call.");
        return query.getResultList();
    }
}
