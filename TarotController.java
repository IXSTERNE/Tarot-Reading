public class TarotController {
    private TarotView view;

    public TarotController(TarotView view) {
        this.view = view;
    }

    public void drawCard(int cardId) {
        
        for(int i = 0; i < 3; i++){

            Tarot card = TarotFactory.createCard(cardId);

            if (card != null) {
                view.displayCard(card, i);
            } else {
                System.out.println("Card not found!");
            }
        }
        
    }
}
