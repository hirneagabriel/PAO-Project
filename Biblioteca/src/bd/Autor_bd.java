package bd;
import Modele.Autor;

import java.sql.*;
import java.util.Hashtable;
import java.util.Map;

public class Autor_bd {
    private final static Connection connection = ConectionBD.dbConnect();
    public void insert(String nume, String nationalitate) {
        String insertAutor = "INSERT INTO autor(nume, nationalitate) VALUES(?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertAutor);
            preparedStatement.setString(1,nume);
            preparedStatement.setString(2,nationalitate);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void insert(Autor autor) {
        String insertAutor = "INSERT INTO autor(nume, nationalitate) VALUES(?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertAutor);
            preparedStatement.setString(1,autor.getNume());
            preparedStatement.setString(2,autor.getNationalitate());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateNume(String nume_vechi,String nume_nou ){
        String update = "UPDATE autor set nume=? where nume=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,nume_nou);
            preparedStatement.setString(2,nume_vechi);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateNume(Integer id,String nume_nou ){
        String update = "UPDATE autor set nume=? where id_autor=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,nume_nou);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteAutor(Integer id){
        String delete = "DELETE FROM autor WHERE id_autor = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Map<String, Autor> citesteAutori(){
        String citire = "SELECT * FROM autor";
        Map<String, Autor> autori = new Hashtable<String, Autor>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(citire);
            while (resultSet.next()){
                Integer id = resultSet.getInt(1);
                String nume = resultSet.getString(2);
                String nationalitatea = resultSet.getString(3);
                autori.put(nume,new Autor(nume,nationalitatea,id));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return autori;
    }
}