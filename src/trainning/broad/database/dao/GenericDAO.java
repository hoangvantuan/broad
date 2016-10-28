package trainning.broad.database.dao;

import java.util.List;

public interface GenericDAO<T> {

	public List<T> listAll();

	public T findById(T object);

	public void delete(T object);

	public void save(T object);

	public void update(T object);
}
