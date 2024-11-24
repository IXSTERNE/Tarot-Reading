public class TarotController {
    private TarotView view;

    public TarotController(TarotView view) {
        this.view = view;
    }

    public void drawCards(int[] cardIds) {
        if (cardIds.length != 3) {
            throw new IllegalArgumentException("Exactly three card IDs are required.");
        }

        for (int i = 0; i < 3; i++) {
            Tarot card = TarotFactory.createCard(cardIds[i]);

            if (card != null) {
                view.displayCard(card, i + 1); // Columns are 1, 2, 3
            } else {
                System.out.println("Card not found for ID: " + cardIds[i]);
            }
        }
    }
}
