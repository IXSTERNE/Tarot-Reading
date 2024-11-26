public class TarotApp {
    public static void main(String[] args) {
        new TarotDAO();
        TarotController controller = new TarotController(null);
        TarotView view = new TarotView(controller);
        
        controller = new TarotController(view);
        view.setController(controller);
    }
}
