package academic.driver;

/**
 * @author 12S22006 Felice Sirait
 * @author 12S22018 Jesica Siburian
 */

 import java.util.*;
 import academic.model.*;

public class Driver1 {

    public static void main(String[] _args) {
        Scanner input = new Scanner(System.in);
        String str;

        ArrayList<Course> courses = new ArrayList<Course>();
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
        ArrayList<Lecturer> lecturers = new ArrayList<Lecturer>();
        ArrayList<CourseOpening> courseOpenings = new ArrayList<CourseOpening>();

        while(input.hasNextLine()){
            str = input.nextLine();
            if(str.equals("---")){
                for (
                    Lecturer lecturer : lecturers){
                    System.out.println(lecturer);
                }

                for (Course course : courses){
                    System.out.println(course);
                }
                
                for (Student student : students){
                    System.out.println(student);
                }

                for (
                    Enrollment enrollment : enrollments){
                    System.out.println(enrollment);
                }
                break;
            }
            else{
                String[] token = str.split("#");
                if (token[0].equals("course-add")){
                    String Name = token[1];
                    String Code = token[2];
                    int Credit = Integer.parseInt(token[3]);
                    String PassingGrade = token[4];
                    
                    Course newCourse = new Course(Code, Name, Credit, PassingGrade);
                    courses.add(newCourse);

                }else if (token[0].equals("course-open")){
                    //course-open#<course-code>#<academic-year>#<semester>#<lecturer-list>
                    //course-open#12S1101#2020/2021#odd#IUS
                    String code = token[1];
                    String academicYear = token[2];
                    String semester = token[3];
                    String[] lecturerList = token[4].split(",");

                    CourseOpening newCourseOpening = new CourseOpening(code, academicYear, semester, lecturerList);
                    courseOpenings.add(newCourseOpening);

                }else if (token[0].equals("course-history")){
                    String code = token[1];
                    
                    Collections.sort(courseOpenings, new Comparator<CourseOpening>() {
                        public int compare(CourseOpening c1, CourseOpening c2) {
                            if (c1.getSemester().equals(c2.getSemester())) {
                                return 0;
                            } else if (c1.getSemester().equals("odd")) {
                                return -1;
                            } else {
                                return 1;
                            }
                        }
                    });

                    //Menampilkan riwayat pelaksanaan mata kuliah
                    for (CourseOpening courseOpening : courseOpenings){
                        if (courseOpening.getCode().equals(code)){
                            for (Course c : courses){
                                for (String initial : courseOpening.getInitial()){
                                    for (Lecturer lecturer : lecturers){
                                        if (lecturer.getInitial().equals(initial)){
                                            //12S1101|Dasar Sistem Informasi|3|D|2020/2021|odd|IUS (iustisia.simbolon@del.ac.id)
                                            System.out.print( c +"|"+ courseOpening.getAcademicYear() +"|" +courseOpening.getSemester() +"|"+ lecturer.getInitial() + " (" + lecturer.getEmail() + ")");
                                            break;
                                        }
                                    }
                                }
                                System.out.println();
                            }
                            for (Enrollment enrollment : enrollments){
                                //print enrollment yang sesuai dengan courseOpening
                                //System.out.println(enrollment.getcode()+"---VS--"+ courseOpening.getCode() +"-----|------"+ enrollment.getAcademicYear()+"-----VS-----"+courseOpening.getAcademicYear() +"--------|-------" +enrollment.getSemester()+"--VS---"+courseOpening.getSemester());
                                if (enrollment.getcode().equals(courseOpening.getCode()) && enrollment.getAcademicYear().equals(courseOpening.getAcademicYear()) && enrollment.getSemester().equals(courseOpening.getSemester())){
                                    System.out.println(enrollment);
                                }
                            }
                        }
                    }

                }else if (token[0].equals("student-add")){
                    String Id = token[1];
                    String Name = token[2];
                    String Year = token[3];
                    String StudyProgram = token[4];

                    Student newStudent = new Student(Id, Name, Year, StudyProgram);
                    students.add(newStudent);

                }else if (token[0].equals("enrollment-add")){
                    String code = token[1];
                    String id = token[2];
                    String academicYear = token[3];
                    String semester = token [4];
                    String grade = "None";

                    Enrollment newEnrollment = new Enrollment(code, id, academicYear, semester, grade);
                    enrollments.add(newEnrollment);
                    
                }else if (token[0].equals ("lecturer-add")){
                    String id = token[1];
                    String name = token[2];
                    String initial = token[3];
                    String email = token[4];
                    String studyProgram = token[5];

                    //cek apakah initial dan email sudah ada
                    boolean isExist = false;
                    for (Lecturer lecturer : lecturers){
                        if (lecturer.getInitial().equals(initial) && lecturer.getEmail().equals(email)){
                            isExist = true;
                            break;
                        }
                    }

                    //jika initial dan email belum ada, maka buat lecturer baru
                    if (!isExist){
                        Lecturer newLecturer = new Lecturer(id, name, initial, email, studyProgram);
                        lecturers.add(newLecturer);
                    }
                }

                else if (token[0].equals ("enrollment-grade")){
                    String code = token[1];
                    String id = token[2];
                    String academicYear = token[3];
                    String semester = token [4];
                    String grade = token[5];

                    //cek apakah enrollment telah didaftarkan
                    boolean isExist = false;
                    for (Enrollment enrollment : enrollments){
                        if (enrollment.getcode().equals(token[1])){
                            isExist = true;
                            break;
                        }
                    }

                    //Jika enrollment telah didaftarkan, maka set grade baru
                    if (isExist){
                        for (Enrollment enrollment : enrollments){
                            if (enrollment.getcode().equals(code) && enrollment.getid().equals(id) && enrollment.getSemester().equals(semester) && enrollment.getgrade() == "None"){
                                if (enrollment.getAcademicYear().equals(academicYear) || enrollment.getAcademicYear().equalsIgnoreCase(academicYear)) {
                                    enrollment.setgrade(grade);
                                }
                            }
                        }
                    }
                 }
                 else if (token[0].equals("student-details")) {
                    String Id = token[1];

                    int Credit = 0; // sks
                    double Grade = 0.00;
                    double GPA = 0.00; // ipk

                    // List untuk menyimpan id mata kuliah yang sudah diproses
                    List<String> Crs = new ArrayList<>();
                    List<String> Grd = new ArrayList<>();

                    for (Enrollment enrollment : enrollments) {
                        if (enrollment.getid().equals(Id) && enrollment.getgrade() != "None"){
                            Crs.add(enrollment.getcode());
                            Grd.add(enrollment.getgrade());
                        }
                    }

                    // Menghapus duplikat dari daftar mata kuliah yang diambil
                    for (int i = 0; i < Crs.size(); i++) {
                        for ( int j = i + 1; j < Crs.size(); j++) {
                            if (Crs.get(i).equals(Crs.get(j))) {
                                Crs.remove(i);
                                Grd.remove(i);
                            }
                        }
                    }

                    for (int i = 0; i < Crs.size(); i++) {
                        for (int j = 0; j < courses.size(); j++) {
                            if (Crs.get(i).equals(courses.get(j).getCode())) {
                                Credit += courses.get(j).getCredit();
                                if(Grd.get(i).equals ("A")){
                                    Grade = 4 * courses.get(j).getCredit();
                                } else if (Grd.get(i).equals ("AB")){
                                    Grade = 3.5 * courses.get(j).getCredit();
                                } else if (Grd.get(i).equals ("B")){
                                    Grade = 3 * courses.get(j).getCredit();
                                } else if (Grd.get(i).equals ("BC")){
                                    Grade = 2.5 * courses.get(j).getCredit();
                                } else if (Grd.get(i).equals ("C")){
                                    Grade = 2 * courses.get(j).getCredit();
                                } else if (Grd.get(i).equals ("D")){
                                    Grade = 1 * courses.get(j).getCredit();
                                } else if (Grd.get(i).equals ("E")){
                                    Grade = 0 * courses.get(j).getCredit();
                                }
                                GPA += Grade;
                            }
                        }
                    }

                    for (Student student : students){
                        if (student.getId().equals(Id)){
                            if (Credit == 0){
                            System.out.println(student + "|" + String.format("%.2f", GPA) + "|" + Credit);
                            } else {
                            System.out.println(student + "|" + String.format("%.2f", GPA / Credit) + "|" + Credit);
                            }
                        }
                    }

                }
                else if (token[0].equals("enrollment-remedial")) {
                    String code = token[1];
                    String id = token[2];
                    String academicYear = token[3];
                    String semester = token[4];
                    String grade = token[5];
                
                    // Mahasiswa yang pernah mendapat grade dapat mengambil remedial
                    for (Enrollment enrollment : enrollments) {
                        if (enrollment.getcode().equals(code) && enrollment.getid().equals(id) && enrollment.getAcademicYear().equals(academicYear) && enrollment.getSemester().equals(semester) && enrollment.getgrade() != "None") {
                            if(enrollment.getPreviousGrade() == null){
                                // Simpan grade sebelumnya
                                    String previousGrade = enrollment.getgrade();
        
                                    // Set previousGrade dengan nilai grade sebelumnya
                                    enrollment.setPreviousGrade(previousGrade);

                                    // Set grade baru
                                    enrollment.setgrade(grade);
                            }
                        }
                    }
                }

                //find-the-best-student#<academic-year>#<semester>
                else if (token[0].equals("find-the-best-student")){
                    String academicYear = token[1];
                    String semester = token[2];

                    List<String> bestStudent = new ArrayList<>();

                    double maxGPA = 0.0;

                    for ( Student student : students){
                        double GPA = 0.0;
                        int Credit = 0;
                        
                        for (Enrollment enrollment : enrollments){
                            if (enrollment.getid().equals(student.getId()) && enrollment.getAcademicYear().equals(academicYear) && enrollment.getSemester().equals(semester) && enrollment.getgrade() != "None"){
                                for (Course course : courses){
                                    if (enrollment.getcode().equals(course.getCode())){
                                        Credit += course.getCredit();

                                        if (enrollment.getgrade().equals("A")){
                                            GPA = 4 * course.getCredit();
                                        } else if (enrollment.getgrade().equals("AB")){
                                            GPA = 3.5 * course.getCredit();
                                        } else if (enrollment.getgrade().equals("B")){
                                            GPA = 3 * course.getCredit();
                                        } else if (enrollment.getgrade().equals("BC")){
                                            GPA = 2.5 * course.getCredit();
                                        } else if (enrollment.getgrade().equals("C")){
                                            GPA = 2 * course.getCredit();
                                        } else if (enrollment.getgrade().equals("D")){
                                            GPA = 1 * course.getCredit();
                                        } else if (enrollment.getgrade().equals("E")){
                                            GPA = 0 * course.getCredit();
                                        }

                                    }
                                }
                            }
                        }

                        if ( Credit != 0) {
                            GPA = GPA/Credit;
                            if(GPA > maxGPA){
                                maxGPA = GPA;
                                bestStudent.clear();
                                bestStudent.add(student.getId());

                            } else if(GPA == maxGPA){
                                bestStudent.add(student.getId());
                            }
                        }
                    }
                    //12S20002|B/A

                    for (String id : bestStudent){
                        System.out.print(id + "|");
                        for (Enrollment enrollment : enrollments){
                            if (enrollment.getid().equals(id) && enrollment.getAcademicYear().equals(academicYear) && enrollment.getSemester().equals(semester) && enrollment.getgrade() != "None"){
                                for (Course course : courses){
                                    if (enrollment.getcode().equals(course.getCode())){
                                        System.out.print(enrollment.getgrade() + "/");
                                    }
                                }
                            }
                        }
                        System.out.println();
                        
                    }

                    
                }
            }
        }
    }
}