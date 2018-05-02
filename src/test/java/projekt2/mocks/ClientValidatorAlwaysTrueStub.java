package projekt2.mocks;

import projekt2.entities.Client;
import projekt2.validators.ClientValidator;

public class ClientValidatorAlwaysTrueStub implements ClientValidator
{
    @Override
    public boolean isValid(Client client)
    {
        return true;
    }
}
