package af.cmr.indyli.akdemia.business.dto;

public class CompanyDto extends UserDto {
	private String name;
	private String activity;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}

}
