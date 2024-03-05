package af.cmr.indyli.akdemia.business.dto;

import java.util.Date;

public class RequirementDTO implements IEntity{
	
	private Integer id;
    private String name;
    private String description;
    private String link;
    private Date creationDate;
    private Date updateDate;
    
    
    
    public RequirementDTO() {
		super();
	}

	public RequirementDTO(Integer id, String name, String description, String link) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.link = link;
	}

	@Override
	public Integer getId() {
		return id;
	}
    
    @Override
	public void setId(Integer id) {
		this.id = id;
	}
    
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	@Override
	public Date getCreationDate() {
		return creationDate;
	}
	
	@Override
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@Override
	public Date getUpdateDate() {
		return updateDate;
	}
	
	@Override
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
