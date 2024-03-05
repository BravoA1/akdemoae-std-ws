package af.cmr.indyli.akdemia.business.dto;

import java.util.Date;

public class UserDto implements IEntity {
	
    private Integer id;
    private String phone;
    private String email;
    private String address;
    private String login;
    private String password;
    private String photo;
    private Date creationDate;
    private Date updateDate;
    
	@Override
	public Integer getId() {
		return this.id;
	}
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhoto() {
		return photo;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Override
	public Date getCreationDate() {
		return this.creationDate;
	}
	
	@Override
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@Override
	public Date getUpdateDate() {
		return this.updateDate;
	}
	
	@Override
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
    
}
