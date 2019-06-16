package application.controller;

import application.DaoImplementation.BooksDAOImpl;
import application.MODEL.Auther;
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





public class MainController implements Initializable {
    private Main application;
    private static BooksDAOImpl booksDAO = new BooksDAOImpl();

    @FXML
    private TextField idField;
    
    @FXML
    private TextField titleField;

    @FXML
    private TextField authorField;
    @FXML
    private TextField authoriDFiled;
    @FXML
    private TextField yearField;

    @FXML
    private TextField pagesField;

    @FXML
    private Button insertButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<Books> TableView;
    
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

    @FXML
    private void insertButton() {
        String id = idField.getText();
        String title = titleField.getText();
        String authername = authorField.getText();
        String year = yearField.getText();
        String pages = pagesField.getText();


        Books newbook = new Books(Integer.parseInt(id),title,authername,Integer.parseInt(year),Integer.parseInt(pages));
        booksDAO.create(newbook);

        showBooks();
    }
    
    
    @FXML 
    private void updateButton() {



        String id = idField.getText();
        String title = titleField.getText();
        String auther = authorField.getText();
        String year = yearField.getText();
        String pages = pagesField.getText();

              Books newbookUpdate = new Books(Integer.parseInt(id), title, auther, Integer.parseInt(year), Integer.parseInt(pages));
              booksDAO.update(newbookUpdate);

              showBooks();

    }
    
    @FXML
    private void deleteButton() {

        String id = idField.getText();
       if (booksDAO.delete(Integer.parseInt(id))!= 0) {
            showBooks();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	showBooks();
    }
    

    
    public ObservableList<Books> getBooksList(){
    	ObservableList<Books> booksList = FXCollections.observableArrayList();
        List<Books> booksLIST = booksDAO.getAll();
			booksLIST.forEach(rs -> booksList.add(rs));
    	       return booksList;
    }

    public void setApp(Main application) {
        this.application = application;
    }

    public void showBooks() {
    	ObservableList<Books> list = getBooksList();

        idColumn.setCellValueFactory(new PropertyValueFactory<Books,Integer>("id"));
    	titleColumn.setCellValueFactory(new PropertyValueFactory<Books,String>("title"));
    	authorColumn.setCellValueFactory(new PropertyValueFactory<Books,String>("author"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Books,String>("author"));
    	yearColumn.setCellValueFactory(new PropertyValueFactory<Books,Integer>("year"));
    	pagesColumn.setCellValueFactory(new PropertyValueFactory<Books,Integer>("pages"));

    	TableView.setItems(list);
    }

}
