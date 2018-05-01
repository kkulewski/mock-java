package projekt2.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projekt2.entities.Client;
import projekt2.mocks.ClientValidatorAlwaysFalseStub;
import projekt2.mocks.ClientValidatorAlwaysTrueStub;
import projekt2.mocks.ClientRepositoryInMemoryMock;
import projekt2.repositories.ClientRepository;
import projekt2.validators.ClientValidator;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientServiceManualTest
{
    private ClientRepository clientRepo;
    private ClientValidator clientValidator;
    private ClientService clientService;
    private Client johnDoe;

    @BeforeEach
    void setup()
    {
        this.clientRepo = new ClientRepositoryInMemoryMock();
        this.clientValidator = new ClientValidatorAlwaysTrueStub();
        this.clientService = new ClientService(clientValidator, clientRepo);
        this.johnDoe = new Client(1, "John", "Doe", "jdoe@test.com");
    }

    @Test
    void addClientWithInvalidClientReturnsFalse()
    {
        // Arrange
        this.clientService = new ClientService(new ClientValidatorAlwaysFalseStub(), this.clientRepo);

        // Act
        boolean result = clientService.addClient(johnDoe);

        // Assert
        assertThat(result).isFalse();
    }

    @Test
    void addClientWhenClientAlreadyExistsReturnsFalse()
    {
        // Arrange
        clientService.addClient(johnDoe);

        // Act
        boolean result = clientService.addClient(johnDoe);

        // Assert
        assertThat(result).isFalse();
    }

    @Test
    void addClientWhenClientIsValidAndDoesNotExistYetReturnsTrue()
    {
        assertThat(clientService.addClient(johnDoe)).isTrue();
    }
}
