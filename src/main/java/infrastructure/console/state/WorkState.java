package infrastructure.console.state;

import application.logic.ClientService;
import application.logic.TransactionService;
import domain.Client;

import java.util.Scanner;
/**
 * Класс состояния, отвечающий за выбор действия клиентом
 */
public class WorkState implements ConsoleState{
    /**
     * Поле, позволяющее работать с логикой клиентов
     */
    private final ClientService clientService = ClientService.getInstance();
    /**
     * Поле, позволяющее работать с логикой транзакций
     */
    private final TransactionService transactionService = TransactionService.getInstance();
    /**
     * Поле следующего состояния
     */
    private ConsoleState nextState;
    /**
     * Поле текущего клиента
     */
    private final Client client;
    /**
     * Конструктор по 1 полю
     * @param client Текущий клиент
     */
    public WorkState(Client client){
        this.client = client;
    }
    /**
     * Метод, отвечающий за выбор действий клиентом
     */
    @Override
    public void process() {
        System.out.println("What do you wanna do:\n1)Log out\n2)Debit\n3)Credit\n4)Check balance\n5)My transactions\n6)My actions");
        Scanner in = new Scanner(System.in);
        String input = in.next();
        switch (input){
            case "1":
                clientService.logout(client.getLogin());
                nextState = new MainState();
                break;
            case "2":
                nextState = new DebitState(client);
                break;
            case "3":
                nextState = new CreditState(client);
                break;
            case "4":
                System.out.println(clientService.checkBalance(client.getLogin())+"\n");
                nextState = new WorkState(client);
                break;
            case "5":
                transactionService.printHistoryOfTransactions(client.getLogin());
                nextState = new WorkState(client);
                break;
            case "6":
                clientService.printHistoryActions(client.getLogin());
                nextState = new WorkState(client);
                break;

            default:
                System.out.println("Invalid input");
                nextState = new WorkState(client);
                break;
        }
    }
    /**
     * Метод для перехода в следующее состояние
     * @return Следующее состояние
     */
    @Override
    public ConsoleState nextState() {
        return nextState;
    }
}
