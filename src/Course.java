public class Course {
    private int credits;
    private String Course_code,Course_name;
    private String Teacher_id;

    public Course(int credits, String course_code, String course_name, String teacher_id) {
        this.credits = credits;
        Course_code = course_code;
        Course_name = course_name;
        Teacher_id = teacher_id;
    }

    public Course(String teacher_id) {
        Teacher_id = teacher_id;
    }
    
    public int getCredits() {
        return credits;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }
    public String getCourse_code() {
        return Course_code;
    }
    public void setCourse_code(String course_code) {
        Course_code = course_code;
    }
    public String getCourse_name() {
        return Course_name;
    }
    public void setCourse_name(String course_name) {
        Course_name = course_name;
    }
    public String getTeacher_id() {
        return Teacher_id;
    }
    public void setTeacher_id(String teacher_id) {
        Teacher_id = teacher_id;
    };

    private static SqlInteract sql = new SqlInteract();
    
    public void saveCourse(){
            String query = "Insert into course values (\""+this.Course_code+"\",\""+this.Course_name+"\","+this.credits+","+this.Teacher_id+")";
            sql.perform(query);
    }

}