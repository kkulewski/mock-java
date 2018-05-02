package projekt2.mocks;

import projekt2.entities.Client;
import projekt2.repositories.ClientRepository;
import projekt2.repositories.InMemoryGenericRepository;

public class ClientRepositoryInMemoryMock extends InMemoryGenericRepository<Client> implements ClientRepository
{
}
