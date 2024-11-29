import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TarotController implements ActionListener{
    private TarotView view;
    private Random rand = new Random();
    private int randomNum;
    private Object cards[];
    private Object five_cards[];
    private Tarot card;

    public TarotController(TarotView view, TarotDAO model) {
        this.view = view;
        this.cards = new Object[3];
        this.five_cards = new Object[5];
    }

    public void setView(TarotView view){
        this.view = view;

    }

    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        System.out.println(command);

        switch(command){
            case "REVEAL_THREE":
                System.out.println("Three Cards reading revealed!");

                for(int i = 0; i < 3; i++){
                    randomNum = rand.nextInt(1, 11);
                    boolean cardInverted = rand.nextBoolean();
                    card = TarotFactory.createCard(randomNum);
                    cards[i] = new CardWithInversion(card, cardInverted);
                }
                view.revealCards(cards);
                break;
            case "REVEAL_FIVE":
                System.out.println("Celtic reading revealed!");

                for(int i = 0; i < 5; i++){
                    randomNum = rand.nextInt(1, 11);
                    boolean cardInverted = rand.nextBoolean();
                    card = TarotFactory.createCard(randomNum);
                    five_cards[i] = new CardWithInversion(card, cardInverted);
                }
                view.revealFiveCards(five_cards);
                break;
        }
    }
}
