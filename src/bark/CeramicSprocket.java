package bark;

/**
 *
 * @author coolm
 */
public class CeramicSprocket extends Sprocket {
    public String ceramicColor;
    
    public CeramicSprocket() {
        this.ceramicColor = "Blue"; 
        this.label = "Generic Sprocket";
        this.weight = 0.0;
    }
    
    public CeramicSprocket(String label, double weight, String ceramicColor) {
        super(label, weight); //Explicitly call that
        this.ceramicColor = ceramicColor;
        this.IDNum = "painted" + this.IDNum; // Adding another word to IDNum
    }
    
    public String getCeramicColor() {
        return this.ceramicColor;
    }
    
    public void setCeramicColor(String ceramicColor) {
        this.ceramicColor = ceramicColor;
    }
    
    public String toString() {
        return super.toString() + ", Paint Color: " + this.ceramicColor;
    }
    

}
