import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.collections.FXCollections;

public class App extends Application {

    private Stages stg = new Stages();

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ImageView imageView = new ImageView(new Image("file:Images/logo1.png"));


        Label idLabel = new Label("ID\t");
        TextField idField= new TextField();

        Label passwordLabel = new Label ("Password ");
        PasswordField passwordField = new PasswordField();
        

        VBox labelbox = new VBox(2,idLabel,passwordLabel);
        labelbox.setAlignment(Pos.CENTER);
        labelbox.setSpacing(10);

        VBox fieldbox = new VBox(2,idField,passwordField);
        fieldbox.setAlignment(Pos.CENTER);
        fieldbox.setSpacing(10);
        
        String[] personArray = {"Student","Teacher","Admin"};
        ComboBox<String> combo_box = new ComboBox <String>(FXCollections.observableArrayList(personArray));
        combo_box.setValue(personArray[0]);
        combo_box.setMaxWidth(220);
        
        HBox hroot = new HBox(3,labelbox,fieldbox);
        hroot.setAlignment(Pos.CENTER);

        Button signInButton = new Button("Sign in!");

        signInButton.setOnAction(e->{
            String person = combo_box.getValue();
            String name = "test";
            boolean isSignedIn = true;
            if (isSignedIn)
            {
                switch (person) {
                    case "Admin":
                        stg.AdminStage(name);
                        break;
                    case "Student":
                        stg.studentStage(name);
                        break;
                    case "Teacher":
                        stg.TeacherStage(name);
                        break;
                    default:
                        break;
                }
            }
        });


        Label forgotPasswordText = new Label("Forgot Password?");
        forgotPasswordText.setTextFill(Color.BLUE);

        forgotPasswordText.setOnMouseClicked(e->{
            stg.forgotPassworstage();
        });
        
        forgotPasswordText.setOnMouseEntered(e->{
            forgotPasswordText.setTextFill(Color.VIOLET);
        });
        
        forgotPasswordText.setOnMouseExited(e->{
            forgotPasswordText.setTextFill(Color.BLUE);
        });
        
        VBox root = new VBox(10,imageView,hroot,combo_box,signInButton,forgotPasswordText);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        Image backgroundImage = new Image("file:Images\\graduation1.jpg");
        root.setBackground(new Background(new BackgroundFill(new ImagePattern(backgroundImage), null, null)));

        primaryStage.setTitle("Sign in !");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}