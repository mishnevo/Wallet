package infrastructure.console.state;

import application.logic.TransactionService;
import domain.Client;

import java.util.Scanner;
/**
 * Класс состояния, отвечающий за снятие денег клиентом
 */
public class CreditState implements ConsoleState{
    /**
     * Поле, позволяющее работать с логикой транзакций
     */
    private final TransactionService transactionService = TransactionService.getInstance();
    /**
     * Поле текущего клиента
     */
    private final Client client;
    /**
     * Поле следующего состояния
     */
    private ConsoleState nextState;

    /**
     * Конструктор по 1 полю
     * @param client Текущий клиент
     */
    public CreditState(Client client){
        this.client = client;
    }
    /**
     * Метод, отвечающий за снятие денег клиентом
     */
    @Override
    public void process() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please write transaction ID: ");
        int transactionID = in.nextInt();
        System.out.println("Write amount: ");
        double amount = in.nextDouble();
        if(transactionService.credit(transactionID, amount, client.getLogin())){
            System.out.println("Operation successful\n");
        } else{
            System.out.println("Sorry, something is wrong\n");
        }
        nextState = new WorkState(client);
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
