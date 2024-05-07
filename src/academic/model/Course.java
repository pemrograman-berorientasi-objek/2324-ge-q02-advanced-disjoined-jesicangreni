package academic.model;

/**
 * @author 12S22006 Felice Sirait
 * @author 12S22018 Jesica Siburian
 */

public class Course {
    private String Name;
    private String Code;
    private int Credit;
    private String PassingGrade;
    // private String[] initial;

public Course(String Name, String Code, int Credit, String PassingGrade) /*, String[] initial)*/ {
    this.Name = Name;
    this.Code = Code; 
    this.Credit = Credit;
    this.PassingGrade = PassingGrade;
    // this.initial = initial;
    }

public String getCode() {
    return this.Code;
}

public String getName() {
    return this.Name;
}

// public String [] getInitial() {
//     return this.initial;
// }

public int getCredit() {
    return this.Credit;
}

public String getPassingGrade() {
    return this.PassingGrade;
}

@Override
public String toString() {
    // //buat variabel array yang menampung semua this.initial
    // String initial = "";
    // for (int i = 0; i < this.initial.length; i++){
    //     initial += this.initial[i];
    //     if (i != this.initial.length - 1){
    //         initial += ";";
    //     }
    // }
    return this.Code + "|" + this.Name + "|" + this.Credit + "|" + this.PassingGrade; // + "|" + initial;
    }
}