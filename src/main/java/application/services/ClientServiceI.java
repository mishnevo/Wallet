package application.services;

import domain.Client;
/**
 * Интерфейс сервиса, реализующий бизнес-логику клиентов
 */
public interface ClientServiceI {
    /**
     * Попытка создания нового клиента
     * @param login Логин клиента
     * @param password Пароль клиента
     * @return Результат попытки регистрации
     */
    boolean createNewClient(String login, String password);
    /**
     * Попытка клиента авторизоваться
     * @param login Логин клиента
     * @param password Пароль клиента
     * @return Результат попытки авторизации
     */
    boolean logInClient(String login, String password);
    /**
     * Проверка баланса
     * @param login Логин
     * @return Баланс
     */
    double checkBalance(String login);
    /**
     * Вывод истории действий клиента
     * @param login Логин клиента
     */
    void printHistoryActions(String login);
    /**
     * Выход клиента из аккаунта
     * @param login Логин клиента
     */
    void logout(String login);
}
