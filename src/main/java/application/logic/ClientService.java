package application.logic;

import application.services.ClientServiceI;
import domain.Client;
import infrastructure.memory.ClientInMemory;
import repositoryInterface.ClientRepository;
/**
 * Класс, реализующий бизнес-логику клиентов
 */
public class ClientService implements ClientServiceI {
    /**
     * Поле для взаимодействия с памятью приложения
     */
    private final ClientRepository clientRepository = ClientInMemory.getInstance();
    /**
     * Поле для реализации паттерна проектирования Singleton
     */
    private final static ClientService INSTANCE = new ClientService();
    /**
     * Реализация регистрации клиента
     * @param login Логин клиента
     * @param password Пароль клиента
     * @return Результат попытки регистрации
     */
    public boolean createNewClient(String login, String password) {
        if(clientRepository.findClient(login) == null) {
            clientRepository.addInActionHistory(login, "Registration");
            Client client = new Client(login, password);
            clientRepository.saveNewClient(client);
            return true;
        } else {
            return false;
        }
    }
    /**
     * Реализация авторизации клиента
     * @param login Логин клиента
     * @param password Пароль клиента
     * @return Результат попытки авторизации
     */
    public boolean logInClient(String login, String password) {
        if ((clientRepository.findClient(login) != null) && (clientRepository.validPassword(new Client(login, password)))){
            clientRepository.addInActionHistory(login, "Log in");
            return true;
        } else{
            return false;
        }
    }
    /**
     * Проверка баланска клиента
     * @param login Логин клиента
     * @return Баланс
     */
    @Override
    public double checkBalance(String login) {
        clientRepository.addInActionHistory(login, "Check balance");
        return clientRepository.findClient(login).getBalance();
    }
    /**
     * Вывод истории действий клиента
     * @param login Логин клиента
     */
    @Override
    public void printHistoryActions(String login) {
        clientRepository.addInActionHistory(login, "Print history");
        clientRepository.printActionHistory(login);
    }
    /**
     * Реализация выхода клиента из аккаунта
     * @param login Логин клиента
     */
    @Override
    public void logout(String login) {
        clientRepository.addInActionHistory(login, "Log out");
    }
    /**
     * Метод для реализации паттерна проектирования Singleton
     * @return Возвращает объект типа ClientService
     */
    public static ClientService getInstance(){return INSTANCE;}
}
