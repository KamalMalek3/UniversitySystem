import java.util.Arrays;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Stages{
    
    private Person person;
    private SqlInteract sql= new SqlInteract();

    
    public void forgotPassworstage(){

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Password Reset!");
        
        Label idLabel = new Label("ID\t");
        TextField idField= new TextField();

        Label codeLabel = new Label("Verification Code");
        TextField codeField= new TextField();

        Label passwordLabel = new Label ("New Password ");
        PasswordField passwordField = new PasswordField();

        Label tryPasswordLabel = new Label ("re-Enter Password ");
        PasswordField tryPasswordField = new PasswordField();
        

        VBox labelbox = new VBox(4,idLabel,codeLabel,passwordLabel,tryPasswordLabel);
        labelbox.setAlignment(Pos.CENTER);
        labelbox.setSpacing(15);

        VBox fieldbox = new VBox(4,idField,codeField,passwordField,tryPasswordField);
        fieldbox.setAlignment(Pos.CENTER);
        fieldbox.setSpacing(10);
        
        HBox hroot = new HBox(4,labelbox,fieldbox);
        hroot.setAlignment(Pos.CENTER);

        Button ValidateButton = new Button("Validate!");        

        VBox root = new VBox(10,hroot,ValidateButton);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }
    public void studentStage(String name,String id)
    {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Student !");
        Text welcomeText = new Text("Welcome back, "+name+"!");
        Font font = Font.font("Calibri", 
        FontWeight.BOLD, FontPosture.REGULAR, 20);
        welcomeText.setFont(font);
        welcomeText.setFill(Color.MIDNIGHTBLUE);
        
        
        VBox root = new VBox(welcomeText);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    public void TeacherStage(String name,String id)
    {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Teacher !");
        Text welcomeText = new Text("Welcome back, "+name+"!");
        Font font = Font.font("Calibri", 
        FontWeight.BOLD, FontPosture.REGULAR, 20);
        welcomeText.setFont(font);
        welcomeText.setFill(Color.MIDNIGHTBLUE);

        Text addCourse = new Text("Add Course");
        addCourse.setFill(Color.BLUEVIOLET);
        Text addGrades = new Text("Add Grade");
        addGrades.setFill(Color.BLUEVIOLET);

        addCourse.setOnMouseClicked(e->{
          AddCourse(id);
        });
        
        addCourse.setOnMouseEntered(e->{
            addCourse.setFill(Color.VIOLET);
        });
        
        addCourse.setOnMouseExited(e->{
            addCourse.setFill(Color.BLUE);
        });

        addGrades.setOnMouseClicked(e->{
          
        });
        
        addGrades.setOnMouseEntered(e->{
            addGrades.setFill(Color.VIOLET);
        });
        
        addGrades.setOnMouseExited(e->{
            addGrades.setFill(Color.BLUE);
        });
        
        
        VBox root = new VBox(welcomeText,addCourse,addGrades);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    public void AdminStage()
    {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Admin !");

        Text welcomeText = new Text("Welcome back");
        Font font = Font.font("Calibri", 
        FontWeight.BOLD, FontPosture.REGULAR, 20);
        welcomeText.setFont(font);
        welcomeText.setFill(Color.MIDNIGHTBLUE);

        Text addStudenText = new Text("Add Student");
        addStudenText.setFill(Color.BLUEVIOLET);
        Text addTeacherText = new Text("Add Teacher");
        addTeacherText.setFill(Color.BLUEVIOLET);

        addStudenText.setOnMouseClicked(e->{
           AddUserStage(true);
        });
        
        addStudenText.setOnMouseEntered(e->{
            addStudenText.setFill(Color.VIOLET);
        });
        
        addStudenText.setOnMouseExited(e->{
            addStudenText.setFill(Color.BLUE);
        });

        addTeacherText.setOnMouseClicked(e->{
           AddUserStage(false);
        });
        
        addTeacherText.setOnMouseEntered(e->{
            addTeacherText.setFill(Color.VIOLET);
        });
        
        addTeacherText.setOnMouseExited(e->{
            addTeacherText.setFill(Color.BLUE);
        });

        VBox root = new VBox(3,welcomeText,addStudenText,addTeacherText);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    public void AddUserStage(Boolean isStudent)
    {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Add User!");

        if (isStudent)
            person = new Student();
        else
            person = new Teacher();
   
        Label idLabel = new Label("ID: "+Integer.toString(person.getID()));
        
        Label fullNameLabel= new Label("FullName");
        Label passwordLabel = new Label("Password");
        Label rePasswordLabel = new Label("rePasswordLabel");

        TextField fullNameField = new TextField();
        PasswordField passwordField = new PasswordField();
        PasswordField rePasswordField = new PasswordField();

        Label descriptionLabel = new Label("");

        VBox labelBox = new VBox(3,fullNameLabel,passwordLabel,rePasswordLabel);
        labelBox.setAlignment(Pos.CENTER);
        labelBox.setSpacing(15);

        VBox fieldBox = new VBox(3,fullNameField,passwordField,rePasswordField);
        fieldBox.setAlignment(Pos.CENTER);
        fieldBox.setSpacing(10);

        HBox Vroot = new HBox(2,labelBox,fieldBox);
        Vroot.setAlignment(Pos.CENTER);
        Vroot.setSpacing(10);

        Button ValidateButton = new Button("Validate!"); 
        ValidateButton.setOnAction(e -> {
            String password = passwordField.getText();
            String rePassword = rePasswordField.getText();
            String Name = fullNameField.getText();
            String TabelName = (isStudent)? "students":"teachers";
        
            if (password.equals(rePassword) && !fullNameField.getText().isEmpty()) {
                sql.perform("Insert into "+TabelName+" Values ("+person.getID()+",\""+Name+"\",\""+password+"\")");
                person.updateId(isStudent);
                descriptionLabel.setTextFill(Color.GREEN);
                descriptionLabel.setText("Validated!");
                passwordField.clear();
                fullNameField.clear();
                rePasswordField.clear();
                idLabel.setText("ID: "+Integer.toString(person.getID()));
            } else if (fullNameField.getText().isEmpty()) {
                descriptionLabel.setTextFill(Color.RED);
                descriptionLabel.setText("Empty name!");
            }else{
                descriptionLabel.setTextFill(Color.RED);
                descriptionLabel.setText("password do not match!");
            }
        });

        VBox root = new VBox(3,idLabel,Vroot,ValidateButton,descriptionLabel);

        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    public void AddCourse(String Teacher_id)
    {
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
        HBox creditsBox = new HBox(5,creditsLabel, credit0, credit1, credit2, credit3);
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
                Popup popup = new Popup();
                popup.showError("Error while Saving course");
            }
            }
        });
        

        VBox root = new VBox(3, vRoot, creditsBox, validateButton, descriptionLabel);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        primaryStage.setScene(new Scene(root, 400, 300));
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
        Label GradeLabel = new Label("Grade");
        Label creditsLabel = new Label("Credits");
        Label TotalgradeLabel = new Label("Total Grade");

        TextField courseNameField = new TextField();
        courseNameField.setEditable(false);
        TextField GradeField = new TextField();
        TextField creditField = new TextField();
        creditField.setEditable(false);
        TextField TotalGradeField = new TextField();
        TotalGradeField.setEditable(false);

        ComboBox<String> studentsCodeBox = new ComboBox<>();
        studentsCodeBox.setMaxWidth(200);
        studentsCodeBox.setDisable(true);

        String[] courses = sql.fill("Select Course_code from course where Teacher_id="+Teacher_id) ;
        ComboBox<String> courseCodeBox = new ComboBox<>();
        courseCodeBox.getItems().addAll(courses);
        courseCodeBox.setValue("");
        courseCodeBox.setMaxWidth(200);

        Button validateButton = new Button("Validate!");
        validateButton.setDisable(true);

        courseCodeBox.setOnAction(e -> {
            String CourseName =  sql.fill("Select Course_Name from course where Course_code =\""+courseCodeBox.getValue()+"\"")[0];
            courseNameField.setText(CourseName);
            studentsCodeBox.setDisable(false);
            studentsCodeBox.getItems().clear();

            String[] students = sql.fill("Select Student_id from enrollments where Course_code =\""+courseCodeBox.getValue()+"\"");
            
            studentsCodeBox.getItems().addAll(Arrays.asList(students));
            creditField.setText(sql.fill("Select credits from course where course_code=\""+courseCodeBox.getValue()+"\"")[0]);
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
        });

        validateButton.setOnAction(e -> {
            String courseCode = courseCodeBox.getValue();
            String student = studentsCodeBox.getValue();
            String grade = GradeField.getText();

            Grade gradeG = new Grade(courseCode, student, Integer.parseInt(grade));
            gradeG.saveGrade();
        });

        VBox labelBox = new VBox(6, courseCodeLabel, courseNameLabel, studentLabel, GradeLabel, creditsLabel, TotalgradeLabel);
        labelBox.setAlignment(Pos.CENTER);
        labelBox.setSpacing(20);

        VBox fieldBox = new VBox(6, courseCodeBox, courseNameField, studentsCodeBox, GradeField, creditField, TotalGradeField);
        fieldBox.setAlignment(Pos.CENTER);
        fieldBox.setSpacing(10);

        HBox vRoot = new HBox(2, labelBox, fieldBox);
        vRoot.setAlignment(Pos.CENTER);
        vRoot.setSpacing(10);

        VBox root = new VBox(3, vRoot, validateButton, descriptionLabel);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }


}