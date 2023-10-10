package infrastructure.console.state;

import application.logic.ClientService;
import domain.Client;

import java.util.Scanner;
/**
 * Класс состояния, отвечающий за авторизацию клиента
 */
public class LogInState implements ConsoleState{
    /**
     * Поле, позволяющее работать с логикой клиентов
     */
    private final ClientService clientService = ClientService.getInstance();
    /**
     * Поле следующего состояния
     */
    private ConsoleState nextState;
    /**
     * Метод, отвечающий за авторизацию клиента
     */
    public void process() {
        Scanner in = new Scanner(System.in);
        System.out.println("Login:");
        String login = in.next();
        System.out.println("Password:");
        String password = in.next();
        Client newClient = new Client(login, password);
        if(clientService.logInClient(login, password)){
            System.out.println("Welcome!\n");
            nextState = new WorkState(newClient);
        } else{
            System.out.println("Sorry, something is wrong.\n");
            nextState = new MainState();
        }
    }
    /**
     * Метод для перехода в следующее состояние
     * @return Следующее состояние
     */
    public ConsoleState nextState() {
        return nextState;
    }
}
