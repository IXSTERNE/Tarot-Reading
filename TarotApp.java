public class TarotApp {
    public static void main(String[] args) {
        TarotDAO model = new TarotDAO();
        TarotView view = new TarotView();
        TarotController controller = new TarotController(view);

        int[] cardIds = {1, 2, 3}; // Example card IDs
        controller.drawCards(cardIds);
    }
}

