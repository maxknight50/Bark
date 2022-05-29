
package bark;

/**
 *
 * 
 */

public class Sprocket implements Comparable<Sprocket> {
    
    public String IDNum;
    public String label;
    public double weight;
    
    static public int sprocketCount = 0;
    
    // Constructor initializes objects
    
    
    // 0-Arg Constructor
    public Sprocket() {
        this.IDNum = "sprocket" + sprocketCount; // Use the string so you won't get incompatible type error
        this.label = "Generic Sprocket";
        this.weight = 0.0;
        
        sprocketCount++;
    }
    
    public Sprocket(String label, double weight) {
        this.IDNum = "sprocket" + sprocketCount;
        this.label = label;
        this.weight = weight;
        
        sprocketCount++;
        //Only one constructor will be called to initialize a single object
    }
    
    // Getters and setters form interface
    public String getLabel() {
        return this.label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    public double getWeight() {
        return this.weight;
    }
    
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public String getIDNum() {
        return this.IDNum; 
    }
    
    @Override
    public String toString() {
        return "Type: " + this.label 
                + ", Weight: " + this.weight 
                + ", oz., Spr #: " + this.IDNum;
    }
    
    public int compareTo(Sprocket o) {
        // return a value < 0 if thiis object is less on some metric
        // return a value >0 if this object is greater on some metric
        // return 0 exactly if they are the "same"
        // Application: sprocket1.compareTo(sprocket2);
        
        int result = 0;
        if (this.weight < o.weight) 
            result = -1;
        else if (this.weight > o.weight)
            result = 1;
        else
            result = 0;
        
        return result;             
        
    }
    
    public static void main(String[] args) {
        
    }
    
}
