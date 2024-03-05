package af.cmr.indyli.akdemia.business.dto;

import java.util.Date;

public class ParticularDto extends UserDto {

	private String firstname;
    private String lastname;
    private String gender;
    private String activity;
    private String highestDiploma;
    private Date birthDate;
    
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getActivity() {
		return activity;
	}
	
	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	public String getHighestDiploma() {
		return highestDiploma;
	}
	
	public void setHighestDiploma(String highestDiploma) {
		this.highestDiploma = highestDiploma;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
}
