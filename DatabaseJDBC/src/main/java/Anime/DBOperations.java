package Anime;


import java.sql.*;

public class DBOperations {
    Connection conn;
    public void doConnectDB() {
        String connectionStr = "jdbc:mysql://localhost:3306/"+Utils.DB_NAME;
        String userName = "root";
        String password = "";
        try {
            conn = DriverManager.getConnection(connectionStr, userName, password);
            System.out.println("DB Connection is seccussful!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fetchData() {
        Statement stmt =null;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM "+Utils.TABLE_NAME);



            while (rs.next()) {
                String name = rs.getString(Utils.COLUMN_Name);
                int seasons = rs.getInt(Utils.SEASONS);
                String genre = rs.getString(Utils.GENRE);
                System.out.println("Name of Anime: " + name + ", Seasons: " + seasons + ", Genre: "+genre);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        try {
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM "+Utils.TABLE_NAME2);
            System.out.println("Here are the genres and the animes of that genre available in our database");
            while (rs1.next()){
                String genre=rs1.getString(Utils.GENRE_TYPE);
                int available=rs1.getInt(Utils.GENRE_COUNT);
                System.out.println("Genre: "+genre+" Animes of that genre: "+available);
            }

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
