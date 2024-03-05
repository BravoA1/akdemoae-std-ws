package af.cmr.indyli.akdemia.business.service;

import java.util.List;

import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;

public interface IEntityService<E> {
	public List<E> findAll();
	public E findById(Integer id) throws AkdemiaBusinessException;
	public void deleteById(Integer id) throws AkdemiaBusinessException;
	public E create(E ent) throws AkdemiaBusinessException;
}
