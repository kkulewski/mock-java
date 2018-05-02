package projekt2.services;

import projekt2.entities.Client;
import projekt2.repositories.ClientRepository;
import projekt2.repositories.OrderRepository;
import projekt2.validators.ClientValidator;

public class ClientService
{
    private ClientValidator clientValidator;
    private ClientRepository clientRepo;
    private OrderRepository orderRepo;

    public ClientService(ClientValidator clientValidator, ClientRepository clientRepo, OrderRepository orderRepo)
    {
        this.clientValidator = clientValidator;
        this.clientRepo = clientRepo;
        this.orderRepo = orderRepo;
    }

    public boolean addClient(Client client)
    {
        if (client == null)
        {
            throw new IllegalArgumentException("client is null");
        }

        if (!clientValidator.isValid(client))
        {
            return false;
        }

        return clientRepo.add(client);
    }

    public boolean deleteClient(Client client)
    {
        if (client == null)
        {
            throw new IllegalArgumentException("client is null");
        }

        if (!orderRepo.getByClientId(client.getId()).isEmpty())
        {
            return false;
        }

        return clientRepo.delete(client);
    }
}
