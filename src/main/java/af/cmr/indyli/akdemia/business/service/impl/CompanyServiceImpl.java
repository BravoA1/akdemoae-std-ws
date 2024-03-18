package af.cmr.indyli.akdemia.business.service.impl;

import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import af.cmr.indyli.akdemia.business.dao.IEntityDAO;
import af.cmr.indyli.akdemia.business.dao.impl.CompanyDAOImpl;
import af.cmr.indyli.akdemia.business.dao.ICompanyDAO;
import af.cmr.indyli.akdemia.business.dto.CompanyDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.ICompanyService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;

@Service(AkdemiaConstantesService.COMPANY_SERVICE_KEY)
public class CompanyServiceImpl extends AbstractEntityServiceImpl<CompanyDto> implements ICompanyService {

	private ICompanyDAO companyDAO = new CompanyDAOImpl();
	private BCryptPasswordEncoder bcryptEncoder;

	@Override
	public IEntityDAO<CompanyDto> getDAO() {
		return this.companyDAO;
	}

	public CompanyServiceImpl(BCryptPasswordEncoder bcryptEncoder) {
		super();
		this.bcryptEncoder = bcryptEncoder;
	}

	@Override
	public CompanyDto update(CompanyDto company) throws AkdemiaBusinessException {

		CompanyDto existingcompany = this.companyDAO.findById(company.getId());

		if (company.getPassword() != null && !company.getPassword().isEmpty()) {

			existingcompany.setAddress(company.getAddress());
			existingcompany.setLogin(company.getLogin());
			existingcompany.setEmail(company.getEmail());
			existingcompany.setPhone(company.getPhone());
			existingcompany.setPhoto(company.getPhoto());

			if (company.getCreationDate() == null) {
				existingcompany.setCreationDate(new Date());
			} else {
				existingcompany.setUpdateDate(new Date());
			}
			existingcompany.setPassword(bcryptEncoder.encode(company.getPassword()));

			existingcompany.setName(company.getName());
			existingcompany.setActivity(company.getActivity());

			return this.companyDAO.create(existingcompany);

		} else {
			if (company.getAddress() != null) {
				existingcompany.setAddress(company.getAddress());
			}

			if (company.getLogin() != null) {
				existingcompany.setLogin(company.getLogin());
			}

			if (company.getEmail() != null) {
				existingcompany.setEmail(company.getEmail());
			}
			if (company.getPhone() != null) {
				existingcompany.setPhone(company.getPhone());
			}

			if (company.getPhoto() != null) {
				existingcompany.setPhoto(company.getPhoto());
			}
			existingcompany.setUpdateDate(new Date());
			if (company.getName() != null) {
				existingcompany.setName(company.getName());
			}
			if (company.getActivity() != null) {
				existingcompany.setActivity(company.getActivity());
			}

			return this.companyDAO.create(existingcompany);
		}

	}

}
