package af.cmr.indyli.akdemia.business.service;

import java.util.List;

import af.cmr.indyli.akdemia.business.dto.UserDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;

public interface IUserService extends IEntityService<UserDto>{

	public List<UserDto> findByLoginOrEmail(String login,String email) throws AkdemiaBusinessException;
	
	public UserDto findByEmail(String email) throws AkdemiaBusinessException;
	
	public UserDto findByLogin(String login) throws AkdemiaBusinessException;
	
	public UserDto findByLoginAndPassword(String login,String password) throws AkdemiaBusinessException ;
	
	public UserDto update(UserDto user) throws AkdemiaBusinessException;
}
