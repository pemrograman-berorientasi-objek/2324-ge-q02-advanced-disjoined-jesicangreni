package academic.model;

/**
 * @author 12S22006 Felice Sirait
 * @author 12S22018 Jesica Siburian
 */

public class Human {
    public String Id;
    public String Name;

//constructor
    public Human(String Id, String Name) {
        this.Id = Id;
        this.Name = Name;
    }

public String getId() {
    return this.Id;
    }

public String getName() {
    return this.Name;
    }
    
}
