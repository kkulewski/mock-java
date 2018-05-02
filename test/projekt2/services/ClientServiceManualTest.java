package projekt2.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projekt2.entities.Client;
import projekt2.mocks.*;
import projekt2.repositories.ClientRepository;
import projekt2.repositories.OrderRepository;
import projekt2.validators.ClientValidator;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientServiceManualTest
{
    private ClientRepository clientRepo;
    private OrderRepository orderRepository;
    private ClientValidator clientValidator;
    private ClientService clientService;
    private Client johnDoe;

    @BeforeEach
    void setup()
    {
        this.clientRepo = new ClientRepositoryInMemoryMock();
        this.orderRepository = new OrderRepositoryEmptyStub();
        this.clientValidator = new ClientValidatorAlwaysTrueStub();
        this.clientService = new ClientService(clientValidator, clientRepo, orderRepository);
        this.johnDoe = new Client(1, "John", "Doe", "jdoe@test.com");
    }

    @Test
    void addClientWithInvalidClientReturnsFalse()
    {
        // Arrange
        this.clientService =
                new ClientService(new ClientValidatorAlwaysFalseStub(), this.clientRepo, this.orderRepository);

        // Act
        boolean result = clientService.addClient(johnDoe);

        // Assert
        assertThat(result).isFalse();
    }

    @Test
    void addClientWhenClientAlreadyExistsReturnsFalse()
    {
        // Arrange
        clientRepo.add(johnDoe);

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

    @Test
    void deleteClientWithNullClientReturnsFalse()
    {
        assertThat(clientService.deleteClient(null)).isFalse();
    }

    @Test
    void deleteClientWhenClientDoesNotExistReturnsFalse()
    {
        assertThat(clientService.deleteClient(johnDoe)).isFalse();
    }

    @Test
    void deleteClientWhenClientIsValidAndExistsReturnsTrue()
    {
        // Arrange
        clientRepo.add(johnDoe);

        // Act
        boolean result = clientService.deleteClient(johnDoe);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    void deleteClientActuallyDeletesExistingClient()
    {
        // Arrange
        clientRepo.add(new Client(5, "Jack", "Black", "jblack@test.com"));
        clientRepo.add(new Client(6, "Jack", "White", "jwhite@test.com"));
        clientRepo.add(johnDoe);

        // Act
        clientService.deleteClient(johnDoe);

        // Assert
        assertThat(clientRepo.getAll())
                .doesNotContain(johnDoe)
                .hasSize(2);
    }

    @Test
    void deleteClientReturnsFalseWhenClientHasOrders()
    {
        // Arrange
        clientRepo.add(johnDoe);
        this.clientService =
                new ClientService(this.clientValidator, this.clientRepo, new OrderRepositoryNonEmptyStub());

        // Act
        boolean result = clientService.deleteClient(johnDoe);

        // Assert
        assertThat(result).isFalse();
    }
}
