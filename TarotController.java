import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TarotController implements ActionListener{
    private TarotView view;

    public TarotController(TarotView view, TarotDAO model) {
        this.view = view;
    }

    public void setView(TarotView view){
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();

        Random rand = new Random();
        int randomNum = rand.nextInt(1, 11);
        boolean isInverted = rand.nextBoolean();

        Tarot card = TarotFactory.createCard(randomNum);

        switch(command){
            case "REVEAL_PAST":
                System.out.println("Past revealed!");
                view.revealCards(card, 1, isInverted);
                break;
            case "REVEAL_PRESENT":
                System.out.println("Present revealed!");
                view.revealCards(card, 2, isInverted);
                break;
            case "REVEAL_FUTURE":
                System.out.println("Future revealed!");
                view.revealCards(card, 3, isInverted);
                break;
            case "RESET":
                System.out.println("Reset Successful");
                view.resetCards();
        }
    }
}
