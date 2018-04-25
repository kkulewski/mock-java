package projekt2.repositories;

import projekt2.entities.Client;
import java.util.List;

public interface ClientRepository
{
    Client getById(int clientId);
    List<Client> getAll();
    void add(Client client);
    void update(Client client);
    void delete(Client client);
}
