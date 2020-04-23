
package u10a1_java_streams;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author bberkland
 * 
 *  Class to hold grade entries as they are read from data file. 
 * 
 */
public class GradeEntry {
    final private int id;   // Student ID
    final private String assignment;  // Assignment ID
    final private int score;   // Percentage score
    final private LocalDate submissionDate;
    final private DateTimeFormatter dateFormat; // Format for parsing dates

    public GradeEntry(String id, String assignment, String score, String submissionDate) {
        this.dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
        this.id = Integer.parseInt(id);
        this.assignment = assignment;
        this.score = Integer.parseInt(score);
        this.submissionDate = LocalDate.parse(submissionDate, dateFormat);
    }    
    // =====================================================
    // Add methods getId(), getAssignment(), getScore(), getSubmissionDate() 
    // ======================================================
    
    public int getId() {
        return id;
    }
    public String getAssignment() {
        return assignment;
    }
    public int getScore() {
        return score;
    }
    public LocalDate getSubmissionDate() {
        return submissionDate;
    }
}
