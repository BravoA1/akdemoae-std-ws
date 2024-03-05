package af.cmr.indyli.akdemia.business.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.akdemia.business.dao.IUserDAO;
import af.cmr.indyli.akdemia.business.dto.UserDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;

public class UserDAOImpl extends AbstractEntityDAOImpl<UserDto> implements IUserDAO {
	
	@Override
	public UserDto findByLoginAndPassword(String login, String password) {
		for (UserDto user : entityInMemory) {
	        if (user != null && user.getLogin().equals(login) && user.getPassword().equals(password)) {
	            return user; 
	        }
	    }
	    return null;
	}

	@Override
	public UserDto findByEmail(String email) {
		for (UserDto entity : entityInMemory) {
	        if (entity != null && entity.getEmail().equals(email)) {
	            return entity;
	        }
	    }
		return null;
	}

	@Override
	public UserDto findByLogin(String login) {
		for (UserDto entity : entityInMemory) {
	        if (entity != null && entity.getLogin().equals(login)) {
	            return entity;
	        }
	    }
		return null;
	}

	@Override
	public List<UserDto> findByLoginOrEmail(String login, String email) throws AkdemiaBusinessException {
		if (login == null && email == null) {
	        throw new AkdemiaBusinessException("Login et email ne peuvent pas être tous les deux null");
	    }
		List<UserDto> result = new ArrayList<>();
		
		for (UserDto user : entityInMemory) {
	        // Vérifiez si l'utilisateur a le login ou l'email correspondant
	        if (user != null && (user.getLogin().equals(login) || user.getEmail().equals(email))) {
	            result.add(user);
	        }
	    }
	    return result;
	}
	
	@Override
	public void initData() {
		
		entityInMemory =  new ArrayList<UserDto>();
		UserDto user1 = new UserDto();
		UserDto user2 = new UserDto();
		UserDto user3 = new UserDto();
		UserDto user4 = new UserDto();
		UserDto user5 = new UserDto();
		
		user1.setAddress("Paris, France");
		user1.setEmail("abdou.zoyim@indyli-services.com");
		user1.setId(1);
		user1.setLogin("loti");
		user1.setPassword(this.getPasswordEncoder().encode("1234"));
		user1.setPhone("06987546");
		user1.setCreationDate(new Date());
		entityInMemory.add(user1);
		
		user2.setAddress("Bruxelles, Belgique");
		user2.setEmail("Jacques@example.com");
		user2.setId(2);
		user2.setLogin("Jacques");
		user2.setPassword(this.getPasswordEncoder().encode("1234"));
		user2.setPhone("0678945612");
		user2.setCreationDate(new Date());
		entityInMemory.add(user2);
		
		user3.setAddress("Helsinki, Finlande");
		user3.setEmail("jackes@example.com");
		user3.setId(3);
		user3.setLogin("Jacques");
		user3.setPassword(this.getPasswordEncoder().encode("1234"));
		user3.setPhone("0678945612");
		user3.setCreationDate(new Date());
		entityInMemory.add(user3);
		
		user4.setAddress("Lisbone, Portugal");
		user4.setEmail("daniel@example.com");
		user4.setId(4);
		user4.setLogin("Daniel");
		user4.setPassword(this.getPasswordEncoder().encode("1234"));
		user4.setPhone("064523879");
		user4.setCreationDate(new Date());
		entityInMemory.add(user4);
		
		user5.setAddress("Buenos-aires, Argentine");
		user5.setEmail("leonel@example.com");
		user5.setId(5);
		user5.setLogin("leonel");
		user5.setPassword(this.getPasswordEncoder().encode("1234"));
		user5.setPhone("06735148");
		user5.setCreationDate(new Date());
		entityInMemory.add(user5);
	}
}
