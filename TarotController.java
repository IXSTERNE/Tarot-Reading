import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TarotController implements ActionListener{
    private TarotView view;
    private TarotDAO model;

    public TarotController(TarotView view, TarotDAO model) {
        this.view = view;
        this.model = model;
    }

    public void reveal(){
        System.out.println("Test");
    }

    @Override
    public void actionPerformed(ActionEvent event){
        System.out.println("Button is clicked!");
    }
}
