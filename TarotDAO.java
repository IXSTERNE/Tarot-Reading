import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class TarotDAO{

    private Connection connection;
    private Statement statement;
    
    
    public TarotDAO(){
        setupDatabase();
        
    }

    public void setupDatabase(){
        try{
            Database database = Database.getInstance();
            connection = database.getConnection();
            statement = connection.createStatement();

            createTables();
            populateDatabase();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void createTables() throws SQLException{
        
        statement.executeUpdate("DROP TABLE IF EXISTS cards");
        statement.executeUpdate("DROP TABLE IF EXISTS spreads");
        statement.executeUpdate("DROP TABLE IF EXISTS readings");

        statement.executeUpdate("CREATE TABLE cards (id integer, name string, arcana string, suit string, upright_meaning string, reversed_meaning string)");
        statement.executeUpdate("CREATE TABLE spreads (id integer, name string)");
        statement.executeUpdate("CREATE TABLE readings (id integer, name string, cards string)");
    }

    public void populateDatabase(){
        try{
            statement.executeUpdate("INSERT INTO cards VALUES(1, 'The Fool', 'Major', NULL, 'New beginnings', 'Recklessness')");
            statement.executeUpdate("INSERT INTO cards VALUES(2, 'The Magician', 'Major', NULL, 'Power, skill', 'Manipulation')");
            statement.executeUpdate("INSERT INTO cards VALUES(3, 'The Sun', 'Major', NULL, 'One above all', 'Absolution')");

            statement.executeUpdate("INSERT INTO spreads VALUES(1, 'Celtic')");
            statement.executeUpdate("INSERT INTO readings VALUES('1', 'Placeholder', 'The Fool')");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
