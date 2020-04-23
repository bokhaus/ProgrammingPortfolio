/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u4a1_javafxregisterforcourse;

/**
 *
 * @author omora
 */
public class Course {
    
    private String code = "";
    private boolean isRegisterdFor = false;
    
    public Course(String code){
        this.code = code;
    }
    
    public void setCode(String code){
        this.code = code;
    }
    
    public String getCode() {
        return this.code;
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
