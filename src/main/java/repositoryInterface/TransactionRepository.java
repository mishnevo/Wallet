package repositoryInterface;

import domain.Client;
import domain.Transaction;
/**
 * Интерфейс, предоставляющий методы для работы с транзакциями
 */
public interface TransactionRepository {
    /**
     * Добавление транзации в память
     * @param transaction Транзакция
     */
    void addTransaction(Transaction transaction);
    /**
     * Проверка валидности суммы транзакции
     * @param transaction Транзакция
     * @param client Клиент
     * @return Результат проверки
     */
    boolean validateAmount(Transaction transaction, Client client);
    /**
     * Проверка валидности уникального номера транзакции
     * @param transaction Транзакция
     * @return Результат проверки
     */
    boolean validateId(Transaction transaction);
    /**
     * Добавление транзакции для каждого клиента в его историю
     * @param transaction Транзакция
     * @param client Клиент
     */
    void addTransactionToHistory(Transaction transaction, Client client);
    /**
     * Вывод истории транзакций для каждого клиента
     * @param client Клиент
     */
    void printTransactions(Client client);
}
