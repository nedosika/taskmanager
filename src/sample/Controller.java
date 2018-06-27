package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
    @FXML
    private TextField txtLogin;

    @FXML
    private Button btnSignUp;

    @FXML
    private Button btnEnter;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void initialize() {

    }



    public void signUpAction(ActionEvent actionEvent) {
        btnSignUp.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/signUp.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public void onEnterAction(ActionEvent actionEvent) {
        String loginText = txtLogin.getText().trim();
        String loginPassword = txtPassword.getText().trim();

        if(!loginText.equals("") && !loginPassword.equals("")){
            loginUser(loginText, loginPassword);
        }
        else {
            System.out.println("no login/password");
        }
    }

    private void loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(loginPassword);

        ResultSet resultSet = dbHandler.getUser(user);

        int counter = 0;
        try {
            while (resultSet.next()) {
                counter++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        if (counter >= 1){
            System.out.println("Login");
        }
    }
}
