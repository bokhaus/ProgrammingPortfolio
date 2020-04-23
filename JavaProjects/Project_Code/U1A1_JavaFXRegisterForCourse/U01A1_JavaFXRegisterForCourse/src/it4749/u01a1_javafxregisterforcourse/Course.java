package it4749.u01a1_javafxregisterforcourse;

/**
 *
 * @author <Your Name Here>
 */
public class Course {
    
    private String code;   // Course number/designation such as IT2230
    private boolean isRegisterdFor;
    private final int credits;
    
    public Course(String code, int credits){
        this.isRegisterdFor = false;
        this.code = code;
        this.credits = credits;
    }
    
    public void setCode(String code){
        this.code = code;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public int getCredits() {
        return this.credits;
    }
    
    public void setIsRegisteredFor(boolean trueOrFalse){
        this.isRegisterdFor = trueOrFalse;
    }
    
    public boolean getIsRegisteredFor() {
        return this.isRegisterdFor;
    } 
    
    @Override
    public String toString(){
        return this.getCode();
    }   
}
