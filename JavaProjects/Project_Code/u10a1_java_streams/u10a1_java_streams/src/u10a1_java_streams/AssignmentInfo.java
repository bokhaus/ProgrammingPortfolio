
package u10a1_java_streams;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author bberkland
 * 
 *  Simple class to hold assignment ID and average of scores for assignment
 * 
 */
public class AssignmentInfo {
    final private String assignmentID;   // u01a1, u02a1, etc...
    final private double average;          // Average perecentage score for assignment

    public AssignmentInfo(String assignmentID, double average) {
        this.assignmentID = assignmentID;
        this.average = average;
    }

    public String getAssignmentID() {
        return assignmentID;
    }
    //Added formatting to limit number of positions past decimal point
    public double getAverage() {
        NumberFormat nf = new DecimalFormat("##.0000");
        double avgScore = Double.parseDouble(nf.format(average));
        return avgScore;
    }
}
