import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SecondaryStages {

    public SecondaryStages() {

    }

    private Person person;
    private SqlInteract sql = new SqlInteract();
    private Popup popup = new Popup();

    public void AddUserStage(Boolean isStudent) {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Add User!");

        if (isStudent)
            person = new Student();
        else
            person = new Teacher();

        Label idLabel = new Label("ID: " + Integer.toString(person.getID()));

        Label fullNameLabel = new Label("FullName");
        Label passwordLabel = new Label("Password");
        Label rePasswordLabel = new Label("rePasswordLabel");

        TextField fullNameField = new TextField();
        PasswordField passwordField = new PasswordField();
        PasswordField rePasswordField = new PasswordField();

        Label descriptionLabel = new Label("");

        VBox labelBox = new VBox(3, fullNameLabel, passwordLabel, rePasswordLabel);
        labelBox.setAlignment(Pos.CENTER);
        labelBox.setSpacing(15);

        VBox fieldBox = new VBox(3, fullNameField, passwordField, rePasswordField);
        fieldBox.setAlignment(Pos.CENTER);
        fieldBox.setSpacing(10);

        HBox Vroot = new HBox(2, labelBox, fieldBox);
        Vroot.setAlignment(Pos.CENTER);
        Vroot.setSpacing(10);

        Button ValidateButton = new Button("Validate!");
        ValidateButton.setOnAction(e -> {
            String password = passwordField.getText();
            String rePassword = rePasswordField.getText();
            String Name = fullNameField.getText();
            String TabelName = (isStudent) ? "students" : "teachers";

            if (password.equals(rePassword) && !fullNameField.getText().isEmpty() && !password.isEmpty()) {

                if (sql.perform("Insert into " + TabelName + " Values (" + person.getID() + ",\"" + Name + "\",\""
                        + password + "\")") == 1) {
                    person.updateId(isStudent);
                    descriptionLabel.setTextFill(Color.GREEN);
                    descriptionLabel.setText("Validated!");
                    passwordField.clear();
                    fullNameField.clear();
                    rePasswordField.clear();
                    idLabel.setText("ID: " + Integer.toString(person.getID()));
                } else {
                    popup.showError("Person May alraedy exist");
                    descriptionLabel.setTextFill(Color.RED);
                    descriptionLabel.setText("ID already exists!");
                }

            } else if (fullNameField.getText().isEmpty() || password.isEmpty()) {
                descriptionLabel.setTextFill(Color.RED);
                descriptionLabel.setText("one or more Empty Fields!");
            } else {
                descriptionLabel.setTextFill(Color.RED);
                descriptionLabel.setText("password do not match!");
            }
        });

        VBox root = new VBox(3, idLabel, Vroot, ValidateButton, descriptionLabel);

        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        Image icon = new Image("file:Images/logo2.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    public void AddCourse(String Teacher_id) {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Add Course!");

        Label codeLabel = new Label("Course Code");
        Label nameLabel = new Label("Course Name");
        Label creditsLabel = new Label("Credits:\t");

        TextField nameField = new TextField();
        TextField codeField = new TextField();

        Label descriptionLabel = new Label("");

        VBox labelBox = new VBox(3, nameLabel, codeLabel);
        labelBox.setAlignment(Pos.CENTER);
        labelBox.setSpacing(15);

        VBox fieldBox = new VBox(3, nameField, codeField);
        fieldBox.setAlignment(Pos.CENTER);
        fieldBox.setSpacing(10);

        // Create a ToggleGroup for the radio buttons
        ToggleGroup toggleGroup = new ToggleGroup();

        // Create radio buttons for each credit option
        RadioButton credit0 = new RadioButton("0");
        RadioButton credit1 = new RadioButton("1");
        RadioButton credit2 = new RadioButton("2");
        RadioButton credit3 = new RadioButton("3");

        // Add radio buttons to the ToggleGroup
        credit0.setToggleGroup(toggleGroup);
        credit1.setToggleGroup(toggleGroup);
        credit2.setToggleGroup(toggleGroup);
        credit3.setToggleGroup(toggleGroup);

        // Set initial selection
        credit0.setSelected(true);

        // Create a VBox to hold the radio buttons
        HBox creditsBox = new HBox(5, creditsLabel, credit0, credit1, credit2, credit3);
        creditsBox.setAlignment(Pos.CENTER);
        creditsBox.setPadding(new Insets(10));
        creditsBox.setSpacing(5);

        HBox vRoot = new HBox(2, labelBox, fieldBox);
        vRoot.setAlignment(Pos.CENTER);
        vRoot.setSpacing(10);

        Button validateButton = new Button("Validate!");

        validateButton.setOnAction(e -> {
            // Get user input from text fields
            String name = nameField.getText();
            String code = codeField.getText();

            // Get selected credit from the ToggleGroup
            RadioButton selectedCredit = (RadioButton) toggleGroup.getSelectedToggle();
            int credit = Integer.parseInt(selectedCredit.getText());

            // Check if any field is empty
            if (name.isEmpty() || code.isEmpty()) {
                descriptionLabel.setTextFill(Color.RED);
                descriptionLabel.setText("One or more fields are empty!");
            } else {

                try {
                    Course course = new Course(credit, code, name, Teacher_id);
                    course.saveCourse();
                    descriptionLabel.setTextFill(Color.GREEN);
                    descriptionLabel.setText("Saved!");
                } catch (Exception E) {
                    popup.showError("Error while Saving course");
                    descriptionLabel.setTextFill(Color.RED);
                    descriptionLabel.setText("Not Saved!");
                }
            }
        });

        VBox root = new VBox(3, vRoot, creditsBox, validateButton, descriptionLabel);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        primaryStage.setScene(new Scene(root, 400, 300));
        Image icon = new Image("file:Images/logo2.png");
        primaryStage.getIcons().add(icon);
        primaryStage.show();
    }

    private boolean isCourseSelected = false;
    private boolean isStudentSelected = false;
    private boolean isGradeValid = false;

    public void addGrades(String Teacher_id) {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Add grades!");

        Label courseCodeLabel = new Label("Course Code");
        Label courseNameLabel = new Label("Course Name");
        Label studentLabel = new Label("Student");
        Label studentNameLabel = new Label("Student Name");
        Label gradeLabel = new Label("Grade");
        Label creditsLabel = new Label("Credits");
        Label TotalgradLabel = new Label("Total Grade");

        TextField courseNameField = new TextField();
        courseNameField.setEditable(false);
        TextField GradeField = new TextField();
        TextField studentNameField = new TextField();
        studentNameField.setEditable(false);
        TextField creditField = new TextField();
        creditField.setEditable(false);
        TextField TotalGradeField = new TextField();
        TotalGradeField.setEditable(false);

        ComboBox<String> studentsCodeBox = new ComboBox<>();
        studentsCodeBox.setMaxWidth(200);
        studentsCodeBox.setDisable(true);

        String[] courses = sql.fill("Select Course_code from course where Teacher_id=" + Teacher_id);
        ComboBox<String> courseCodeBox = new ComboBox<>();
        courseCodeBox.getItems().addAll(courses);
        courseCodeBox.setValue("");
        courseCodeBox.setMaxWidth(200);

        Button validateButton = new Button("Validate!");
        validateButton.setDisable(true);

        courseCodeBox.setOnAction(e -> {
            String CourseName = sql
                    .fill("Select Course_Name from course where Course_code =\"" + courseCodeBox.getValue() + "\"")[0];
            courseNameField.setText(CourseName);
            studentsCodeBox.setDisable(false);
            studentsCodeBox.getItems().clear();

            String[] students = sql
                    .fill("Select Student_id from enrollments where Course_code =\"" + courseCodeBox.getValue() + "\"");

            studentsCodeBox.getItems().addAll(Arrays.asList(students));
            creditField.setText(
                    sql.fill("Select credits from course where course_code=\"" + courseCodeBox.getValue() + "\"")[0]);
            GradeField.setDisable(false);
            isCourseSelected = true;
            validateButton.setDisable(!(isCourseSelected && isStudentSelected && isGradeValid));
        });

        Label descriptionLabel = new Label("");

        GradeField.setOnKeyTyped(e -> {
            String str = GradeField.getText();
            if (!str.matches("\\d+")) {
                descriptionLabel.setText("The grade contains characters other than numbers.");
                descriptionLabel.setTextFill(Color.RED);
                isGradeValid = false;
            } else {
                int grade = Integer.parseInt(str);
                if (grade > 100 || grade < 0) {
                    descriptionLabel.setText("The grade is Invalid.");
                    descriptionLabel.setTextFill(Color.RED);
                    isGradeValid = false;
                } else {
                    descriptionLabel.setText("");
                    isGradeValid = true;
                    TotalGradeField.setText(String.valueOf(Integer.parseInt(creditField.getText()) * grade));
                }
            }
            validateButton.setDisable(!(isCourseSelected && isStudentSelected && isGradeValid));
        });

        studentsCodeBox.setOnAction(e -> {
            isStudentSelected = true;
            validateButton.setDisable(!(isCourseSelected && isStudentSelected && isGradeValid));
            studentNameField.setText(Methods.getNameString(true, studentsCodeBox.getValue()));
        });

        validateButton.setOnAction(e -> {
            String courseCode = courseCodeBox.getValue();
            String student = studentsCodeBox.getValue();
            String grade = GradeField.getText();

            Grade gradeG = new Grade(courseCode, student, Integer.parseInt(grade));
            gradeG.saveGrade();
        });

        VBox labelBox = new VBox(6, courseCodeLabel, courseNameLabel, studentLabel, studentNameLabel, gradeLabel,
                creditsLabel, TotalgradLabel);
        labelBox.setAlignment(Pos.CENTER);
        labelBox.setSpacing(20);

        VBox fieldBox = new VBox(6, courseCodeBox, courseNameField, studentsCodeBox, studentNameField, GradeField,
                creditField, TotalGradeField);
        fieldBox.setAlignment(Pos.CENTER);
        fieldBox.setSpacing(10);

        HBox vRoot = new HBox(2, labelBox, fieldBox);
        vRoot.setAlignment(Pos.CENTER);
        vRoot.setSpacing(10);

        VBox root = new VBox(3, vRoot, validateButton, descriptionLabel);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        Image icon = new Image("file:Images/logo2.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

    @SuppressWarnings("unchecked")
    public void viewGrades(String studentId) {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("View Grades");
        Image icon = new Image("file:Images/logo2.png");
        primaryStage.getIcons().add(icon);
        VBox root;

        String studentCheckQuery = "SELECT COUNT(*) FROM enrollments WHERE student_id = " + studentId;
        int enrollmentCount = Integer.parseInt(sql.fill(studentCheckQuery)[0]);
        if (enrollmentCount > 0) {
            TableView<GradeTable> table = new TableView<>();
            TableColumn<GradeTable, String> courseCodeColumn = new TableColumn<>("Course Code");
            TableColumn<GradeTable, String> courseNameColumn = new TableColumn<>("Course Name");
            TableColumn<GradeTable, Integer> gradeColumn = new TableColumn<>("Grade");
            TableColumn<GradeTable, String> creditsColumn = new TableColumn<>("Credits");
            TableColumn<GradeTable, Integer> totalgradeColumn = new TableColumn<>("Total Grade");
            courseCodeColumn.setCellValueFactory(new PropertyValueFactory<>("course_code"));
            courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("course_Name"));
            gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
            creditsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));
            totalgradeColumn.setCellValueFactory(new PropertyValueFactory<>("totalGrade"));
    
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    
            ObservableList<GradeTable> data = FXCollections.observableArrayList();
            
            String[] courseCodes = sql.fill("Select course_code from grades where student_id=" + studentId);
            String[] grades = sql.fill("Select grade from grades where student_id=" + studentId);
            int[] credits = new int[grades.length];
            int[] totalGrade = new int[grades.length];
            String[] courseNames = new String[courseCodes.length];
            int i = 0;
            for (String code : courseCodes) {
                credits[i] = Integer.parseInt(sql.fill("Select credits from course where course_code=\"" + code + "\"")[0]);
                totalGrade[i] = credits[i] * Integer.parseInt(grades[i]);
                courseNames[i++] = sql.fill("Select Course_name from course where course_code=\"" + code + "\"")[0];
            }
    
            String getTotalCreditsQuery = "SELECT SUM(c.credits) FROM enrollments e INNER JOIN course c ON e.course_code = c.Course_code INNER JOIN grades g ON e.Student_id = g.student_id AND e.course_code = g.course_code WHERE e.Student_id ="
                    + studentId + " GROUP BY e.Student_id";
    
            int totalGrades = 0;
            for (int grade : totalGrade) {
                totalGrades += grade;
            }
            int totalCredits = Integer.parseInt(sql.fill(getTotalCreditsQuery)[0]);
            double GPA = totalGrades / totalCredits * 1.00;
    
            for (i = 0; i < courseCodes.length; i++) {
                data.add(new GradeTable(courseCodes[i], courseNames[i], Integer.parseInt(grades[i]), credits[i],
                        totalGrade[i]));
            }
            table.setItems(data);
    
            table.getColumns().addAll(courseCodeColumn, courseNameColumn, gradeColumn, creditsColumn, totalgradeColumn);
    
            Label totalGradesLabel = new Label("totalGrades:\t" + totalGrades);
            Label GPALabel = new Label("GPA:\t" + GPA + "%");
            Label totalcreditsLabel = new Label("Total credits finishied:\t" + totalCredits);
    
            root = new VBox(10, table, totalGradesLabel, totalcreditsLabel, GPALabel);
        }else{
            root = new VBox(1,new Label("You are not enrolled in any courses yet"));
        }

        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    public void addDrop(String studentId) {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Course Manager");
        Image icon = new Image("file:Images/logo2.png");
        primaryStage.getIcons().add(icon);

        String[] enrolledCourses = sql.fill(
                "SELECT c.Course_Name FROM course c INNER JOIN enrollments e ON c.Course_code = e.course_code WHERE e.Student_id = "
                        + studentId);
        String[] availableCourses = sql.fill(
                "SELECT c.Course_Name FROM course c WHERE NOT EXISTS (SELECT 1 FROM enrollments e WHERE e.Student_id = "
                        + studentId + " AND e.course_code = c.Course_code)");
        // Create ListView for Enrolled Courses
        ListView<String> enrolledListView = new ListView<>();
        enrolledListView.getItems().addAll(enrolledCourses);

        ListView<String> availableListView = new ListView<>();
        availableListView.getItems().addAll(availableCourses);

        Button dropButton = new Button("Drop");
        dropButton.setOnAction(e -> {
            String selectedCourse = enrolledListView.getSelectionModel().getSelectedItem();
            if (selectedCourse != null) {
                enrolledListView.getItems().remove(selectedCourse);
                availableListView.getItems().add(selectedCourse);
            }
        });

        Button enrollButton = new Button("Enroll");
        enrollButton.setOnAction(e -> {
            String selectedCourse = availableListView.getSelectionModel().getSelectedItem();
            if (selectedCourse != null) {
                availableListView.getItems().remove(selectedCourse);
                enrolledListView.getItems().add(selectedCourse);
            }
        });

        Button saveButton = new Button("Save!");
        saveButton.setOnAction(event -> {
            Object[] courses = enrolledListView.getItems().toArray();
            if (sql.perform("DELETE FROM enrollments WHERE Student_id = " + studentId) == -1) {
                popup.showError("Error deleting existing enrollments!");
                return;
            }
            for (Object courseObj : courses) {
                String course = courseObj.toString();
                String insertEnrollmentQuery = "INSERT INTO enrollments (Student_id, course_code) " +
                        "SELECT " + studentId + ", c.Course_code " +
                        "FROM course c " +
                        "WHERE c.Course_Name = '" + course + "'";
                if (sql.perform(insertEnrollmentQuery) != 1) {
                    popup.showError("Error inserting enrollment for course: " + course);
                    return;
                }
            }
        });

        Button cancelButton = new Button("Cancel!");
        cancelButton.setOnAction(e -> {
            primaryStage.close();
        });

        HBox buttonHBox = new HBox(2, saveButton, cancelButton);
        buttonHBox.setSpacing(10);
        buttonHBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(enrolledListView, dropButton, availableListView, enrollButton, buttonHBox);
        root.setSpacing(10);
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }
}