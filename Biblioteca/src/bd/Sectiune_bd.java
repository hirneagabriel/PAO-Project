package bd;

import Modele.Autor;
import Modele.Carte;
import Modele.Editura;
import Modele.Sectiune;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class Sectiune_bd {
    private final static Connection connection = ConectionBD.dbConnect();
    public void insert(String nume) {
        String insertAutor = "INSERT INTO sectiune (nume) VALUES(?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertAutor);
            preparedStatement.setString(1,nume);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void insert(Sectiune sectiune) {
        String insertAutor = "INSERT INTO sectiune(nume) VALUES(?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertAutor);
            preparedStatement.setString(1,sectiune.getNume());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateNume(Integer id,String nume_nou ){
        String update = "UPDATE sectiune set nume=? where id_sectiune=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,nume_nou);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Set<Sectiune> citesteSectiuni(){
        String citire = "SELECT * FROM sectiune";
        Set<Sectiune> sectiuni = new HashSet<Sectiune>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(citire);
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String nume = resultSet.getString(2);
                Set<Carte> carti;
                Carte_bd carte_bd =new Carte_bd();
                carti= carte_bd.getCartiIdSec(id);
                Sectiune sectiune = new Sectiune(nume,id);
                sectiune.addCarti(carti);
                sectiuni.add(sectiune);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return sectiuni;
    }
}
