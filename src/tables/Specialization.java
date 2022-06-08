package tables;


public class Specialization {
    public int specializationID; 
    public String specialization_Name; 
    public static int specializationCount = 0; 
    
    public Specialization(){
        this.specializationID = specializationCount + 1; 
        this.specialization_Name = ""; 
    }
    
    public Specialization(int id, String name){
        this.specializationID = id; 
        this.specialization_Name = name; 
    }

    public int getSpecializationID() {
        return specializationID;
    }

    public void setSpecializationID(int specializationID) {
        this.specializationID = specializationID;
    }

    public String getSpecialization_Name() {
        return specialization_Name;
    }

    public void setSpecialization_Name(String specialization_Name) {
        this.specialization_Name = specialization_Name;
    }

    public static int getSpecializationCount() {
        return specializationCount;
    }

    public static void setSpecializationCount(int specializationCount) {
        Specialization.specializationCount = specializationCount;
    }
    
}
