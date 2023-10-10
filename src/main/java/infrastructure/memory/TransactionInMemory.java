package infrastructure.memory;

import domain.Client;
import domain.Transaction;
import domain.TransactionType;
import repositoryInterface.TransactionRepository;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
/**
 * Класс для работы транзакций с памятью приложения
 */
public class TransactionInMemory implements TransactionRepository {
    /**
     * Словарь для всех транзакций с ключом - уникальным номером транзакции и значением - самой транзакцией
     */
    private final Map<Integer, Transaction> transactionInMemory = new HashMap<>();
    /**
     * Словарь для истории транзакций каждого клиента с ключом - логином и значением - транзакцией
     */
    private final MultiValuedMap<String, Transaction> historyOfTransactions = new ArrayListValuedHashMap<>();
    /**
     * Поле для реализации паттерна проектирования Singleton
     */
    private final static TransactionRepository INSTANCE = new TransactionInMemory();
    /**
     * Метод для реализации паттерна проектирования Singleton
     * @return Экземпляр класса
     */
    public static TransactionRepository getInstance() {
        return INSTANCE;
    }
    /**
     * Добавление транзакции в словарь
     * @param transaction Транзакция
     */
    @Override
    public void addTransaction(Transaction transaction) {
        transactionInMemory.put(transaction.getId(), transaction);
    }
    /**
     * Проверка на валидность суммы транзакции
     * @param transaction Транзакция
     * @param client Клиент
     * @return Резульат проверки
     */
    @Override
    public boolean validateAmount(Transaction transaction, Client client) {
        if(transaction.getType() == TransactionType.CREDIT){
            return true;
        }
        return transaction.getMoney() < client.getBalance();
    }
    /**
     * Проверка на валидность уникального номера транзакции
     * @param transaction Транзакция
     * @return Результат проверки
     */
    @Override
    public boolean validateID(Transaction transaction) {
        return !transactionInMemory.containsKey(transaction.getId());
    }
    /**
     * Добавление транзакции в историю транзакций клиента
     * @param transaction Транзакция
     * @param client Клиент
     */
    @Override
    public void addTransactionToHistory(Transaction transaction, Client client) {
        historyOfTransactions.put(client.getLogin(), transaction);
    }
    /**
     * Вывод истории транзакций определенного клиента
     * @param client Клиент
     */
    @Override
    public void printTransactions(Client client) {
        historyOfTransactions.get(client.getLogin()).forEach(transaction -> {
            System.out.println(transaction.getId() + ", " + transaction.getType() + ", " + transaction.getMoney());
        });
    }
}
