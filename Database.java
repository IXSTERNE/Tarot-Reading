import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Database instance;
    private Connection connection;


    private Database(){

        try{
            this.connection = DriverManager.getConnection("jdbc:sqlite:tarot.db");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static Database getInstance(){
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }
}
