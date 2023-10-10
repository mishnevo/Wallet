package infrastructure.console.state;
/**
 * Интерфейс состояния приложения
 */
public interface ConsoleState {
    /**
     * Метод для работы нужного состояния
     */
    void process();
    /**
     * Метод для перехода в следующее состояние
     * @return Следующее состояние
     */
    ConsoleState nextState();
}
