package af.cmr.indyli.akdemia.business.dto;

import java.util.Date;

public class TrainingDto implements IEntity {
	
	private Integer id;
	private String title;
    private String description;
    private Double trainingPrice;
    private String logo;
    private Date creationDate;
    private Date updateDate;
    private RequirementDTO requirement;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getTrainingPrice() {
		return trainingPrice;
	}
	public void setTrainingPrice(Double trainingPrice) {
		this.trainingPrice = trainingPrice;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public RequirementDTO getRequirement() {
		return requirement;
	}
	public void setRequirement(RequirementDTO requirement) {
		this.requirement = requirement;
	}
    
    
    

}
