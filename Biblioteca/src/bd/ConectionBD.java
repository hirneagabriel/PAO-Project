package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionBD {
    private static final String url = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String user = "root";
    private static final String password = "mereuimpreuna";

    private  static Connection dbConnection;

    private ConectionBD(){

    }

    public static Connection dbConnect(){
        try{
            if (dbConnection == null || dbConnection.isClosed()){
                dbConnection = DriverManager.getConnection(url,user,password);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return dbConnection;
    }

    public static void dbClose(){
        try{
            if (dbConnection != null || !dbConnection.isClosed()){
                dbConnection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
