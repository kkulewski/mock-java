package projekt2.entities;

public class Order extends Entity
{
    private int clientId;
    private boolean isConfirmed;

    public Order(int id, int clientId)
    {
        this.id = id;
        this.clientId = clientId;
    }

    public int getClientId()
    {
        return clientId;
    }

    public void setClientId(int clientId)
    {
        this.clientId = clientId;
    }

    public boolean isConfirmed()
    {
        return isConfirmed;
    }

    public void setConfirmed(boolean isConfirmed)
    {
        this.isConfirmed = isConfirmed;
    }
}
