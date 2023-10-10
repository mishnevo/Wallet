package infrastructure.memory;

import domain.Client;
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

public class ClientInMemoryTest {
    private ClientInMemory clientInMemory;
    @BeforeEach
    void setUp() throws IllegalAccessException, NoSuchFieldException {
        clientInMemory = new ClientInMemory();
        Map<String, Client> tempMap = new HashMap<>();
        tempMap.put("Sasha", new Client("Sasha", "qwerty", 0));
        Field clientMap = clientInMemory.getClass().getDeclaredField("memory");
        clientMap.setAccessible(true);
        clientMap.set(clientInMemory, tempMap);
    }
    @Test
    void findClientTrue() {
        Client test = clientInMemory.findClient("Sasha");
        Assertions.assertEquals(test, new Client("Sasha", "qwerty"));
    }
    @Test
    void findClientFalse() {
        Client test = clientInMemory.findClient("Petya");
        Assertions.assertNotEquals(test, new Client("Petya", "123"));
    }
    @Test
    void saveNewClient() throws NoSuchFieldException, IllegalAccessException {
        ClientInMemory saveClient = new ClientInMemory();
        Map<String, Client> expected = new HashMap<>();
        Client client = new Client("Misha", "123");
        expected.put(client.getLogin(), client);
        saveClient.saveNewClient(client);
        Field clientMap = saveClient.getClass().getDeclaredField("memory");
        clientMap.setAccessible(true);
        Object actual = clientMap.get(saveClient);
        Assertions.assertEquals(expected,actual);

    }
    @Test
    void validPasswordTrue(){
        boolean checkPassword = clientInMemory.validPassword(new Client("Sasha", "qwerty"));
        Assertions.assertTrue(checkPassword);
    }
    @Test
    void validPasswordFalse(){
        boolean checkPassword = clientInMemory.validPassword(new Client("Sasha", "123"));
        Assertions.assertFalse(checkPassword);
    }
    @Test
    void addInActionHistory() throws NoSuchFieldException, IllegalAccessException {
        MultiValuedMap<String,String> expected = new ArrayListValuedHashMap<>();
        expected.put("Sasha", "Log in");
        clientInMemory.addInActionHistory("Sasha", "Log in");
        Field actionHistory = clientInMemory.getClass().getDeclaredField("historyOfActions");
        actionHistory.setAccessible(true);
        Object actual = actionHistory.get(clientInMemory);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void printActionHistory() throws NoSuchFieldException, IllegalAccessException {
        Field actionHistory = clientInMemory.getClass().getDeclaredField("historyOfActions");
        actionHistory.setAccessible(true);
        MultiValuedMap<String, String> tempMap = new ArrayListValuedHashMap<>();
        tempMap.put("Sasha", "Log in");
        actionHistory.set(clientInMemory, tempMap);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        clientInMemory.printActionHistory("Sasha");
        Assertions.assertEquals("Log in",outputStream.toString().trim());


    }

}
