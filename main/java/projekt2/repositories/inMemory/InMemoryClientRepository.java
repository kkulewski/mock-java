package projekt2.repositories.inMemory;

import projekt2.entities.Client;
import projekt2.repositories.ClientRepository;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.List;

public class InMemoryClientRepository implements ClientRepository
{
    private List<Client> clients;

    public InMemoryClientRepository()
    {
        this.clients = new ArrayList<>();
    }

    @Override
    public Client getById(int clientId)
    {
        return Iterables.tryFind(clients, x -> x.getId() == clientId).orNull();
    }

    @Override
    public List<Client> getAll()
    {
        return new ArrayList<>(clients);
    }

    @Override
    public void add(Client client)
    {
        clients.add(client);
    }

    @Override
    public void update(Client client)
    {
        Client oldClient = Iterables.tryFind(clients, x -> x.getId() == client.getId()).orNull();
        if (oldClient != null)
        {
            clients.remove(oldClient);
            clients.add(client);
        }
    }

    @Override
    public void delete(Client client)
    {
        clients.remove(client);
    }
}
