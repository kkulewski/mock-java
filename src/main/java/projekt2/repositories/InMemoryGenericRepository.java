package projekt2.repositories;

import com.google.common.collect.Iterables;
import projekt2.entities.Entity;
import projekt2.repositories.GenericRepository;
import java.util.ArrayList;
import java.util.List;

public class InMemoryGenericRepository<TEntity extends Entity> implements GenericRepository<TEntity>
{
    private List<TEntity> entities;

    public InMemoryGenericRepository()
    {
        this.entities = new ArrayList<>();
    }

    @Override
    public TEntity getById(int id)
    {
        return Iterables.tryFind(this.entities, x -> x.getId() == id).orNull();
    }

    @Override
    public List<TEntity> getAll()
    {
        return new ArrayList<>(this.entities);
    }

    @Override
    public boolean add(TEntity entity)
    {
        if (this.getById(entity.getId()) != null)
        {
            return false;
        }

        this.entities.add(entity);
        return true;
    }

    @Override
    public boolean update(TEntity entity)
    {
        TEntity oldEntity = Iterables.tryFind(this.entities, x -> x.getId() == entity.getId()).orNull();
        if (oldEntity == null)
        {
            return false;
        }

        entities.remove(oldEntity);
        entities.add(entity);
        return true;
    }

    @Override
    public boolean delete(TEntity entity)
    {
        if (this.getById(entity.getId()) == null)
        {
            return false;
        }

        entities.remove(entity);
        return true;
    }
}
