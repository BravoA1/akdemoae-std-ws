package af.cmr.indyli.akdemia.business.dao;

import java.util.List;

public interface IEntityDAO<E> {

	public List<E> findAll();
	public E findById(Integer id);
	public void deleteById(Integer id);
	public E create(E ent);
}
