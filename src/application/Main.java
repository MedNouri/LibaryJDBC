package application;

import application.controller.HomeController;
import application.controller.MainController;
import application.controller.Search;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
    private Group root = new Group();
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Projet Meryem");
        stage.setScene(new Scene(createContent()));
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();
    }

    public Parent createContent() {
        HomeVC();
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }



    public void HomeVC() {
        try {
            HomeController login = (HomeController) replaceSceneContent("/view/HomePane.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void goToSearch() {
        try {
            Search search = (Search) replaceSceneContent("/view/SearchPane.fxml");
            search.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void gotoMain() {
        try {
            MainController mainController = (MainController) replaceSceneContent("/view/MainPane.fxml");
            mainController.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        root.getChildren().clear();
        root.getChildren().addAll(page);
        return (Initializable) loader.getController();
    }
}
