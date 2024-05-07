package academic.model;

/**
 * @author 12S22006 Felice Sirait
 * @author 12S22018 Jesica Siburian
 */

 public class Enrollment {
    public String code;
    public String id;
    public String academicYear;
    public String semester;
    public String grade;
    public String perivousGrade = null;
    
    
//constructor
    public Enrollment(String code, String id, String academicYear, String semester, String grade) {
        this.code = code;
        this.id = id;
        this.academicYear = academicYear;
        this.semester = semester;
        this.grade = grade;
        this.perivousGrade = null;
    }

public String getcode() {
    return this.code;
    }

public String getid() {
    return this.id;
    }

public String getgrade(){
    return this.grade;
}

public String getAcademicYear(){
    return this.academicYear;
}

public String getSemester(){
    return this.semester;
}

public String getPreviousGrade(){
    return this.perivousGrade;
}

//setter grade
public void setgrade(String grade){
    this.grade = grade;
}

//setter previous grade
public void setPreviousGrade(String grade){
    this.perivousGrade = grade;
}


public String toString() {
    if (perivousGrade == null) {
        return this.code + "|" + this.id + "|" + this.academicYear + "|" + this.semester + "|" + this.grade;
    } else {
        return this.code + "|" + this.id + "|" + this.academicYear + "|" + this.semester + "|" + this.grade +  "(" + perivousGrade + ")";
    }
}
}