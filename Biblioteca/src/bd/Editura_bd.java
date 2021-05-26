package bd;
import Modele.Editura;

import java.sql.*;
import java.util.Hashtable;
import java.util.Map;

public class Editura_bd {
    private final static Connection connection = ConectionBD.dbConnect();
    public void insert(String nume, String adresa) {
        String insertEditura = "INSERT INTO editura(nume, adresa) VALUES(?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertEditura);
            preparedStatement.setString(1,nume);
            preparedStatement.setString(2,adresa);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void insert(Editura editura) {
        String insertEditura = "INSERT INTO editura(nume, adresa) VALUES(?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertEditura);
            preparedStatement.setString(1,editura.getNume());
            preparedStatement.setString(2,editura.getAdresa());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateNume(String nume_vechi,String nume_nou ){
        String update = "UPDATE editura set nume=? where nume=?";

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
        String update = "UPDATE editura set nume=? where id_editura=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,nume_nou);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteEditura(Integer id){
        String delete = "DELETE FROM editura WHERE id_editura = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Editura citesteEdituraId(Integer id_editura){
        String citire = "SELECT * FROM editura where id_editura= ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(citire);
            preparedStatement.setInt(1, id_editura);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String nume = resultSet.getString(2);
                String adresa = resultSet.getString(3);
                return new Editura(nume, adresa,id_editura);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, Editura> citesteEdituri(){
        String citire = "SELECT * FROM editura";
        Map<String, Editura> edituri = new Hashtable<String, Editura>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(citire);
            while (resultSet.next()){
                Integer id = resultSet.getInt(1);
                String nume = resultSet.getString(2);
                String adresa = resultSet.getString(3);
                edituri.put(nume,new Editura(nume,adresa,id));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return edituri;

    }
}
