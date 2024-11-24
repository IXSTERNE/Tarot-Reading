public class TarotApp {
    public static void main(String[] args) {
        TarotDAO model = new TarotDAO();
        TarotView view = new TarotView();
        TarotController controller = new TarotController(view);

        // Example: Draw a card with ID 1
        controller.drawCard(3);
    }
}

