package application.DaoImplementation;

import application.MODEL.Auther;
import application.MODEL.Books;
import application.dao.DAO;
import application.singleton.ConnexionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAOImpl implements DAO<Auther> {
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;



    @Override
    public boolean create(Auther obj) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            con = ConnexionHandler.getInstance().getConnection();
            String query = "insert into Auther (id, name,lastname, year) values (?,?,?,?)";
            pstmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, obj.getId());
            pstmt.setString(2, obj.getName());
            pstmt.setString(3, obj.getLastName());
            pstmt.setInt(4, obj.getYear());

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
            return false;
        }
    }

    @Override
    public int delete(int id) {
        ResultSet rs = null;
        try {

            con = ConnexionHandler.getInstance().getConnection();
            stmt = con.createStatement();

            String sql = "DELETE FROM Auther WHERE ID = "+ id;
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
    public boolean update(Auther obj) {
        PreparedStatement pstmt = null;
        try {

            con = ConnexionHandler.getInstance().getConnection();
            stmt = con.createStatement();

            PreparedStatement st = con.prepareStatement("UPDATE auther SET name = ?, lastname = ?, Year = ?,pages = ? WHERE id = ?");


            st.setString(1, obj.getName());
            st.setString(2, obj.getLastName());
            st.setInt(3, obj.getYear());
            st.setInt(5, obj.getId());

            st.executeUpdate();




            while (rs.next()) {


                return true;
            }



        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public Auther find(String name) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {


            con = ConnexionHandler.getInstance().getConnection();
            String sql = "SELECT * FROM auther ,books INNER JOIN  auther.id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();



            while (rs.next()) {
                int idb = rs.getInt("Id");
                String Title = rs.getString("name");
                String Author = rs.getString("lastName");
                int Year = rs.getInt("Year");

                Auther result = new Auther(idb, Title, Author, Year);
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
    public List<Auther> getAll() {
        List<Auther> autherlist  = new ArrayList<>();
        try {

            con = ConnexionHandler.getInstance().getConnection();
            stmt = con.createStatement();
            String query=
                    "SELECT  * FROM auther ";


            rs = stmt.executeQuery(query);


            while (rs.next()) {
                int idb = rs.getInt("Id");
                String Title = rs.getString("name");
                String Author = rs.getString("lastName");
                int Year = rs.getInt("Year");

                Auther result = new Auther(idb, Title, Author, Year);

                autherlist.add(result);

            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return autherlist;

    }
}
