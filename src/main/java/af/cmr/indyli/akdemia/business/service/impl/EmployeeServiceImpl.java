package af.cmr.indyli.akdemia.business.service.impl;

import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import af.cmr.indyli.akdemia.business.dao.IEmployeeDAO;
import af.cmr.indyli.akdemia.business.dao.IEntityDAO;
import af.cmr.indyli.akdemia.business.dao.impl.EmployeeDAOImpl;
import af.cmr.indyli.akdemia.business.dto.EmployeeDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IEmployeeService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;

@Service(AkdemiaConstantesService.EMPLOYEE_SERVICE_KEY)
public class EmployeeServiceImpl extends AbstractEntityServiceImpl<EmployeeDto> implements IEmployeeService {

	private IEmployeeDAO employeeDAO = new EmployeeDAOImpl();

	private BCryptPasswordEncoder bcryptEncoder;

	@Override
	public IEntityDAO<EmployeeDto> getDAO() {
		// TODO Auto-generated method stub
		return this.employeeDAO;
	}

	public EmployeeServiceImpl(BCryptPasswordEncoder bcryptEncoder) {
		super();
		this.bcryptEncoder = bcryptEncoder;
	}

	@Override
	public EmployeeDto update(EmployeeDto employee) throws AkdemiaBusinessException {

		EmployeeDto existingemployee = this.employeeDAO.findById(employee.getId());

		if (employee.getPassword() != null && !employee.getPassword().isEmpty()) {

			existingemployee.setAddress(employee.getAddress());
			existingemployee.setLogin(employee.getLogin());
			existingemployee.setEmail(employee.getEmail());
			existingemployee.setPhone(employee.getPhone());
			existingemployee.setPhoto(employee.getPhoto());
			existingemployee.setUpdateDate(new Date());
			// System.out.println("Company password before crypt : "+company.getPassword());
			existingemployee.setPassword(bcryptEncoder.encode(employee.getPassword()));
			// System.out.println("Company password after crypt :
			// "+existingcompany.getPassword());
			existingemployee.setFirstname(employee.getFirstname());
			existingemployee.setLastname(employee.getLastname());
			existingemployee.setGender(employee.getGender());
			existingemployee.setHighestDiploma(employee.getHighestDiploma());

			if (employee.getCreationDate() == null) {
				existingemployee.setCreationDate(new Date());
			} else {
				existingemployee.setUpdateDate(new Date());
			}

			return this.employeeDAO.create(existingemployee);

		} else {
			if (employee.getAddress() != null) {
				existingemployee.setAddress(employee.getAddress());
			}
			if (employee.getLogin() != null) {
				existingemployee.setLogin(employee.getLogin());
			}
			if (employee.getEmail() != null) {
				existingemployee.setEmail(employee.getEmail());
			}
			if (employee.getPhone() != null) {
				existingemployee.setPhone(employee.getPhone());
			}
			if (employee.getPhoto() != null) {
				existingemployee.setPhoto(employee.getPhoto());
			}
			if (employee.getFirstname() != null) {
				existingemployee.setFirstname(employee.getFirstname());
			}
			if (employee.getLastname() != null) {
				existingemployee.setLastname(employee.getLastname());
			}
			if (employee.getGender() != null) {
				existingemployee.setGender(employee.getGender());
			}
			if (employee.getHighestDiploma() != null) {
				existingemployee.setHighestDiploma(employee.getHighestDiploma());
			}
			existingemployee.setUpdateDate(new Date());

			return this.employeeDAO.create(existingemployee);

		}
	}
}