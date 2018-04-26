package projekt2.repositories;

import java.util.List;

public interface GenericRepository<TEntity>
{
    TEntity getById(int id);
    List<TEntity> getAll();
    boolean add(TEntity entity);
    boolean update(TEntity entity);
    boolean delete(TEntity entity);
}
