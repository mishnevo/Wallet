package domain;

import lombok.Getter;
/**
 * Класс, описывающий сущность транзакции
 */
@Getter
public class Transaction {
    /**
     * Поле, обозначающее уникальный номер транзакции
     */
    private final Integer id;
    /**
     * Поле, обозначающее тип транзакции, т.е. дебет или кредит
     */
    private final TransactionType type;
    /**
     * Поле, обозначающее сумму, которую клиент хочет снять или положить
     */
    private final Double money;

    /**
     * Конструктор по 3 поля
     * @param id ID транзакции
     * @param type Тип транзакции
     * @param money Сумма транзакции
     */
    public Transaction(Integer id, TransactionType type, Double money) {
        this.id = id;
        this.type = type;
        this.money = money;
    }
}
