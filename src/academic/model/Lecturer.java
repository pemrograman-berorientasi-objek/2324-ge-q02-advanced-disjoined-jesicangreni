package academic.model;

/**
 * @author 12S22006 Felice Sirait
 * @author 12S22018 Jesica Siburian
 */

 public class Lecturer extends Human{

    public String initial;
    public String email;
    public String studyProgram;

//constract
    public Lecturer(String Id, String Name, String initial, String email, String studyProgram) {
        super(Id, Name);
        this.initial = initial;
        this.email = email;
        this.studyProgram = studyProgram;
    }

//getInitial
    public String getInitial() {
        return initial;
    }

    public String getEmail() {
        return email;
    }

@Override
public String toString() {
    return this.Id + "|" + this.Name + "|" + this.initial + "|" + this.email + "|" + this.studyProgram;
    }
}
