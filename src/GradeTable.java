public class GradeTable {
    private String Course_code;
    private String Course_Name;
    private int grade;
    private int credits;
    private int totalGrade;

    public GradeTable(String course_code, String course_Name, int grade, int credits, int totalGrade) {
        Course_code = course_code;
        Course_Name = course_Name;
        this.grade = grade;
        this.credits = credits;
        this.totalGrade = totalGrade;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(int totalGrade) {
        this.totalGrade = totalGrade;
    }

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
