package application.services;

import domain.Client;
/**
 * Интерфейс сервиса, реализующий бизнес-логику транзакций
 */
public interface TransactionServiceI {
    /**
     * Снятие средство со счета
     * @param id Уникальный номер транзакции
     * @param amount Сумма
     * @param login Логин клиента
     * @return Результат попытки снятия
     */
    boolean debit (int id, double amount, String login);
    /**
     * Пополнение счета
     * @param id Уникальный номер транзакции
     * @param amount Сумма
     * @param login Логин клиента
     * @return Результат попытки пополнения
     */
    boolean credit(int id, double amount, String login);
    /**
     * Вывод истории транзакций
     * @param login Логин клиента
     */
    void printHistoryOfTransactions(String login);
}
