package infrastructure.memory;

import domain.Client;
import domain.Transaction;
import domain.TransactionType;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class TransactionInMemoryTest {
    private TransactionInMemory transactionInMemory;

    @BeforeEach
    void setUp() throws IllegalAccessException, NoSuchFieldException {
        transactionInMemory = new TransactionInMemory();
        Map<Integer, Transaction> tempMap = new HashMap<>();
        tempMap.put(1, new Transaction(1, TransactionType.DEBIT, 100.5));
        Field transactionMap = transactionInMemory.getClass().getDeclaredField("transactionInMemory");
        transactionMap.setAccessible(true);
        transactionMap.set(transactionInMemory, tempMap);
    }
    @Test
    void validateIdTrue(){
        boolean valid = transactionInMemory.validateId(new Transaction(2, TransactionType.CREDIT, 100.0));
        Assertions.assertTrue(valid);
    }
    @Test
    void validateIdFalse(){
        boolean valid = transactionInMemory.validateId(new Transaction(1, TransactionType.CREDIT, 100.0));
        Assertions.assertFalse(valid);
    }
    @Test
    void validateAmountTrue(){
        Client client = new Client("Sasha","qwerty", 100.0);
        boolean test = transactionInMemory.validateAmount(new Transaction(3,TransactionType.CREDIT, 100.0), client);
        Assertions.assertTrue(test);
    }
    @Test
    void validateAmountFalse(){
        Client client = new Client("Sasha","qwerty", 100.0);
        boolean test = transactionInMemory.validateAmount(new Transaction(3,TransactionType.DEBIT, 150.0), client);
        Assertions.assertFalse(test);
    }
    @Test
    void addTransaction() throws NoSuchFieldException, IllegalAccessException {
        TransactionInMemory test = new TransactionInMemory();
        Map<Integer, Transaction> expected = new HashMap<>();
        Transaction transaction = new Transaction(4, TransactionType.DEBIT, 100.0);
        expected.put(transaction.getId(), transaction);
        test.addTransaction(transaction);
        Field map = test.getClass().getDeclaredField("transactionInMemory");
        map.setAccessible(true);
        Object actual = map.get(test);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void addTransactionToHistory() throws NoSuchFieldException, IllegalAccessException {
        MultiValuedMap<String, Transaction> expected = new ArrayListValuedHashMap<>();
        Transaction transaction = new Transaction(5, TransactionType.CREDIT, 100.0);
        expected.put("Sasha", transaction);
        transactionInMemory.addTransactionToHistory(transaction,new Client("Sasha", "qwerty", 500.0));
        Field historyOfTransactions = transactionInMemory.getClass().getDeclaredField("historyOfTransactions");
        historyOfTransactions.setAccessible(true);
        Object actual = historyOfTransactions.get(transactionInMemory);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void printTransactions() throws NoSuchFieldException, IllegalAccessException {
        Field history = transactionInMemory.getClass().getDeclaredField("historyOfTransactions");
        history.setAccessible(true);
        MultiValuedMap<String, Transaction> map = new ArrayListValuedHashMap<>();
        Transaction transaction = new Transaction(7, TransactionType.DEBIT, 100.0);
        map.put("Sasha", transaction);
        history.set(transactionInMemory, map);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        transactionInMemory.printTransactions(new Client("Sasha", "qwerty", 100.0));
        Assertions.assertEquals("7, DEBIT, 100.0", outputStreamCaptor.toString().trim());
    }
}
