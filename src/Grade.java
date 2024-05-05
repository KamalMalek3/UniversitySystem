public class Grade {
    private String Course_code;
    private String student_id;
    private int grade;

    public Grade(String course_code, String student_id, int grade) {
        Course_code = course_code;
        this.student_id = student_id;
        this.grade = grade;
    }

    private SqlInteract sql = new SqlInteract();

    public String getCourse_code() {
        return Course_code;
    }

    public void setCourse_code(String course_code) {
        Course_code = course_code;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void saveGrade() {

        try {
            String query = "Insert into grades (student_id, course_code, grade) values (" + this.student_id + ",\""
                    + this.Course_code + "\"," + this.grade + ")";
            if (sql.perform(query) != 1) {
                throw new Exception();
            }
        } catch (Exception E) {
            Popup pop = new Popup();
            pop.showError("Error While Saving Grade, Grade my for student may alraedy exist");
        }
    }
}
