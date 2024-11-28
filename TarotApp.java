public class TarotApp {
    public static void launchTarotApp(int width, int height, boolean fullscreen){

        TarotDAO model = new TarotDAO();
        TarotController controller = new TarotController(null, model);
        TarotView view = new TarotView(controller, width, height, fullscreen);
        
        controller.setView(view);
        view.setController(controller);
    }
}
