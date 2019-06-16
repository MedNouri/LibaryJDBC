package application.controller;

import application.DaoImplementation.BooksDAOImpl;
import application.MODEL.Books;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Search implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void setApp(Main application) {
        this.application = application;
    }

    @FXML
    private javafx.scene.control.TableView<Books> TableView;

    @FXML
    private TableColumn<Books, Integer> idColumn;

    @FXML
    private TableColumn<Books, String> titleColumn;

    @FXML
    private TableColumn<Books, String> authorColumn;

    @FXML
    private TableColumn<Books, Integer> yearColumn;

    @FXML
    private TableColumn<Books, Integer> pagesColumn;

    private static BooksDAOImpl booksDAO = new BooksDAOImpl();

    @FXML
    private TextField searchTextFiled;

    @FXML
    private Button searchButton;


    private Main application;

    public void searchAction() {
        ObservableList<Books> list = showBooks() ;

        idColumn.setCellValueFactory(new PropertyValueFactory<Books,Integer>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Books,String>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Books,String>("author"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Books,Integer>("year"));
        pagesColumn.setCellValueFactory(new PropertyValueFactory<Books,Integer>("pages"));

        TableView.setItems(list);
    }

    public ObservableList<Books> showBooks(){
        String name = searchTextFiled.getText();
        ObservableList<Books> booksList = FXCollections.observableArrayList();
        Books returnedBook = booksDAO.find(name);
        if (returnedBook != null) {

            booksList.add(returnedBook);
System.out.println(returnedBook);
        }
        return booksList;
    }



}
