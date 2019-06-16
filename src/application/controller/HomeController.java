package application.controller;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable  {
    private Scene secondScene;
    private Main application;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setApp(Main application) {
        this.application = application;
    }

    public void setSecondScene(Scene scene) {
        secondScene = scene;
    }

    @FXML
    private void RecherchButton() {
        application.goToSearch();
    }
    private Stage primaryStage;




    @FXML
    private void MainAppButton() {
     application.gotoMain();
    }







}
