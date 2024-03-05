package af.cmr.indyli.akdemia.business.service.impl;

import java.util.List;

import af.cmr.indyli.akdemia.business.dao.IEntityDAO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IEntityService;

public abstract class AbstractEntityServiceImpl<E> implements IEntityService<E> {
	
	public abstract IEntityDAO<E> getDAO();

	@Override
	public List<E> findAll() {
		return this.getDAO().findAll();
	}

	@Override
	public E findById(Integer id) throws AkdemiaBusinessException {
		return this.getDAO().findById(id);
	}

	@Override
	public void deleteById(Integer id) throws AkdemiaBusinessException {
		this.getDAO().deleteById(id);
	}

	@Override
	public E create(E ent) throws AkdemiaBusinessException {
		this.getDAO().create(ent);
		return ent;
	}

}
