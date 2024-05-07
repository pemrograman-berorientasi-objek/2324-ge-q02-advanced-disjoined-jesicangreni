package academic.model;

/**
 * @author 12S22006 Felice Sirait
 * @author 12S22018 Jesica Siburian
 */

 public class Student extends Human{

    private Integer Year;
    private String StudyProgram;

//constructor
    public Student(String Id, String Name, String Year, String StudyProgram) {
        super(Id, Name);
        this.Year = Integer.parseInt(Year);
        this.StudyProgram = StudyProgram;
    }

@Override
public String toString() {
    return this.Id + "|" + this.Name + "|" + this.Year + "|" + this.StudyProgram;
    }
}