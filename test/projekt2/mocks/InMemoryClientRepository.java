package projekt2.mocks;

import projekt2.entities.Client;
import projekt2.repositories.ClientRepository;
import projekt2.repositories.inMemory.InMemoryGenericRepository;

public class InMemoryClientRepository extends InMemoryGenericRepository<Client> implements ClientRepository
{
}
