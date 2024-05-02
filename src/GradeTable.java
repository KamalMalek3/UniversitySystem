public class GradeTable {
    private String Course_code;
    private String Course_Name;
    private int grade;
    public String getCourse_code() {
        return Course_code;
    }
    public void setCourse_code(String course_code) {
        Course_code = course_code;
    }
    public String getCourse_Name() {
        return Course_Name;
    }
    public void setCourse_Name(String course_Name) {
        Course_Name = course_Name;
    }
    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public GradeTable(String course_code, String course_Name, int grade) {
        Course_code = course_code;
        Course_Name = course_Name;
        this.grade = grade;
    }
    
}
