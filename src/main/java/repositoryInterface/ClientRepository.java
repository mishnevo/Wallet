package repositoryInterface;

import domain.Client;
/**
 * Интерфейс, предоставляющий методы для работы с клиентами
 */
public interface ClientRepository {
    /**
     * Добавление клиента в память
     *
     * @param client Клиент
     */
    void saveNewClient(Client client);
    /**
     * Поиск клиента по его логину
     * @param login Логин
     * @return Клиент с определенным логином
     */
    Client findClient(String login);

    /**
     * Проверка пароля клиента
     * @param client клиент
     * @return Верный ли пароль
     */
    boolean validPassword(Client client);
    /**
     * Добавление записи в историю действий клиента по его логину
     * @param login Логин
     * @param action Действие клиента
     */
    void addInActionHistory(String login, String action);
    /**
     * Вывод аудита клиента по его логину
     * @param login Логин
     */
    void printActionHistory(String login);
}
