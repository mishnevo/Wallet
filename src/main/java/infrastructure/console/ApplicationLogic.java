package infrastructure.console;

import infrastructure.console.state.ConsoleState;
import infrastructure.console.state.MainState;

/**
 * Класс, отвечающий за логику работы приложения
 */
public class ApplicationLogic {
    /**
     * Поле текущего состояния
     */
    private ConsoleState currentState;

    /**
     * Конструктор без параметров
     */
    public ApplicationLogic() {
        this.currentState = new MainState();
    }

    /**
     * Метод, отвечающий за работу приложения
     */
    public void process() {
        while (true) {
            try {
                currentState.process();
                if (currentState.nextState() != null) {
                    ConsoleState nextState = currentState.nextState();
                    currentState = nextState;
                }
            } catch (Exception e) {
                System.err.println("Problem with input: " + e.getMessage());
                System.exit(1);
            }
        }
    }
}

