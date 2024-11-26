import java.util.Random;
import java.util.HashSet;

public class TarotApp {
    public static void main(String[] args) {
        TarotDAO model = new TarotDAO();
        TarotView view = new TarotView();
        TarotController controller = new TarotController(view, model);

        Random rand = new Random();

        HashSet<Integer> uniqueIds = new HashSet<>();
        while (uniqueIds.size() < 3) {
            int randId = rand.nextInt(1, 11); // Generate random ID between 1 and 10
            uniqueIds.add(randId); // Only adds if it's not already in the set
        }

        // Convert HashSet to an array
        int[] cardIds = uniqueIds.stream().mapToInt(Integer::intValue).toArray();

        // Pass the unique card IDs to the controller
    }
}
