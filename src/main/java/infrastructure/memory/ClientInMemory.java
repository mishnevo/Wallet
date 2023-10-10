package infrastructure.memory;

import domain.Client;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import repositoryInterface.ClientRepository;

import java.util.HashMap;
import java.util.Map;
/**
 * Класс для работы клиентов с памятью приложения
 */
public class ClientInMemory implements ClientRepository {
    /**
     * Словарь для всех клиентов с ключом - логином и значением - самим клиентом
     */
    private final Map<String, Client> memory = new HashMap<String, Client>();
    /**
     * Словарь для аудита каждого клиента с ключом - логином и значением - действием клиента
     */
    private final MultiValuedMap<String, String> historyOfActions = new ArrayListValuedHashMap<>();
    /**
     * Поле для реализации паттерна проектирования Singleton
     */
    private final static ClientRepository INSTANCE = new ClientInMemory();
    /**
     * Метод для реализации паттерна проектирования Singleton
     * @return Экземпляр класса
     */
    public static ClientRepository getInstance() {
        return INSTANCE;
    }

    /**
     * Добавление клиента в словарь всех клиентов
     * @param client Клиент
     */
    public void saveNewClient(Client client) {
        memory.put(client.getLogin(), client);
    }
    /**
     * Поиск клинта по логину
     * @param login Логин
     * @return Клиент
     */
    public Client findClient(String login) {
        return memory.get(login);
    }

    /**
     * Проверка пароля клиента
     * @param client Клиент
     * @return Правильность пароля
     */
    public boolean validPassword(Client client) {
        return memory.get(client.getLogin()).getPassword().equals(client.getPassword());
    }
    /**
     * Добавление действия клиента в историю действий
     * @param login Логин
     * @param action Действие клиента
     */
    @Override
    public void addInActionHistory(String login, String action) {
        historyOfActions.put(login, action);
    }
    /**
     * Вывод истории действий клиента с определенным логином
     * @param login Логин
     */
    @Override
    public void printActionHistory(String login) {
        historyOfActions.get(login).forEach(System.out::println);
    }
}
