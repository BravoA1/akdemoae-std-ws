package af.cmr.indyli.akdemia.business.dto;

public class EmployeeDto extends UserDto {
	private String firstname;
	private String lastname;
	private String highestDiploma;
	private String gender;
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
	public String getHighestDiploma() {
		return highestDiploma;
	}
	public void setHighestDiploma(String highestDiploma) {
		this.highestDiploma = highestDiploma;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

}
