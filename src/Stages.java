import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class Stages{
    private SecondaryStages secondaryStages = new SecondaryStages();

    public void forgotPasswordStage() 
    {
        SqlInteract sql = new SqlInteract();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Password Reset!");
    
        Label idLabel = new Label("ID:");
        TextField idField = new TextField();
    
        Label codeLabel = new Label("Verification Code:");
        TextField codeField = new TextField();
    
        Label passwordLabel = new Label("New Password:");
        PasswordField passwordField = new PasswordField();
    
        Label tryPasswordLabel = new Label("Re-Enter Password:");
        PasswordField tryPasswordField = new PasswordField();

        Label descriptionLabel = new Label("");
        descriptionLabel.setTextFill(Color.RED);
    
        String[] personArray = {"Student", "Teacher"};
        ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList(personArray));
        comboBox.setValue(personArray[0]);
        comboBox.setMaxWidth(250);
    
        VBox labelBox = new VBox(4, idLabel, codeLabel, passwordLabel, tryPasswordLabel);
        labelBox.setAlignment(Pos.CENTER);
        labelBox.setSpacing(15);
    
        VBox fieldBox = new VBox(4, idField, codeField, passwordField, tryPasswordField);
        fieldBox.setAlignment(Pos.CENTER);
        fieldBox.setSpacing(10);
    
        HBox hRoot = new HBox(4, labelBox, fieldBox);
        hRoot.setAlignment(Pos.CENTER);
    
        Button validateButton = new Button("Validate");
    
        VBox root = new VBox(10, comboBox, hRoot, validateButton,descriptionLabel);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
    
        validateButton.setOnAction(e -> {
            if (!passwordField.getText().equals(tryPasswordField.getText())){
                descriptionLabel.setText("Passwords don't Match");
            }else if (passwordField.getText().isBlank() || codeField.getText().isBlank() || idField.getText().isBlank()) {
                descriptionLabel.setText("Please fill all the fields");
            }else{
                String TableName = comboBox.getValue() + "s";
                String idname = (comboBox.getValue().equals("Teacher") ? "Teacher_id" : "Student_id") ;
                String passname = (comboBox.getValue().equals("Teacher") ? "Teacher_password" : "Student_password") ;
                String CheckQuery = "SELECT " + idname+ " From "+TableName+" Where "+idname+"="+idField.getText();
                if (sql.fill(CheckQuery).length == 0){
                    descriptionLabel.setText("ID doesn't Exists");
                    idField.clear();
                    codeField.clear(); 
                    passwordField.clear();
                    tryPasswordField.clear();
                }else{
                    String query = "Update "+TableName+" Set "+passname+"="+tryPasswordField.getText()+" Where "+idname+"="+idField.getText();
                    if (sql.perform(query)==1){
                        descriptionLabel.setTextFill(Color.GREEN);
                        descriptionLabel.setText("Password changed Successfull!");

                    }else{
                        descriptionLabel.setText("Error! Password not updated");
                    }
                }

            }
           
        });
    
        primaryStage.setScene(new Scene(root, 400, 300));
        Image icon = new Image("file:Images/logo2.png");
        primaryStage.getIcons().add(icon);
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

        Text addDropCourse = new Text("Add/drop Course");
        addDropCourse.setFill(Color.BLUEVIOLET);
        Text viewGrades = new Text("View Grade");
        viewGrades.setFill(Color.BLUEVIOLET);
        
        
        addDropCourse.setOnMouseClicked(e->{
            secondaryStages.addDrop(id);
        });
        
        addDropCourse.setOnMouseEntered(e->{
            addDropCourse.setFill(Color.VIOLET);
        });
        
        addDropCourse.setOnMouseExited(e->{
            addDropCourse.setFill(Color.BLUE);
        });

        viewGrades.setOnMouseClicked(e->{
           secondaryStages.viewGrades(id);
        });
        
        viewGrades.setOnMouseEntered(e->{
            viewGrades.setFill(Color.VIOLET);
        });
        
        viewGrades.setOnMouseExited(e->{
            viewGrades.setFill(Color.BLUE);
        });
        
        
        VBox root = new VBox(welcomeText,addDropCourse,viewGrades);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        primaryStage.setScene(new Scene(root, 400, 300));
        Image icon = new Image("file:Images/logo2.png");
        primaryStage.getIcons().add(icon);
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
            secondaryStages.AddCourse(id);
        });
        
        addCourse.setOnMouseEntered(e->{
            addCourse.setFill(Color.VIOLET);
        });
        
        addCourse.setOnMouseExited(e->{
            addCourse.setFill(Color.BLUE);
        });

        addGrades.setOnMouseClicked(e->{
            secondaryStages.addGrades(id);
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
        Image icon = new Image("file:Images/logo2.png");
        primaryStage.getIcons().add(icon);
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
            secondaryStages.AddUserStage(true);
        });
        
        addStudenText.setOnMouseEntered(e->{
            addStudenText.setFill(Color.VIOLET);
        });
        
        addStudenText.setOnMouseExited(e->{
            addStudenText.setFill(Color.BLUE);
        });

        addTeacherText.setOnMouseClicked(e->{
            secondaryStages.AddUserStage(false);
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
        Image icon = new Image("file:Images/logo2.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();

    }
}