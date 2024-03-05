package af.cmr.indyli.akdemia.business.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import af.cmr.indyli.akdemia.business.dao.IEntityDAO;
import af.cmr.indyli.akdemia.business.dao.IUserDAO;
import af.cmr.indyli.akdemia.business.dao.impl.UserDAOImpl;
import af.cmr.indyli.akdemia.business.dto.UserDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IUserService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;

@Service(AkdemiaConstantesService.USER_SERVICE_KEY)
public class UserServiceImpl extends AbstractEntityServiceImpl<UserDto> implements IUserService, UserDetailsService {
	
	private IUserDAO userDAO = new UserDAOImpl();
	
	private BCryptPasswordEncoder bcryptEncoder;

	  public UserServiceImpl() {
		  this.bcryptEncoder = new BCryptPasswordEncoder();
	  }

	@Override
	public IEntityDAO<UserDto> getDAO() {
		return this.userDAO;
	}

	@Override
	public List<UserDto> findByLoginOrEmail(String login, String email) throws AkdemiaBusinessException {
		return this.userDAO.findByLoginOrEmail(login, email);
	}

	@Override
	public UserDto findByEmail(String email) throws AkdemiaBusinessException {
		if (StringUtils.isBlank(email))
		      throw new AkdemiaBusinessException("VOUS DEVEZ RENSEINGER L'EMAIL");
		    return this.userDAO.findByEmail(email);
	}

	@Override
	public UserDto findByLogin(String login) throws AkdemiaBusinessException {
		return this.userDAO.findByLogin(login);
	}

	@Override
	public UserDto findByLoginAndPassword(String login, String password) throws AkdemiaBusinessException {
		if (StringUtils.isBlank(login) || StringUtils.isBlank(password)) {
		      throw new AkdemiaBusinessException("VOUS DEVEZ RENSEINGER LES 2 CHAMPS");
		    }
		    UserDto foundUser = userDAO.findByLoginAndPassword(login, password);
		    return foundUser;
	}
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UserDto user = userDAO.findByLogin(login);
	    if (user == null) {
	      throw new UsernameNotFoundException("Invalid Login or Password.");
	    }
	    return new org.springframework.security.core.userdetails.User(user.getLogin(),
	        user.getPassword(), getAuthority());
	}
	
	private List<SimpleGrantedAuthority> getAuthority() {
	    return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"),
	        new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
	    // ROLE_ANONYMOUS
	}
	
	@Override
	public UserDto create(UserDto user) throws AkdemiaBusinessException {
	   if (user.getId() != null) {
	      UserDto userInBase = this.userDAO.findById(user.getId());
	      userInBase.setAddress(user.getAddress());
	      userInBase.setLogin(user.getLogin());
	      userInBase.setEmail(user.getEmail());
	      userInBase.setPhone(user.getPhone());
	      userInBase.setPhoto(user.getPhoto());
	      userInBase.setCreationDate(new Date());
	      user = userInBase;
	   }
	   // On verifie si l'email et le login n'existent pas déjà en base
	   List<UserDto> listeUserExistantEnBaseAvecLoginOuEmail =
	        this.userDAO.findByLoginOrEmail(user.getLogin(), user.getEmail());
	   if (!listeUserExistantEnBaseAvecLoginOuEmail.isEmpty()) {
	      throw new AkdemiaBusinessException("Le login :" + user.getLogin() + " Ou L'email :"
	          + user.getEmail() + " semblent déjà pris");
	   }
	   String encryptPassword = bcryptEncoder.encode(user.getLogin());
	   user.setPassword(encryptPassword);
	   user = this.userDAO.create(user);
	   return user;
	}

	@Override
	public UserDto update(UserDto user) throws AkdemiaBusinessException {
		UserDto existingUser  = this.userDAO.findById(user.getId());
		
		if(user.getPassword() != null && !user.getPassword().isEmpty()) {
			
			existingUser.setAddress(user.getAddress());
			existingUser.setLogin(user.getLogin());
			existingUser.setEmail(user.getEmail());
			existingUser.setPhone(user.getPhone());
			existingUser.setPhoto(user.getPhoto());
			existingUser.setUpdateDate(new Date());
			existingUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		    return this.userDAO.create(existingUser);
		    
		}else {
			existingUser.setAddress(user.getAddress());
			existingUser.setLogin(user.getLogin());
			existingUser.setEmail(user.getEmail());
			existingUser.setPhone(user.getPhone());
			existingUser.setPhoto(user.getPhoto());
			existingUser.setUpdateDate(new Date());
    		return this.userDAO.create(existingUser); 
		}
	}

}
