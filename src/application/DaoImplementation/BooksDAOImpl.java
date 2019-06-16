package application.DaoImplementation;

import application.dao.DAO;
import application.MODEL.Books;
import application.singleton.ConnexionHandler;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class BooksDAOImpl implements DAO<Books> {

    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;


    @Override
    public boolean create(Books obj) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            con = ConnexionHandler.getInstance().getConnection();
            String query = "insert into Books (id, title,author, year,pages) values (?,?,?,?,?)";
            pstmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, obj.getId());
            pstmt.setString(2, obj.getTitle());
            pstmt.setString(3, obj.getAuthor());
            pstmt.setInt(4, obj.getYear());
            pstmt.setInt(5, obj.getPages());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if(rs != null && rs.next()){
                System.out.println("Generated Emp Id: "+rs.getInt(1));
                return true;
            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception ex) {
            }


        }
        return false;
    }



    @Override
    public int delete(int id) {


        ResultSet rs = null;
        try {

            con = ConnexionHandler.getInstance().getConnection();
            stmt = con.createStatement();

            String sql = "DELETE FROM books WHERE ID = "+ id;
            stmt.executeUpdate(sql);

            while (rs.next()) {
                System.out.print("commande deleted");
                return 1;
            }



        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }






        return 0;
    }

    @Override
    public boolean update(Books obj)
    {
        PreparedStatement pstmt = null;
        try {

            con = ConnexionHandler.getInstance().getConnection();
            stmt = con.createStatement();

            PreparedStatement st = con.prepareStatement("UPDATE books SET title = ?, Author = ?, Year = ?,pages = ? WHERE id = ?");


            st.setString(1, obj.getTitle());
            st.setString(2, obj.getAuthor());
            st.setInt(3, obj.getYear());
            st.setInt(4, obj.getPages());
            st.setInt(5, obj.getId());

            st.executeUpdate();




            while (rs.next()) {
                String Title = rs.getString("Title");
                String Author = rs.getString("Author");
                int Year = rs.getInt("Year");
                int Pages = rs.getInt("Pages");

                return true;
            }



        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public Books find(String name) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {


            con = ConnexionHandler.getInstance().getConnection();
            String sql = "SELECT * FROM books WHERE title = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);

              rs = ps.executeQuery();



            while (rs.next()) {
                int idb = rs.getInt("Id");
                String Title = rs.getString("Title");
                String Author = rs.getString("Author");
                int Year = rs.getInt("Year");
                int Pages = rs.getInt("Pages");
                Books result = new Books(idb,Title,Author,Year,Pages);
               return result;



            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // No Result we just return a null
        return null;

    }


    @Override
    public List<Books> getAll() {
       List<Books> bookslist  = new ArrayList<>();
        try {

            con = ConnexionHandler.getInstance().getConnection();
            stmt = con.createStatement();
            String query=
                    "SELECT  * FROM books ";


            rs = stmt.executeQuery(query);


            while (rs.next()) {
                int id = rs.getInt("Id");
                String Title = rs.getString("Title");
                String Author = rs.getString("Author");
                int Year = rs.getInt("Year");
                int Pages = rs.getInt("Pages");
                Books result = new Books(id,Title,Author,Year,Pages);
                bookslist.add(result);



            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bookslist;

    }
}
