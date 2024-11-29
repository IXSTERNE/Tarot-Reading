package tarot_read;

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
            statement.executeUpdate("INSERT INTO cards VALUES(3, 'The High Priestess', 'Major', NULL, 'Inner wisdom, secrets', 'Lack of trust in self')");
            statement.executeUpdate("INSERT INTO cards VALUES(4, 'The Empress', 'Major', NULL, 'Creativity, care', 'Dependency, neglect')");
            statement.executeUpdate("INSERT INTO cards VALUES(5, 'The Emperor', 'Major', NULL, 'Control, discipline', 'Tyranny, rigidity')");
            statement.executeUpdate("INSERT INTO cards VALUES(6, 'The Hierophant', 'Major', NULL, 'Conformity, wisdom', 'Rebellion, unconventionality')");
            statement.executeUpdate("INSERT INTO cards VALUES(7, 'The Lovers', 'Major', NULL, 'Harmony, allignment', 'Disharmony, imbalanace')");
            statement.executeUpdate("INSERT INTO cards VALUES(8, 'The Chariot', 'Major', NULL, 'Control, success', 'Lack of direction, opposition')");
            statement.executeUpdate("INSERT INTO cards VALUES(9, 'The Strength', 'Major', NULL, 'Compassion, resilience', 'Doubt, fear, weakness')");
            statement.executeUpdate("INSERT INTO cards VALUES(10, 'The Hermit', 'Major', NULL, 'Reflection, guidance', 'Isolation, loneliness')");

            statement.executeUpdate("INSERT INTO spreads VALUES(1, 'Celtic')");
            statement.executeUpdate("INSERT INTO readings VALUES('1', 'Placeholder', 'The Fool')");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
