package academic.model;

/**
 * @author 12S22006 Felice Sirait
 * @author 12S22018 Jesica Siburian
 */

public class CourseOpening {
    public String code;
    public String academicYear;
    public String semester;
    private String[] initial;

public CourseOpening(String courseCode, String academicYear, String semester, String [] lecturerList) {
    this.code = courseCode;
    this.academicYear = academicYear;
    this.semester = semester;
    this.initial = lecturerList;
    }


public String getAcademicYear() {
    return this.academicYear;
}

public String getSemester() {
    return this.semester;
}

public String getCode() {
    return this.code;
}

public String[] getInitial() {
    return this.initial;
}

@Override
public String toString() {
    String initial = "";
    for (int i = 0; i < this.initial.length; i++){
        initial += this.initial[i];
        if (i != this.initial.length - 1){
            initial += ";";
        }
    }
    return this.code + "|" + this.academicYear + "|" + this.semester + "|" + initial;
    }
}