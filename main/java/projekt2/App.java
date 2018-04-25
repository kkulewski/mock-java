package projekt2;

import projekt2.entities.*;
import projekt2.repositories.*;
import projekt2.repositories.inMemory.*;

public class App
{
    public static void main(String[] args)
    {
        ClientRepository cr = new InMemoryClientRepository();

        Client john = new Client();
        john.setId(1);
        john.setEmail("john@email.com");

        Client dave = new Client();
        dave.setId(2);
        dave.setEmail("dave@email.com");


        cr.add(john);
        cr.add(dave);

        Client result = cr.getById(1);
        Client result2 = cr.getById(3);
        System.out.println(result.getEmail());
    }
}
