import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;

public class TarotController implements ActionListener{
    private TarotView view;
    private Random rand = new Random();
    private Set<Integer> drawnCardNumbers;
    private Object cards[];
    private Object five_cards[];
    private Tarot card;

    public TarotController(TarotView view, TarotDAO model) {
        this.view = view;
        this.cards = new Object[3];
        this.five_cards = new Object[5];
        this.drawnCardNumbers = new HashSet<>();
    }

    public void setView(TarotView view){
        this.view = view;

    }

    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        System.out.println(command);

        drawnCardNumbers.clear();

        switch(command){
            case "REVEAL_THREE":
                System.out.println("Three Cards reading revealed!");

                drawCards(cards, 3);
                view.revealCards(cards);
                break;

            case "REVEAL_FIVE":
                System.out.println("Celtic Cards reading revealed!");
                
                drawCards(five_cards, 5);
                view.revealFiveCards(five_cards);
                break;
        }
    }

    private void drawCards(Object[] cardArray, int count){
        for(int i = 0; i < count; i++) {
            int randomNum;
            do {
                // We "only" have 10 card so...
                randomNum = rand.nextInt(1, 11);
            } while(drawnCardNumbers.contains(randomNum));

            drawnCardNumbers.add(randomNum);
            boolean cardInverted = rand.nextBoolean();
            card = TarotFactory.createCard(randomNum);
            cardArray[i] = new CardWithInversion(card, cardInverted);
        }
    }
}
