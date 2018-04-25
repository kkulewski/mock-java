package projekt2.repositories;

import java.util.List;

public interface GenericRepository<TEntity>
{
    TEntity getById(int id);
    List<TEntity> getAll();
    void add(TEntity entity);
    void update(TEntity entity);
    void delete(TEntity entity);
}
