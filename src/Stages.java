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
    
   // private Popup popup = new Popup();

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
    public void studentStage(String name)
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
    public void TeacherStage(String name)
    {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Teacher !");
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
    public void AdminStage(String name)
    {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Admin !");

        Text welcomeText = new Text("Welcome back, "+name+"!");
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

        Person person;

        if (isStudent)
            person = new Student();
        else
            person = new Teacher();

        int id = person.getID();
        
        Label idLabel = new Label("ID: "+Integer.toString(id));
        
        Label fullNameLabel= new Label("FullName");
        Label passwordLabel = new Label("Password");
        Label rePasswordLabel = new Label("rePasswordLabel");

        TextField fullNameField = new TextField();
        PasswordField passwordField = new PasswordField();
        PasswordField rePasswordField = new PasswordField();

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

        VBox root = new VBox(3,idLabel,Vroot,ValidateButton);

        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

}