package domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
/**
 * Класс, описывающий сущность клиента
 */
@Getter
@EqualsAndHashCode
public class Client {
    /**
     * Поле, обозначающее логин клиента
     */
    private final String login;
    /**
     * Поле, обозначающее пароль клиента
     */
    private final String password;
    /**
     * Поле, обозначающее баланс клиента
     */
    private final double balance;

    /**
     *  Конструктор по 2 полям
     * @param login Логин клиента
     * @param password Пароль клиента
     */
    public Client(String login, String password){
        this.login = login;
        this.password = password;
        this.balance = 0;
    }

    /**
     * Конструктор по 3 полям
     * @param login Логин клиента
     * @param password Паррль клиента
     * @param balance Баланс клиента
     */
    public Client(String login, String password, double balance){
        this.login = login;
        this.password = password;
        this.balance = balance;
    }

}
