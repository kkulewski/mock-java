package projekt2;

import projekt2.entities.*;
import projekt2.repositories.*;
import projekt2.repositories.inMemory.*;

public class App
{
    public static void main(String[] args)
    {
        ClientRepository cr = new InMemoryClientRepository();
        cr.add(new Client(1, "John", "Doe", "john@email.com"));
        cr.add(new Client(2, "Dave", "Smith", "dave@email.com"));
        Client result = cr.getById(1);
        Client result2 = cr.getById(3);
        System.out.println(result.getEmail());
    }
}
