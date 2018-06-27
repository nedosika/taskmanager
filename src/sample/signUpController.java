package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.xml.crypto.Data;


public class signUpController {
    @FXML
    public RadioButton rbtnFemale;

    @FXML
    public RadioButton rbtnMale;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtLogin;

    @FXML
    private Button btnEnter;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtRepeatPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private Label labelError;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPhone;


    @FXML
    public void onEnterAction(ActionEvent actionEvent) {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String login = txtLogin.getText();
        String password = txtPassword.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String gender = "";

        if(rbtnFemale.isSelected()){
            gender = "Женский";
        }
        else gender = "Мужской";

        User user = new User(firstName, lastName, login, password, email, phone, gender);

        dbHandler.signUpUser(user);

    }
}
