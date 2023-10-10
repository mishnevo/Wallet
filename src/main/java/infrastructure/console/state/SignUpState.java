package infrastructure.console.state;

import application.logic.ClientService;
import domain.Client;

import java.util.Scanner;
/**
 * Класс состояния, отвечающий за регистрацию клиента
 */
public class SignUpState implements ConsoleState{
    /**
     * Поле, позволяющее работать с логикой клиентов
     */
    private final ClientService clientService = ClientService.getInstance();
    /**
     * Поле следующего состояния
     */
    private ConsoleState nextState;
    /**
     * Метод, отвечающий за регистрацию клиента
     */
    public void process() {
        Scanner in = new Scanner(System.in);
        System.out.println("Login:");
        String login = in.next();
        System.out.println("Password:");
        String password = in.next();
        if(clientService.createNewClient(login, password)){
            System.out.println("Registration complete!\n");
            nextState = new MainState();

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
