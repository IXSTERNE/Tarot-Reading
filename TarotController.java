import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TarotController implements ActionListener{
    private TarotView view;

    public TarotController(TarotView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e){

        String command = e.getActionCommand();
        Tarot card = TarotFactory.createCard(1);


        switch(command){
            case "REVEAL_PAST":
                System.out.println("Past revealed!");
                view.revealCards(card, 1);
                break;
            case "REVEAL_PRESENT":
                System.out.println("Present revealed!");
                view.revealCards(card, 2);
                break;
            case "REVEAL_FUTURE":
                System.out.println("Future revealed!");
                view.revealCards(card, 3);
                break;
            default:
                throw new IllegalArgumentException("Something wrong happened");
        }
    }
}
