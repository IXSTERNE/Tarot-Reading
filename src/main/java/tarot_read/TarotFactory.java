package tarot_read;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TarotFactory {
    public static Tarot createCard(int cardId){
        try{
            Database database = Database.getInstance();
            Statement statement = database.getConnection().createStatement();
            String query = "SELECT * FROM cards WHERE id = " + cardId;
            ResultSet result_set = statement.executeQuery(query);

            if(result_set.next()){
                return new Tarot(
                    result_set.getString("name"),
                    result_set.getString("arcana"),
                    result_set.getString("suit"),
                    result_set.getString("upright_meaning"),
                    result_set.getString("reversed_meaning")
                );
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
