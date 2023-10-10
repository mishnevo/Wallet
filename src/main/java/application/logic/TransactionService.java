package application.logic;

import application.services.TransactionServiceI;
import domain.Client;
import domain.Transaction;
import infrastructure.memory.TransactionInMemory;
import infrastructure.memory.ClientInMemory;
import domain.TransactionType;
import repositoryInterface.ClientRepository;
import repositoryInterface.TransactionRepository;
/**
 * Класс, реализующий бизнес-логику транзакций
 */
public class TransactionService implements TransactionServiceI {
    /**
     * Метод для реализации паттерна проектирования Singleton
     * @return Возвращает объект типа TransactionService
     */
    public static TransactionService getInstance() {
        return INSTANCE;
    }
    /**
     * Поле для реализации паттерна проектирования Singleton
     */
    private final static TransactionService INSTANCE = new TransactionService();
    /**
     * Поле для взаимодействия с памятью приложения
     */
    private final TransactionRepository transactionRepository = TransactionInMemory.getInstance();
    /**
     * Поле для взаимодействия с памятью приложения
     */
    private final ClientRepository clientRepository = ClientInMemory.getInstance();
    /**
     * Реализация снятия средств со счета
     * @param id Уникальный номер транзакции
     * @param amount Сумма
     * @param login Логин клиента
     * @return Результат попытки снятия
     */
    @Override
    public boolean debit(int id, double amount, String login) {
        Transaction newTransaction = new Transaction(id, TransactionType.DEBIT, amount);
        Client client = clientRepository.findClient(login);
        if(transactionRepository.validateId(newTransaction) && transactionRepository.validateAmount(newTransaction, client)){
            transactionRepository.addTransaction(newTransaction);
            transactionRepository.addTransactionToHistory(newTransaction,clientRepository.findClient(login));
            clientRepository.addInActionHistory(client.getLogin(), "Debit transaction");
            Client newClient = new Client(client.getLogin(), client.getPassword(), client.getBalance() - amount);
            clientRepository.saveNewClient(newClient);
            return true;
        }
        return false;
    }
    /**
     * Реализация пополнения счета
     * @param id Уникальный номер транзакции
     * @param amount Сумма
     * @param login Логин клиента
     * @return Результат попытки пополнения
     */
    @Override
    public boolean credit(int id, double amount, String login) {
        Transaction newTransaction = new Transaction(id, TransactionType.CREDIT, amount);
        Client client = clientRepository.findClient(login);
        if(transactionRepository.validateId(newTransaction)){
            transactionRepository.addTransaction(newTransaction);
            transactionRepository.addTransactionToHistory(newTransaction,clientRepository.findClient(login));
            clientRepository.addInActionHistory(client.getLogin(), "Credit transaction");
            Client newClient = new Client(client.getLogin(), client.getPassword(), client.getBalance() + amount);
            clientRepository.saveNewClient(newClient);
            return true;
        }
        return false;
    }
    /**
     * Вывод истории транзакций по логину
     * @param login Логин клиента
     */
    @Override
    public void printHistoryOfTransactions(String login) {
        clientRepository.addInActionHistory(login, "History of transactions");
        transactionRepository.printTransactions(clientRepository.findClient(login));
    }
}
