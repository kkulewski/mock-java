package Projekt2.Repositories;

import Projekt2.Entities.Client;
import java.util.List;

public interface ClientRepository
{
    Client getById(int clientId);
    List<Client> getAll();
    void add(Client client);
    void update(Client client);
    void delete(Client client);
}
