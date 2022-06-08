
package tables;

import java.util.Date;
public class Experience {

    int specializationID;
    int volID;
    String experienceLevel;
    int years;
    String description;
    String degree;
    String degreeType;
            
    public Experience(int sID, int vID, String exp, int years, String description, String degree, String degreeType) {
        this.specializationID = sID;
        this.volID = vID;
        this.experienceLevel = exp;
        this.years = years;
        this.description = description;
        this.degree = degree;
        this.degreeType = degreeType;
        // TODO code application logic here
    }
    public int getSpecializationID(){
        return specializationID;
    }
    public void setSpecializationID(int specializationID){
        this.specializationID = specializationID;
    }
    public int getVolID(){
        return volID;
    }
    public void setVolID(int VolID){
        this.volID = volID;
    }
    public String getExperienceLevel(){
        return experienceLevel;
    }
    public void setExperienceLevel(String experienceLevel){
        this.experienceLevel = experienceLevel;
    }
    public int getYears(){
        return years;
    }
    public void setYears(int years){
        this.years = years;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDegree(){
        return degree;
    }
    public void setDegree(String degree){
        this.degree = degree;
    }
    public String getDegreeType(){
        return degreeType;
        
    }
    public void setDegreeType(String degreeType){
        this.degreeType = degreeType;
    }
    
}
