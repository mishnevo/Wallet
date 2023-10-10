package infrastructure.console;

/**
 * Класс, отвечающий за работу приложения
 */
public class ConsoleApplication {

    /**
     * Работа программы
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new ApplicationLogic().process();
    }

}
