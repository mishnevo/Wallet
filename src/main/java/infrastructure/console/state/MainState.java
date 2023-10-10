package infrastructure.console.state;

import application.logic.ClientService;

import java.util.Scanner;
/**
 * Класс начального состояния приложения
 */
public class MainState implements ConsoleState {
    /**
     * Поле следующего состояния
     */
    private ConsoleState nextState;
    /**
     * Метод, отвечающий за начальные взаимодействия пользователя с приложением
     */
    public void process() {
        System.out.println("What do you wanna do:\n1)Log in\n2)Sign up\n3)Exit");
        Scanner in = new Scanner(System.in);
        String input = in.next();
        switch (input){
            case "1":
                nextState = new LogInState();
                break;
            case "2":
                nextState = new SignUpState();
                break;
            case "3":
                System.exit(0);
            default:
                System.out.println("Invalid input");
                nextState = new MainState();
                break;
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
