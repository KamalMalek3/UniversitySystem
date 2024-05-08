import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
        TextField idField = new TextField();

        Label passwordLabel = new Label("Password ");
        PasswordField passwordField = new PasswordField();

        Label descriptionLabel = new Label("");
        descriptionLabel.setTextFill(Color.RED);

        VBox labelbox = new VBox(2, idLabel, passwordLabel);
        labelbox.setAlignment(Pos.CENTER);
        labelbox.setSpacing(10);

        VBox fieldbox = new VBox(2, idField, passwordField);
        fieldbox.setAlignment(Pos.CENTER);
        fieldbox.setSpacing(10);

        String[] personArray = { "Student", "Teacher", "Admin" };
        ComboBox<String> combo_box = new ComboBox<String>(FXCollections.observableArrayList(personArray));
        combo_box.setValue(personArray[0]);
        combo_box.setMaxWidth(220);
        // todo

        HBox hroot = new HBox(3, labelbox, fieldbox);
        hroot.setAlignment(Pos.CENTER);

        Button signInButton = new Button("Sign in!");

        signInButton.setOnAction(e -> {
            String person = combo_box.getValue();
            String user = idField.getText();
            String pass = passwordField.getText();
            String name;

            boolean isSignedIn = Methods.canSignIn(user, pass, person);

            if (isSignedIn) {
                idField.clear();
                passwordField.clear();
                descriptionLabel.setText("");
                switch (person) {
                    case "Admin":
                        stg.AdminStage();
                        break;
                    case "Student":
                        name = (Methods.getNameString(true, user));
                        stg.studentStage(name, user);
                        break;
                    case "Teacher":
                        name = (Methods.getNameString(false, user));
                        stg.TeacherStage(name, user);
                        break;
                    default:
                        break;
                }
            } else {
                descriptionLabel.setText("Incorrect ID or Password! :(");
            }
        });

        Label forgotPasswordText = new Label("Forgot Password?");
        forgotPasswordText.setTextFill(Color.BLUE);

        forgotPasswordText.setOnMouseClicked(e -> {
            stg.forgotPasswordStage();
        });

        forgotPasswordText.setOnMouseEntered(e -> {
            forgotPasswordText.setTextFill(Color.VIOLET);
        });

        forgotPasswordText.setOnMouseExited(e -> {
            forgotPasswordText.setTextFill(Color.BLUE);
        });

        idField.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                passwordField.requestFocus();
            }
        });

        passwordField.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                signInButton.fire();
            }
        });

        VBox root = new VBox(10, imageView, hroot, combo_box, signInButton, forgotPasswordText, descriptionLabel);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        Image backgroundImage = new Image("file:Images\\graduation1.jpg");
        root.setBackground(new Background(new BackgroundFill(new ImagePattern(backgroundImage), null, null)));

        primaryStage.setTitle("Sign in !");
        Image icon = new Image("file:Images/logo2.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}