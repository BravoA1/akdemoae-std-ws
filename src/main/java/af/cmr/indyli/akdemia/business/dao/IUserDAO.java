package af.cmr.indyli.akdemia.business.dao;

import java.util.List;

import af.cmr.indyli.akdemia.business.dto.UserDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;

public interface IUserDAO extends IEntityDAO<UserDto> {
	public UserDto findByLoginAndPassword(String login,String password);
	public UserDto findByEmail(String email);
	public UserDto findByLogin(String login);
	public List<UserDto> findByLoginOrEmail(String login,String email) throws AkdemiaBusinessException;
    
}
