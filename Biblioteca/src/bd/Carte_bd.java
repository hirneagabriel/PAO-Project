package bd;

import Modele.Autor;
import Modele.Carte;
import Modele.Editura;

import java.sql.*;
import java.util.*;

public class Carte_bd {
    private final static Connection connection = ConectionBD.dbConnect();

    public void insert(Carte carte, Integer id){
        String insertCarte = "INSERT INTO carte (denumire, id_editura, id_sectiune) VALUES(?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertCarte);
            preparedStatement.setString(1,carte.getDenumire());
            preparedStatement.setInt(2,carte.getEditura().getId_editura());
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void insertA(List<Integer>autorId, Integer id_carte){
        String insertData = "INSERT INTO autor_carte (id_carte, id_autor) VALUES(?, ?)";
        for(Integer id:autorId){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertData);
                preparedStatement.setInt(1,id_carte);
                preparedStatement.setInt(2,id);
                preparedStatement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public Set<Carte> getCartiIdSec(Integer id){
        String citire = "SELECT * FROM carte where id_sectiune=?";
        Set<Carte> carti = new HashSet<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(citire);
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer id_carte = resultSet.getInt(1);
                String denumire = resultSet.getString(2);
                Integer id_editura = resultSet.getInt(3);
                Editura_bd editura_bd = new Editura_bd();
                Editura editura = editura_bd.citesteEdituraId(id_editura);
                String citireAutori = "select a.id_autor, nume,nationalitate from autor a, autor_carte c where c.id_carte=? and c.id_autor = a.id_autor";
                preparedStatement = connection.prepareStatement(citireAutori);
                preparedStatement.setInt(1,id_carte);
                ResultSet resultSet1 = preparedStatement.executeQuery();
                Carte carte = new Carte(denumire,editura,id_carte);
                while (resultSet1.next()){
                    Integer id_autor = resultSet1.getInt(1);
                    String nume_autor = resultSet1.getString(2);
                    String nationalitate = resultSet1.getString(3);
                    Autor autor = new Autor(nume_autor,nationalitate,id_autor);
                    carte.addAutor(autor);
                    carti.add(carte);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    return carti;

    }
}
