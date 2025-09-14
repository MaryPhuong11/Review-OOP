package app;
import service.ProductService;
import ui.Menu;

public class Main {
    public static void main(String[] args) {
        ProductService service = new ProductService();
        Menu menu = new Menu(service);
        menu.showMenu();
    }
}
